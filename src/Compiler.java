import AST.*;

public class Compiler {
  private char []input;
  private char token;
  private int  tokenPos;

  public Program compile(char []pInput) {
    input = pInput;
    tokenPos = 0;
    nextToken();
    return declaration();
  }

  public Program declaration() {
    if(token == 'v') {
      nextToken();
      if(token == 'm') {
        nextToken();
        if(token == '(') {
          nextToken();
          if(token == ')') {
            nextToken();
            StatementBlock stmtblock = stmtBlock();
          }
          else
            error();
        }
        else
          error();
      }
      else
        error();
    }
    else
      error();
    Program decl = new Program(stmtBlock());
    return decl;
  }

  public ArrayList<Variable> variableDeclaration() {
    ArrayList<Variable> vars = new ArrayList<Variable>();
    while(token == 'i' || token == 'd' || token == 'c'){
      variable(vars);
    }
    return vars;
  }

  public void variable(ArrayList<Variable> vars) {
    Type type = type();
    String identifier = identifier();

    nextToken();
    if(token != ';')
      error();

    Variable var = new Variable(type, identifier);
    vars.add(var);
    nextToken();
  }

  public Type type() {
    char t = token;
    nextToken();
    if(token == '[') {
      nextToken();
      if(token != ']')
        error();

      Type type = new ArrayType(type);
    } else {
      Type type = new StandardType(type);
    }
    return type;
  }

  public StatementBlock stmtBlock() {
    if(token == '{') {
      nextToken();
      ArrayList<Variable> vars = variableDeclaration();
      ArrayList<Statement> statements = ArrayList<Statement>();
      while(token == 'f' || token == 'w' || token == 'b' || token == 'p') {
        statement(statements);
      }
      if(token != '}') {
        error();
      }
    } else
      error();
    return new StatementBlock(vars, statements);
  }

  public void statement(ArrayList<Statement> statements) {
    switch(token) {
      case 'f':
        statements.add(ifStatement());
      case 'w':
        statements.add(whileStatement());
      case 'b':
        breakStatement();
      case 'p':
        statements.add(printStatement());
      default:
        expression();
        nextToken();
        if(token != ';')
          error();
    }
  }

  public IfStatement ifStatement() {
    nextToken();
    if(token == '(') {
      nextToken();
      Expression expression = expression();
      nextToken();
      // Then part begins.
      if(token == '{') {
        nextToken();
        ArrayList<Statement> thenStatements = new ArrayList<Statement>();
        // How to detect expression inside statement here?
        // If it detects any letter besides the reserved ones should it loop?
        while(token == 'f' || token == 'w' || token == 'b' || token == 'p')
          thenStatements.add(statement());
        nextToken();
        if(token != '}')
          error();
        nextToken();
        // Else part begins
        if(token == 'e') {
          nextToken();
          ArrayList<Statement> elseStatements = new ArrayList<Statement>();
          if(token == '{') {
            nextToken();
            while(token == 'f' || token == 'w' || token == 'b' || token == 'p')
              elseStatements.add(statement());
            nextToken();
            if(token != '}')
              error();
          }
          else
            error();
        }
      }
      else
        error();
    }
    else
      error();

    return new IfStatement(expression, thenStatements, elseStatements);
  }

  public WhileStatement whileStatement() {
    nextToken();
    // Expression block
    if(token == '(') {
      nextToken();
      Expression expression = expression();
      nextToken();
      if(token == ')') {
        nextToken();
        // Statements block
        if(token == '{') {
          nextToken();
          ArrayList<Statement> statements = new ArrayList<Statement>();
          while(token == 'f' || token == 'w' || token == 'b' || token == 'p')
            statements.add(statement());
          nextToken();
          if(token != '}')
            error();
        }
        else
          error();
      }
      else
        error();
    }
    else
      error();

    return new WhileStatement(expression, statements);
  }

  public void breakStatement() {
    nextToken();
    if(token != ';')
      error();
  }

  public PrintStatement printStatement() {
    nextToken();
    ArrayList<Expression> expressions = new ArrayList<Expression>();
    if(token == '(') {
      expressions.add(expression());
      nextToken();
      while(token == ',') {
        expressions.add(expression())
        nextToken();
      }
      nextToken();
      if(token != ')')
        error();
    }
    else
      error();

    return new PrintStatement(expressions);
  }

  public void expression() {
    simpleExpression();

    if(token == '=' || token == '#' || token == '<' || token == '>') {
      relationalOperator();
      expression();
    }
  }

  // SimExpr ::= [Unary] Term { AddOp Term }
  public void simpleExpression() {
    if(token == '+' || token == '-' || token == '!')
      unary();

    term();

    while(token == '+' || token == '-') {
      addOperator();
      term();
    }
  }

  public void term() {
    factor();

    while(token == '*' || token == '/' || token == '%') {
      multiplicationOperator();
      factor();
    }
  }

  public void factor() {

  }

  public void leftValue() {
    identifier();

    if(token == '['){
      expression();

      if(token != ']')
        error();
    }
  }

  public void identifier() {
    letter();
    nextToken();

    while(('A' <= token && 'z' >= token) || (token >= '0' && token <= '9')) {
      if('A' <= token && 'z' >= token)
        letter();
      if(token >= 0 && token <= 9)
        digit();
    }
  }

  public void relationalOperator() {
    if(token == '=' || token == '#' || token == '<' || token == '>')
      nextToken();
    else
      error();
  }

  public void addOperator() {
    if(token == '+' || token == '-')
      nextToken();
    else
      error();
  }

  public void multiplicationOperator() {
    if(token == '*' || token == '/' || token == '%')
      nextToken();
    else
      error();
  }

  public void unary() {
    if(token == '+' || token == '-' || token == '!') {
      nextToken();
    }
    else
      error();
  }

  public void digit() {
    if(token >= '0' && token <= '9')
      nextToken();
    else
      error();
  }

  public void letter() {
    if('A' <= token && 'z' >= token)
      nextToken();
    else
      error();
  }

  public void nextToken() {
    while(tokenPos < input.length && input[tokenPos] == ' ')
      tokenPos++;

    if(tokenPos >= input.length)
      token = '\0';
    else {
      token = input[tokenPos];
      tokenPos++;
    }
    System.out.print(" " + token + " ");
  }

  public void error() {
    if ( tokenPos == 0 )
     tokenPos = 1;
    else
     if ( tokenPos >= input.length )
      tokenPos = input.length;

    String strInput = new String( input, tokenPos - 1, input.length - tokenPos + 1 );
    String strError = "Error at \"" + strInput + "\"";
    System.out.println( strError );
    throw new RuntimeException(strError);
  }
}

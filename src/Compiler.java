import AST.*;
import java.util.ArrayList;

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
    String ident = " ";
    Type type = type();
    identifier();

    nextToken();
    if(token != ';')
      error();

    Variable var = new Variable(type, ident);
    vars.add(var);
    nextToken();
  }

  public Type type() {
    char letter = token;
    Type type;

    nextToken();
    if(token == '[') {
      nextToken();
      if(token != ']')
        error();

      type = new ArrayType(letter);
    }
    else
      type = new StandardType(letter);

    return type;
  }

  public StatementBlock stmtBlock() {
    ArrayList<Variable> vars = null;
    Statement stmt = null;
    if(token == '{') {
      nextToken();
      vars = variableDeclaration();
      stmt = statement();
      if(token != '}') {
        error();
      }
    } else
      error();
    return new StatementBlock(vars, stmt);
  }

  public Statement statement() {
    Statement stmt = null;
    switch(token) {
      case 'f':
        stmt = ifStatement();
      case 'w':
        stmt = whileStatement();
      case 'b':
        stmt = breakStatement();
      case 'p':
        stmt = printStatement();
      default:
        expression();
        nextToken();
        if(token != ';')
          error();
    }

    return stmt;
  }

  public IfStatement ifStatement() {
    ArrayList<Statement> thenStatements = new ArrayList<>() ;
    ArrayList<Statement> elseStatements = new ArrayList<>();
    Expression expression = null;

    nextToken();
    if(token == '(') {
      nextToken();
      expression = expression();
      nextToken();
      // Then part begins.
      if(token == '{') {
        nextToken();
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
    ArrayList<Statement> statements = new ArrayList<>();
    Expression expression = null;

    nextToken();
    // Expression block
    if(token == '(') {
      nextToken();
      expression = expression();
      nextToken();
      if(token == ')') {
        nextToken();
        // Statements block
        if(token == '{') {
          nextToken();
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

  public BreakStatement breakStatement() {
    nextToken();
    if(token != ';')
      error();

    return new BreakStatement();
  }

  public PrintStatement printStatement() {
    ArrayList<Expression> expressions = new ArrayList<>();

    nextToken();
    if(token == '(') {
      expressions.add(expression());
      nextToken();
      while(token == ',') {
        expressions.add(expression());
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

  public Expression expression() {
    simpleExpression();

    if(token == '=' || token == '#' || token == '<' || token == '>') {
      relationalOperator();
      expression();
    }

    return new Expression();
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

import AST.*;
import java.util.*;
import Lexer.*;
import java.io.*;

public class Compiler {
    private Hashtable<String, Variable> symbolTable;
    private Lexer lexer;
    private CompilerError error;

    public Program compile(char []input, PrintWriter outError) {
        symbolTable = new Hashtable<String, Variable>();
        error = new CompilerError(outError);
        lexer = new Lexer(input, error);
        error.setLexer(lexer);

        lexer.nextToken();
        return declaration();
    }

    public Program declaration() {
        // Decl ::= 'void' 'main' '(' ')' StmtBlock
        if(lexer.token != Symbol.VOID)
            error.signal("'void' expected");
        lexer.nextToken();

        if(lexer.token != Symbol.MAIN)
            error.signal("'main' expected");
        lexer.nextToken();

        if(lexer.token != Symbol.LEFTPAR)
            error.signal("'(' expected");
        lexer.nextToken();

        if(lexer.token != Symbol.RIGHTPAR)
            error.signal("')' expected");
        lexer.nextToken();

        return new Program(stmtBlock());
    }

    public StatementBlock stmtBlock() {
        // StmtBlock ::= '{' {VariableDecl} {Stmt} '}'
        if(lexer.token != Symbol.LEFTBRACE)
            error.signal("'{' expected");
        lexer.nextToken();

        ArrayList<Variable> vars = new ArrayList<Variable>();
        ArrayList<Statement> statements = new ArrayList<Statement>();

        vars = variableDeclaration();
        statements = statementList();

        if(lexer.token != Symbol.RIGHTBRACE)
            error.signal("'}' expected");
        lexer.nextToken();

        return new StatementBlock(vars, statements);
    }

    public ArrayList<Variable> variableDeclaration() {
        ArrayList<Variable> vars = new ArrayList<Variable>();
        while(lexer.token == Symbol.INT ||
            lexer.token == Symbol.DOUBLE ||
            lexer.token == Symbol.CHAR) {
                variable(vars);
            }
        return vars;
    }

    public void variable(ArrayList<Variable> vars) {
        Type type = type();

        if(lexer.token != Symbol.IDENT)
            error.signal("Identifier expected");
        String identifier = lexer.getStringValue();
        lexer.nextToken();

        if(symbolTable.get(identifier) != null)
            error.signal("Variable " + identifier + " has already been declared!");

        Variable var = new Variable(type, identifier);

        symbolTable.put(identifier, var);
        vars.add(var);

        if(lexer.token != Symbol.SEMICOLON)
            error.signal("';' expected");
        lexer.nextToken();
    }

    public Type type() {
        Type type;

        switch(lexer.token) {
            case INT:
                type = Type.intType;
                break;
            case DOUBLE:
                type = Type.doubleType;
                break;
            case CHAR:
                type = Type.charType;
                break;
            default:
                error.signal("Type expected");
                type = null;
        }
        lexer.nextToken();
        return type;
    }

    public ArrayList<Statement> statementList() {
        ArrayList<Statement> statements = new ArrayList<Statement>();

        while(lexer.token == Symbol.IDENT ||
            lexer.token == Symbol.IF ||
            lexer.token == Symbol.WHILE ||
            lexer.token == Symbol.BREAK ||
            lexer.token == Symbol.PRINT) {
                statements.add(statement());
            }
        return statements;
    }

    public Statement statement() {
        switch(lexer.token) {
            case IF:
                lexer.nextToken();
                return ifStatement();
                break;
            case WHILE:
                lexer.nextToken();
                return whileStatement();
                break;
            case BREAK:
                lexer.nextToken();
                return breakStatement();
                break;
            case PRINT:
                lexer.nextToken();
                return printStatement();
                break;
            case IDENT:
                return expression();
                if(lexer.token != Symbol.SEMICOLON)
                    error.signal("';' expected");
                break;
            default:
                error.signal("Statement expected");
        }
    }

    public IfStatement ifStatement() {
        if(unary(lexer.token))
            lexer.nextToken();

        if(lexer.token != Symbol.LEFTPAR)
            error.signal("'(' expected");
        lexer.nextToken();

        Expression expression = expression();

        // Then part
        if(lexer.token != Symbol.RIGHTPAR)
            error.signal("')' expected");
        lexer.nextToken();

        if(lexer.token != Symbol.LEFTBRACE)
            error.signal("'{' expected");
        lexer.nextToken();

        ArrayList<Statement> thenStatements = statementList();

        if(lexer.token != Symbol.RIGHTBRACE)
            error.signal("'}' expected");
        lexer.nextToken();

        ArrayList<Statement> elseStatements = null;

        // If there's an Else part.
        if(lexer.token == Symbol.ELSE) {
            lexer.nextToken();

            if(lexer.token != Symbol.LEFTBRACE)
                error.signal("'{' expected");
            lexer.nextToken();

            ArrayList<Statement> elseStatements = statementList();

            if(lexer.token != Symbol.RIGHTBRACE)
                error.signal("'}' expected");
            lexer.nextToken();
        }
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

        if(relationalOperator(lexer.token))
            expression();

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

    public Boolean relationalOperator(c) {
        if(c == Symbol.EQ || c == Symbol.NEQ || c == Symbol.LT ||
        c == Symbol.LTE || c == Symbol.GT || c == Symbol.GTE)
            return true;
        return false;
    }

    public Boolean addOperator(c) {
        if(c == Symbol.PLUS || c == Symbol.MINUS || c == Symbol.OR)
            return true;
        return false;
    }

    public Boolean multiplicationOperator(c) {
        if(c == Symbol.MULT || c == Symbol.DIV || c == Symbol.MOD ||
        c == Symbol.AND)
            return true;
        return false;
    }

    public Boolean unary(c) {
        if(c == Symbol.PLUS || c == Symbol.MINUS || c == Symbol.NOT)
            return true;
        return false;
  }

  public void factor() {

  }

    public void leftValue() {
        if(lexer.token != Symbol.IDENT)
            error.signal("Identifier expected");



        if(token == '['){
            expression();

        if(token != ']')
            error();
        }
    }
}

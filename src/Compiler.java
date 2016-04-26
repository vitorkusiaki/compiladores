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
      error.signal("Variable '" + identifier + "' has already been declared!");

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
      case Symbol.INT:
        type = Type.intType;
        break;
      case Symbol.DOUBLE:
        type = Type.doubleType;
        break;
      case Symbol.CHAR:
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
    ArrayList<Statement> statements = new ArrayList<>();

    while(lexer.token == Symbol.IDENT ||
          lexer.token == Symbol.IF    ||
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
      case WHILE:
        lexer.nextToken();
        return whileStatement();
      case BREAK:
        lexer.nextToken();
        return breakStatement();
      case PRINT:
        lexer.nextToken();
        return printStatement();
      case IDENT:
        return expression();
      default:
        error.signal("Statement expected");
    }
  }

  public IfStatement ifStatement() {
    ArrayList<Statement> thenStatements = new ArrayList<>();
    ArrayList<Statement> elseStatements = new ArrayList<>();

    if(unary(lexer.token))
      lexer.nextToken();

    lexer.nextToken();
    if(lexer.token == Symbol.LEFTPAR) {
      lexer.nextToken();
      expression = expression();
      lexer.nextToken();

      if(lexer.token == Symbol.RIGHTPAR){
        lexer.nextToken();
      }
      else
        error.signal("')' expected");

      // Then part begins.
      if(lexer.token == Symbol.LEFTBRACE) {
        lexer.nextToken();
        // How to detect expression inside statement here?
        // If it detects any letter besides the reserved ones should it loop?
        while(lexer.token == Symbol.IDENT ||
              lexer.token == Symbol.IF    ||
              lexer.token == Symbol.WHILE ||
              lexer.token == Symbol.BREAK ||
              lexer.token == Symbol.PRINT)
          thenStatements.add(statement());

        lexer.nextToken();

        if(lexer.token != Symbol.RIGHTBRACE)
          error.signal("'}' expected");

        lexer.nextToken();
          // Else part begins
        if(lexer.token == Symbol.ELSE) {
          lexer.nextToken();

          if(lexer.token == Symbol.LEFTBRACE) {
            lexer.nextToken();
            while(lexer.token == Symbol.IDENT ||
                  lexer.token == Symbol.IF    ||
                  lexer.token == Symbol.WHILE ||
                  lexer.token == Symbol.BREAK ||
                  lexer.token == Symbol.PRINT)
              elseStatements.add(statement());
            lexer.nextToken();
            if(lexer.token != Symbol.RIGHTBRACE)
              error.signal("'}' expected");
          }
          else
            error.signal("'{' expected");
        }
      }
      else
        error.signal("'{' expected");
    }
    else
      error.signal("'(' expected");

    return new IfStatement(expression, thenStatements, elseStatements);
  }

  public WhileStatement whileStatement() {
    ArrayList<Statement> statements = new ArrayList<>();
    Expression expression = null;

    lexer.nextToken();
    // Expression block
    if(lexer.token == Symbol.LEFTPAR) {
      lexer.nextToken();
      expression = expression();
      lexer.nextToken();
      if(lexer.token == Symbol.RIGHTPAR) {
        lexer.nextToken();
        // Statements block
        if(lexer.token == Symbol.LEFTBRACE) {
          lexer.nextToken();
          while(lexer.token == Symbol.IDENT ||
                lexer.token == Symbol.IF    ||
                lexer.token == Symbol.WHILE ||
                lexer.token == Symbol.BREAK ||
                lexer.token == Symbol.PRINT)
            statements.add(statement());
          lexer.nextToken();
          if(lexer.token != Symbol.RIGHTBRACE)
            error.signal("'}' expected");
        }
        else
          error.signal("'{' expected");
      }
      else
        error.signal("')' expected");
    }
    else
      error.signal("'(' expected");

    return new WhileStatement(expression, statements);
  }

  public BreakStatement breakStatement() {
    if(lexer.token != Symbol.BREAK)
      error.signal("'break' expected");
    nextToken();
    if(lexer.token != Symbol.SEMICOLON)
      error.signal("';' expected");

    return new BreakStatement();
  }

  public PrintStatement printStatement() {
    ArrayList<Expression> expressions = new ArrayList<>();

    lexer.nextToken();
    if(lexer.token == Symbol.LEFTPAR) {
      expressions.add(expression());
      lexer.nextToken();
      while(lexer.token == Symbol.COMMA) {
        expressions.add(expression());
        lexer.nextToken();
      }
      lexer.nextToken();
      if(lexer.token != Symbol.RIGHTPAR)
        error.signal();
    }
    else
      error.signal();

    return new PrintStatement(expressions);
  }

  public Expression expression() {
    simpleExpression();

    if(lexer.token == Symbol.EQ   ||
       lexer.token == Symbol.NEQ  ||
       lexer.token == Symbol.LT   ||
       lexer.token == Symbol.LTE  ||
       lexer.token == Symbol.GT   ||
       lexer.token == Symbol.GTE) {

      relationalOperator();
      expression();
    }
    return new Expression();
  }

    // SimExpr ::= [Unary] Term { AddOp Term }
  public void simpleExpression() {
    if(lexer.token == Symbol.PLUS  ||
       lexer.token == Symbol.MINUS ||
       lexer.token == Symbol.NOT)
      unary();

    term();

    while(lexer.token == Symbol.PLUS  || lexer.token == Symbol.MINUS) {
      addOperator();
      term();
    }
  }

  public void term() {
    factor();

    while(lexer.token == Symbol.MULT ||
          lexer.token == Symbol.DIV  ||
          lexer.token == Symbol.MOD) {
      multiplicationOperator();
      factor();
    }
  }

  public void factor() {

  }

  public Boolean unary(c) {
    if(c == Symbol.PLUS || c == Symbol.MINUS || c == Symbol.OR)
      return true;
    return false;
  }

  public void leftValue() {
    identifier();

    if(lexer.token == Symbol.LEFTBRACKET){
      expression();

      if(lexer.token != Symbol.RIGHTBRACKET)
        error.signal("']' expected");
    }
  }
}

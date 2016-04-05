/**
 *
 * @author Vitor
 */
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

    return new Program(declaration());
  }

  public StatementBlock declaration(){
    StatementBlock stmtBlock = null;
    if(token == 'v') {
      nextToken();
      if(token == 'm') {
        nextToken();
        if(token == '(') {
          nextToken();
          if(token == ')') {
            nextToken();
            stmtBlock = stmtBlock();

            ArrayList<Variable> variables_array = new ArrayList<Variable>();
            variables_array = variableDeclaration();
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

    return stmtBlock;
  }

  public void variableDeclaration() {
    ArrayList<Variable> variables = new ArrayList<Variable>();

    while(token == 'i' || token == 'd' || token == 'c'){
      variables.add(variable());
    }
  }

  public Variable variable() {
    char var_type = type();
    Identifier ident = identifier();

    nextToken();
    if(token != ';')
      error();

    nextToken();
    return new Variable(var_type, ident);
  }

  public void type() {
    char var_type = token;
    nextToken();
    if(token == '['){
      nextToken();
      if(token == ']')
        nextToken();
      else
        error();
    }
    return var_type;
  }

  public void standardType() {

  }

  public void arrayType() {

  }

  public StatementBlock stmtBlock() {
    if(token == '{') {
      nextToken();
      variableDeclaration();
      statement();
      if(token != '}'){
        error();
      }
    }
    else
      error();

    return new StatementBlock();
  }

  public void statement() {
    switch(token) {
      case 'f':
        ifStatement();
      case 'w':
        whileStatement();
      case 'b':
        breakStatement();
      case 'p':
        printStatement();
      default:
        expression();
        nextToken();
        if(token != ';'){
          error();
        }
    }
  }

  public void ifStatement() {
    nextToken();
    if(token == '('){
      nextToken();
      expression();
      nextToken();
      if(token == '{'){
        nextToken();
        // How to detect expression inside statement here?
        // If it detects any letter besides the reserved ones should it loop?
        while(token == 'f' || token == 'w' || token == 'b' || token == 'p'){
          statement();
        }
        nextToken();
        if(token != '}'){
          error();
        }
        nextToken();
        if(token == 'e'){
          nextToken();
          if(token == '{'){
            nextToken();
            while(token == 'f' || token == 'w' || token == 'b' || token == 'p'){
              statement();
            }
            nextToken();
            if(token != '}'){
              error();
            }
          } else{
            error();
          }
        }
      } else{
        error();
      }
    } else{
      error();
    }
  }

  public void whileStatement() {
    nextToken();
    if(token == '('){
      nextToken();
      expression();
      nextToken();
      if(token == ')'){
        nextToken();
        if(token == '{'){
          nextToken();
          while(token == 'f' || token == 'w' || token == 'b' || token == 'p'){
            statement();
          }
          nextToken();
          if(token != '}'){
            error();
          }
        } else{
          error();
        }
      } else{
        error();
      }
    } else{
      error();
    }
  }

  public void breakStatement() {
    nextToken();
    if(token != ';')
      error();
  }

  public void printStatement() {
    nextToken();
    if(token == '('){
      expression();
      nextToken();
      while(token == ','){
        expression();
        nextToken();
      }
      nextToken();
      if(token != ')'){
        error();
      }
    } else{
      error();
    }
  }

  public void expression() {

  }

  public void simpleExpression() {

  }

  public void term() {

  }

  public void factor() {

  }

  public void leftValue() {

  }

  public void identifier() {

  }

  public void relationalOperator() {

  }

  public void addOperator() {

  }

  public void multiplicationOperator() {

  }

  public void unary() {

  }

  public void digit() {

  }

  public void letter() {

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

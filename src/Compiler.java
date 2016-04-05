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
    ArrayList<Variable> variables = new ArrayList<>();
    
    while(token != 'i' || token != 'd' || token != 'c'){
      variables.add(variable());
    }
  }
  
  public Variable variable() {  
    type();
    Identifier ident = identifier();
    
    nextToken();
    if(token != ';')
      error();
    
    return new Variable(varType, ident);
  }
  
  public void type() {
    if(token == 'i' || token == 'c' || token == 'd')
      nextToken();
    
    if(token == '['){
      nextToken();
      if(token == ']')
        nextToken();
      else
        error();
    }
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
    }
  }
  
  public void ifStatement() {
    
  }
  
  public void whileStatement() {
    
  }
  
  public void breakStatement() {
    
  }
  
  public void printStatement() {
    
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

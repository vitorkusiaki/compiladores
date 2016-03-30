/**
 *
 * @author Vitor
 */
import AST.*;

public class Compiler {
  private char []input;
  private char token;
  private int  tokenPos;

  public Program compile(char []pInput) {
    Declaration decl;
    input = pInput;
    tokenPos = 0;
    nextToken();

    decl = declaration();
    
    return new Program(decl);
  }
    
  public Declaration declaration(){
    Declaration decl = null;
    if(token == 'v') {
      nextToken();
      if(token == 'm') {
        nextToken();
        if(token == '(') {
          nextToken();
          if(token == ')') {
            nextToken();
            // call statementBlock function
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

    return decl;
  }
  
  public StatementBlock stmtBlock(){
    
  }
    
  public void nextToken() {
    while(tokenPos < input.length && input[tokenPos] == ' ') {
      tokenPos++;
    }

    if(tokenPos >= input.length)
      token = '\0';
    else {
      token = input[tokenPos];
      tokenPos++;
    }
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

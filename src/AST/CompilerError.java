/* ------------------------------
Charles David de Moraes RA: 489662
Vitor Kusiaki             RA: 408140
------------------------------ */

package AST;

import Lexer.*;
import java.io.*;

public class CompilerError {

    private Lexer lexer;
    private String filename;
    PrintWriter out;

    public CompilerError(PrintWriter out) {
        this.out = out;
    }

    public void setLexer(Lexer lexer) {
        this.lexer = lexer;
    }

    public void setFilename(String filename) {
      String []temp = filename.split("/");
      this.filename = temp[temp.length - 1];
    }

    public void signal(String strMessage) {
        out.println(filename + ": " + lexer.getLineNumber() + ": " + strMessage);
        out.println(lexer.getCurrentLine());
        if (out.checkError())
            System.out.println("Error in signaling an error");
        throw new RuntimeException(strMessage);
    }
}

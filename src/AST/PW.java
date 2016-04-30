/* ------------------------------
Charles David de Moraes RA: 489662
Vitor Kusiaki             RA: 408140
------------------------------ */

package AST;

import java.io.*;

public class PW {

    int currentIndent = 0;

    public int step = 4;
    public PrintWriter out;

    static final private String space = "                                                                                                        ";

   public void add() {
      currentIndent += step;
  }

   public void sub() {
      currentIndent -= step;
   }

   public void set(PrintWriter out) {
      this.out = out;
      currentIndent = 0;
   }

   public void set(int indent) {
      currentIndent = indent;
   }

   public void print(String s) {
      out.print(space.substring(0, currentIndent));
      out.print(s);
   }

   public void println(String s) {
      out.print(space.substring(0, currentIndent));
      out.println(s);
   }
}

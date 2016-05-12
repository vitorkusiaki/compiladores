/* ------------------------------
Charles David de Moraes RA: 489662
Vitor Kusiaki             RA: 408140
------------------------------ */

package AST;

public class Program {
  private final StatementBlock stmtBlock;

  public Program(StatementBlock stmtBlock){
    this.stmtBlock = stmtBlock;
  }

  public void genC(PW pw) {
    pw.println("#include <stdio.h>");
    pw.println("#include <stdlib.h>");
    pw.println("");
    pw.println("void main() {");

    pw.add();
    stmtBlock.genC(pw);

    pw.sub();
    pw.println("}");
  }
}

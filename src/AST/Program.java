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

  public genC(PW pw) {
    pw.out.println("#include <stdio.h>");
    pw.out.println("#include <stdlib.h>");
    pw.out.println();
    pw.out.println("void main() {");

    pw.add();
    stmtBlock.genC(pw);

    pw.sub();
    pw.out.println("}");
  }
}

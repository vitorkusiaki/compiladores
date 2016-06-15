/* ------------------------------
Charles David de Moraes RA: 489662
Vitor Kusiaki             RA: 408140
------------------------------ */

package AST;

public class FunctionDeclaration {
  private final Type type;
  private final String ident;
  private final Formal formal;
  private final StatementBlock stmtBlock;

  public FunctionDeclaration(Type type, String ident, Formal formal, StatementBlock stmtBlock){
    this.type = type;
    this.ident = ident;
    this.formal = formal;
    this.stmtBlock = stmtBlock;
  }

  public void genC(PW pw) {
    type.genC(pw);
    pw.print("(")
    pw.print(ident);
    pw.print(")")
    stmtBlock.genC(pw);
  }
}

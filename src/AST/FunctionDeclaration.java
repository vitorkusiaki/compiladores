/* ------------------------------
Charles David de Moraes RA: 489662
Vitor Kusiaki             RA: 408140
------------------------------ */

package AST;
import java.util.ArrayList;

public class FunctionDeclaration {
  private final Type type;
  private final String ident;
  private final ArrayList<Variable> formals;
  private final StatementBlock stmtBlock;

  public FunctionDeclaration(Type type, String ident, ArrayList<Variable> formals, StatementBlock stmtBlock){
    this.type = type;
    this.ident = ident;
    this.formals = formals;
    this.stmtBlock = stmtBlock;
  }

  public void genC(PW pw) {
    type.genC(pw);
    pw.print(" (");
    pw.print(ident);
    pw.print(")");
    stmtBlock.genC(pw);
  }
}

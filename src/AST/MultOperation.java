/* ------------------------------
Charles David de Moraes RA: 489662
Vitor Kusiaki             RA: 408140
------------------------------ */

package AST;

public class MultOperation {
  final private String multOperator;
  final private Factor factor;

  public MultOperation(String multOp, Factor factor) {
    this.multOperator = multOp;
    this.factor = factor;
  }

  public void genC(PW pw) {
    pw.out.print(multOperator + " ");
    factor.genC(pw);
  }
}

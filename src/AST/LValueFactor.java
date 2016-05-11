/* ------------------------------
Charles David de Moraes RA: 489662
Vitor Kusiaki             RA: 408140
------------------------------ */

package AST;

public class LValueFactor extends Factor {
  private final LValue lvalue;

  public LValueFactor(LValue lvalue) {
    this.lvalue = lvalue;
  }

  public genC(PW pw) {
    lvalue.genC(pw);
  }
}

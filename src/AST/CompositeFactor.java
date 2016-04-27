/* ------------------------------
Charles David de Moraes RA: 489662
Vitor Kusiaki             RA: 408140
------------------------------ */

package AST;

public class CompositeFactor extends Factor {
  private final LValue rValue;

  public CompositeFactor(LValue lvalue, LValue rValue) {
    super(lvalue);
    this.rValue = rValue;
  }
}

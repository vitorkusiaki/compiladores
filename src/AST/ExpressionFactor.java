/* ------------------------------
Charles David de Moraes RA: 489662
Vitor Kusiaki             RA: 408140
------------------------------ */

package AST;

public class ExpressionFactor extends Factor {
  private final ExpressionStatement expression;

  public ExpressionFactor(LValue lvalue, ExpressionStatement expr) {
    super(lvalue);
    this.expression = expr;
  }
}

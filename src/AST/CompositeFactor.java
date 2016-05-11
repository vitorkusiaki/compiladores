/* ------------------------------
Charles David de Moraes RA: 489662
Vitor Kusiaki             RA: 408140
------------------------------ */

package AST;

public class CompositeFactor extends Factor {
  private final LValue lvalue;
  private final ExpressionStatement expression;

  public CompositeFactor(LValue lvalue, ExpressionStatement expr) {
    this.lvalue = lvalue;
    this.expression = expr;
  }

  public void genC(PW pw) {
    lvalue.genC(pw);
    pw.out.print (" := ");
    expression.genC(pw);
  }
}

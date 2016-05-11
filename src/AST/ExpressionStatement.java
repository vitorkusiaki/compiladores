/* ------------------------------
Charles David de Moraes RA: 489662
Vitor Kusiaki             RA: 408140
------------------------------ */

package AST;

public class ExpressionStatement extends Statement {
  final private SimpleExpression simExpression;
  final private String relOperator;
  final private ExpressionStatement exprStatement;

  public ExpressionStatement(SimpleExpression simExpr, String relOp, ExpressionStatement expr) {
    this.simExpression = simExpr;
    this.relOperator = relOp;
    this.exprStatement = expr;
  }

  public genC(PW pw) {
    simExpr.genC();

    if(relOp != null)
      pw.out.print(" " + relOp + " ");

    if(exprStatement != null)
      exprStatement.genC();
  }
}

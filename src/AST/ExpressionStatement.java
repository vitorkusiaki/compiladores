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
}

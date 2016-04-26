package AST;

public class ExpressionStatement extends Statement {
  final private SimpleExpression simExpression;
  final private RelationalOperator relOperator;
  final private ExpressionStatement exprStatement;

  public ExpressionStatement(SimpleExpression simExpr, RelationalOperator relOp, ExpressionStatement expr) {
    this.simExpression = simExpr;
    this.relOperator = relOp;
    this.exprStatement = expr;
  }
}

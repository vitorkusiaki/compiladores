package AST;

public class ExpressionFactor extends Factor {
  private final ExpressionStatement expression;

  public ExpressionFactor(LValue lvalue, ExpressionStatement expr) {
    super(lvalue);
    this.expression = expr;
  }
}

public class ExpressionFactor extends Factor {
  private final ExpressionStatement expression;

  public ExpressionFactor(Lvalue lvalue, ExpressionStatement expr) {
    this.lvalue = lvalue;
    this.expression = expression;
  }
}

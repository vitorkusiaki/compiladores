package AST;

public class LValue {
  final private String identifier;
  final private ExpressionStatement expression;

  public LValue(String ident, ExpressionStatement expr) {
    this.identifier = ident;
    this.expression = expr;
  }
}

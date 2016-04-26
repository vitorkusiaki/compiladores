package AST;

public class CompositeFactor extends Factor {
  private final LValue rValue;

  public CompositeFactor(LValue lvalue, LValue rValue) {
    super(lvalue);
    this.rValue = rValue;
  }
}

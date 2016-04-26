package AST;

public class NumberFactor extends Factor {
  private final Numberino number;

  public NumberFactor(LValue lvalue, Numberino number) {
    super(lvalue);
    this.number = number;
  }
}

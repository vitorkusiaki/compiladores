public class CompositeFactor extends Factor {
  private final Lvalue rValue;

  public CompositeFactor(Lvalue lvalue, Lvalue rValue) {
    this.lvalue = lvalue;
    this.rvalue = rvalue;
  }
}

public class CompositeFactor extends Factor {
  private final Lvalue rvalue;

  public CompositeFactor(Lvalue lvalue, Lvalue rvalue) {
    this.lvalue = lvalue;
    this.rvalue = rvalue;
  }
}

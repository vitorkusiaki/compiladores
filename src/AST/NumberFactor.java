public class NumberFactor extends Factor {
  private final Number number;

  public NumberFactor(Lvalue lvalue, Number number) {
    this.lvalue = lvalue;
    this.number = number;
  }
}

public class ReadTypeFactor extends Factor {
  private final String readType;

  public ReadTypeFactor(Lvalue lvalue, String readType) {
    this.lvalue = lvalue;
    this.readType = readType;
  }
}

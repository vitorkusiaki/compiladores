package AST;

public class ReadTypeFactor extends Factor {
  private final String readType;

  public ReadTypeFactor(LValue lvalue, String readType) {
    super(lvalue);
    this.readType = readType;
  }
}

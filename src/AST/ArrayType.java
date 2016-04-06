package AST;

public class ArrayType extends StandardType {
  private char type;

  public ArrayType(char type) {
    super(type + "[]");
  }
}

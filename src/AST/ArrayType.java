package AST;

public class ArrayType extends Type {
  final private char type;
  final private char leftBracket;
  final private char rightBracket;


  public ArrayType(char type) {
    super(type);
    this.leftBracket = '[';
    this.rightBracket = ']';
  }
}

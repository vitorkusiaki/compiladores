package AST;

abstract public class Type {

    private String name;
    private IntNumber length;

    public static Type intType = new IntType();
    public static Type doubleType = new DoubleType();
    public static Type charType = new CharType();

    public Type(String name) {
      this.name = name;
    }

    public void setLength(Integer length) {
      this.length = length;
    }

    public String getName() {
        return name;
    }

    public IntNumber getLength() {
      return length;
    }
}

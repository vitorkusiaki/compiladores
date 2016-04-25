package AST;

abstract public class Type {

    private String name;

    public static Type intType = new IntType();
    public static Type doubleType = new DoubleType();
    public static Type charType = new CharType();

    public Type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

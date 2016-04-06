package AST;

public class Variable {
  final private Type type;
  final private String identifier;

  public Variable(Type type, String identifier) {
    this.type = type;
    this.identifier = identifier;
  }
}

package AST;

public class Variable {
  final private char type;
  final private String identifier;

  public Variable(char type, String identifier) {
    this.type = type;
    this.identifier = identifier;
  }
}

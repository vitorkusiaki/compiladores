package AST;

public class BreakStatement extends Statement{
  private final String expression;

  public BreakStatement() {
    this.expression = "break ;";
  }
}

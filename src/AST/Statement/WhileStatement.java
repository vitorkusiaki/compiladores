package AST;

public class WhileStatement extends Statement {

  private Expression expression;
  private ArrayList<Statement> statements;

  public WhileStatement(Expression expression, ArrayList<Statement> statements) {
    this.expression = expression;
    this. statements = statements;
  }
}

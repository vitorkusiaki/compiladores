package AST;

public class IfStatement extends Statement {

  private Expression expression;
  private ArrayList<Statement> thenStatements;
  private ArrayList<Statement> elseStatements;

  public IfStatement(Expression expression, ArrayList<Statement> thenStatements, ArrayList<Statement> elseStatements) {
    this.expression = expression;
    this.thenStatements = thenStatements;
    this.elseStatements = elseStatements;
  }
}

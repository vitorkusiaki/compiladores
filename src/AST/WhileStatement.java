package AST;
import java.util.ArrayList;

public class WhileStatement extends Statement {

  final private Expression expression;
  final private ArrayList<Statement> statements;

  public WhileStatement(Expression expression, ArrayList<Statement> statements) {
    this.expression = expression;
    this. statements = statements;
  }
}

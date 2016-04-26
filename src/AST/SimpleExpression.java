package AST;
import java.util.ArrayList;

public class SimpleExpression {
  final private char unary;
  final private Term term;
  final private ArrayList<Operation> operation;

  public ExpressionStatement(char unary, Term term, ArrayList<Operation> operation) {
    this.unary = unary;
    this.term = term;
    this.operation = operation;
  }
}

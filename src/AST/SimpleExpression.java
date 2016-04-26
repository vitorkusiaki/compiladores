package AST;
import java.util.ArrayList;

public class SimpleExpression {
  final private Character unary;
  final private Term term;
  final private ArrayList<AddOperation> operations;

  public SimpleExpression(Character unary, Term term, ArrayList<AddOperation> operations) {
    this.unary = unary;
    this.term = term;
    this.operations = operations;
  }
}

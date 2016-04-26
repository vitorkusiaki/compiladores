package AST;
import java.util.ArrayList;

public class SimpleExpression {
  final private String unary;
  final private Term term;
  final private ArrayList<AddOperation> operations;

  public SimpleExpression(String unary, Term term, ArrayList<AddOperation> operations) {
    this.unary = unary;
    this.term = term;
    this.operations = operations;
  }
}

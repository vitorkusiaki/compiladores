package AST;
import java.util.ArrayList;

public class Term {
  final private Factor factor;
  final private ArrayList<MultOperation> operations;

  public ExpressionStatement(Factor factor, ArrayList<MultOperation> operations) {
    this.factor = factor
    this.operations = operations;
  }
}

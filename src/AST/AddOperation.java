package AST;

public class AddOperation {
  final private String addOperator;
  final private Term term;

  public AddOperation(String addOp, Term term) {
    this.addOperator = addOp;
    this.term = term;
  }
}

package AST;

public class Operation {
  final private String addOperator;
  final private Term term;

  public Operation(String addOp, Term term) {
    this.addOperator = addOp;
    this.term = term;
  }
}

package AST;

public class MultOperation {
  final private String multOperator;
  final private Factor factor;

  public MultOperation(String multOp, Factor factor) {
    this.multOperator = multOp;
    this.factor = factor;
  }
}

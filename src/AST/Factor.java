package AST;

abstract public class Factor {
  protected LValue lvalue;

  public Factor(LValue lvalue) {
    this.lvalue = lvalue;
  }
}

package AST;

public class PrintStatement extends Statement {
  private ArrayList<Expression> expressions;

  public PrintStatement(ArrayList<Expression> expressions) {
    this.expressions = expressions;
  }
}

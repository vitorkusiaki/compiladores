package AST;
import java.util.ArrayList;

public class PrintStatement extends Statement {
  final private ArrayList<Expression> expressions;

  public PrintStatement(ArrayList<Expression> expressions) {
    this.expressions = expressions;
  }
}

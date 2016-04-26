package AST;
import java.util.ArrayList;

public class PrintStatement extends Statement {
  final private ArrayList<ExpressionStatement> expressions;

  public PrintStatement(ArrayList<ExpressionStatement> expressions) {
    this.expressions = expressions;
  }
}

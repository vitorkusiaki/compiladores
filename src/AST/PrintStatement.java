/* ------------------------------
Charles David de Moraes RA: 489662
Vitor Kusiaki             RA: 408140
------------------------------ */

package AST;
import java.util.ArrayList;

public class PrintStatement extends Statement {
  final private ArrayList<ExpressionStatement> expressions;

  public PrintStatement(ArrayList<ExpressionStatement> expressions) {
    this.expressions = expressions;
  }

  public void genC(PW pw) {
    pw.println("printf(");
  }
}

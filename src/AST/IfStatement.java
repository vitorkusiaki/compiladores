/* ------------------------------
Charles David de Moraes RA: 489662
Vitor Kusiaki             RA: 408140
------------------------------ */

package AST;
import java.util.ArrayList;

public class IfStatement extends Statement {

  final private ExpressionStatement expression;
  final private ArrayList<Statement> thenStatements;
  final private ArrayList<Statement> elseStatements;

  public IfStatement(ExpressionStatement expression, ArrayList<Statement> thenStatements, ArrayList<Statement> elseStatements) {
    this.expression = expression;
    this.thenStatements = thenStatements;
    this.elseStatements = elseStatements;
  }
}

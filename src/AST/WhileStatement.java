/* ------------------------------
Charles David de Moraes RA: 489662
Vitor Kusiaki             RA: 408140
------------------------------ */

package AST;
import java.util.ArrayList;

public class WhileStatement extends Statement {

  final private ExpressionStatement expression;
  final private ArrayList<Statement> statements;

  public WhileStatement(ExpressionStatement expression, ArrayList<Statement> statements) {
    this.expression = expression;
    this. statements = statements;
  }

  public void genC(PW pw) {
    pw.print("while(");
    expression.genC(pw);
    pw.println(") {");

    pw.add();
    for(Statement s : statements)
      s.genC(pw);

    pw.sub();
    pw.println("}");
  }
}

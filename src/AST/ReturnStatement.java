/* ------------------------------
Charles David de Moraes RA: 489662
Vitor Kusiaki             RA: 408140
------------------------------ */

package AST;

public class ReturnStatement extends Statement {

  final private ExpressionStatement expression;

  public ReturnStatement(ExpressionStatement expression) {
    this.expression = expression;
  }

  public void genC(PW pw) {
    pw.print("return ");
    expression.genC(pw);
    pw.println(";");
  }
}

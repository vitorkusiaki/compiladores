/* ------------------------------
Charles David de Moraes RA: 489662
Vitor Kusiaki             RA: 408140
------------------------------ */

package AST;

public class LValue {
  final private String identifier;
  final private ExpressionStatement expression;

  public LValue(String ident, ExpressionStatement expr) {
    this.identifier = ident;
    this.expression = expr;
  }

  public void genC(PW pw) {
    pw.print(identifier);

    if(expression != null){
      pw.print("[");
      expression.genC(pw);
      pw.println("]");
    }
  }
}

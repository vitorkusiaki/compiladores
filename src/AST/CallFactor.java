/* ------------------------------
Charles David de Moraes RA: 489662
Vitor Kusiaki             RA: 408140
------------------------------ */

package AST;
import java.util.ArrayList;

public class CallFactor extends Factor {
  final private String ident;
  final private ArrayList<ExpressionStatement> actuals;

  public CallFactor(String ident, ArrayList<ExpressionStatement> actuals) {
    this.ident = ident;
    this.actuals = actuals;
  }

  public void genC(PW pw) {
    pw.print(ident);

    if(actuals != null){
      pw.print("(");
      for(ExpressionStatement a : actuals) {
        a.genC(pw);
        pw.print(",");
      }
      pw.println(")");
    }
  }
}

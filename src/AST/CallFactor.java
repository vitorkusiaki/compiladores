/* ------------------------------
Charles David de Moraes RA: 489662
Vitor Kusiaki             RA: 408140
------------------------------ */

package AST;
import java.util.ArrayList;

public class CallFactor extends Factor {
  final private String ident;
  final private ArrayList<Expression> actuals;

  public Call(String ident, ArrayList<Expression> actuals) {
    this.identifier = ident;
    this.actuals = actuals;
  }

  public void genC(PW pw) {
    pw.print(ident);

    if(actuals != null){
      pw.print("(");
      for(Expression a : actuals) {
        a.genc(pw)
        pw.print(",");
      }
      pw.println(")");
    }
  }
}

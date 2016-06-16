/* ------------------------------
Charles David de Moraes RA: 489662
Vitor Kusiaki             RA: 408140
------------------------------ */

package AST;

public class CallFactor extends Factor {
  final private String ident;
  final private Actuals actuals;

  public Call(String ident, Actuals actuals) {
    this.identifier = ident;
    this.actuals = actuals;
  }

  public void genC(PW pw) {
    pw.print(ident);

    if(actuals != null){
      pw.print("(");
      actuals.genC(pw);
      pw.println(")");
    }
  }
}

/* ------------------------------
Charles David de Moraes RA: 489662
Vitor Kusiaki             RA: 408140
------------------------------ */

package AST;

public class StringConstantFactor extends Factor {
  private final String value;

  public StringConstantFactor(String value) {
    this.value = value;
  }

  public void genC(PW pw) {
    pw.print("\"" + value + "\"");
  }
}

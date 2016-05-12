/* ------------------------------
Charles David de Moraes RA: 489662
Vitor Kusiaki             RA: 408140
------------------------------ */

package AST;

public class DoubleNumber extends Numberino {
  private double number;

  public DoubleNumber(double number) {
    this.number = number;
  }

  public void genC(PW pw) {
    pw.print(Double.toString(number));
  }
}

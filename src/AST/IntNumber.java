/* ------------------------------
Charles David de Moraes RA: 489662
Vitor Kusiaki             RA: 408140
------------------------------ */

package AST;

public class IntNumber extends Numberino {
  private int number;

  public IntNumber(int number) {
    this.number = number;
  }

  public void genC(PW pw ) {
    pw.print(Integer.toString(number));
  }
}

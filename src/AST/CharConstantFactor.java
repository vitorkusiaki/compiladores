/* ------------------------------
Charles David de Moraes RA: 489662
Vitor Kusiaki             RA: 408140
------------------------------ */

package AST;

public class CharConstantFactor extends Factor {
  private final char value;

  public CharConstantFactor(char value) {
    this.value = value;
  }

  public void genC(PW pw) {
    pw.print(value);
  }
}

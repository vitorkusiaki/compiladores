/* ------------------------------
Charles David de Moraes RA: 489662
Vitor Kusiaki             RA: 408140
------------------------------ */

package AST;

public class ReadTypeFactor extends Factor {
  private final String readType;

  public ReadTypeFactor(LValue lvalue, String readType) {
    super(lvalue);
    this.readType = readType;
  }
}

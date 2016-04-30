/* ------------------------------
Charles David de Moraes RA: 489662
Vitor Kusiaki             RA: 408140
------------------------------ */

package AST;

public class BreakStatement extends Statement{
  private final String expression;

  public BreakStatement() {
    this.expression = "break ;";
  }
}

/* ------------------------------
Charles David de Moraes RA: 489662
Vitor Kusiaki             RA: 408140
------------------------------ */

package AST;
import java.util.ArrayList;

public class Term {
  final private Factor factor;
  final private ArrayList<MultOperation> operations;

  public Term(Factor factor, ArrayList<MultOperation> operations) {
    this.factor = factor;
    this.operations = operations;
  }
}

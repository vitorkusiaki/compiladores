/* ------------------------------
Charles David de Moraes RA: 489662
Vitor Kusiaki             RA: 408140
------------------------------ */

package AST;

public class AddOperation {
  final private String addOperator;
  final private Term term;

  public AddOperation(String addOp, Term term) {
    this.addOperator = addOp;
    this.term = term;
  }

  public void genC(PW pw) {
    pw.print(addOperator);
    term.genC(pw);
  }
}

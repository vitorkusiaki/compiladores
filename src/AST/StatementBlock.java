/* ------------------------------
Charles David de Moraes RA: 489662
Vitor Kusiaki             RA: 408140
------------------------------ */

package AST;
import java.util.ArrayList;

public class StatementBlock {

  final private ArrayList<Variable> vars;
  final private ArrayList<Statement> statements;

  public StatementBlock(ArrayList<Variable> vars, ArrayList<Statement> statements) {
    this.vars = vars;
    this.statement = statements;
  }

  public genC(PW pw) {
    for(Variable v : vars)
      v.genC(pw);

    for(Statement s : statements)
      s.genC(pw);
  }
}

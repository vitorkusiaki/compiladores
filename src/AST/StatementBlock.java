package AST;
import java.util.ArrayList;

public class StatementBlock {

  final private ArrayList<Variable> vars;
  final private ArrayList<Statement> statements;

  public StatementBlock(ArrayList<Variable> vars, ArrayList<Statement> statements) {
    this.vars = vars;
    this.statements = statements;
  }
}

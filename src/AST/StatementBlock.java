package AST;
import java.util.ArrayList;

public class StatementBlock {

  final private ArrayList<Variable> vars;
  final private Statement statement;

  public StatementBlock(ArrayList<Variable> vars, Statement statement) {
    this.vars = vars;
    this.statement = statement;
  }
}

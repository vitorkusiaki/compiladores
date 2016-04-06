package AST;

import java.util.*;

public class StatementBlock {

  private ArrayList<Variable> vars;
  private ArrayList<Statement> statements;

  public StatementBlock(ArrayList<Variable> vars, ArrayList<Statement> statements) {
    this.vars = vars;
    this.statements = statements;
  }
}

package AST;

/**
 *
 * @author vitor
 */

// Stmt ::= Expr ‘;’ | ifStmt | WhileStmt | BreakStmt | PrintStmt
public class CompositeStatement extends Statement {
  private final CompositeExpression compExpression;
  private final Statement statement;
}

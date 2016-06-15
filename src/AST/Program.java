/* ------------------------------
Charles David de Moraes RA: 489662
Vitor Kusiaki             RA: 408140
------------------------------ */

package AST;
import java.util.ArrayList;

public class Program {
  final private ArrayList<FunctionDeclaration> declarations;

  public Program(ArrayList<FunctionDeclaration> declarations){
    this.declarations = declarations;
  }

  public void genC(PW pw) {
    for(FunctionDeclaration d : declarations)
      d.genC(pw);
  }
}

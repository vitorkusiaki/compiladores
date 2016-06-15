/* ------------------------------
Charles David de Moraes RA: 489662
Vitor Kusiaki             RA: 408140
------------------------------ */

package AST;

abstract public class Type {

  private String name;
  private Integer length;

  public static Type intType = new IntType();
  public static Type doubleType = new DoubleType();
  public static Type charType = new CharType();

  public Type(String name) {
    this.name = name;
  }

  public void setLength(Integer length) {
    this.length = length;
  }

  public String getName() {
    return name;
  }

  public Integer getLength() {
    return length;
  }

  public void genC(PW pw) {
    pw.print(name);
  }
}

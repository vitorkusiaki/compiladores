import AST.*;
import java.io.*;

public class Main {
  public static void main(String []args){

    File file;
    FileReader stream;
    int numChRead;
    Program program;

    if(args.length != 2) {
      System.out.println("Usage:\n java Main input_file output_file");
    } else {
      file = new File(args[0]);
      if(!file.exists() || !file.canRead()) {
        throw new RuntimeException();
      }
      try {
        stream = new FileReader(file);
      } catch(FileNotFoundException e) {
        throw new RuntimeException();
      }

      char []input = new char[(int)file.length() + 1];

      try {
        numChRead = stream.read(input, 0, (int)file.length());
      } catch(IOException e) {
        throw new RuntimeException();
      }

      if(numChRead != file.length()) {
        throw new RuntimeException();
      }

      try {
        stream.close();
      } catch(IOException e) {
        throw new RuntimeException();
      }

      Compiler compiler = new Compiler();
      FileOutputStream output;
      try {
        output = new FileOutputStream(args[1]);
      } catch(IOException e) {
        throw new RuntimeException();
      }

      PrintWriter pw = new PrintWriter(output);
      program = null;

      try {
        program = compiler.compile(input, pw);
      } catch(RuntimeException e) {
        System.out.println(e);
      }
    }
  }
}

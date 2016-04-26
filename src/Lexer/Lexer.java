package Lexer;

import java.util.*;
import AST.*;

public class Lexer {

    private char []input;
    public Symbol token;

    private String stringValue;
    private int numberValue;
    private char charValue;

    private int tokenPos;
    //  input[lastTokenPos] is the last character of the last token
    private int lastTokenPos;

    private int lineNumber;

    private CompilerError error;

    public Lexer( char [] input, CompilerError error) {
        this.input = input;
        this.error = error;
        input[input.length - 1] = '\0';
        lineNumber = 1;
        tokenPos = 0;
    }

    static private Hashtable<String, Symbol> keywordsTable;

    static {
        keywordsTable = new Hashtable<String, Symbol>();

        keywordsTable.put("void", Symbol.VOID);
        keywordsTable.put("main", Symbol.MAIN);
        keywordsTable.put("int", Symbol.INT);
        keywordsTable.put("double", Symbol.DOUBLE);
        keywordsTable.put("char", Symbol.CHAR);
        keywordsTable.put("if", Symbol.IF);
        keywordsTable.put("else", Symbol.ELSE);
        keywordsTable.put("while", Symbol.WHILE);
        keywordsTable.put("break", Symbol.BREAK);
        keywordsTable.put("print", Symbol.PRINT);
        keywordsTable.put("readInteger", Symbol.READINTEGER);
        keywordsTable.put("readDouble", Symbol.READDOUBLE);
        keywordsTable.put("readChar", Symbol.READCHAR);
    }

    public void nextToken() {
        char c;

        while((c = input[tokenPos]) == ' ' || c == '\r' || c == '\t' || c == '\n') {
            if(c == '\n')
                lineNumber++;
            tokenPos++;
        }
        if(c == '\0')
            token = Symbol.EOF;
        else if(input[tokenPos] == '/' && input[tokenPos + 1] == '/') {
            // single line coment found
            while(input[tokenPos] != '\0' && input[tokenPos] != '\n')
                tokenPos++;
            nextToken();
        } else if(input[tokenPos] == '/' && input[tokenPos + 1] == '*') {
            // Multi line comment found
            while(true) {
                if(input[tokenPos] == '*') {
                    tokenPos++;
                    if(input[tokenPos] == '/') {
                        tokenPos++;
                        break;
                    }
                }
                tokenPos++;
            }
            nextToken();
        } else if(Character.isLetter(c)) {
            StringBuffer ident = new StringBuffer();
            while(Character.isLetter(input[tokenPos]) || input[tokenPos] == '_') {
                ident.append(input[tokenPos]);
                tokenPos++;
            }
            stringValue = ident.toString();

            Symbol value = keywordsTable.get(stringValue);
            if(value == null)
                token = Symbol.IDENT;
            else
                token = value;
        } else if(Character.isDigit(c)) {
            StringBuffer number = new StringBuffer();
            while ( Character.isDigit(input[tokenPos])) {
                number.append(input[tokenPos]);
                tokenPos++;
            }
            token = Symbol.NUMBER;
        } else {
            tokenPos++;
            switch(c) {
                case '+':
                    token = Symbol.PLUS;
                    break;
                case '-':
                    token = Symbol.MINUS;
                    break;
                case '*':
                    token = Symbol.MULT;
                    break;
                case '/':
                    token = Symbol.DIV;
                    break;
                case '%':
                    token = Symbol.MOD;
                    break;
                case '<':
                    if(input[tokenPos] == '=') {
                        tokenPos++;
                        token = Symbol.LTE;
                    } else
                        token = Symbol.LT;
                    break;
                case '>':
                    if(input[tokenPos] == '=') {
                        tokenPos++;
                        token = Symbol.GTE;
                    } else
                        token = Symbol.GT;
                    break;
                case '=':
                    token = Symbol.ASSIGN;
                    break;
                case '[':
                    token = Symbol.LEFTBRACKET;
                    break;
                case ']':
                    token = Symbol.RIGHTBRACKET;
                    break;
                case '(':
                    token = Symbol.LEFTPAR;
                    break;
                case ')':
                    token = Symbol.RIGHTPAR;
                    break;
                case '{':
                    token = Symbol.LEFTBRACE;
                    break;
                case '}':
                    token = Symbol.RIGHTBRACE;
                    break;
                case ',':
                    token = Symbol.COMMA;
                    break;
                case ';':
                    token = Symbol.SEMICOLON;
                    break;
                case '.':
                    token = Symbol.DOT;
                    break;
                case '!':
                    if(input[tokenPos] == '=') {
                        tokenPos++;
                        token = Symbol.NEQ;
                    } else
                        token = Symbol.NOT;
                    break;
                case '&':
                    if(input[tokenPos] == '&') {
                        tokenPos++;
                        token = Symbol.AND;
                    } else
                        error.signal("& expected");
                    break;
                case '|':
                    if(input[tokenPos] == '|') {
                        tokenPos++;
                        token = Symbol.OR;
                    } else
                        error.signal("| expected");
                    break;
                default:
                    error.signal("Invalid character: '" + c + "'");
            }
        }
        lastTokenPos = tokenPos - 1;
    }

    public String getStringValue() {
        return stringValue;
    }

    public int getNumberValue() {
        return numberValue;
    }

    public char getCharValue() {
        return charValue;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public String getCurrentLine() {
        int i = lastTokenPos;
        if ( i == 0 )
            i = 1;
        else
            if ( i >= input.length )
                i = input.length;

        StringBuffer line = new StringBuffer();
        // go to the beginning of the line
        while ( i >= 1 && input[i] != '\n' )
            i--;
        if ( input[i] == '\n' )
            i++;
        // go to the end of the line putting it in variable line
        while ( input[i] != '\0' && input[i] != '\n' && input[i] != '\r' ) {
            line.append( input[i] );
            i++;
        }
        return line.toString();
    }
}

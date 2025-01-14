/**
 * Delimiter Checker Class: Reads through each
 * character of the Java source file to determine
 * if the delimiters match.
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DelimiterChecker {
    private BufferedReader reader;
    private int lineNumber = 1;
    private int charNumber = 0;
    private boolean inSingleLineComment = false;
    private boolean inMultiLineComment = false;
    private boolean inStringLiteral = false;
    private boolean inCharLiteral = false;

    public DelimiterChecker(String fileName) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(fileName));
    }

    public char getNextChar() throws IOException {
        int character;
        char currentChar = 0;

        while ((character = reader.read()) != -1) {
            charNumber++;
            currentChar = (char) character;
            if (currentChar == '\n') {
                lineNumber++;
                charNumber = 0; 
            }
            updateState(currentChar);
            if (inCommentOrLiteral()) {
                continue; 
            }
            return currentChar;
        }
        return currentChar; 
    }

    public String getCurrentPosition() {
        return "line " + lineNumber + ", character " + charNumber;
    }

    private boolean inCommentOrLiteral() {
        return inSingleLineComment || inMultiLineComment || inStringLiteral || inCharLiteral;
    }

    private void updateState(char currentChar) throws IOException {
        if (inSingleLineComment) {
            if (currentChar == '\n') {
                inSingleLineComment = false;
            }
        } else if (inMultiLineComment) {
            if (currentChar == '*' && peekNextChar() == '/') {
                inMultiLineComment = false;
                getNextChar();
            }
        } else if (inStringLiteral) {
            if (currentChar == '"') {
                inStringLiteral = false;
            } else if (currentChar == '\\') {
                getNextChar(); 
            }
        } else if (inCharLiteral) {
            if (currentChar == '\'') {
                inCharLiteral = false;
            } else if (currentChar == '\\') {
                getNextChar(); 
            }
        } else {
            if (currentChar == '/' && peekNextChar() == '/') {
                inSingleLineComment = true;
                getNextChar(); 
            } else if (currentChar == '/' && peekNextChar() == '*') {
                inMultiLineComment = true; 
                getNextChar(); 
            } else if (currentChar == '"') {
                inStringLiteral = true; 
            } else if (currentChar == '\'') {
                inCharLiteral = true; 
            }
        }
    }

    private int peekNextChar() throws IOException {
        reader.mark(1);
        int nextChar = reader.read();
        reader.reset();
        return nextChar;
    }
}
/*  
 * CMSC 330 Advanced Programming Languages
 * Project 1, February 2, 2025 
 * Scene.java class defines a syntax error.
 */

// Class that defines a syntax error

class SyntaxError extends Exception
{
    // Constructor that creates a syntax error object given the line number and error

    public SyntaxError(int line, String description) {
        super("Syntax Error on Line: " + line + " " + description);
    }
}
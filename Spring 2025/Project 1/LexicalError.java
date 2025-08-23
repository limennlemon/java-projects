/*  
 * CMSC 330 Advanced Programming Languages
 * Project 1, February 2, 2025 
 * LexicalError.java class defines the lexical error.
 */

// Class that defines a lexical error

class LexicalError extends Exception
{
    // Constructor that creates a lexical error object given the line number and error

    public LexicalError(int line, String description)    {
        super("Lexical Error on Line: " + line + " " + description);
    }
}
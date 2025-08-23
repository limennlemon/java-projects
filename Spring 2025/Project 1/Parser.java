/*  
 * CMSC 330 Advanced Programming Languages
 * Project 1, February 2, 2025 
 * Parser.java class provides the skeleton parser for Project 1.
 */

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

class Parser {
    private JFrame window;
    private Token token;
    private Lexer lexer;

    // Constructor to create new lexical analyzer from input file

    public Parser(File file) throws IOException {
        lexer = new Lexer(file);
    }

    // Parses the production
    // scene -> SCENE IDENTIFIER number_list images END '.'

    public Scene parseScene() throws LexicalError, SyntaxError, IOException {
        verifyNextToken(Token.SCENE);
        verifyNextToken(Token.IDENTIFIER);
        String window = lexer.getLexeme();
        int[] dimensions = getNumberList(2);
        Scene scene = new Scene(window, dimensions[0], dimensions[1]);
        parseImages(scene, lexer.getNextToken());
        verifyNextToken(Token.PERIOD);
        return scene;
    }

    // Parses the following productions

    // images -> image images | image
    // image -> right_triangle | rectangle | parallelogram | regular_polygon | isosceles   | text  
    // right_triangle -> RIGHT_TRIANGLE COLOR number_list AT number_list HEIGHT NUMBER WIDTH NUMBER ';'
    // rectangle -> RECTANGLE_ COLOR number_list AT number_list HEIGHT NUMBER WIDTH NUMBER ';'
    // parallelogram -> PARALLELOGRAM COLOR number_list AT number_list number_list OFFSET    NUMBER ';' 
    // regular_polygon -> REGULAR_POLYGON COLOR number_list AT number_list SIDES NUMBER RADIUS NUMBER ';' 
    // isosceles_triangle -> ISOSCELES COLOR number_list AT number_list HEIGHT NUMBER WIDTH NUMBER ';' 
    // text -> TEXT COLOR number_list AT number_list STRING ';'

    private void parseImages(Scene scene, Token imageToken) throws LexicalError, SyntaxError, IOException {
        int height, width, offset, radius;
        verifyNextToken(Token.COLOR);
        int[] colors = getNumberList(3);
        Color color = new Color(colors[0], colors[1], colors[2]);
        verifyNextToken(Token.AT);
        int[] location = getNumberList(2);
        Point point = new Point(location[0], location[1]);
        if (imageToken == Token.RIGHT_TRIANGLE) {
            verifyNextToken(Token.HEIGHT);
            verifyNextToken(Token.NUMBER);
            height = lexer.getNumber();
            verifyNextToken(Token.WIDTH);
            verifyNextToken(Token.NUMBER);
            width = lexer.getNumber();
            RightTriangle triangle = new RightTriangle(color, point, height, width);
            scene.addImage(triangle);
        } else if (imageToken == Token.RECTANGLE) {
            verifyNextToken(Token.HEIGHT);
            verifyNextToken(Token.NUMBER);
            height = lexer.getNumber();
            verifyNextToken(Token.WIDTH);
            verifyNextToken(Token.NUMBER);
            width = lexer.getNumber();
            Rectangle rectangle = new Rectangle(color, point, height, width);
            scene.addImage(rectangle);
        } else if (imageToken == Token.ISOSCELES_TRIANGLE) {
            verifyNextToken(Token.HEIGHT);
            verifyNextToken(Token.NUMBER);
            height = lexer.getNumber();
            verifyNextToken(Token.WIDTH);
            verifyNextToken(Token.NUMBER);
            width = lexer.getNumber();
            IsoscelesTriangle isoscelesTriangle = new IsoscelesTriangle(color, point, height, width);
            scene.addImage(isoscelesTriangle);
        } else if (imageToken == Token.PARALLELOGRAM) {
            int[] location2 = getNumberList(2); 
            Point lowerRight = new Point(location2[0], location2[1]);
            verifyNextToken(Token.OFFSET); 
            verifyNextToken(Token.NUMBER);  
            offset = lexer.getNumber();  
            Parallelogram parallelogram = new Parallelogram(color, point, lowerRight, offset);
            scene.addImage(parallelogram);
        } else if (imageToken == Token.REGULAR_POLYGON) {
        	verifyNextToken(Token.SIDES);
            verifyNextToken(Token.NUMBER);
            int sides = lexer.getNumber();
            verifyNextToken(Token.RADIUS);
            verifyNextToken(Token.NUMBER);
            radius = lexer.getNumber();
            if (sides < 3) { // error message if less than 3 sides
               System.out.println("Line: " + lexer.getLineNo() + " A polygon must have at least three sides ");
       	    } else {
                RegularPolygon regularPolygon = new RegularPolygon(color, sides, point, radius);
                scene.addImage(regularPolygon);
            }
        } else if (imageToken == Token.TEXT) {
            verifyNextToken(Token.STRING);
            String text = lexer.getLexeme();
            Text textImage = new Text(color, point, text);
            scene.addImage(textImage);
        }  else {
             throw new SyntaxError(lexer.getLineNo(), "Unexpected image name " + imageToken);
        }
        verifyNextToken(Token.SEMICOLON);
        token = lexer.getNextToken();
        if (token != Token.END)
            parseImages(scene, token);
    }

    // Parses the following productions

    // number_list -> '(' numbers ')'
    // numbers -> NUMBER | NUMBER ',' numbers

    // Returns an array of the numbers in the number list

    private int[]  getNumberList(int count) throws LexicalError, SyntaxError, IOException {
        int[] list = new int[count];
        verifyNextToken(Token.LEFT_PAREN);
        for (int i = 0; i < count; i++) {
            verifyNextToken(Token.NUMBER);
            list[i] = lexer.getNumber();
            token = lexer.getNextToken();
            if (i < count - 1)
                verifyCurrentToken(Token.COMMA);
            else
                verifyCurrentToken(Token.RIGHT_PAREN);
        }
        return list;
    }

    // Returns a list of numbers

    private int[] getNumberList() throws LexicalError, SyntaxError, IOException {
        ArrayList<Integer> list = new ArrayList<Integer>();
        verifyNextToken(Token.LEFT_PAREN);
        do {
            verifyNextToken(Token.NUMBER);
            list.add((int) lexer.getNumber());
            token = lexer.getNextToken();
        }
        while (token == Token.COMMA);
        verifyCurrentToken(Token.RIGHT_PAREN);
        int[] values = new int[list.size()];
        for (int i = 0; i < values.length; i++)
            values[i] = list.get(i);
        return values;
    }

    // Verifies that the next token is the expected token

    private void verifyNextToken(Token expectedToken) throws LexicalError, SyntaxError, IOException {
        token = lexer.getNextToken();
        verifyCurrentToken(expectedToken);
    }

    // Verifies that the current token is the expected token

    private void verifyCurrentToken(Token expectedToken) throws SyntaxError {
        if (token != expectedToken)
            throw new SyntaxError(lexer.getLineNo(), "Expecting token " + expectedToken + " not " + token);
    }
}
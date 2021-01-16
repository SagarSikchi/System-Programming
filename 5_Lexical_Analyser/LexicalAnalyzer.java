/*
 * Name: Sagar Sikchi
 * Batch B2
 * Roll No. 65
 * Lab6: Lexical Analyser
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LexicalAnalyzer {

    public static int index = 0;
    public static String keyWords_string = "";
    // public static boolean isBreak = false;
    public static ArrayList<String> __KEYWORDS__ = new ArrayList<>(
            Arrays.asList("int", "float", "short", "double", "if", "else", "char", "do", "while", "for", "continue",
                    "break", "auto", "register", "goto", "enum", "extern", "void", "sizeof", "switch", "typedef",
                    "default", "struct", "signed", "unsigned", "long", "case", "return"));
    public static ArrayList<String> __ARITHMETIC_OPERATORS__ = new ArrayList<>(Arrays.asList("+", "-", "*", "/", "%"));
    public static ArrayList<String> __LOGICAL_OPERATORS__ = new ArrayList<>(Arrays.asList("&&", "||", "!"));
    public static ArrayList<String> __RELATIONAL_OPERATORS__ = new ArrayList<>(
            Arrays.asList("<", ">", "<=", ">=", "!=", "=="));
    public static ArrayList<String> __BITWISE_OPERATORS__ = new ArrayList<>(
            Arrays.asList("&", "|", "^", "~", "<<", ">>"));
    public static ArrayList<String> __ASSIGNMENT_OPERATORS__ = new ArrayList<>(
            Arrays.asList("=", "+=", "-=", "*=", "/=", "%=", "<<=", ">>=", "&=", "|=", "^="));

    public static void main(String[] args) {
        System.out.println("\n\n######################## Lexical Analysis ########################\n");
        // ArrayList for lexemes and HashTable for the Symbol Table
        ArrayList<String> lines = new ArrayList<>();
        ArrayList<String> headers = new ArrayList<>();
        Map<String, List<String>> symbolTable = new HashMap<String, List<String>>();
        String str = null;
        try {
            // FileReader fptr = new FileReader("E:\\Desktop Folders\\Sagar Files 2020\\SEM
            // V TYIT\\IT3202 SP\\SP-LAB\\input.txt");
            FileReader fptr = new FileReader(
                    "C:\\Users\\hp\\Desktop\\System-Programming\\5_Lexical_Analyser\\input.c");
            BufferedReader data = new BufferedReader(fptr);
            // Reads every line and then splits everything into lexemes
            while ((str = data.readLine()) != null) {
                if (!(str.length() == 0)) { // If no blank lines detected, then continue
                    if (str.contains("#include")) {
                        headers.add(str.trim().substring(1));
                        continue;
                    }
                    // Regex for splitting the code into lexemes
                    String[] strSplit = str.trim().split("\\s+|\\s*,\\s*|\\;+|\\\"+|\\:+|\\[+|\\]+");
                    List<String> list = Arrays.asList(strSplit); // Save the lexeme array into a Container List
                    lines.addAll(list); // Add the Container List to the ArrayList or HashTable
                }
            }
            if (headers.size() > 0) {
                symbolTable.put("Headers", headers);
            }

            String[] linesArray = lines.toArray(new String[0]);

            for (String key : __KEYWORDS__) {
                keyWords_string += key + "|";
            }

            List<String> identifiers = new ArrayList<String>();
            for (int count = 0; count < linesArray.length; count++) {
                // Use regex here for variables
                if ((linesArray[count].matches("\\w+") || linesArray[count].equals("main()"))
                        && !linesArray[count].matches("\\d+") && !linesArray[count].matches(keyWords_string)) {
                    if (!identifiers.contains(linesArray[count])) {
                        identifiers.add(linesArray[count]);
                    }
                }
            }
            symbolTable.put("Identifiers", identifiers);

            List<String> others = new ArrayList<String>();
            for (int count = 0; count < linesArray.length; count++) {
                if (linesArray[count].matches("\\(|\\)|\\{|\\}")) { // Use regex here for variables
                    if (!identifiers.contains(linesArray[count])) {
                        others.add(linesArray[count]);
                    }
                }
            }
            symbolTable.put("Others", others);

            List<String> keywords = new ArrayList<String>();
            for (int i = 0; i < __KEYWORDS__.size(); i++) {
                if (lines.contains(__KEYWORDS__.get(i))) {
                    index = lines.indexOf(__KEYWORDS__.get(i));
                    keywords.add(lines.get(index));
                }
            }
            symbolTable.put("Keywords", keywords);

            List<String> arithmetic_operators = new ArrayList<String>();
            for (int i = 0; i < __ARITHMETIC_OPERATORS__.size(); i++) {
                if (lines.contains(__ARITHMETIC_OPERATORS__.get(i))) {
                    index = lines.indexOf(__ARITHMETIC_OPERATORS__.get(i));
                    arithmetic_operators.add(lines.get(index));
                }
            }
            symbolTable.put("Arithmetic Operators", arithmetic_operators);

            List<String> logical_operators = new ArrayList<String>();
            for (int i = 0; i < __LOGICAL_OPERATORS__.size(); i++) {
                if (lines.contains(__LOGICAL_OPERATORS__.get(i))) {
                    index = lines.indexOf(__LOGICAL_OPERATORS__.get(i));
                    logical_operators.add(lines.get(index));
                }
            }
            symbolTable.put("Logical Operators", logical_operators);

            List<String> relational_operators = new ArrayList<String>();
            for (int i = 0; i < __RELATIONAL_OPERATORS__.size(); i++) {
                if (lines.contains(__RELATIONAL_OPERATORS__.get(i))) {
                    index = lines.indexOf(__RELATIONAL_OPERATORS__.get(i));
                    relational_operators.add(lines.get(index));
                }
            }
            symbolTable.put("Relational Operators", relational_operators);

            List<String> bitwise_operators = new ArrayList<String>();
            for (int i = 0; i < __BITWISE_OPERATORS__.size(); i++) {
                if (lines.contains(__BITWISE_OPERATORS__.get(i))) {
                    index = lines.indexOf(__BITWISE_OPERATORS__.get(i));
                    bitwise_operators.add(lines.get(index));
                }
            }
            symbolTable.put("Bitwise Operators", bitwise_operators);

            List<String> assignment_operators = new ArrayList<String>();
            for (int i = 0; i < __ASSIGNMENT_OPERATORS__.size(); i++) {
                if (lines.contains(__ASSIGNMENT_OPERATORS__.get(i))) {
                    index = lines.indexOf(__ASSIGNMENT_OPERATORS__.get(i));
                    assignment_operators.add(lines.get(index));
                }
            }
            symbolTable.put("Assignment Operators", assignment_operators);

            List<String> digits = new ArrayList<String>();
            for (int count = 0; count < linesArray.length; count++) {
                if (linesArray[count].matches("\\d+|\\d+\\.\\d+|")) { // Use regex here for numbers
                    digits.add(linesArray[count]);
                }
            }
            symbolTable.put("Numerical Values", digits);

            List<String> characters = new ArrayList<String>();
            for (int count = 0; count < linesArray.length; count++) {
                if (linesArray[count].matches("'[a-zA-Z_%@!$#^*?\\[\\]\\(\\)\\{\\}]'")) { // Use regex here for
                                                                                          // characters
                    characters.add(linesArray[count]);
                }
            }
            symbolTable.put("Character Values", characters);

            // Prints the ArrayList
            System.out.println("The Lexemes:");
            System.out.println(lines);
            System.out.println();

            // Prints the Symbol Table
            for (Map.Entry<String, List<String>> entry : symbolTable.entrySet()) {
                String key = entry.getKey();
                List<String> values = entry.getValue();
                System.out.printf("%-30s: ", key);
                System.out.println(values);
            }
            data.close();
        } catch (FileNotFoundException exception) {
            System.out.println("Unable to open file!");
        } catch (IOException exception) {
            System.out.println("Error in reading file!");
        }
        System.out.println("\n########################### Thank You ###########################\n");
    }
}

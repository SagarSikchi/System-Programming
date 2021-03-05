/*
 * Name: Sagar Sikchi
 * Batch B2
 * Roll No. 65
 * Lab2: Assembler PASS I and PASS II
 */

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Assembler_Lab2 {

    public static String[][] outputTable = new String[50][6];
    public static HashMap<String, Integer> ST = new HashMap<String, Integer>();
    public static HashMap<String, Integer> MOT = new HashMap<String, Integer>() {
        private static final long serialVersionUID = 1L;
        {
            put("ADD", 1);
            put("SUB", 2);
            put("MULT", 3);
            put("JMP", 4);
            put("JNEG", 5);
            put("JPOS", 6);
            put("JZ", 7);
            put("LOAD", 8);
            put("STORE", 9);
            put("READ", 10);
            put("WRITE", 11);
            put("STOP", 12);
        }
    };
    public static HashMap<String, Integer> POT = new HashMap<String, Integer>() {
        private static final long serialVersionUID = 1L;
        {
            put("DB", 1);
            put("DW", 2);
            put("ORG", 1);
            put("ENDP", 0);
            put("CONST", 2);
            put("END", 0);
        }
    };

    public static void main(String[] args) {
        System.out.println(
                "\n##################################################### Assembler Pass 1 and 2 ######################################################\n");
        String string = null;
        int LC = 0;
        int tableIndex = 1;
        boolean POT_FLAG = false;

        outputTable[0][0] = "LINE NO.";
        outputTable[0][1] = "LABEL";
        outputTable[0][2] = "MNEMONIC";
        outputTable[0][3] = "OPERAND";
        outputTable[0][4] = "LOC. COUNTER";
        outputTable[0][5] = "OUTPUT ADD.";
        for (int i = 1; i < outputTable.length; i++) {
            for (int j = 0; j < outputTable[0].length; j++) {
                outputTable[i][j] = "";
            }
        }

        try {
            String filepath = "2_Assembler_Pass_I_and_Pass_II\\Assembler_Input.txt"; // When to run this file from Parent Folder - System-Programming
	    // String filepath = "Assembler_Input.txt"; // When to run this file from this Folder - 2_Assembler_Pass_I_and_Pass_II

            FileReader fptr = new FileReader(filepath);
            BufferedReader data = new BufferedReader(fptr);
            while ((string = data.readLine()) != null) {
                outputTable[tableIndex][0] = String.valueOf(tableIndex);
                string = string.trim().toUpperCase();
                for (String key : POT.keySet()) {
                    if (string.contains(key) && POT_FLAG == false) {
                        String[] strings = string.split(" ");
                        if (strings[0].equals("ENDP")) {
                            outputTable[tableIndex][2] = strings[0];
                            System.out.println(
                                    "\n--------------------------------------- SYMBOL TABLE AFTER PASS 1 -------------------------------------------- \n");
                            for (String name : ST.keySet()) {
                                System.out.println(name + "\t" + ST.get(name));
                            }
                            System.out.println(
                                    "\n############################################################################################################# \n");
                        } else if (strings[0].equals("END")) {
                            outputTable[tableIndex][2] = strings[0];
                            System.out.println(
                                    "\n--------------------------------------- SYMBOL TABLE AFTER PASS 2 -------------------------------------------- \n");
                            for (String name : ST.keySet()) {
                                System.out.println(name + "\t" + ST.get(name));
                            }
                            System.out.println(
                                    "\n############################################################################################################# \n");
                        } else if (ST.containsKey(strings[0])) {
                            outputTable[tableIndex][1] = strings[0];
                            outputTable[tableIndex][2] = strings[1];
                            ST.put(strings[0], LC);
                        } else if (!ST.containsKey(strings[0])) {
                            outputTable[tableIndex][1] = strings[0];
                            outputTable[tableIndex][2] = strings[1];
                            ST.put(strings[0], 0);
                        }
                        outputTable[tableIndex][5] = "XX";
                        outputTable[tableIndex][4] = String.valueOf(LC);
                        LC += POT.get(key);
                        POT_FLAG = true;
                    }
                }
                if (POT_FLAG == false) {
                    String[] words = string.split(" ");
                    for (String word : words) {
                        if (MOT.containsKey(word)) {
                            outputTable[tableIndex][4] = String.valueOf(LC);
                            outputTable[tableIndex][2] = word;
                            outputTable[tableIndex][5] += String.valueOf(MOT.get(word));
                            if (!word.equals("STOP"))
                                LC += 2;
                            else
                                LC += 1;

                        } else if (word.contains(":")) { // word.equals("LOOP:") || word.equals("OUTER:")
                            outputTable[tableIndex][1] = word;
                            String temp = word.substring(0, word.length()-1);
                            // if (word.equals("LOOP:"))
                            //     temp = "LOOP";
                            // if (word.equals("OUTER:"))
                            //     temp = "OUTER";
                            ST.put(temp, LC);
                        } else if (!ST.containsKey(word)) {
                            outputTable[tableIndex][3] = word;
                            ST.put(word, 0);
                        } else
                            outputTable[tableIndex][3] = word;
                    }
                }
                POT_FLAG = false;
                tableIndex++;
            }
            for (int i = 0; i < tableIndex; i++) {
                for (String key : ST.keySet()) {
                    if (outputTable[i][3].equals(key)) {
                        outputTable[i][5] += String.valueOf(ST.get(key));
                    }
                }
            }
            System.out.println(
                    "\n--------------------------------------- ------ OUTPUT TABLE ------ -------------------------------------------- \n");
            for (int i = 0; i < tableIndex; i++) {
                for (int j = 0; j < outputTable[0].length; j++) {
                    System.out.printf("%-15s", outputTable[i][j]);
                }
                System.out.println();
            }
            System.out.println(
                    "\n############################################### Thank You ###################################################### \n");
            data.close();
            fptr.close();
        } catch (FileNotFoundException exception) {
            System.out.println("Unable to open file!");
        } catch (IOException exception) {
            System.out.println("Error in reading file!");
        }
    }
}

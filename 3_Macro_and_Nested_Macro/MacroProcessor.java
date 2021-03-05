/*
 * Name: Sagar Sikchi
 * Batch B2
 * Roll No. 65
 * Lab3 and Lab4: Macro and Nested Macro Programming
 */

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MacroProcessor {
    public static HashMap<String, Integer> MNT = new HashMap<>();
    public static String[][] MDT = new String[50][2];
    public static String[][] ALA = new String[30][4];
    public static ArrayList<String> outputList = new ArrayList<>();
    public static int MDT_IDX = 1;
    public static int ALA_IDX = 1;
    public static boolean break_flag = true;
    public static boolean isbreak = false;
    public static boolean callBreak = false;

    public static void MacroCall(String[] words) {
        if (MNT.containsKey(words[0])) {
            int MDT_idx_ = MNT.get(words[0]);
            // System.out.println(MDT_idx_);
            int L = words.length;
            int idx_ = 1;
            if (L > 1) { // for macro-call with arguments
                for (int j = 1; j < ALA_IDX && callBreak == false; j++) {
                    if (ALA[j][0].equals(words[0])) {
                        idx_ = j;
                        int temp_idx = idx_;
                        for (int y = 1; y < L; y++) {
                            ALA[temp_idx++][3] = words[y].contains(",") ? words[y].substring(0, words[y].length() - 1)
                                    : words[y];
                        }
                        temp_idx = 0;
                        callBreak = true;
                    }
                }
            }
            callBreak = false;
            String t = "";
            // Printing_Table(ALA, ALA_IDX);
            // Printing_Table(MDT, MDT_IDX);
            while (!(t = MDT[MDT_idx_++][1]).contains("MEND")) {
                // System.out.println(t);
                String outputString = "";
                String[] tStrings = t.split(" ");
                for (String _tempString : tStrings) {
                    if (L > 1) {
                        if (_tempString.contains("#") && _tempString.contains(",")) { // macro-call line with multiple arguments
                            for (int z = idx_; z < ALA_IDX && callBreak == false; z++) {
                                if (ALA[z][2].equals(_tempString.substring(0, _tempString.length() - 1))) {
                                    _tempString = ALA[z][3] + ",";
                                    callBreak = true;
                                }
                            }
                            callBreak = false;
                        } else if (_tempString.contains("#")) { // macro-call line with single argument
                            for (int z = idx_; z < ALA_IDX && callBreak == false; z++) {
                                if (ALA[z][2].equals(_tempString)) {
                                    _tempString = ALA[z][3];
                                    callBreak = true;
                                }
                            }
                            callBreak = false;
                        }
                    }
                    outputString += _tempString + " ";
                }
                outputString = outputString.substring(0, outputString.length() - 1);
                String[] newStrings = outputString.split(" ");
                MacroCall(newStrings);
                if (!MNT.containsKey(newStrings[0])) {
                    outputList.add(outputString);
                }
            }
            callBreak = false;
            isbreak = true;
            return;
        }
    }

    public static void Set_Default_Values(String[][] table) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (table[i][j] == null) {
                    table[i][j] = " - ";
                }
            }
        }
    }

    public static void Printing_Table(String[][] table, int index) {
        for (int i = 0; i < index; i++) {
            for (int j = 0; j < table[i].length; j++) {
                System.out.printf("%-20s", table[i][j]);
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {
        System.out.println(
                "\n################################## MACRO PROCESSOR PASS 1 AND PASS 2 ##################################\n");
        MDT[0][0] = "MDT INDEX";
        MDT[0][1] = "CARD";
        ALA[0][0] = "MACRO NAME";
        ALA[0][1] = "FORMAL ARG";
        ALA[0][2] = "POSITIONAL ARG";
        ALA[0][3] = "ACTUAL ARG";
        String string = null;
        try {
            Set_Default_Values(MDT);
            Set_Default_Values(ALA);

            String filepath = "3_Macro_and_Nested_Macro\\MacroProcessorInput.txt"; // When to run this file from Parent Folder - System-Programming
		    // String filepath = "MacroProcessorInput.txt"; // When to run this file from this Folder - 3_Macro_and_Nested_Macro

            FileReader fptr = new FileReader(filepath);
            BufferedReader data = new BufferedReader(fptr);
            while ((string = data.readLine()) != null) {
                String[] words = string.split(" ");
                if (!string.contains("MEND") && !string.contains("MACRO") && !string.contains("END")
                        && !MNT.containsKey(words[0])) {
                    outputList.add(string);
                    continue;
                }
                MacroCall(words);
                if (isbreak == false) {
                    if (words[0].equals("END")) { // pass 1 and 2 completed
                        break;
                    } else if (words[0].equals("MACRO") && words.length == 2) { // macro without arguments
                        MNT.put(words[1], MDT_IDX);
                        MDT[MDT_IDX][0] = String.valueOf(MDT_IDX);
                        ALA[ALA_IDX][0] = words[1];
                        ALA_IDX += 1;
                        break_flag = true;
                        while (break_flag) {
                            string = data.readLine();
                            MDT[MDT_IDX][0] = String.valueOf(MDT_IDX);
                            MDT[MDT_IDX][1] = string;
                            MDT_IDX++;
                            if (string.equals("MEND")) {
                                break_flag = false;
                            }
                        }
                        break_flag = true;
                        continue;
                    } else if (words[0].equals("MACRO") && words.length > 2) { // macro with arguments
                        MNT.put(words[1], MDT_IDX);
                        MDT[MDT_IDX][0] = String.valueOf(MDT_IDX);
                        ALA[ALA_IDX][0] = words[1];
                        int startIndex = ALA_IDX;
                        for (int x = 2; x < words.length; x++) {
                            ALA[ALA_IDX][1] = words[x].contains(",") ? words[x].substring(0, words[x].length() - 1)
                                    : words[x];
                            ALA[ALA_IDX][2] = "#" + String.valueOf(x - 1);
                            ALA_IDX += 1;
                        }
                        break_flag = true;
                        while (break_flag) {
                            string = data.readLine();
                            String tempString = "";
                            String[] ___sString = string.split(" ");
                            Boolean isFound = false;
                            for (String __s : ___sString) {
                                for (int x = startIndex; x < ALA_IDX && isFound == false; x++) {
                                    if (__s.contains(",") && __s.substring(0, __s.length() - 1).equals(ALA[x][1])) {
                                        __s = ALA[x][2] + ",";
                                        isFound = true;
                                    } else if (__s.equals(ALA[x][1])) {
                                        __s = ALA[x][2];
                                        isFound = true;
                                    }
                                }
                                tempString += __s + " "; // checking for last " "
                                isFound = false;
                            }
                            MDT[MDT_IDX][0] = String.valueOf(MDT_IDX);
                            // MDT[MDT_IDX][1] = tempString;
                            MDT[MDT_IDX][1] = tempString.substring(0, tempString.length() - 1);
                            MDT_IDX += 1;
                            if (string.equals("MEND")) {
                                break_flag = false;
                            }
                        }
                        break_flag = true;
                        continue;
                    }
                }
                isbreak = false;
            }
            data.close();
            System.out.println("\n");
            // print results
            System.out.println("MACRO NAME" + "\t    " + "MDT INDEX");
            for (String key : MNT.keySet()) {
                System.out.println(key + "\t\t    " + MNT.get(key));
            }
            System.out.println("\n");
            Printing_Table(MDT, MDT_IDX);
            Printing_Table(ALA, ALA_IDX);
            System.out.println("EXPANDED CODE:\n");
            for (int i = 0; i < outputList.size(); i++) {
                System.out.println(outputList.get(i));
            }
            System.out.println("\n");

        } catch (FileNotFoundException exception) {
            System.out.println("Unable to open file!");
        } catch (IOException exception) {
            System.out.println("Error in reading file!");
        }
        System.out
                .println("\n###################################### THANK YOU ######################################\n");
    }
}

/* 
 * Sagar Sikchi (65)
 * GR No.: 11810844
 * TYIT-A: Batch B2
 * System Programming: LAB 5 --> Loader & Linker
 * 
 */

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Loader_and_Linker_Copy1 {
    public static Scanner scanner = new Scanner(System.in);
    public static int CURRENT_ADDRESS = 1000;

    public static HashMap<String, List<String>> GVT = new HashMap<>();
    public static HashMap<String, HashMap<String, List<String>>> _LGVT_ = new HashMap<>();
    public static HashMap<String, Integer> DataTypes = new HashMap<>() {
        private static final long serialVersionUID = 1L;
        {
            put("short", 2);
            put("int", 4);
            put("float", 4);
            put("double", 8);
            put("char", 1);
        }
    };

    private static String RemovePunctuation(String string) {
        return (string.contains(",") || string.contains(";")) ? string.substring(0, string.length() - 1) : string;
    }

    // private static void PrintTable(String[][] table) {
    // for (int i = 0; i < table.length - 1; i++) {
    // for (int j = 0; j < table[0].length; j++) {
    // System.out.printf("%-25s", table[i][j]);
    // }
    // System.out.println();
    // }
    // System.out.println();
    // }

    private static void PrintHashTables(String[][] strings) {
        for (int i = 1; i < strings.length; i++) {
            String key = strings[i][0];
            System.out.println("KEY ---> " + key + "\n");
            System.out.printf("%-25s", "NAME");
            System.out.printf("%-25s", "ADDRESS");
            System.out.printf("%-25s", "VALUE");
            System.out.printf("%-25s", "DATA-TYPE OF VARIABLE");
            if (key.equals("GVT")) {
                System.out.printf("%-30s", "EXTERN TO WHICH FILE(S)?");
                System.out.printf("%-30s", "LOCAL TO WHICH FILE?");
            }
            System.out.println();
            for (String s : _LGVT_.get(key).keySet()) {
                System.out.printf("%-25s", s);
                System.out.printf("%-25s", _LGVT_.get(key).get(s).get(0));
                System.out.printf("%-25s", _LGVT_.get(key).get(s).get(1));
                System.out.printf("%-25s", _LGVT_.get(key).get(s).get(2));
                if (key.equals("GVT")) {
                    System.out.printf("%-30s", _LGVT_.get(key).get(s).get(3));
                    System.out.printf("%-30s", _LGVT_.get(key).get(s).get(4));
                } else {
                    if (_LGVT_.get("GVT").containsKey(s)) {
                        _LGVT_.get("GVT").get(s).set(0, _LGVT_.get(key).get(s).get(0));
                        _LGVT_.get("GVT").get(s).set(1, _LGVT_.get(key).get(s).get(1));
                        _LGVT_.get("GVT").get(s).set(2,
                                _LGVT_.get("GVT").get(s).get(2) + ", " + _LGVT_.get(key).get(s).get(2)); // new line
                                                                                                         // added for
                                                                                                         // datatype
                        _LGVT_.get("GVT").get(s).set(4, key);
                    }
                }
                System.out.println();
            }
            System.out.println("\n");
        }
    }

    private static String CalculateFileMemory(String FileName) {
        String length = "";
        try {
            FileReader fptr;
            fptr = new FileReader("C:\\Users\\hp\\Desktop\\System-Programming\\4_Loader_and_Linker\\" + FileName);
            BufferedReader data = new BufferedReader(fptr);
            int total_length = 0;
            String string = null;
            while ((string = data.readLine()) != null) {
                total_length += string.trim().length();
            }
            data.close();
            fptr.close();
            length = String.valueOf(total_length);
        } catch (FileNotFoundException exception) {
            length = "-1";
        } catch (IOException exception) {
            length = "-1";
        }
        return length;
    }

    private static void LOADER(String[][] files) {
        Boolean isBreak = true;
        while (isBreak) {
            System.out.print("Enter Memory Size: ");
            int memory = scanner.nextInt();
            int end = 0, start;
            for (int i = 1; i < (files.length - 1) && isBreak; i++) {
                start = end;
                end += Integer.parseInt(files[i][1]);
                if (end <= memory) {
                    System.out.println(files[i][0] + " can be loaded ---> Start: " + start + "\tand End: " + end);
                    end++;
                } else {
                    // System.out.println("Insufficient Memory for File " + i + " ---> Start: " +
                    // prev + " and End: " + start);
                    System.out.println("Insufficient Memory for " + files[i][0]);
                    System.out.println("Enter Value Again!\n");
                    isBreak = false;
                }
            }
            isBreak = (isBreak) ? false : true;
        }
    }

    private static HashMap<String, List<String>> LINKER(String FileName) {
        HashMap<String, List<String>> LVT = new HashMap<>();
        String string = null;
        try {
            FileReader fptr;
            fptr = new FileReader("C:\\Users\\hp\\Desktop\\System-Programming\\4_Loader_and_Linker\\" + FileName);
            BufferedReader data = new BufferedReader(fptr);
            while ((string = data.readLine()) != null) {
                string = string.trim();
                String[] strings = string.split(" ");
                int length = strings.length;

                if (strings[0].equals("extern")) {
                    for (int i = 1; i < length; i++) {
                        String temp = RemovePunctuation(strings[i]);
                        if (!GVT.containsKey(temp)) {
                            List<String> list = new ArrayList<>();
                            list.add("-");
                            list.add("-");
                            list.add("-");
                            list.add("-");
                            list.add("-");
                            // list.set(0, "-");
                            // list.set(1, "-");
                            list.set(2, "extern");
                            list.set(3, FileName);
                            GVT.put(temp, list);
                        } else {
                            // String x = GVT.get(temp).get(3) + ", " + FileName;
                            // GVT.get(temp).set(3, x);
                            GVT.get(temp).set(3, GVT.get(temp).get(3) + ", " + FileName);
                        }
                    }
                } else if (DataTypes.containsKey(strings[0]) && !string.contains("(") && !string.contains(")")) {
                    for (int i = 1; i < length; i++) {
                        if (strings[i].equals("=")) {
                            i = i + 1;
                            LVT.get(RemovePunctuation(strings[i - 2])).set(1, RemovePunctuation(strings[i]));
                        } else {
                            List<String> list_ = new ArrayList<>();
                            list_.add("-");
                            list_.add("-");
                            list_.add("-");
                            String temp = RemovePunctuation(strings[i]);
                            list_.set(0, String.valueOf(CURRENT_ADDRESS));
                            // list_.set(1, "-");
                            list_.set(2, strings[0]);
                            LVT.put(temp, list_);
                            CURRENT_ADDRESS += DataTypes.get(strings[0]);
                        }
                    }
                }
            }
            data.close();
            fptr.close();
        } catch (FileNotFoundException exception) {
            System.out.println("Unable to open file!");
        } catch (IOException exception) {
            System.out.println("Error in reading file!");
        }
        return LVT;
    }

    public static void main(String[] args) {
        System.out.println("\n############################### LOADER AND LINKER ###############################\n");
        System.out.print("\nEnter the number of Input Files: ");
        int N = scanner.nextInt() + 2;
        String[][] files = new String[N][2];
        files[0][0] = "File Name";
        files[0][1] = "Length of File";
        files[N - 1][0] = "GVT";
        files[N - 1][1] = "-1";
        System.out.println("\nEnter the file names one by one:");
        for (int i = 1; i < N - 1; i++) {
            System.out.print("Enter file " + i + ": ");
            files[i][0] = scanner.next();
            files[i][1] = CalculateFileMemory(files[i][0]);
            if (files[i][1] == "-1" || files[i][1] == "0") {
                System.out.println("\nError in reading " + files[i][0]);
                System.out.println("Enter Again!\n");
                i--;
            }
        }
        // System.out.println("\nThe Files Table is as follows:\n");
        // PrintTable(files);
        System.out.println();
        LOADER(files);

        for (int i = 1; i < N - 1; i++) {
            _LGVT_.put(files[i][0], LINKER(files[i][0]));
        }
        _LGVT_.put("GVT", GVT);
        System.out.println("\n############################### LVT & GVT ###############################\n");
        PrintHashTables(files);
        scanner.close();
        System.out.println("\n################################### THANK YOU ###################################\n");
    }
}

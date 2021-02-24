import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TableOfSymbol {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// String filepath = "C:\\Users\\HP\\Desktop\\input.txt";
		String filepath = "C:\\Users\\hp\\Desktop\\System-Programming\\1_Symbol_Table_Generation\\input.txt";
        String line = null;
        int add=0;
		try {
            FileReader fileReader = new FileReader(filepath);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
//            System.out.printf("literal \t size \t address\n");
            while((line = bufferedReader.readLine()) != null) {
            	if(line.contains("int ")) {
            		int index = line.indexOf("int ");
            		System.out.println("literal: "+line.substring(index+4,index+5)+ " datatype: int"+" size: 4 " + "address: "+add);
            		add=add+4;
            	}
            	if(line.contains("char ")) {
            		int index = line.indexOf("char ");
            		System.out.println("literal: "+line.substring(index+5,index+6)+ " datatype: char"+" size: 2 " + "address: "+add);
            		add=add+2;
            	}
            	if(line.contains("float ")) {
            		int index = line.indexOf("float ");
            		System.out.println("literal: "+line.substring(index+6,index+7)+ " datatype: float"+" size: 4 " + "address: "+add);
            		add=add+2;
            	}
            	if(line.contains("int[] ")) {
            		int index = line.indexOf("int[] ");
            		int count=0;
            		if(line.contains(",") == true) {count++;}
            		char[] ch = line.toCharArray();
            		for(int i=0;i<ch.length;i++) {
            			if((ch[i] ==',')) {
            				count++;
            			}
            		}
            		System.out.println("literal: "+line.substring(index+6,index+7)+ " datatype: int[] "+" size:" +count*4 +"address: "+add);
            		add=add+4*count;
            	}
            	
//                System.out.println(line);
            }   
//            System.out.println(line);
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file: " + filepath);                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + filepath + "'");                  
        }
	}

}
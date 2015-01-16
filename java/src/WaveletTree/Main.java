package WaveletTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException{
		int number;
		
		BufferedReader queueInput = new BufferedReader(new FileReader("/home/marijana/Desktop/input_3"));
		
		try {
	        StringBuilder sb = new StringBuilder();
	        String line = queueInput.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append(System.lineSeparator());
	            line = queueInput.readLine();
	        }
	        String everything = sb.toString();
	        
	        BufferedReader numberInput = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.print("Enter the number: ");
			number = Integer.parseInt(numberInput.readLine());
			
			if(number < 2 || number > everything.length()) {
				System.out.println("Error!");
			}
			
			else {
				//time to executed method
				long startTime = System.currentTimeMillis();

				WaveletTree.WaveletTrees(everything, number);

				long endTime = System.currentTimeMillis();
				System.out.println("That took " + (endTime - startTime) + " milliseconds");
				
				Runtime runtime_max = Runtime.getRuntime();
				Runtime runtime_free = Runtime.getRuntime();
				
				double memoryUsage = (double)runtime_max.totalMemory()/1024 - (double)runtime_free.freeMemory()/1024;
				System.out.println("Memory usage: " + memoryUsage);
			}
	    }	finally {
	    		queueInput.close();
	    }
	   }
	 }
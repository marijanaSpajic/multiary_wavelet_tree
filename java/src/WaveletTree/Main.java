package WaveletTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

	public static void main(String[] args) throws IOException{
		String queue;
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
				WaveletTree.WaveletTrees(everything, number);
			}
	    }	finally {
	    		queueInput.close();
	    }
	   }
	 }
		
		/*System.out.print("Enter the queue: ");
			queue = queueInput.readLine();
		
		if(queue.length() == 0) {
			System.out.println("Error!");
		}*/

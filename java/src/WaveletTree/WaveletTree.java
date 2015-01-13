package WaveletTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class WaveletTree {
	
	public static void WaveletTrees(String queue, int number) throws IOException{
		int i, j, k;
		String temp;
		String asciiQueue = "";
		String intoBase[]= new String[number*queue.length()];
		String level[] = new String[queue.length()];
		ArrayList listOfLevels = new ArrayList();
		int numberOfLevels;
		
		for(i = 0; i < queue.length(); i++) {
			intoBase[i] = Integer.toString((int)queue.charAt(i), number);
			asciiQueue += intoBase[i];
		}
		
		System.out.println(asciiQueue);
		
		numberOfLevels = (int)(7/(Math.log(number)/Math.log(2)));
		System.out.println(numberOfLevels);
		
		for(j = 0, k = 0; j < numberOfLevels; j++, k++) {
			temp = "";
			while(k < queue.length()*number) {
				 temp += Character.toString(asciiQueue.charAt(k));
				 level[j] = temp;
				k += number;
			}
			k -= queue.length()*number;
			listOfLevels.add(j, level[j]);
			System.out.println("level" + j + ":" + level[j]);
		}
		
		String queueFunction;
		int paramNumber;
		String paramChar;
		
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Enter function rank or select:");
		queueFunction = input.readLine();
		while(!queueFunction.equals("rank") && !queueFunction.equals("select")) {
			System.out.print("Error! Enter again:");
			queueFunction = input.readLine();
		}
		if(queueFunction.equals("rank")) {
			//koliko se puta znak pojavljuje u nizu
			BufferedReader inputNumber = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter number");
			paramNumber = Integer.parseInt(inputNumber.readLine());
			
			BufferedReader inputChar = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter character");
			paramChar = inputChar.readLine();
			
			Rank.Rank(paramNumber, Integer.toString((int)paramChar.charAt(0), number), listOfLevels);
		}
		else if(queueFunction.equals("select")){
			BufferedReader inputNumber = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("oujeeea");
		}
	}
}

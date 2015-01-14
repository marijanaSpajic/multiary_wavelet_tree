package WaveletTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class WaveletTree {
	
	public static void WaveletTrees(String queue, int number) throws IOException{
		
		int i, j, k;
		String temp;
		String asciiQueue = "";
		String intoBase[]= new String[number*queue.length()];
		String level[] = new String[queue.length()];
		String newlevel[] = new String[queue.length()];
		ArrayList listOfLevels = new ArrayList();
		queue = queue.replace("\r\n", "").replace("\n", "");
		int numberOfLevels;
		
		for(i = 0; i < queue.length(); i++) {
			intoBase[i] = Integer.toString((int)queue.charAt(i), number);
			asciiQueue += intoBase[i];
		}
		
		System.out.println(asciiQueue);
		
		numberOfLevels = (int)(7/(Math.log(number)/Math.log(2)));
		System.out.println(numberOfLevels);
		
		for(j = 0, k = 0; j <= numberOfLevels; j++, k++) {
			temp = "";
			while(k < queue.length()*number) {
				 temp += Character.toString(asciiQueue.charAt(k));
				 level[j] = temp;
				k += number;
			}
			k -= queue.length()*number;
			listOfLevels.add(j, level[j]);
			System.out.println("level" + j + ":" + level[j]);
			Algorithm(queue, level[0], number);
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
			System.out.println(listOfLevels);
			
			Rank.RankFunction(paramNumber, Integer.toString((int)paramChar.charAt(0), number), queue, number);	
		}
		else if(queueFunction.equals("select")){
			BufferedReader inputNumber = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("oujeeea");
		}
	}
	
	public static void Algorithm(String niz, String root, int number) {
		HashMap<Integer, Node> map = new HashMap<Integer, Node>();
		int i, j, k;
		String letter = "";
		
		String asciiQueue = "";
		String intoBase[]= new String[number*niz.length()];
		int z,v;
		String level0 = ""; 
		Node node;
		Node rootNode = new Node(null, root , 0);
		Node temporaryNode = rootNode;
		for(z = 0; z < niz.length(); z++) {
			intoBase[z] = Integer.toString((int)niz.charAt(z), number);
			asciiQueue += intoBase[z];
			System.out.println(intoBase[z]);
			for(v = 1; v < number; v++) {
				if(v == 1) {
					node = new Node(rootNode.parent, Character.toString(intoBase[z].charAt(v)), Integer.parseInt(Character.toString(intoBase[z].charAt(0))));
					temporaryNode = node;
				}
				else {
					node = new Node(temporaryNode.parent, Character.toString(intoBase[z].charAt(v)), Integer.parseInt(Character.toString(intoBase[z].charAt(0))));
				}
					
			}	
	}
}

	public static String ReturnLevel(String queue, int number, int m) {
		int i, j, k;
		String temp = "";
		String asciiQueue = "";
		String intoBase[]= new String[number*queue.length()];
		String level[] = new String[queue.length()];
		int numberOfLevels;
		String listOfLevels = "";
		
		for(i = 0; i < queue.length(); i++) {
			intoBase[i] = Integer.toString((int)queue.charAt(i), number);
			asciiQueue += intoBase[i];
		}
		
		while(m < queue.length()*number) {
			listOfLevels += Character.toString(asciiQueue.charAt(m));
			m += number;
		}
		System.out.println(listOfLevels);
		return listOfLevels;
	}
	
	public static void Root(String rootNode, String queue, int number) {
		String asciiQueue = "";
		String intoBase[]= new String[number*queue.length()];
		int i;
		
		System.out.println(rootNode);
		for(i = 0; i < queue.length(); i++) {
			intoBase[i] = Integer.toString((int)queue.charAt(i), number);
			asciiQueue += intoBase[i];
		}
	}
}

package WaveletTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class WaveletTree {
	
	public static void WaveletTrees(String queue, int number) throws IOException{
		
		int i, j;
		String temp = "";
		String asciiQueue = "";
		String intoBase[]= new String[number*queue.length()];
		String level[] = new String[queue.length()];
		queue = queue.replace("\r\n", "").replace("\n", "");
		int numberOfLevels;
		
		//conver into acsii
		for(i = 0; i < queue.length(); i++) {
			intoBase[i] = Integer.toString((int)queue.charAt(i), number);
			asciiQueue += intoBase[i];
		}
		
		//number of levels
		numberOfLevels = (int)(7/(Math.log(number)/Math.log(2)));
		
		//root node
		int k = 0;
		while(k < queue.length()*number) {
			 temp += Character.toString(asciiQueue.charAt(k));
			 level[0] = temp;
			k += number;
		}
		
		System.out.println("level0: " + level[0]);
		Node node = Algorithm(queue, level[0], number);
		
		
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
			//System.out.println(listOfLevels);
			
			Rank.RankFunction(paramNumber, Integer.toString((int)paramChar.charAt(0), number), queue, number);	
		}
		else if(queueFunction.equals("select")){
			BufferedReader inputNumber = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("oujeeea");
		}
	}
	
	public static Node Algorithm(String queue, String root, int number) {
		String intoBase[]= new String[number*queue.length()];
		int z,v;
		Node rootNode = new Node(null, root , 0);
		
		Node nodeLevel1;
		Node nodeLeveln;
		
		for(int i = 0; i < number; i++) {
			Node child = new Node(null, null, -1);
			rootNode.addChild(child, i);
		}
		
		for(z = 0; z < queue.length(); z++) {
			intoBase[z] = Integer.toString((int)queue.charAt(z), number);
			Node temporaryNode = rootNode;
			for(v = 1; v < number; v++) {
				if(v == 1) {
					//create child if doesn't exist
					if(rootNode.children.get(Integer.parseInt(Character.toString(rootNode.value.charAt(z)))).index == -1) {
						nodeLevel1 = new Node(rootNode.value, Character.toString(intoBase[z].charAt(v)), Integer.parseInt(Character.toString(intoBase[z].charAt(0))));
						//create childNode
						for(int i = 0; i < number; i++) {
							Node children = new Node(nodeLevel1.value, null, -1);
							rootNode.addChild(children, i);
							nodeLevel1.addChild(children, i);
						}
						temporaryNode = nodeLevel1;
						rootNode.addChild(nodeLevel1, Integer.parseInt(Character.toString(rootNode.value.charAt(z))));
						
					}
					
					//child already exist
					else {
						temporaryNode = rootNode.children.get(Integer.parseInt(Character.toString(intoBase[z].charAt(0))));
						rootNode.children.get(Integer.parseInt(Character.toString(rootNode.value.charAt(z)))).value += Character.toString(intoBase[z].charAt(v));
						
					}		
				}
				else {
					//create child if doesn't exist
					if(temporaryNode.children.get(Integer.parseInt(temporaryNode.value)).index == -1) {
						nodeLeveln = new Node(temporaryNode.value, Character.toString(intoBase[z].charAt(v)), Integer.parseInt(temporaryNode.value));
						//create childNode
						if(v != number-1) {
							for(int i = 0; i < number; i++) {
								Node children = new Node(nodeLeveln.value, null, -1);
								nodeLeveln.addChild(children, i);
								temporaryNode.addChild(nodeLeveln, i);
							}
						}
						temporaryNode.addChild(nodeLeveln, Integer.parseInt(temporaryNode.value));
						temporaryNode = nodeLeveln;
					}
					//child already exist
					else {
						temporaryNode.children.get(Integer.parseInt(Character.toString(temporaryNode.value.charAt(temporaryNode.value.length())))).value += Character.toString(intoBase[z].charAt(v));
						temporaryNode = temporaryNode.children.get(Integer.parseInt(Character.toString(temporaryNode.value.charAt(temporaryNode.value.length()))));
					}
				}
			}
		}
		return rootNode;
	}

	public static String ReturnLevel(String queue, int number, int m) {
		int i;
		String asciiQueue = "";
		String intoBase[]= new String[number*queue.length()];
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

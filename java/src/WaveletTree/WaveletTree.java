package WaveletTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class WaveletTree {
	
	public static void WaveletTrees(String queue, int number) throws IOException{
		
		int i, j;
		int intoBase[]= new int[number*queue.length()];
		int number_layers;
		
		queue = queue.replace("\r\n", "").replace("\n", "");
		
		//number of levels
		number_layers = (int)(7/(Math.log(number)/Math.log(2)));
		
		
		Node node = CreateTree(queue, number);
		
		
		String queueFunction;
		int paramNumber;
		String paramChar;
		boolean temp = true;
		
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Enter function rank or select:");
		queueFunction = input.readLine();
		while(temp) {
			if(queueFunction.equals("rank")) {
		
				BufferedReader inputNumber = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("Enter number");
				paramNumber = Integer.parseInt(inputNumber.readLine());
				
				BufferedReader inputChar = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("Enter character");
				paramChar = inputChar.readLine();
				
				
				//Rank.Rank_fuction(node, paramNumber, number_layers, intoBase);	
			}
			
			else if(queueFunction.equals("select")){
				
				BufferedReader inputNumber = new BufferedReader(new InputStreamReader(System.in));
		
				System.out.print("Enter number");
				paramNumber = Integer.parseInt(inputNumber.readLine());
				
				BufferedReader inputChar = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("Enter character");
				paramChar = inputChar.readLine();
				
				//Select.Select_function(node, paramNumber, num_layers, number, length)
			}
			
			else if(queueFunction.equals("exit")) {
				temp = false;
				break;
			}
			
			else {
				System.out.print("Error! Enter again:");
			}
		}
	}
	
	
	public static Node CreateTree(String queue, int number) {		
		int i, j, k;
		String intoBase[] = new String[number*queue.length()];
		
		Node rootNode = new Node(null, 0);
		
		Node nodeLevel1;
		Node nodeLeveln;
		
		for(i = 0; i < number; i++) {
			Node child = new Node(rootNode, -1);
			rootNode.addChild(child, i);
		}
		
		for(j = 0; j < queue.length(); j++) {
			intoBase[j] = Integer.toString((int)(queue.charAt(j)), number);
			Node temporaryNode = rootNode;
			for(k = 0; k < number; k++) {
				if(k == 0) {
					rootNode.value[j] += Integer.parseInt(Character.toString(intoBase[j].charAt(k)));
					rootNode.numberOfValue = rootNode.numberOfValue + 1;
				}
				
				else {
					Node node = temporaryNode.children.get(temporaryNode.value);
					
					if((node.value[0]) == -1) {
						node.value[0] += Integer.parseInt(Character.toString(intoBase[j].charAt(k)));
						node.numberOfValue = 1;
						
						for(i = 0; i < number; i++) {
							Node children = new Node(rootNode, -1);
							node.addChild(children, i);
						}
						
						temporaryNode = node;
					}
					else {
						int size = node.numberOfValue + 1;
						node.value[size-1] += Integer.parseInt(Character.toString(intoBase[j].charAt(k)));
						node.numberOfValue = node.numberOfValue;
						temporaryNode = node;
					}
				}
			}
		}
		return rootNode;
	}
					//create child if doesn't exist
					/*if(rootNode.children.get(Integer.parseInt(Character.toString(rootNode.value.charAt(z)))).index == -1) {
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
			}*/
}

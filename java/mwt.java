import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;


public class mwt {
	
	public static HashMap<Character,ArrayList<Integer>> alphabet = new HashMap<Character,ArrayList<Integer>>();
	
	public static ArrayList<Integer> CalculateIntoBase(int ascii_character, int cardinality, int number_layer) {
		ArrayList<Integer> number_in_list = new ArrayList<Integer>();
		int div, mod;
		
		div = ascii_character/cardinality;
		mod = ascii_character%cardinality;
		number_in_list.add(mod);
		
			while(div > 0) {
				mod = div%cardinality;
				div = div/cardinality;
				number_in_list.add(mod);
				
			}
			//reverse array
			Collections.reverse(number_in_list);
			
			//fill lists with 0s
			while(number_in_list.size() != number_layer) {
				number_in_list.add(0, 0);
			}
		
		return number_in_list;
	}
	
	//rank(position, symbol)
	public static int Rank(Root root, int position, char character) {
		ArrayList<Integer> char_into_base = new ArrayList<Integer>();
		
		int digit, rank = 0;
		Root rootNode = root;
		
		char_into_base.addAll(alphabet.get(character));
		
		digit = char_into_base.remove(0);
		rank = Rank_layer(rootNode, position, digit);
		Node node = rootNode.children.get(digit);
		position = rank;
		
		while(char_into_base.size() > 0) {
			digit = char_into_base.remove(0);
			rank = Rank_layer(node, position, digit);
			if(char_into_base.size() == 0) {
				break;
			}
			
			node = node.children.get(digit);
			position = rank;
		}
		char_into_base.clear();
		return rank;
	}
	
	//for each layer returns the number of symbol in data
	public static int Rank_layer(Root node, int position, int digit) {
		int rank = 0, i;
		ArrayList<Integer> data = node.getData();
		
		for(i = 0; i < position; i++) {
			if(data.get(i) == digit) {
				rank++;
			}
		}
		
		return rank;
	}
	
	//select(rank, symbol)
	public static int Select (Root root, int num_repeat, char character) {
		ArrayList<Integer> char_into_base = new ArrayList<Integer>();
		int i, digit, select = 0;
		
		Root rootNode = root;
		Node node;
		boolean temp = false;
		
		char_into_base.addAll(alphabet.get(character));
		
		digit = char_into_base.get(0);
		node = rootNode.children.get(digit);
		
		for(i = 0; i < char_into_base.size()-2; i++) {
			digit = char_into_base.get(i+1);
			node = node.children.get(digit);
		}
		
		Collections.reverse(char_into_base);
		
		while(char_into_base.size() > 0) {
			digit = char_into_base.remove(0);
			//nodes
			if(!temp) {
				select = Select_layer(node, num_repeat, digit);
			}
			//root nodes
			else{
				select = Select_root(root, num_repeat, digit);
			}
			
			if(select == -1) {
				System.out.println("Error!");
				break;
			}
			
			if(char_into_base.size() == 0) {
				break;
			}
			
			if(node.parent == null) {
				root = node.parent_root;
				temp = true;	
			}
			else {
				node = node.parent;
			}
			
			num_repeat = select + 1;
			
		}
		char_into_base.clear();
		return select;
	}
	
	public static int Select_layer(Node node, int num_repeat, int digit) {
		int select = 0, i;
		ArrayList<Integer> data = node.getData();
		
		if(num_repeat > data.size()) {
			return -1;
		}
		
		for(i = 0; i < data.size(); i++) {
			if(data.get(i) == digit) {
				select++;
			
			if (select == num_repeat) {
				return i;
			}
		}
	}
		return -1;
}
	//when node is root
	public static int Select_root(Root root, int num_repeat, int digit) {
		int select = 0, i;
		ArrayList<Integer> data = root.getData();
		
		for(i = 0; i < data.size(); i++) {
			if(data.get(i) == digit) {
				select++;
			
			if (select == num_repeat) {
				return i;
			}
		}
	}
		return -1;
}
	
	public static void main(String[] args) throws IOException {
		char i;
		int j;
		
		if(args.length != 2) {
			System.err.println("Invalid command line, exactly two argument required!");
			System.exit(1);
		}
		
		int cardinality = Integer.parseInt(args[1]);
		String filename = args[0];
		File file = new File(filename);
		
		try {
			
			//time to executed method
			long startTime = System.currentTimeMillis();
			
			//open file
			Scanner fileScanner = new Scanner(file);
			String queue = fileScanner.nextLine();
			
			ArrayList<Integer> number_base = new ArrayList<Integer>();
			
			//number of layers
			int number_layer = (int)Math.ceil((7/(Math.log(cardinality)/Math.log(2))));
			Root root = new Root();
			
			queue.trim();
			
			//read character and if doesn't exist save in alphabet
			for(j = 0; j < queue.length(); j++) {
				i = queue.charAt(j);
				if(!alphabet.containsKey(i)) {
					//calculate base of a character
					number_base = CalculateIntoBase((int)i, cardinality, number_layer);
					alphabet.put(i, number_base);
				}
				
				root.Add_signs(alphabet.get(i));
			}
			System.out.println("Creating tree finished!");
			
			
			long endTime = System.currentTimeMillis();
			System.out.println("Time: " + (endTime - startTime) + " milliseconds");
			
			Runtime runtime_max = Runtime.getRuntime();
		 	Runtime runtime_free = Runtime.getRuntime();
			
		 	double memoryUsage = (double)runtime_max.totalMemory()/1024 - (double)runtime_free.freeMemory()/1024;
			System.out.println("Memory usage: " + memoryUsage + " KB");
			
			int position, _rank;
			char symbol;
			boolean temp = true;
			BufferedReader input_position, input_symbol;
			long startTimeCommand, endTimeCommand;
			Runtime runtime_max_command, runtime_free_command;
			double memoryUsage_command;
			
			while(temp) {
				BufferedReader functionInput = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("Enter command <rank/select/exit>: ");
				String function = functionInput.readLine();
				if(function.equals("rank")) {
					
					 input_position = new BufferedReader(new InputStreamReader(System.in));
					 System.out.print("Enter position: ");
					 position = Integer.parseInt(input_position.readLine());
					 
					 input_symbol = new BufferedReader(new InputStreamReader(System.in));
					 System.out.print("Enter symbol: ");
					 symbol = input_symbol.readLine().charAt(0);
					
					 startTimeCommand = System.currentTimeMillis();
					 
					 int rank = Rank(root, position, symbol);
					 System.out.println("Rank = " + rank);
					 
					 endTimeCommand = System.currentTimeMillis();
					 System.out.println("Time: " + (endTimeCommand - startTimeCommand) + " milliseconds");
						
					 runtime_max_command = Runtime.getRuntime();
					 runtime_free_command = Runtime.getRuntime();
						
					 memoryUsage_command = (double)runtime_max_command.totalMemory()/1024 - (double)runtime_free_command.freeMemory()/1024;
					 System.out.println("Memory usage: " + memoryUsage_command + " KB");		
			}
				if(function.equals("select")) {
					
					 input_position = new BufferedReader(new InputStreamReader(System.in));
					 System.out.print("Enter position: ");
					 _rank = Integer.parseInt(input_position.readLine());
					 
					 input_symbol = new BufferedReader(new InputStreamReader(System.in));
					 System.out.print("Enter symbol: ");
					 symbol = input_symbol.readLine().charAt(0);
					
					 startTimeCommand = System.currentTimeMillis();
					 
					 int select = Select(root, _rank, symbol);
					 if(select == -1) {
						 System.out.println("There aren't that many symbols in input file");
					 }
					 else {
						 System.out.println(_rank + ". " + "character " + symbol + " is on position: " + select);
						 
						 endTimeCommand = System.currentTimeMillis();
						 System.out.println("Time: " + (endTimeCommand - startTimeCommand) + " milliseconds");
							
						 runtime_max_command = Runtime.getRuntime();
						 runtime_free_command = Runtime.getRuntime();
							
						 memoryUsage_command = (double)runtime_max_command.totalMemory()/1024 - (double)runtime_free_command.freeMemory()/1024;
						 System.out.println("Memory usage: " + memoryUsage_command  + " KB");
					 }
					 
			}
				else if(function.equals("exit")) {
					temp = false;
					break;
				}
		}
			fileScanner.close();
		}
		
		finally {
			
		}
	}
}
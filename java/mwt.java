import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


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
			
			while(number_in_list.size() != number_layer) {
				number_in_list.add(0, 0);
			}
		System.out.println(number_in_list);
		return number_in_list;
	}
	
	public static void main(String[] args) throws IOException {
		
		
		int cardinality;
		char i;
		int j;
		
		/*BufferedReader queueInput = new BufferedReader(new FileReader("/home/marijana/bio/multiary_wavelet_tree/generate_inputs/inputs/input_1"));
		String queue = queueInput.readLine();
		BufferedReader numberInput = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Unesi:" );*/
		String queue = "CGTCCTGGATCTTTATTAGA";
		cardinality = 4;
		ArrayList number_base = new ArrayList();
		
		int number_layer = (int)Math.ceil((7/(Math.log(cardinality)/Math.log(2))));
		
		Root root = new Root();
		
		try {
			queue.trim();
			for(j = 0; j < queue.length(); j++) {
				i = queue.charAt(j);
				if(!alphabet.containsKey(i)) {
					number_base = CalculateIntoBase((int)i, cardinality, number_layer);
					alphabet.put(i, number_base);
				}
				//System.out.println(alphabet.get(i));
				root.Add_signs(alphabet.get(i));
			}

			System.out.println(root.data);
			int rank = Rank(root, 4, 'C');
			System.out.println(rank);
			
		}
		
		finally {
			//queueInput.close();	
		}
	}
	
	public static int Rank(Root root, int position, char character) {
		ArrayList<Integer> char_into_base = alphabet.get(character);
		Root rootNode = root;
		int digit, rank = 0;
		//System.out.println(alphabet);
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
		
		return rank;
	}
	
	public static int Rank_layer(Root node, int position, int digit) {
		int rank = 0, i;
		ArrayList<Integer> data = node.getData();
		//System.out.println(data);
		for(i = 0; i < position; i++) {
			if(data.get(i) == digit) {
				rank++;
			}
		}
		
		return rank;
		
	}
}
	
	/*public static int Select (Root root, int num_repeat, String character) {
		Root rootNode = root;
		ArrayList<Integer> char_into_base = alphabet.get(character);
		int i, digit, select = 0;
		
		for(i = char_into_base.size(); i < 0; i--) {
			Node node = rootNode.children.get(i);
		}
		
		Collections.reverse(char_into_base);
		while(char_into_base.size() > 0) {
			digit = char_into_base.remove(0);
			select = Select_layer(node,);
		}
		return select;
	}
	
	public static int Select_layer(Node node, int num_repeat, ) {
		int select = 0;
		
		return select;
	}
}*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


public class mwt {
	
	public static ArrayList CalculateIntoBase(int ascii_character, int cardinality, int number_layer) {
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
		
		HashMap<Character,ArrayList> alphabet = new HashMap<Character,ArrayList>();
		int cardinality;
		char i;
		
		BufferedReader queueInput = new BufferedReader(new FileReader("/home/marijana/bio/multiary_wavelet_tree/generated_inputs/input/input_1"));;
		BufferedReader numberInput = new BufferedReader(new InputStreamReader(System.in));
		cardinality = Integer.parseInt(numberInput.readLine());
		
		int number_layer = (int)Math.ceil((7/(Math.log(cardinality)/Math.log(2))));
		
		Root root = new Root();
		
		try {
			queueInput.toString().trim();
			while((i = (char)queueInput.read()) != -1) {
				if(!alphabet.containsKey(i)) {
					ArrayList number_base = CalculateIntoBase((int)i, cardinality, number_layer);
					alphabet.put(i, number_base);
				}
				
				root.Add_signs(alphabet.get(i));
			}
				
		}
		
		finally {
			queueInput.close();
		}
	}
}

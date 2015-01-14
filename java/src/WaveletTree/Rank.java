package WaveletTree;

import java.util.ArrayList;
import java.util.Objects;

public class Rank {
	
	public static void RankFunction(int number, String character, String queue, int cardinality) {
		queue = queue.replace("\r\n", "").replace("\n", "");
		 int length = queue.length()*cardinality;
		int i = 0, nextNumber = 0;
		int firstNumber = number;
		String a = "";
		
		while(i < length) {
			a = WaveletTree.ReturnLevel(queue, cardinality, i);
			System.out.println(firstNumber + "," + character.charAt(i) + "," + a);
			nextNumber = Recursion(firstNumber, character.charAt(i), a);
			firstNumber = nextNumber;
			i++;
		}
		System.out.println("Rank(" + number + "," + character + ") = " + nextNumber);
	}
	
	public static int Recursion(int number, char character, String level) {	
		int i, count = 0;
		
		for(i = 0; i < number; i++) {
			if(character == level.charAt(i)) {
				count++;
			}
		}
		return count;
	}
}
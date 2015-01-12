package WaveletTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		String queue;
		int number;
		
		BufferedReader queueInput = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Enter the queue: ");
		queue = queueInput.readLine();
		
		if(queue.length() == 0) {
			System.out.println("Error!");
		}
		else {
			BufferedReader numberInput = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.print("Enter the number: ");
			number = Integer.parseInt(numberInput.readLine());
			
			if(number < 2 || number > queue.length()) {
				System.out.println("Error!");
			}
		}
	}
}

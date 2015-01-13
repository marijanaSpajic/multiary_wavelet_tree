package WaveletTree;

public class WaveletTree {
	
	public static void WaveletTrees(String queue, int number) {
		int i, j, k;
		String temp;
		String asciiQueue = "";
		String intoBase[]= new String[number*queue.length()];
		String level[] = new String[queue.length()];
		
		for(i = 0; i < queue.length(); i++) {
			intoBase[i] = Integer.toString((int)queue.charAt(i), number);
			asciiQueue += intoBase[i];
		}
		
		System.out.println(asciiQueue);
		
		for(j = 0, k = 0; j < number; j++, k++) {
			temp = "";
			while(k < queue.length()*number) {
				 temp += Character.toString(asciiQueue.charAt(k));
				 level[j] = temp;
				k += number;
			}
			k -= queue.length()*number;
			System.out.println("level" + j + ":" + level[j]);
		}
	}
}

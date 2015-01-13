package WaveletTree;

import java.util.Arrays;

public class WaveletTree {
	
	public static void WaveletTrees(String queue, int number) {
		int i, j, k, count = 0;
		String asciiQueue[] = new String[queue.length()*number];
		String asciiSortQueue[]= new String[number*queue.length()];
		String sortQueue = sortString(queue);
		int ascii[] = new int[sortQueue.length()];
		for(i = 0; i < sortQueue.length(); i++) {
			ascii[i] += (int)sortQueue.charAt(i);
			System.out.println(ascii[i]);
			count++;	
		}
		for(j = 0; j < count; j++) {
			asciiSortQueue[j] = Integer.toString(ascii[j], number);
			System.out.println(asciiSortQueue[j]);
		}
		
		for(k = 0; k < queue.length(); k++) {
			//rijesit, vraca null
			asciiQueue[k] += Integer.toString((int)queue.charAt(k), number);
			System.out.println(Integer.toString((int)queue.charAt(k), number));
		}
	}
	
	public static String sortString(String queue) {
        int i;
        String newString = "";
        char chars[] = queue.toCharArray();
        Arrays.sort(chars);
        String sortString = new String(chars);
         
        for(i = 0; i < queue.length(); i++) {
            if (i == 0 || sortString.charAt(i) != sortString.charAt(i-1)) {
                newString += sortString.charAt(i);
            }
        }
        return newString;
    }
	
}

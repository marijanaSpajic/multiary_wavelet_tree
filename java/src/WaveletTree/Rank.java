package WaveletTree;

public class Rank {
	
	public static void Rank_fuction(Node rootNode, int position, int num_layers, int[] character) {
		int i, digit;
		int rank = 0;
		Node node = rootNode;
		
		for(i = 0; i < num_layers; i++) {
			digit = character[i];
			rank = RankLayer(node, position, digit);
			position = rank;
			node = node.children.get(digit);
		}
		
	}
	
	public static int RankLayer(Node rootNode, int position, int digit) {
		int i = 0;
		int rank = 0;
		
		for (i = 0; i < position; i++) {
			if(rootNode.value[0] == digit) {
				rank++;
			}
		}
		return rank;
	}
}
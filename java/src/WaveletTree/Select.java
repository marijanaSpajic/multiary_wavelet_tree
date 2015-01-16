package WaveletTree;

public class Select {
	
	public static int Select_function(Node rootNode, int num_repeat, int num_layers, int[] number, int length)	{
		int i, j, digit, select = 0;
		Node node = rootNode;
		
		for(i = 1; i < num_layers; i++) {
			digit = number[i-1];
			node = node.children.get(digit);
		}
		
		for(j = num_layers; j > 0; i--) {
			select = SelectLayer(node, num_repeat, number[i-1], length);
			num_repeat = select + 1;
			node = node.parent;
		}
		
		return select;
	}
	
	public static int SelectLayer(Node rootNode, int num_repeat, int digit, int length) {
		int i, select = 0;
		Node node = rootNode;
		
		for(i = 0; i < length; i++) {
			if (node.value[i] == digit) {
				select++;
				if (select == num_repeat) {
					return i;
				}
			}
		}
		
		
		return select;
	}
}

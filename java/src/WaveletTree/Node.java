package WaveletTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Node {
	
	HashMap<Integer, Node> children = new HashMap<Integer,Node>();
	int[] value;
	Node parent;
	int index;
	int numberOfValue;
	
	public Node (Node parent, int index){
		
		this.children = new HashMap<Integer,Node>();
		this.value = value;
		this.parent = parent;
		this.index = index;
		this.numberOfValue = 0;
	}
	
	public void addChild(Node child, int index) {
		children.put(index, child);
	}
	
}

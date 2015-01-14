package WaveletTree;

import java.util.ArrayList;
import java.util.List;

public class Node {
	
	List<Node> children = null;
	String value;
	Node parent = null;
	int index;
	
	public Node (Node parent, String value, int index){
		
		this.children = new ArrayList<>();
		this.value = value;
		this.parent = parent;
		this.index = index;
	}
	
	public void addChild(Node child) {
		children.add(child);
	}
}

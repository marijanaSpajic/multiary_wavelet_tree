package WaveletTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Node {
	
	HashMap<Integer, Node> children = new HashMap<Integer,Node>();
	String value;
	String parent;
	int index;
	
	public Node (String parent, String value, int index){
		
		this.children = new HashMap<Integer,Node>();
		this.value = value;
		this.parent = parent;
		this.index = index;
	}
	
	public void addChild(Node child, int index) {
		children.put(index, child);
	}
	
}

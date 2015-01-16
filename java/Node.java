import java.util.ArrayList;
import java.util.Collections;


public class Node extends Root{
	Node parent;
	Root parent_root;
	
	public Node(Node parent) {
		this.parent = parent;
	}
	
	public Node(Root parent_root) {
		this.parent_root = parent_root;
	}
	
	public void setData(ArrayList<Integer> list_signs) {
		//Collections.copy(this.list_signs, list_signs);
		this.list_signs.addAll(list_signs);
		this.index = this.list_signs.remove(0);
		this.data.add(this.index);
		
		if(this.list_signs.size() > 0) {
			if(!this.children.containsKey(this.index)) {
				this.CreateChild(this.index);
			}
			this.children.get(this.index).setData(this.list_signs);
		}
		this.list_signs.clear();
		
	}
}

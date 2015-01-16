import java.util.ArrayList;


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
		this.index = list_signs.remove(0);
		this.data.add(this.index);
		
		if(list_signs.size() > 0) {
			if(!this.children.containsKey(this.index)) {
				this.CreateChild(this.index);
			}
			this.children.get(this.index).setData(list_signs);
		}
		
	}
}

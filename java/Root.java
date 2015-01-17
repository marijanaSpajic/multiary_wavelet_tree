import java.util.ArrayList;
import java.util.HashMap;


public class Root {
	HashMap<Integer, Node> children = new HashMap<Integer,Node>();
	ArrayList<Integer> data = new ArrayList<Integer>();
	ArrayList<Integer> list_signs = new ArrayList<Integer>();
	int index;
	
	//add signs into a node
	public void Add_signs(ArrayList<Integer> list_signs) {
		this.list_signs.addAll(list_signs);
		this.index = this.list_signs.remove(0);
		this.data.add(this.index);
		
		//if children doesn't exist create child
		if (!children.containsKey(this.index)) {
			this.CreateChild(this.index);
		}
		//save the data in node child
		this.children.get(this.index).setData(this.list_signs);
		this.list_signs.clear();
	}
	
	public void CreateChild(int index) {
		//for root
		if(this.toString().startsWith("Root")) {
			this.children.put(index, new Node(this));
		}
		//for other nodes
		else if(this.toString().startsWith("Node")) {
			this.children.put(index, new Node((Node) this));
		}
	}
	
	public ArrayList<Integer> getData() {
		return this.data;
	}
}
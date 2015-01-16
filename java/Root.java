import java.util.ArrayList;
import java.util.HashMap;


public class Root {
	HashMap<Integer, Node> children = new HashMap<Integer,Node>();
	ArrayList data = new ArrayList();
	int index = 0;
	//ArrayList list_signs = new ArrayList();
	
	public void Add_signs(ArrayList<Integer> list_signs) {
		this.index = list_signs.remove(0);
		this.data.add(this.index);
		
		if (!children.containsKey(this.index)) {
			this.CreateChild(this.index);
		}
		this.children.get(this.index).setData(list_signs);
	}
	
	public void CreateChild(int index) {
		this.children.put(index, new Node(this));
	}
	
	public ArrayList getData() {
		return this.data;
	}
}



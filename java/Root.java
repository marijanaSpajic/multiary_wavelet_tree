import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


public class Root {
	HashMap<Integer, Node> children = new HashMap<Integer,Node>();
	ArrayList data = new ArrayList();
	int index;
	ArrayList<Integer> list_signs = new ArrayList<Integer>();
	
	public void Add_signs(ArrayList<Integer> list_signs) {
		//Collections.copy(this.list_signs, list_signs);
		this.list_signs.addAll(list_signs);
		//System.out.println(this.list_signs);
		this.index = this.list_signs.remove(0);
		//System.out.println(this.index);
		this.data.add(this.index);
		System.out.println(this.data);
			
		if (!children.containsKey(this.index)) {
			this.CreateChild(this.index);
		}
		this.children.get(this.index).setData(this.list_signs);
		this.list_signs.clear();
	}
	
	public void CreateChild(int index) {
		this.children.put(index, new Node(this));
	}
	
	public ArrayList getData() {
		return this.data;
	}
}



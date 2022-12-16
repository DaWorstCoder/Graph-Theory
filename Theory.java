import java.util.*;

/**Used for testing. If you really wanted to you could input your own nodes here.**/
class Theory {
	static ArrayList<Node> nodeList = new ArrayList<>();;

	static void theory() {
		nodeList.add(new Node(75, 200, 0));
		nodeList.add(new Node(225, 100, 1));
		nodeList.add(new Node(225, 300, 2));
		nodeList.add(new Node(375, 200, 3));

		addNode(0, 1);
		addNode(0, 2);
		addNode(2, 1);
		addNode(2, 3);
		addNode(2, 0);
		addNode(3, 1);
		addNode(3, 2);
		addNode(0, 3);
		addNode(3, 0);
	}
	
	void printGraph() {
		
	}


	static void addNode(int pos, int pos2) {
		nodeList.get(pos).addNode(nodeList.get(pos2));
	}
}
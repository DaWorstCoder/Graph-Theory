import java.util.*;
class Node {
	/**The x coordinate of the node**/
	int x;
	/**The y coordinate of the node**/
	int y;
	/**The number associated with the node**/
	int num;
	/**List of all node connections**/
	ArrayList<Node> connections = new ArrayList<>(); 

	Node(int x, int y, int num) {
		this.x = x;
		this.y = y;
		this.num = num;
	}

	/**Adds a new node to the connections list**/
	void addNode(Node newNode) {
		connections.add(newNode);
	}
}
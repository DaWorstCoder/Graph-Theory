import java.awt.*;
import javax.swing.*;
import java.util.*; 
/**This is the most important class as all the main Maze functions are in here**/
class Maze {
	/**Determines the size of each block in the maze**/
	static double size = Graph.blockSize; 

	/**Uncomment this and comment the other maze if you want to create you own custom Maze**/
	static int[][] customMaze = {
	{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},	
	{2, 1, 1, 0, 1, 1, 0, 1, 1, 0, 2},
	{2, 1, 1, 1, 1, 0, 1, 1, 1, 1, 2},
	{2, 1, 0, 1, 1, 1, 1, 1, 0, 1, 2},
	{2, 1, 1, 1, 1, 1, 0, 1, 1, 1, 2},
	{2, 1, 0, 1, 0, 1, 1, 1, 1, 0, 2},
	{2, 1, 1, 0, 1, 1, 1, 1, 1, 1, 2},
	{2, 1, 1, 1, 1, 0, 1, 1, 0, 1, 2},
	{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
	};

	/**Creates of maze for this class based on the maze created by the Imaging class**/
	static int[][] imageMaze = Imaging.fixedImportArray();

	/**The main maze used throughout the class. Set equal to either imageMaze or customMaze based on which maze you want to run**/
	static int[][] maze = imageMaze;

	/**Turns the nodeMazeCreator into a nodeMaze that is used throughout the class**/
	static ArrayList<Node> bigNodeMaze;

	/**Take a guess**/
	static void runMaze() {
		bigNodeMaze = nodeMazeCreator(maze);
		solveMaze();
	}

	/**Creates a list of all the nodes**/
	static ArrayList<Node> nodeMazeCreator(int[][] maze) {
		ArrayList<Node> nodeMaze = new ArrayList<>();
		/**Used to create the node.num**/
		int num = 0;

		//This loop creates a new node in the nodeMaze for every position in the regular maze that has a 1 in it
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				if (maze[i][j] == 1) {
					Node newNode = new Node((int) (size * j + size/2), (int) (size * i + size/2), num);
					nodeMaze.add(newNode);
					num++;	
				}
			}
		}

		//All of this code is to check for connections
		for(Node newNode : nodeMaze) {
			int i = (int) ((newNode.y - size/2)/size);
			int j = (int) ((newNode.x - size/2)/size);

			//If there is a node above this node, it cycles through all the nodes in nodeMaze until it finds the node that has the same x and y coords as the one above it. It then adds the node to its connections list
			if(maze[i - 1][j] == 1) {
				boolean flag = false;
				int index = 0;
				while (!flag) {
					if(nodeMaze.get(index).x == (int) (size * (j) + size/2) && nodeMaze.get(index).y == (int) ((i - 1) * size + size/2)) {
						newNode.addNode(nodeMaze.get(index));
						flag = true;
					}
					index++;
				}
			}

			//If there is a node below this node, it cycles through all the nodes in nodeMaze until it finds the node that has the same x and y coords as the one below it. It then adds the node to its connections list
			if(maze[i + 1][j] == 1) {
				boolean flag = false;
				int index = 0;
				while (!flag) {
					if(nodeMaze.get(index).x == (int) (size * (j) + size/2) && nodeMaze.get(index).y == (int) ((i + 1) * size + size/2)) {
						newNode.addNode(nodeMaze.get(index));
						flag = true;
					}
					index++;
				}
			}

			//If there is a node left of this node, it cycles through all the nodes in nodeMaze until it finds the node that has the same x and y coords as the one left of it. It then adds the node to its connections list
			if(maze[i][j - 1] == 1) {
				boolean flag = false;
				int index = 0;
				while (!flag) {
					if(nodeMaze.get(index).x == (int) (size * (j - 1) + size/2) && nodeMaze.get(index).y == (int) ((i) * size + size/2)) {
						newNode.addNode(nodeMaze.get(index));
						flag = true;
					}
					index++;
				}
			}

			//If there is a node right of this node, it cycles through all the nodes in nodeMaze until it finds the node that has the same x and y coords as the one right of it. It then adds the node to its connections list
			if(maze[i][j + 1] == 1) {
				boolean flag = false;
				int index = 0;
				while (!flag) {
					if(nodeMaze.get(index).x == (int) (size * (j + 1) + size/2) && nodeMaze.get(index).y == (int) ((i) * size + size/2)) {
						newNode.addNode(nodeMaze.get(index));
						flag = true;
					}
					index++;
				}
			}
				
		}

		return nodeMaze;
	}

	/**Used to find the solution to the maze**/
	static void solveMaze() {
		Node startNode = findNode(true);
		Node endNode = findNode(false);

		//?????
		for (int i = 0; i < bigNodeMaze.size(); i++) {
			bigDickArray.add(null);
		}

		theLongWay(startNode, startNode, endNode, pathFindArray);
	}

	/**Looks for the start and endNodes. Set to true if looking for startNode, false if looking for endNode**/
	static Node findNode(boolean start) {
		for (Node nodes : bigNodeMaze) {
			int i = (int) ((nodes.y - size/2)/size);
			int j = (int) ((nodes.x - size/2)/size);

			if (start) {
				if (i == 2 && j == 1) {
					return nodes;
				}
			} else {
				if (i == maze.length - 3 && j == maze[0].length - 2) {
					return nodes;
				}
			}
		}
		System.out.println("shit.");
		return null;
	}

	// peanut butter and jelly the loooong way (the looong way) the looooong way

	static ArrayList<Node> pathFindArray = new ArrayList<>();
	/**List of all the nodes actually in the solve.**/
	static ArrayList<Node> bigDickArray = new ArrayList<>();

	//Arguably the most important method in the whole program. A recursive algorithm that solves the maze. Credit goes to Emmanuel for helping figure this part out but most Neel for writing and figuring out the final product.
	static void theLongWay(Node node, Node startNode, Node endNode, ArrayList<Node> previousArray) {
		if (node.equals(endNode)) {
			System.out.println("found the end");
			previousArray.add(node);

			if (previousArray.size() < bigDickArray.size()) {
				bigDickArray.clear();
				for (Node finalNode : previousArray) {
					bigDickArray.add(finalNode);
					System.out.println(finalNode.num);
				}
			}
			return;
		}
		
		if (node.connections.size() == 1 && !(node.equals(startNode))) {
			previousArray.remove(node); 
			return;
		} else {
			previousArray.add(node);
		}

		for (Node subNode : node.connections) {
			if (!previousArray.contains(subNode)) {
				ArrayList<Node> nextArray = new ArrayList<>();
				for (Node oldNode : previousArray) {
					nextArray.add(oldNode);
				}
				theLongWay(subNode, startNode, endNode, nextArray);
			
			}
		}
	}
}
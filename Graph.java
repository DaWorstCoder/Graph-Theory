import java.awt.*;
import java.util.*;

/**Most of the drawing related stuff is done in this class.**/
class Graph {
	/**Determines the size of each block in the maze**/
	static double blockSize = 40; 

	/**Draws background of the maze**/
	static void drawMaze(Graphics g) {
		int[][] maze = Maze.maze;
		
		//Drawing the black paths
		for(int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				if (maze[i][j] == 1) {
					g.setColor(Color.black); //Change if you want a new color for the paths
					g.fillRect((int) (j * blockSize), (int) (i * blockSize), (int) blockSize, (int) blockSize);
				}
			}
		}

		//Determines color of the start maze 
		g.setColor(new Color(0, 255, 0, 50));
		g.fillRect((int) (blockSize), (int) (2 * blockSize), (int) blockSize, (int) blockSize);

		//Determines color of your end maze
		g.setColor(new Color(255, 0, 0, 50));
		g.fillRect((int) ((maze.length - 2) * blockSize), (int) ((maze[0].length - 3) * blockSize), (int) blockSize, (int) blockSize);
	}	
	
	/**Draws nodes and the connections of the nodes**/
	static void drawGraphs(ArrayList<Node> nodeList, Graphics g) {
		for (Node node: nodeList){
			for (Node subNode: node.connections) {
				drawConnection(node, subNode, g);
			}
		}
		
		for (Node node: nodeList) {
			if (Maze.bigDickArray.contains(node)) { 
				drawNode(node, true, g);
			} else {
				drawNode(node, false, g);
			}
			
		}
	}

	/**Draws nodes based on node.x and node.y**/
	static void drawNode(Node node, boolean highlight, Graphics g) {
		double size = .4; //Determines the size of the drawn node relative to the regular size

		// Creates the highlight by creating a larger oval behind the node
		if (highlight) {
			g.setColor(new Color(238, 138,238));
			g.fillOval((int) (node.x - Math.round(size * (38 + 3))), (int) (node.y - Math.round(size * (38 + 3))), (int) Math.round(size * 82), (int) Math.round(size * 82)); // 38 -> half reg size | +3 -> center | 82 -> highlight size
		}

		// Creates the circle
		g.setColor(new Color(12, 101, 149));
		g.fillOval((int) (node.x - Math.round(size * (38))), (int) (node.y - Math.round(size * (38))), (int) Math.round(size * 76), (int) Math.round(size * 76)); // 38 -> center of circle| 76 -> circle size

		// Creates the numbers
		g.setFont(new Font("TimesRoman", Font.PLAIN, (int) Math.round(size * 50)));
		g.setColor(new Color(206, 211, 222));

		String sNum = Integer.toString(node.num);
		if (sNum.length() == 1) {
			g.drawString(sNum, (int) (node.x - Math.round(size * (38 - 23))), (int) (node.y - Math.round(size * (38 - 55)))); // 38 -> center of circle | 23 -> x center for string w/ 1 | 55 -> y center for string
		} else {
			g.drawString(sNum, (int) (node.x - Math.round(size * (38 - 6))), (int) (node.y - Math.round(size * ( 38 - 55)))); //38 -> center of circle | 6 -> x center for string w/ 2 | 55 -> y center for string
		}
		
	}

	/**Draws connections**/
	static void drawConnection(Node node1, Node node2, Graphics g) {
		g.setColor(Color.white);
		g.drawLine(node1.x, node1.y, node2.x, node2.y);
	}
	
}
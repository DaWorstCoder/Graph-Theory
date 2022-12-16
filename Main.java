import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

class Main extends JPanel implements ActionListener {

	static final int height = 2000;
	static final int width = 2000;
	Timer timer;
	//static ArrayList<Node> mainNodeMaze = new ArrayList<>();

	/**All basic boilerplate code for Graphics**/
	Main() {
		JFrame Frame = new JFrame("Graph Theory");
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setResizable(true);
        Frame.pack();
        Frame.setVisible(true);
		Frame.setSize(width, height);
		Frame.add(this);
		
		this.setPreferredSize(new Dimension(width - 1 , height - 1 + 100));
        this.setBackground(new Color(33, 33, 33));
        this.setFocusable(true);

		timer = new Timer(20, this);
		//timer.start();

    }

	/**Anything in here gets painted**/
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
		
		Graph.drawMaze(g);
        Graph.drawGraphs(Maze.bigNodeMaze, g);
    }

	/**Anything in here happens over and over again**/
	public void actionPerformed(ActionEvent e) {
		repaint();
	}


	public static void main(String[] args) {
		Maze.runMaze();
		new Main();
		

		// WORKING AT 1386
	}
}
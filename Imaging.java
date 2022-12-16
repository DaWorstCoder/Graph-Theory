import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;

/**Does all the image processing. Takes the maze image and converts it into an array that the program can interpret**/
public class Imaging {
	
	/**Converts the importedMazeArray into the interpetable array. Because thr program requires 2s on the edges to negate edge cases, all this really does is add 2s on th edges of the importedMazeArray**/
	static int[][] fixedImportArray() {
		int[][] importMazeArray = importedMazeArray("maze (3).png");
		int[][] fixedImportedArray = new int[importMazeArray.length + 2][importMazeArray[0].length + 2];

		for (int row = 0; row < fixedImportedArray.length; row++) {
			for (int col = 0; col < fixedImportedArray[0].length; col++) {
				if (edgePiece(fixedImportedArray, row, col)) {
					fixedImportedArray[row][col] = 2;
				} else {
					fixedImportedArray[row][col] = importMazeArray[row - 1][col - 1];
				}
				
			}
		}
		return fixedImportedArray;
	}

	/**This is the crucial part of the image processing. It takes in an image and depending on where the black and white pixels are, it creates a new array with black being 0 (wall), and white being 1 (path)**/
	static int[][] importedMazeArray(String fileName) {
		BufferedImage image = null;
		try {
			File file = new File(fileName);
			image = ImageIO.read(file);
		}
		catch (IOException e) {
			System.out.println(e);
		}

		int width = image.getWidth();
		int height = image.getHeight();

		int[][] pixelArray = new int[height][width];

		for (int row = 0; row < width; row++) {
			for (int col = 0; col < height; col++) {
				int pixel = image.getRGB(col, row);

				//Creating a Color object from pixel value
            	Color pixelColor = new Color(pixel, true);
				
				if (pixelColor.equals(Color.black)) {
					pixelArray[row][col] = 0;
				} else {
					pixelArray[row][col] = 1;
				}
			}
		}

		return pixelArray;
	}

	/**Quick boolean method to determine whether or not a piece is and edge piece or not**/
	static boolean edgePiece(int[][] fixedImportedArray, int row, int col) {
		if (row == 0 || row == fixedImportedArray.length - 1) {
			return true;
		} else if (col == 0 || col == fixedImportedArray[0].length - 1) {
			return true;
		} 
		return false;
	}

	
	/**Used for testing**/
	static void test() {
		BufferedImage image = null;
		try {
			File imageFile = new File("maze.png");
			image = ImageIO.read(imageFile);
		}
		catch (IOException e) {
			System.out.println(e);
		}
	
		int pixel = image.getRGB(0, 0);
		System.out.println(pixel);
	}
}

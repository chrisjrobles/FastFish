import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;



/*
 * Class Name: Stars2
 * Team: Graphics
 * Primary Author: Chad
 * 
 * Description:
 * This class creates the stars that populate across the sky
 * The method, setGraphic() USES RECURSION to create the number of stars
 * set in the constructor of Stars2
 * The fields that are arrays are initialized in the constructor to be the 
 * size of the number of stars.
 * There is another ArrayList that holds the color objects for each star
 * The challenge was creating individual characteristics for each star that
 * were consistent across each frame refresh. This consistent randomness was
 * created with GameUtility.randomSeed() which would output a semi-random (random enough)
 * number between -1 and 1 that was used for various modulations throughout this class
 * Some examples of consistently randomized characteristics would be the positions, 
 * size, and color of each star 
 * 
 */
public class Stars2 {

	public Graphics2D starGraphic;
	public int numberOfStars;
	public int[] xPos;
	public int[] yPos;
	public double[] seedArray;
	public ArrayList<Color> colors = new ArrayList<Color>();
	public int[] size;
	
	/*
	 * Constructor: Stars2
	 * 
	 * Takes in the number of stars to render
	 * Initializes object property arrays (xPos, yPos, color, seedArrary)
	 * seedArray is the array that holds the random number associated with each
	 * 	star so that the randomSeed() method does not run numberOfStars*(use of random seed)
	 * 	times per second
	 */
	public Stars2(int numberOfStars) {
		this.numberOfStars = numberOfStars;
		colors.ensureCapacity(numberOfStars);
		size = new int[numberOfStars];
		xPos = new int[numberOfStars];
		yPos = new int[numberOfStars];
		seedArray = new double[numberOfStars];
		for (int i=0; i<numberOfStars; i++) {
			setPos(i);
			seedArray[i] = GameUtility.randomSeed(i);
			size[i] = (seedArray[i] < 0.5 ? 1 : 2);
		}
		
		makeColors(numberOfStars);
	}
	
	/*
	 * Method Names: setPos, setXPos, setYPos
	 * 
	 * Work in tandem to be able to generate a completely a new position.
	 * Works with the window size to always re-populate new areas 
	 * created by resizing the window
	 */
	private void setPos(int star) {
		setXPos(star);
		setYPos(star);
	}
	
	private void setXPos(int star) {
		xPos[star] = (int) (FishMain.WINDOW_LENGTH_X*GameUtility.randomSeed(star*2));
		xPos[star] = xPos[star] < 0 ? -xPos[star] : xPos[star];
	}
	private void setYPos(int star) {
		yPos[star] = GameUtility.plusOrMinusRandom(0, FishMain.oceanSurfaceHeight);
		yPos[star] = yPos[star] < 0 ? -yPos[star] : yPos[star];
	}
	
	
	/*
	 * Method Name: makeColors(int numberOfStars)
	 * 
	 * Takes in the total number of stars and populates the colors ArrayList
	 * with the initial, reference colors per star 
	 * 
	 * Called in the constructor
	 * 
	 */
	public void makeColors(int numberOfStars) {
		int rBaseColor = 30;
		int gBaseColor = 30;
		int bBaseColor = 30;
		for (int i=0; i<numberOfStars; i++)
		{
			int timeFactor = (int)(seedArray[i]*5);
			Color color;
			if (timeFactor < 0)
				color = ColorChanger.ColorUpDown((int)(rBaseColor*seedArray[i]),
						(int)(gBaseColor*seedArray[i]), 
						(int)(bBaseColor*seedArray[i]),
						-timeFactor/2, 
						255+timeFactor, 100,255);
			else
				color = ColorChanger.ColorUpDown((int)(rBaseColor*seedArray[i]),
						(int)(gBaseColor*seedArray[i]), 
						(int)(bBaseColor*seedArray[i]),
						timeFactor/3, 
						255+timeFactor, 100,255);
			
			colors.add(color);
		}
	}
	
	/*
	 * Method Name: resetColor(int star)
	 * 
	 * This method is similar to makeColors() except that this is for an
	 * individual star. It returns the color of the "star-th" star
	 * 
	 * Called in setGraphic()
	 */
	public void resetColor(int star) {
		double seed = seedArray[star];
		int rBaseColor = (int) (100*seed);
		int gBaseColor = (int) (100*seed/2);
		int bBaseColor = (int) (100*seed*2);
		double timeFactor = (2*seed);
		Color color;
		
		if (timeFactor < 0)
			color = ColorChanger.ColorUpDown((int)(rBaseColor*seed),
					(int)(gBaseColor*seed), 
					(int)(bBaseColor*seed),
					-timeFactor, 
					255+timeFactor, 100,255);
		else
			color = ColorChanger.ColorUpDown((int)(rBaseColor*seed),
					(int)(gBaseColor*seed), 
					(int)(bBaseColor*seed),
					timeFactor/1.5, 
					255+timeFactor, 100,255);
			
			colors.set(star, color);
	}
	
	/*
	 * Method Name: makeGraphic(Graphics g)
	 * 
	 * The method that is called from paintComponent() of FishMain
	 * to render the stars on the JFrame.
	 * Calls setGraphic(), the recursive method, to actually render the stars
	 */
	public Graphics2D makeGraphic(Graphics g) {
		return setGraphic(g, numberOfStars);
	}

	/*
	 * Method Name: setGraphic(Graphics g, int star)
	 * 
	 * This method is what defines the star objects to be output into
	 * makeGraphic() and paintComponent()
	 * It has a RECURSIVE methodology, where the base case is star==0
	 * Each time setGraphic runs through, the input, star, is decremented
	 * Each star is a circle, with an individual size, position, and color
	 *  
	 */
	public Graphics2D setGraphic(Graphics g, int star) {
		
		starGraphic = (Graphics2D)g;
		
		
		if (star==0)
			return starGraphic;
		star--;
		
		//if (FishMain.gameCounter%2==0 && star%10==0)
		setXPos(star);
		
		if (FishMain.gameCounter%4==0)
			resetColor(star);
		
		starGraphic.setColor(colors.get(star));
		starGraphic.fillOval(xPos[star], yPos[star], size[star], size[star]);
		
		return setGraphic(g, star-1);	
	}
	
	
}

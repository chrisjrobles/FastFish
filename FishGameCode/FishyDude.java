import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;



/*
 * Class Name: FishyDude
 * Extends MyGraphicsObject
 * Team: Graphics
 * Primary Author: Chad
 * 
 * This class creates the shape of the fish that goes into paintComponent() of FishyMain.
 * It is essentially a bunch of points that define the points on the fish.
 * Then the methods createXArray and createYArray create the arrays that get formed into the
 * fish object.
 */

public class FishyDude extends MyGraphicsObject {	
	
	//Defines the hard points of the fish polygon:
	private int[] xShape = {0,10,40, 50, 52, 84, 98, 110, 122, 110, 125, 110, 95, 50, 51, 37, 13, 0};
	private int[] yShape = {0,7, 13, 30, 12,  8,  1,  18,  22, -5,  -33, -28,-10,-22,-35, -23,-15, 0};
	
	public int fishLength=125;
	public int fishHeight=60;
	
	public int[] xArray = new int[xShape.length];
	public int[] yArray = new int[xShape.length];
	public int arrayLength=xShape.length;
	
	public int[] xBounds = new int[2];
	public int[] yBounds = new int[2];
	
	
	public boolean movingRight;
	public boolean onAHook = false;
	
	public int previousPosition = xPos;
	
	Color fishEye = new Color(255,255,255);
	Color fishPupil = new Color(0,0,0);
	

	/*
	 * Constructor: FishyDude
	 * 
	 * Initializes the x and y position of the fish
	 */
	public FishyDude(int x, int y) {
		xPos = x;
		yPos = y;
	}
	
	/*
	 * (non-Javadoc)
	 * @see MyGraphicsObject#makeGraphic(java.awt.Graphics)
	 * 
	 * As with all MyGraphicsObject's, this method creates the
	 * fish graphical object.
	 * Most of the graphics are dependent on the direction the fish is moving
	 * which is represented by the "if (movingRight)" statement
	 */
	public Graphics2D makeGraphic(Graphics g) {
		
		Graphics2D fishGraphic = (Graphics2D) g;

		GradientPaint fishColor = new GradientPaint(xPos,yPos+3,
				ColorChanger.ColorUpDown(200,0,200, 80, -30),
				xPos+fishLength,yPos+3,ColorChanger.ColorUpDown(255,17,80, 90, -20));
		
		fishColor.isCyclic();
		fishGraphic.setPaint(fishColor);
		Polygon fishPoly = new Polygon(createXArray(),createYArray(), xShape.length);
		fishGraphic.fillPolygon(fishPoly);
		
		
		fishGraphic.setColor(fishEye);
		if (movingRight)
		{
			fishGraphic.fillOval(xPos+fishLength-20, yPos-10, 10, 7); //eye
			fishGraphic.setColor(fishPupil);							//set pupil color
			fishGraphic.fillOval(xPos+fishLength-15, yPos-7, 5, 3); //pupil

		}
		else
		{
			fishGraphic.fillOval(xPos+10, yPos-10, 10, 7); //eye
			fishGraphic.setColor(fishPupil);				//set pupil color
			fishGraphic.fillOval(xPos+10, yPos-7, 5, 3); //pupil
		}

		
		return fishGraphic;
	}

	/*
	 * Method Name: setPreviousPosition(int xPos)
	 * 
	 * This method sets a field, previousPosition to be able to 
	 * perform the analysis that finds whether the 
	 */
	public void setPreviousPosition(int xPos) {
		this.previousPosition = xPos;
	}
	
	
	/*
	 * Method Names: createXArray(), createYArray()
	 * 
	 * 
	 * Since the x and y arrays that form the shape of the fish are stored in
	 * xShape and yShape, the two create_Array() methods return an array that can take on the superclass
	 * name, xArray and yArrary.
	 * These are the methods that implement the movingRight variable which defines
	 * whether the fish is moving right or not. 
	 * 
	 */
	public int[] createXArray()
	{
		
		if (previousPosition != xPos) {
			if (previousPosition < xPos)
				movingRight = true;
			else
				movingRight = false;
		}
		
		setXPos(GameUtility.Wrapper(0, FishMain.WINDOW_LENGTH_X, xPos));
		
		if (!movingRight) {
			for (int i=0; i<xShape.length; i++)
				xArray[i] = xPos+xShape[i];
		}
		else if (movingRight) {
			for (int i=0; i<xShape.length; i++)
				xArray[i] = xPos+(fishLength-xShape[i]);
		}
	
		getXBounds();
		
		return xArray;
		
	}
	
	
	public int[] createYArray()
	{
		if (FishMain.ocean.getDynamic())
			setYPos(GameUtility.Clipper(FishMain.oceanSurfaceHeight, 
					FishMain.WINDOW_LENGTH_Y, yPos));
		else
			setYPos(GameUtility.Clipper(0, FishMain.WINDOW_LENGTH_Y, yPos));
		
		for (int i=0; i<xShape.length; i++)
			yArray[i] = yPos+yShape[i];
		
		
		getYBounds();
		
		return yArray;
	}
	
	@Override
	public int[] getXBounds() {
		xBounds = GameUtility.findMinAndMax(xArray);
		xBounds[0] -= 15;
		xBounds[1] += 15; 
		return xBounds;
	}
	
	@Override
	public int[] getYBounds() {
		yBounds = GameUtility.findMinAndMax(yArray);
		yBounds[0] -= 10;
		yBounds[1] += 10;
		
		return yBounds;
	}
	

}

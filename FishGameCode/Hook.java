import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import javafx.scene.paint.Paint;

/*
 * Class Name: Hook
 * Team: Graphics
 * Primary Author: Chad
 * 
 * This class contains the methods that create the Graphcis2D object
 * that is painted in paintComponent() of FishMain.
 * 
 * 
 */
public class Hook extends MyGraphicsObject {

	
	
	
	int[] xShape = {39, 50, 50, 25,  0, 20,  8, 26, 35, 39};
	int[] yShape = { 0,  0, 70, 83, 50, 45, 52, 69, 60, 0};
	
	
	public int arrayLength = xShape.length;

	public int[] xArray = new int[arrayLength];
	public int[] yArray = new int[arrayLength];
	public int xPos=300;
	public int yPos=300;
	public int xPosBoat;
	public int yPosBoat;
	public int stringLength = 15;
	public int stringModulation;
	public int[] sideLengths = new int[arrayLength+1];
	public int arrayLength2;
	public int[] xArray2 = new int[arrayLength2];
	public int[] yArray2 = new int[arrayLength2];
	public int[] xBounds;
	public int[] yBounds;
	public int maxStringLength;
	public int upAndDownSpeed;
	Boat boat;
	
	/*
	 * Constructor: Hook
	 * 
	 * Summary:
	 * This constructor takes in the boat associated with this hook.
	 * This pointing allows the hook/string position to be connected with the boat
	 */
	public Hook(Boat boat) {
		this.boat = boat;
		xPosBoat = boat.getXPosition();
		yPosBoat = boat.getYPosition();
		maxStringLength = GameUtility.plusOrMinusRandom(500, 2*FishMain.getWindowY());
		upAndDownSpeed = GameUtility.Clipper(2, 5, (int) (5*Math.random()));
	}
	
	
	/*
	 * Method Name: setStringGraphic
	 * 
	 * This method creates the graphics string that connects the boat to the hook
	 */
	public void setStringGraphic(Graphics2D hookObject) {
		GradientPaint stringColor = new GradientPaint(xPosBoat, yPosBoat, boat.boatColor1,
				xPosBoat, yPosBoat+stringModulation, boat.boatColor2);
		hookObject.setPaint(stringColor);
		
		if (Boat.movingRight) {
			xPosBoat = boat.getXPosition()+boat.boatLength;
			yPosBoat = boat.getYPosition();
		}
		else {
			xPosBoat = boat.getXPosition();
			yPosBoat = boat.getYPosition();
		}
		hookObject.fillRect(xPosBoat, yPosBoat, 2, stringModulation);	
	}
	
	/*
	 * Method Name: setHookGraphic
	 * 
	 * This method creates the graphics for the hook
	 */
	public void setHookGraphic(Graphics2D hookObject) {
		GradientPaint hookColor = boat.boatColor;
		
		hookObject.setPaint(hookColor);

		Polygon hookPolygon = new Polygon(createXArray(), createYArray(), arrayLength);
		hookObject.fillPolygon(hookPolygon);
	}
	
	
	/*
	 * Method Name: makeGraphic()
	 * 
	 * This method creates the hook and string graphic elements
	 * They connect with the boat object referenced in the object's constructor
	 * This allows the hook to follow along at the edge of the boat.
	 * The method contains two components, the hook and the string.
	 * setStringGraphic(hookObject) and setHookGraphic(hookObject) are the two methods
	 * that create the string and hook graphic	
	 */
	public Graphics2D makeGraphic(Graphics g) {
		stringModulation = stringLength + GameUtility.upAndDown(upAndDownSpeed, maxStringLength);
		findSides(xArray, yArray);

		Graphics2D hookObject = (Graphics2D) g;
		setStringGraphic(hookObject);
		setHookGraphic(hookObject);		
				
		return hookObject;
		
		
	}
	
	public int[] findSides(int[] xArray, int[] yArray) {
		
		int arraySizeHolder=0;
		for (int i=1; i<arrayLength; i++)
		{
			int xLength = Math.abs(xShape[i-1] - xShape[i]);
			int yLength = Math.abs(yShape[i-1] - yShape[i]);
			sideLengths[i] = xLength>yLength ? xLength : yLength;
			arraySizeHolder+=sideLengths[i];
		}
		
		this.arrayLength2=arraySizeHolder;
		return sideLengths;
	}
	
	
	/*
	 * Method Names: createXArray(), createYArray()
	 * 
	 * These methods define the x and y arrays of the hook only
	 * The x and y Shape arrays are what define the corner points,
	 * Converting to x and y Array is what positions the shape on the screen 
	 */
	public int[] createXArray() {
		
		
		for (int i=0; i<arrayLength; i++)
			xArray[i] = xPosBoat - 42 + xShape[i];
		
		return xArray;
	}
	
	
	public int[] createYArray() {
		
		
		for (int i=0; i<arrayLength; i++)
			yArray[i] = yPosBoat + stringModulation-10 + yShape[i];
		
		return yArray;
	}
	

	
	public int[] getXBounds() {
		xBounds = GameUtility.findMinAndMax(xArray);
		return xBounds;
	}
	
	
	public int[] getYBounds() {
		yBounds = GameUtility.findMinAndMax(yArray);
		return yBounds;
	}	
	
	
}

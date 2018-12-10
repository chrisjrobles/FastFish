import java.awt.Graphics;
import java.awt.Graphics2D;


//This superclass only describes/creates Polygon Objects
/*
 * Class Name: MyGraphicsObject
 * Team: Graphics
 * Primary Author: Chad
 * 
 * This the abstract super class that sets a general template for the graphics objects
 * such as the fish, shield, and hook. Each of these objects has an x and y position, and an x and
 * y array. These can be accessed with the following getter and setter methods.
 * 
 * 
 */
public abstract class MyGraphicsObject  {

	
	public int xPos;
	public int yPos;
	public int size;
	public int arrayLength;
	public int[] xArray;
	public int[] yArray;
	public int[] xBounds;
	public int[] yBounds;
	public boolean dynamic = true;
	
	
	public  Graphics2D makeGraphic(Graphics g) {
		return null;
	}
	
	//Returns the object's xPosition
	public int getXPos() {
		return this.xPos;
	}
	
	//Returns the object's yPosition
	public int getYPos() {
		return this.yPos;
	}
	
	//Returns the xArray() of the object (where applicable)
	public int[] getXArray() {
		return this.xArray;
	}
	//Returns the yArray() of the object (where applicable)
	public int[] getYArray() {
		return this.yArray;
	}
	//Sets the xPosition
	public void  setXPos(int xPos) {
		this.xPos = xPos;
	}
	//Sets the yPosition
	public void setYPos(int yPos) {
		this.yPos = yPos;
	}
	
	public int getArrayLength() {
		return this.arrayLength;
	}
	
	
	/*
	 * Method Name: getXBounds(), getYBounds()
	 * Team: Graphics
	 * Primary Author: Chad
	 * 
	 * This is a more recent approach to determining when the fish and hook have collided.
	 * It basically generates an array of length 2 that has the x or y lower bound and
	 * x or y upper bound. These two boundary lines combined will allow us to determine
	 * if the fish or hook are in the same rectangle with a >= or <= logic. 
	 * 
	 */
	public int[] getXBounds() {
		xBounds = GameUtility.findMinAndMax(xArray);
		return xBounds;
	}
	
	
	public int[] getYBounds() {
		yBounds = GameUtility.findMinAndMax(yArray);
		return yBounds;
	}
	
	public void setDynamic(boolean bool) {
		this.dynamic = bool;
	}
	
	public boolean getDynamic() {
		return this.dynamic;
	}
	
	public void behavior() {}
	
}

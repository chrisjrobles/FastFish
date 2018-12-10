import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

//import javafx.scene.paint.Color;
import java.awt.Color;


/*
 * Class Name: Boat
 * Extends MyGraphicsObject
 * Team: Graphics
 * Primary Authors: Chad
 * 
 * This class contains all the methods that create the shape, color, and Graphics2D object
 * that becomes the boat.
 * It can be (and is in FishMain) instantiated so that the position of the boat can be
 * changed from other classes with the getter and setter methods in MyGraphicsObject.
 * 
 * Fields:
 * boatCorner#: these fields were very useful in indexing through the arrays that create
 * the boat. The corners start at the top-right, then go to top-left, then bottom-left,
 * then bottom-right.
 * 
 * 
 * 
 * 
 */


public class Boat extends MyGraphicsObject {

	public int boatHeight=160;//FishMain.oceanSurfaceHeight;
	public int boatLength=400;
	
	public int xPos=FishMain.WINDOW_LENGTH_X/2;
	public int yPos=FishMain.oceanSurfaceHeight-boatHeight/2;
	private int boatCorner1=0;
	private int boatCorner2=boatLength;
	private int boatCorner3=boatCorner2+boatHeight;
	private int boatCorner4=boatCorner3+boatLength;
	public int rand=1;
	public static int xMover;
	
	private  int vectorLength=boatCorner1+boatCorner2+boatCorner3+boatCorner4+1;
	private  int[] xVec = new int[vectorLength];
	private  int[] yVec = new int[vectorLength];
	public int innerY;
	
	public static boolean movingRight = false;
	
	
	
	//Initialize boatColors
	Color boatColor1 = ColorChanger.ColorUpDown(139,69,19, 80, -30);
	Color boatColor2 = ColorChanger.ColorUpDown(68,0,0, 90, -20);
	GradientPaint boatColor = new GradientPaint(0,yPos,
			boatColor1,	xPos+boatLength,yPos+boatHeight, boatColor2);
	
	//Boat CONSTRUCTOR allows a boat object to be created with an initial position and direction.
	public Boat(double queue, boolean movingRight) {
		
		this.yPos = 40 + (int) (11*Math.random());
		
		if (movingRight) 
			this.xPos = (int) (-queue*boatLength);
		else 
			this.xPos = (int) (FishMain.WINDOW_LENGTH_X+queue*boatLength);
		
		setMovingRight(movingRight);
		rand =  -(int) (8*Math.random());
		rand = (rand==-8) ? 0:rand;
		
		if (-rand<=2) { //brown
			boatColor1 = ColorChanger.ColorUpDown(139,69,19, 80, -30);
			boatColor2 = ColorChanger.ColorUpDown(68,0,0, 90, -20);
		}
		else if (-rand<5) { //greenish
			boatColor1 = ColorChanger.ColorUpDown(194, 194, 184, 80, -30);
			boatColor2 = ColorChanger.ColorUpDown(46, 46, 31, 90, -20);
		}
		else if (-rand<6) { //blueish
			boatColor1 = ColorChanger.ColorUpDown(209, 224, 224, 80, -30);
			boatColor2 = ColorChanger.ColorUpDown(0, 26, 26, 90, -20);
		}
		else {
			boatColor1 = ColorChanger.ColorUpDown(240, 245, 100, 80, -30);
			boatColor2 = ColorChanger.ColorUpDown(0, 0, 26, 90, -20);
		}	
		
	}
	
	
	
	
	//----------------------------------------------------------------------------//

	//--------------------------Method Section Start------------------------------//
	
	public void boatRiseUp(int speed) {
		this.setYPos(this.getYPos()-speed);
	}
	
	public static boolean getMovingRight() {
		return movingRight;
	}
	public static void setMovingRight(boolean direction) {
		movingRight = direction;
		xMover = GameUtility.Clipper(4, 7, GameUtility.plusOrMinusRandom(4, 8));//(int) (8*Math.random()));
		
		if (!movingRight)
			xMover*=-1;
		
	}
	

	public void setXPos(int xPos) {
		this.xPos=xPos;
	}
	//--------------------------Method Break------------------------------//

	public void setYPos(int yPos) {
		this.yPos=yPos;
	}
	
	public int getXPosition() {
		return this.xPos;
	}
	public int getYPosition() {
		return this.innerY;
	}
	
	
	//--------------------------Method Break------------------------------//
	Graphics g;
	Graphics2D boatGraphic = (Graphics2D)g;
	
	public static void setDirection() {
		if ((FishMain.gameTimer.getElapsedTimeInSeconds("int") - FishMain.neutralStartTime) < (FishMain.fishyGame.endStage1 
				- FishMain.fishyGame.neutralStartTime)/2.0)
			setMovingRight(true);
		else
			setMovingRight(false);
	}
	
	public Graphics2D makeGraphic(Graphics g) {
		//Controls the boat's direction (Alternates right-->left every 15 seconds)
		
		boatGraphic = (Graphics2D)g;
		
		Polygon boatPolygon = new Polygon(createXArray(),createYArray(),vectorLength);
		boatColor = new GradientPaint(0,yPos,
				boatColor1,	xPos+boatLength,yPos+boatHeight, boatColor2);
	
		
		boatGraphic.setPaint(boatColor);
		boatGraphic.fillPolygon(boatPolygon);
		
		return boatGraphic;
	}
	
	//--------------------------Method Break------------------------------//
	
	//This method creates the vector for the x-coordinates
	protected int[] createXArray()
	{

		xPos = xPos + xMover;
		
		if (!movingRight)
		{
		
			//Top of boat
			for (int i=boatCorner1; i<boatCorner2; i++)
				xVec[i]=xPos+i;
			//Back side of boat
			for (int i=boatCorner2; i<boatCorner3; i++)
				xVec[i]=xPos+boatLength;
			//Hull of boat
			for (int i=boatCorner3; i<=boatCorner4; i++)
				xVec[i]=xPos+boatLength-(i-boatCorner3);
		}
		else if (movingRight)
		{
			//Top of boat
			for (int i=0; i<=boatCorner2; i++)
				xVec[i]=xPos+boatLength-i;
			//Back side of boat
			for (int i=boatCorner2; i<boatCorner3; i++)
				xVec[i]=xPos;
			//Hull of boat
			for (int i=boatCorner3; i<=boatCorner4; i++)
				xVec[i]=xPos+(i-boatCorner3);
		}
		

		//It somehow had points all over the place that created glitchy artifacts...
		//this removes them
		for (int i=0; i<vectorLength; i++)
			xVec[i]=GameUtility.Clipper(xPos, xPos+boatLength, xVec[i]);	
		
		return xVec;
	//End createXArray() method
	}
	
	//--------------------------Method Break------------------------------//

	
	//This method creates the vector for the y-coordinates
	protected int[] createYArray()
	{
		innerY=yPos+GameUtility.sineWave(10+GameUtility.upAndDown(20, 10), 0.5, 50+3.14/(8+rand), 0, 0)
				+ FishMain.oceanSurfaceHeight - 130; //phase;
		double j;
		
		
		if (!movingRight)
		{
			for (int i=boatCorner1; i<boatCorner2; i++)
				yVec[i] = innerY;
			
			//back side of boat
			for (int i=boatCorner2; i<boatCorner3; i++)
				yVec[i]=innerY+(i-boatCorner2);
			
			
			//Hull
			for (int i=boatCorner3; i<=boatCorner4; i++)
			{
				j=(i-boatCorner3);
				yVec[ i]=innerY+ (int) (((double) boatHeight)
						*Math.sqrt(1-Math.pow((j)/((double) boatLength), 2)));
				//j=(i-boatCorner3+1);
			}
		}
		else if (movingRight)
		{
			for (int i=boatCorner1; i<boatCorner2; i++)
				yVec[i] = innerY;
			
			//back side of boat
			for (int i=boatCorner2; i<boatCorner3; i++)
				yVec[i]=innerY+(i-boatCorner2);
			
			
			//Hull
			for (int i=boatCorner3; i<=boatCorner4; i++)
			{
				j=(i-boatCorner3);
				yVec[ i]=innerY+ (int) (((double) boatHeight)
						*Math.sqrt(1-Math.pow((j)/((double) boatLength), 2)));
				//j=(i-boatCorner3+1);
			}
		}	
		
		
		//It somehow had points all over the place that created glitchy artifacts...
		//this removes them
		for (int i=0; i<vectorLength; i++) 
			yVec[i]=GameUtility.Clipper(innerY, innerY+boatHeight, yVec[i]);
			

		return yVec;
	//End createYArray() method
	}
	
	
	
//======================================End of Boat Class===============================//
}

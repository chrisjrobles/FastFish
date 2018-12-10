import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;


/* Class Name: Bubbles
 * Team: Graphics
 * Primary Author: Chad
 * 
 * xPos = the x position of the bubble object
 * yPos = the y position of the bubbles
 * directionSwitcher is either -1 or 1 and it's value switches at a randomized moment
 * 		it's purpose is to allow the bubbles to independently switch directions
 * size = sets the size of the object. Initialized to 10, but the constructor with one int parameter
 * 		sets the size
 * speed = the speed that the bubble object will move up the screen
 * rand = an array of 6 random numbers that are added to the color values of the bubble to give each
 * 		bubble its own slightly different color
 * 
 * In all cases, the x and y position refers to the center of the bubble
 * 
 */
public class Bubbles extends MyGraphicsObject {
	
	
	//DATA FIELDS
	public  int xPos=(int) (FishMain.WINDOW_LENGTH_X*Math.random());
	public  int yPos=(int) (FishMain.WINDOW_LENGTH_Y*Math.random());
	public  int directionSwitcher=1;
	public  int size=10;
	public  int speed=12-size;
	public Color topColorBubble;
	public Color bottomColorBubble;
	public static boolean dynamic = true;
	public static int oceanRiseFactor = 0;
	public static int colorFactor = 0;
	public double sizeChanger;
	public boolean sizeIsIncreasing;
	
	
	private int[] rand = new int[6];
	
	

	//BUBBLE CONSTRUCTORS
	public Bubbles() {
		xPos=(int) (FishMain.WINDOW_LENGTH_X*Math.random());
		yPos=(int) (FishMain.WINDOW_LENGTH_Y*Math.random());
		speedFromSize();
		setRand();
		this.topColorBubble = ColorChanger.ColorUpDown(60,205+rand[1],240+rand[2],2+rand[0],-15,10,-40,8,14);
		this.bottomColorBubble = ColorChanger.ColorUpDown(0,50+rand[4],51+rand[5],20,-10+rand[3]);
	}
	
	public Bubbles(int size) {
		this.size=(int) ((double)size*Math.random());
		if (this.size<5)
			this.size=5;
		speedFromSize();
		setRand();
		this.topColorBubble = ColorChanger.ColorUpDown(60,205+rand[1],240+rand[2],2+rand[0],-15,10,-40,8,14);
		this.bottomColorBubble = ColorChanger.ColorUpDown(0,50+rand[4],51+rand[5],20,-10+rand[3]);

	}
	
	//--------------------------Method Section Start------------------------------//

	
	//Void method to make the speed of the bubble dependent on the size
	public void speedFromSize() {
		if (size==5)
			speed+=3;
		else if (size>10)
			speed=2;
		else if (speed>15)
			speed=3;
	}
	
	//--------------------------Method Break------------------------------//
	/*
	 * Method Header: setXYPos
	 * Team: Graphics
	 * Primary Authors: Chad
	 * 
	 * This method allows you to set the xPos and yPos of a single bubble object
	 */
	public void setXYPos(int xPos, int yPos) {
		this.xPos=xPos;
		this.yPos=yPos;
	}
	
	

	
	//--------------------------Method Break------------------------------//
	/*
	 * Method Header: setSpeed
	 * Team: Graphics
	 * Primary Authors: Chad
	 * 
	 * This method allows you to set the speed of a single bubble object
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	/*
	 * Method Names: setTopColor(), setBottomColor()
	 * 
	 * The setter methods set the color that gets displayed by the bubble
	 */
	public void setTopColor(int r, int g, int b) {
		topColorBubble = ColorChanger.ColorUpDown(r+colorFactor, g+rand[1]+colorFactor, b+rand[2]+colorFactor,
										2+rand[0],-15,10,-40,8,14);
	}
	
	public void setBottomColor(int r, int g, int b) {
		bottomColorBubble = ColorChanger.ColorUpDown(r+colorFactor, g+rand[4]+colorFactor, b+rand[5]+colorFactor,
												20,-10+rand[3]);
	}
	
	/*
	 * Method Name: setSizeChanger()
	 * 
	 * The purpose of this method is to slowly alter the size of each bubble so that as they
	 * travel across the screen, they change size in addition to the position changes
	 */
	public void setSizeChanger() {
		if (size > 13)
			sizeIsIncreasing = false;
		else if (size < 2)
			sizeIsIncreasing = true;
		
		if (sizeIsIncreasing)
			sizeChanger+= 0.1;
		else {
			sizeChanger-=0.1;
		}
			
	}

	//--------------------------Method Break------------------------------//
	/*
	 * Method Header: setRand
	 * Team: Graphics
	 * Primary Authors: Chad
	 * 
	 * This method allows creates an int array of 6 random numbers between 1 and 10
	 */
	public void setRand() {
		for (int i=0; i<6; i++) 
			rand[i] = (int) (10*Math.random());
	}
	
	//--------------------------Method Break------------------------------//
	/*
	 * Method Header: singleBubble(Graphics g)
	 * Team: Graphics
	 * Primary Authors: Chad
	 * 
	 * This is the method that gets called in FishMain class in the paintComponent() method to create a 
	 * Graphics2D object of a bubble. This method returns a Graphics2D object.
	 * 	 
	 */
	public Graphics2D makeGraphic(Graphics g) 
	{
		Graphics2D oneBubble = (Graphics2D) g;
		
		setSizeChanger();
		setTopColor(60,205,240);
		setBottomColor(0,51,50);
		
		

		
		//Create Current Color object as a GradientPaint
		GradientPaint bubbleColor = new GradientPaint(xPos, yPos+size, topColorBubble,
				xPos, yPos-2*size, bottomColorBubble);
		//Set color of the graphic object to the current color, bubbleColor
		oneBubble.setPaint(bubbleColor);
		//Create an object that fills with the current color
		this.xPos = wanderingX(10);
		this.yPos = wanderingY(10);
		oneBubble.fillOval(xPos, yPos, size, size);
		
		
		setTopColor(60,215,255);
		setBottomColor(0,61,80);
		bubbleColor = new GradientPaint(xPos, yPos+size, topColorBubble,
				xPos, yPos-2*size, bottomColorBubble);
		oneBubble.setPaint(bubbleColor);
		int innerBubble = GameUtility.lowLimiter(1, size-2);
		oneBubble.fillOval(xPos, yPos, innerBubble, innerBubble);
		
		
		
		return oneBubble;
	}
	
	
	
	/*
	 * Method Header: wanderingX
	 * Team: Graphics
	 * Primary Authors: Chad
	 * 
	 * This method creates the "wandering" like pattern of the bubbles's x-coordinates as they travel up the screen
	 * The first line sets the size from its speed,
	 * The first if statement is an arbitrary, periodic condition that gets run every so often
	 * The inside of this if statement increments the bubble's xPos by a random factor of its speed
	 * and in a randomly switching direction. The direction switches based on directionSwitcher in the if
	 * statement below. 
	 * Then, if the bubble exceeds the limits of the window, it gets reassigned to a random spot in the ocean
	 * A high value of speed will make the changes slower
	 * A low value of speed will make the changes faster
	 */
	public int wanderingX(int speed)
	{
		speedFromSize();
			if (Math.random()*10<5)
					{
						xPos = xPos +
								GameUtility.Clipper(-3, 3, FishMain.refreshRate*directionSwitcher*((int) (Math.random()*speed)));
						if (Math.random()<0.5)
							directionSwitcher*=-1;
					}
			if (xPos<0 || xPos>FishMain.WINDOW_LENGTH_X) 
				xPos=(int) (FishMain.WINDOW_LENGTH_X*Math.random());
			return xPos;
	
	}
	
	/*
	 * Method Header: wanderingY
	 * Team: Graphics
	 * Primary Authors: Chad
	 * 
	 * This method creates the "wandering" like pattern of the bubbles's y-coordinates as they travel up the screen
	 * The first if statement makes it so the y position increases on average 4 out of 5 frames of the game
	 * The inside of this if statement increments the bubble's yPos by a random factor of its speed
	 * and in a randomly switching direction. The direction switches based on directionSwitcher in the if
	 * statement below. 
	 * Then, once the bubble reaches the top of the screen, it gets reassigned to the bottom of the screen
	 * A high value of speed will make the changes slower
	 * A low value of speed will make the changes faster
	 */
	public int wanderingY(int speed)
	{
		
		if (Math.random()*5<3)
			yPos = yPos-GameUtility.Clipper(0,2+FishMain.refreshRate, directionSwitcher*((int) (Math.random()*speed)));
		
		//This line sets it so that once the ocean surface is above the frame, the bubbles 
		//wrap around when they reach y=0
		int limit = (dynamic) ? FishMain.oceanSurfaceHeight+40 : 0;
		
		if (yPos<limit || yPos>FishMain.WINDOW_LENGTH_Y)
		{ 
			yPos=(int) (FishMain.WINDOW_LENGTH_Y);
			this.xPos+=(int) (FishMain.WINDOW_LENGTH_X*Math.random());
		}
	
		yPos -= oceanRiseFactor;
		
		return yPos;
	}
	

	
}

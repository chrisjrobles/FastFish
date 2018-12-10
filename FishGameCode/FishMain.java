import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.WindowConstants;




/*
 * Class Name: FishMain
 * Extends: JComponent
 * Implements: ActionListener & KeyEvent?
 * Team: Graphics
 * Primary Authors: Chad, Robert, Chris
 * 
 * This is the class that contains the program's main method. In the main method, 
 * a new FishMain object is created and made an input argument of a Timer object, timer1.
 * Upon starting the timer, the actionPerformed method of ActionListener runs every refreshRate
 * milliseconds. This performs the repaint() method of JComponent to refresh the paintComponent()
 * method. paintComponent() is what adds Graphics objects to the JFrame created in the main method.
 * 
 * Due to the large amount of fields, they are broken up into four categories
 * 1. Main fields (very over-reaching fields in the main method, i.e. window size, main instantiation etc)
 * 2. Graphics objects: instantiation of the graphics objects
 * 3. Graphics fields: creates the fields that deal with the graphic object's behavior (which are used mainly in the
 * 			paintComponent method...not all refer directly to a graphic object, however
 * 4. Utility fields: creates the fields that are utilized elsewhere throughout FishMain
 * 
 */


public class FishMain extends JComponent implements ActionListener, KeyListener {
	
	
	//--------------------------Category 1: Main fields------------------------------//
	private static final long serialVersionUID = 1L;
	static FishMain fishyGame = new FishMain();
	static JFrame frame = new JFrame("Avoid the Hook!");
	public static Timer timer1;
	public static int gameCounter = 0;
	public static int WINDOW_LENGTH_X=1000;
	public static int WINDOW_LENGTH_Y=800;
	//This rectangle object is what will store the dimensions of the 
	//JFrame when it is resized
	public Rectangle2D r;

	 
	//--------------------------Category 2: Graphics Objects------------------------------//
	public static int oceanSurfaceHeight = 220;//150;
	public static int refreshRate = 20;
	public static int numberOfBackgroundBubbles = 80;
	public static int numberOfStars = 500;
	public static int numberOfBigStars = 50;
	public static int numberOfBoats = 5;
	public static int numberOfTorpedos = 12;
	public static int numberOfSteamBubbles = 200;
	public int steamReleaseSize = numberOfSteamBubbles;
	public static ArrayList<Bubbles> bubbleList = new ArrayList<Bubbles>();
	public static ArrayList<Boat> boat = new ArrayList<Boat>();
	public static ArrayList<Hook> hook = new ArrayList<Hook>();
	public static ArrayList<Torpedo> torpedo = new ArrayList<Torpedo>();
	public static ArrayList<SteamBubbles> steamBubbles = 
                                        new ArrayList<SteamBubbles>();
	public static ScoreDisplay scoreDisplay = new ScoreDisplay(0);
	public static Announcement level1 = new Announcement("L E V E L  1");
	public static Announcement level2 = new Announcement("L E V E L  2");
	public static Announcement level3 = new Announcement("L E V E L  3");


	public static StartDisplay startDisplay;
	public static Shield shield = new Shield(300,200,50);
	public FishyDude fishy = new FishyDude(700, 350);
	public static GameTime gameTimer = new GameTime();
	public static GameTime stageTimer;
	public static Sun sun = new Sun();
	public static Sky sky = new Sky();
	public static Ocean ocean = new Ocean();
	public static fishFoods food = new fishFoods(125, 585);
	public static fishFoods food1 = new fishFoods(255, 315);
	public static fishFoods food2 = new fishFoods(48, 97);
	public static SteamPattern steamPattern = new SteamPattern(steamBubbles, 
                                                        gameTimer, fishyGame);
	public static Stars2 stars2 = new Stars2(800);
	public static StartScreen startScreen = new StartScreen(ocean, sun, sky, 
													bubbleList, stars2);
	public static Announcement onToLevel2 = new Announcement("On to Level 2");
	public static Murkey murkey = new Murkey(ocean, gameTimer, 300);

	 
	 
	//--------------------------Category 3: Graphics Fields------------------------------//
	public static Color topColorOcean;
	public static Color bottomColorOcean;
	public boolean shootingStar = false;
	public int randomStar;
	public int starXPosition;
	public int starYPosition;
	public int negator = -1;
	public int shootingSpeedX;
	public int shootingSpeedY;
	public int colliderCount=0;
	public int fishDirection;
	public int fishSpeed = 5;
	public boolean shieldOnFish = false; 
	public boolean regenerated = false;
	public boolean steamPresent = false;
	 //Variables that control when each game stage ends
	public int transitionLength = 5;

	 
	 public static int neutralStartTime = 5;
	 public int endStage1;
	 public int endStage2;
	 public int stageLength = 30;
	 public int startGame = 10;

	 
	 
	//--------------------------Category 4: Utility Fields------------------------------//

	 public static boolean hasCollided = false;
	 public static boolean gameOver=false;
	 public int graceTime=0;
	 public GameTime graceTimer;
	 public boolean gracePeriod = false;
	 public Collision collisionChecker;
	 public boolean fishMovingRight = false;
	 public boolean fishMovingLeft = false;
	 public boolean fishMovingUp = false;
	 public boolean fishMovingDown = false;
	 public int fishMovingVertical = 0;
	 public double scoreMultiplier = 0.005;

	//--------------------------Constructor------------------------------//
	
	/*Constructor: FishMain()
	 * 
	 * This constructor begins this methods that register key input
	 */
	 public FishMain() {
		addKeyListener(this);
	    setFocusable(true);
	    setFocusTraversalKeysEnabled(false);
	}
	
	//--------------------------Main Method------------------------------//
	/*
	 * MAIN METHOD
	 * Primary Author: Chad
	 * 
	 * Objectives:
	 * Creates the JFrame that holds the graphics objects, instantiates the main class into fishyGame
	 * Set standard parameters on the JFrame, initialize all the ArrayLists that hold the graphics objects
	 * 
	 */
	 public static void main(String[] args) {
		frame = new JFrame("Avoid the Hook!");
		fishyGame = new FishMain();
		frame.add(fishyGame);
		frame.pack();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(true);
		// Set difficulty of game by either choosing 's' for easiest, 'e' for easy, 'm' for
		// medium, or 'h' for hard.
		Difficulty.setDifficulty('s');
		
		fishyGame.r = frame.getBounds();
		
		
		gameTimer = new GameTime();
		timer1 = new Timer(refreshRate, fishyGame);
		timer1.start();
		
	
		bubbleList.ensureCapacity(numberOfBackgroundBubbles);
		boat.ensureCapacity(numberOfBoats);
		hook.ensureCapacity(numberOfBoats-1);
		torpedo.ensureCapacity(numberOfTorpedos);
		steamBubbles.ensureCapacity(numberOfSteamBubbles);
		
		for (int i=0; i<numberOfBackgroundBubbles; i++)
			bubbleList.add(i, new Bubbles(10));	
	
		
		for (int i=0; i<numberOfBoats; i++) {
			boat.add(i, new Boat((double)i+1, true));
			hook.add(i, new Hook(boat.get(i)));	
		}
		
		for (int i=0; i<numberOfTorpedos; i++)
			torpedo.add(i, new Torpedo(i+1));
		
		for (int i=0; i<numberOfSteamBubbles; i++)
			steamBubbles.add(i, new SteamBubbles(300, 5));
		
		
	}
	
	//--------------------------Method Break------------------------------//

	 /*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#getPreferredSize()
	 */
	 public Dimension getPreferredSize() {
		return new Dimension(WINDOW_LENGTH_X,WINDOW_LENGTH_Y);
	}

	
	
	//===================================================================================//
	//==============================paintComponent() Method==============================//

	/*
	 * Method Name: paintComponent()
	 * Primary Authors: Chad
	 * 
	 * While this method is inherited from JComponent, it is worth explaining what it does
	 * It layers on graphics objects on the JFrame that was created in the main method.
	 * This method is broken by time into 3 sections, each with 1 subsection.
	 * They define the 3 stages of the game (Sunlight zone, twilight zone, and midnight zone)
	 * Within each section contains a transitional period that alters the graphics and collision
	 * parameters so that the graphics transition smoothly to the other stages
	 * 
	 *  The graphics objects all use the makeGraphic(g) method defined in their respective classes
	 *  to return the graphics objects to be painted on the screen.
	 *  
	 *  
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g) {
				
		//preparing to create a start screen
		startScreen.makeGraphic(g);	
		startDisplay = new StartDisplay(gameTimer.getElapsedTimeInSeconds("int"));
		
		if (gameTimer.getElapsedTimeInSeconds("int") <= neutralStartTime) {
			startDisplay.countdown(gameTimer.getElapsedTimeInSeconds("int"));
			startDisplay.makeGraphic(g);
		}
		
		
	//++====Start Game Stage 1===++//
		if (gameTimer.getElapsedTimeInSeconds("int") > neutralStartTime && gameTimer.getElapsedTimeInSeconds("int") < endStage1)
		{
			collisionChecker.setTransitioning(false);
			
			//Create Background	
			// -----Create Sky (only stage 1)------------//
			sky.makeGraphic(g);
			stars2.makeGraphic(g);

			// -----Create Sun (only stage 1)-------------//
			
			sun.makeGraphic(g);
			if (ocean.getDynamic()) {
				for (int i=0; i<boat.size(); i++) 
					boat.get(i).makeGraphic(g);
			}
			
			// Controls the boat's direction (Alternates right-->left every few seconds; time is
			// dependent on the value of endStage1 which is decided by the difficulty method)
			Boat.setDirection();
			level1.makeGraphic(g);
		
			
			//~~~~~~~~~~~TRANSITION TO Game Stage 2~~~~~~~~~~~//
			//~~~~~ This starts 5 seconds before the end of stage 1
			//It makes the ocean surface rise, along with the boats, 
			if (gameTimer.getElapsedTimeInSeconds("int") > endStage1 - transitionLength)
			{
				collisionChecker.setTransitioning(true);
				oceanSurfaceHeight-= 4; //makes the surface rise
				
				//after the ocean surface is out the frame, it switches dynamic to false
				//This also makes the boats lift up more dramatically too
				if (oceanSurfaceHeight < -20) {
					ocean.setDynamic(false);
					Bubbles.dynamic = false;
					for (int i=0; i<numberOfBoats; i++)
						boat.get(i).boatRiseUp(9+i);
				}
				
				for (int i=0; i<numberOfBackgroundBubbles; i++) {
					bubbleList.get(i).setTopColor(102,  204,  255);
					bubbleList.get(i).setBottomColor(0, 68, 102);

				}
				//onToLevel2.makeGraphic(g);
				Bubbles.oceanRiseFactor = 5;
				if (gameTimer.getElapsedTimeInSeconds("int")%2==0)
					ocean.startFade(-1);
			//End transition to game stage 2	
			}
			
			// -----Create Ocean With parameters set from code above^^----//
			ocean.makeGraphic(g);
			
			//------Create the Hooks for the boats----//
			if (!ocean.getDynamic() && hook.get(1).getYPos() > -10000) {
				for (int i=0; i<numberOfBoats; i++) {
					hook.get(i).setYPos(boat.get(i).getYPos()-4+i);
				}
			}
			
			
			for (int i=0; i<numberOfBoats; i++)
				try {
					hook.get(i).makeGraphic(g);	
				}
			catch (Exception indexOutOfBoundsException) {
				System.out.println("Hooks out of bounds");
				continue;
			}
			
		//!!!!!End Game Stage 1!!!!!!!
		}

		
	//++====START GAME STAGE 2===++//
		if (gameTimer.getElapsedTimeInSeconds("int") >= endStage1
				&& gameTimer.getElapsedTimeInSeconds("int") < endStage2)
		{
			collisionChecker.setTransitioning(false);
			ocean.setDynamic(false);
			Bubbles.oceanRiseFactor = 0;
			Torpedo.setWrapping(true);
			
			if (Collision.hasCollision(food, fishy)) {
				scoreDisplay.nonTimeScore += 25;
				food.setYArray(FishMain.WINDOW_LENGTH_Y*2);
				food.makeGraphic(g);
				food.justCollided = true;
			} else if (Collision.hasCollision(food1, fishy)) {
				scoreDisplay.nonTimeScore += 25;
				food1.setYArray(FishMain.WINDOW_LENGTH_Y*2);
				food1.makeGraphic(g);
				food1.justCollided = true;
			} else if (Collision.hasCollision(food2, fishy)) {
				scoreDisplay.nonTimeScore += 25;
				food2.setYArray(FishMain.WINDOW_LENGTH_Y*2);
				food2.makeGraphic(g);
				food2.justCollided = true;
			}
			
			
			//~~~~~~~~~~~Transition to Game Stage 3~~~~~~~~~~~//
			if (gameTimer.getElapsedTimeInSeconds("int") > endStage2 - transitionLength)
			{
				collisionChecker.setTransitioning(true);
				Bubbles.oceanRiseFactor = 13;
				Torpedo.oceanRiseFactor = 13;
				
				ocean.startFade(-1);				
				
				Torpedo.setWrapping(false); //setWrapping(false) causes the torpedos to not return
										   // after leaving the frame	
			}
			
			ocean.makeGraphic(g);

			if(gameTimer.getElapsedTimeInSeconds("int") % 10 <= 4)
			{
				int foodpos = (int)((Math.random()*10)/2);
				switch(foodpos)
				{
					case 1: if (!food.justCollided) food.makeGraphic(g);
						break;
					case 2: if (!food1.justCollided) food1.makeGraphic(g);
						break;
					case 3: if (!food2.justCollided) food2.makeGraphic(g);
						break;
					case 4: if (!food.justCollided) food.makeGraphic(g);
						break;
					case 5: if (!food1.justCollided) food1.makeGraphic(g);
						break;
					case 6: if (!food2.justCollided) food2.makeGraphic(g);
						break;
					default: 
						break;
				}
			}

			level2.makeGraphic(g);
			
			//This next block creates all the torpedos. The if statement within the
			// for loop staggers the addition of the torpedos
			if (gameTimer.getElapsedTimeInSeconds("int") > endStage1) {
				for (int i=0; i<numberOfTorpedos; i++) {
					if (gameTimer.getElapsedTimeInSeconds("int") >= endStage1 + i*2)
						torpedo.get(i).makeGraphic(g);
					}
			}
			
			
		//!!!!!!End Game Stage 2!!!!!!!!
		}
		

	//++====Start Game Stage 3 (final stage)===++//
		if (gameTimer.getElapsedTimeInSeconds("int") >= endStage2)
		{
			collisionChecker.setTransitioning(false);
			Bubbles.oceanRiseFactor = 0;
			Bubbles.colorFactor = -100;
			Torpedo.oceanRiseFactor = 0;
			ocean.setFinalColor(0, 0, 0);
			ocean.makeGraphic(g);
			steamPattern.makeGraphic(g);
			level3.makeGraphic(g);
			for (int i=0; i<numberOfTorpedos; i++) {
				if (gameTimer.getElapsedTimeInSeconds("int") >= endStage1 + i*2)
					torpedo.get(i).makeGraphic(g);
				}
		
			
			
		//!!!!!!!End Game Stage 3!!!!!!!!
		}
		
		
		
	//++======Constant graphics (shield, bubbles, fish, texture)========++//
		//-----Control the Shield Behavior-------
		checkShieldOnFish();
		shield.makeGraphic(g, shieldOnFish);
		
		
		// -----Create half the Bubbles Behind the fish------//
		murkey.makeGraphic(g);
		for (int i=0; i<numberOfBackgroundBubbles/2; i++)
			bubbleList.get(i).makeGraphic(g);
		
		// -----Create the Fishy Dude!! -------//
		fishy.makeGraphic(g);
		fishy.setPreviousPosition(fishy.xPos);
		
		// -----Create the Bubbles in front of the fish -------//
		for (int i=numberOfBackgroundBubbles/2; i<numberOfBackgroundBubbles; i++)
			bubbleList.get(i).makeGraphic(g);

		// -----Create the Score Display!! -------//
		if(gameTimer.getElapsedTimeInSeconds("int") >= neutralStartTime)
			scoreDisplay.setScore((int) ((gameTimer.getElapsedTimeInMilliseconds() - neutralStartTime*1000)*scoreMultiplier));
		scoreDisplay.makeGraphic(g);

		}
	
	//==============================End paintComponent() Method==============================//
	//===================================================================================//
	
	
	
	
	//--------------------------Start Action Performed Method------------------------------//
	/*
	 * Action Performed (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * 
	 * Since actionPerformed is what gets run by timer1 (our Timer object),
	 * everything in this class happens every refreshRate seconds, including repaint()
	 * which sets off the paintComponent() method above.
	 * Contained in this method before repaint() are all the methods that reset
	 * the main parameters that are heavily referenced by the graphics objects classes
	 * Thus, always before rendering, critical fields are updated
	 * 
	 */
	@Override
	//This little method is what performs the action each time. 
	public void actionPerformed(ActionEvent arg0) {
		//action statements
		gameCounter++;
		fishyGame.r = frame.getBounds();	
		WINDOW_LENGTH_Y = (int) r.getHeight();
		WINDOW_LENGTH_X = (int) r.getWidth();
		
		//TODO
		//BELOW THIS LINE IS WHERE YOUR METHODS SHOULD GO
		if (fishMovingUp)
			fishy.yPos-=fishSpeed;
		if (fishMovingDown)
			fishy.yPos+=fishSpeed;
		if (fishMovingLeft)
			fishy.xPos-=fishSpeed;
		if (fishMovingRight)
			fishy.xPos+=fishSpeed;


		
		//checkConditions() holds all the logic behind the collisions
		shield.graceTimerBehavior();
		hasCollided = collisionChecker.checkConditions();
		
		
		

		repaint();
		//DO NOT ADD AFTER THIS LINE
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 * 
	 * keyPressed is the method that take in keyboard information and 
	 * sets variables that control the movement of the fish
	 * 
	 */
	public void keyPressed(KeyEvent e)
	{
		switch (e.getKeyCode()) {
        case KeyEvent.VK_UP:
            fishMovingUp = true;
            break;
        case KeyEvent.VK_DOWN:
            fishMovingDown = true;
            break;
        case KeyEvent.VK_LEFT:
            fishMovingLeft = true;
            break;
        case KeyEvent.VK_RIGHT:
            fishMovingRight = true;
            break;
        default:
	    		fishMovingUp = false;
	    		fishMovingDown = false;
	    		fishMovingRight = false;
	    		fishMovingLeft = false;
        		break;
		}
		
    
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 * 
	 * registers if a key has been released to control the fish movement behavior
	 */
	
	public void keyReleased(KeyEvent e) {
		fishDirection = 0;
		
		switch (e.getKeyCode()) {
        case KeyEvent.VK_UP:
        		fishMovingUp = false;
            break;
        case KeyEvent.VK_DOWN:
            fishMovingDown = false;
            
            break;
        case KeyEvent.VK_LEFT:
            fishMovingLeft = false;
            break;
        case KeyEvent.VK_RIGHT:
            fishMovingRight = false;
            break;
        default:
        		fishMovingUp = false;
        		fishMovingDown = false;
        		fishMovingRight = false;
        		fishMovingLeft = false;
        		break;
		}
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	/*
	 * Method Name: CheckShieldOnFish()
	 * 
	 * This void method checks whether the shield is on the fish
	 */
	public void checkShieldOnFish() {
		int bounds = shield.size / 2;
		if ( (fishy.xPos < (shield.getXPos() + bounds) && fishy.xPos > (shield.getXPos() - bounds) )
				&& fishy.yPos < (shield.getYPos() + bounds) && fishy.yPos > (shield.getYPos() - bounds))
		shieldOnFish=true;
	}
	
	
	/*
	 * Method Names: getWindowX() and getWindowY()
	 * Methods that return the window frame size
	 */
	public static int getWindowX() {
		return WINDOW_LENGTH_X;
	 }
	public static int getWindowY() {
		 return WINDOW_LENGTH_Y;
	 }
	
//======================================End of FishMain Class===============================//
}

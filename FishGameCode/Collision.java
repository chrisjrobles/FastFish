import java.util.ArrayList;


/*
 * Class Header: Collision
 * Team: Logic
 * Primary Authors: Chris, Robert
 * 
 * The purpose of this class is to enable collision testing in a variety
 * of situations within the game. It will also contain methods that implement
 * this collision checker in order to check if bubble or fish have collided with the hook.
 */

public class Collision {
	
	public FishMain fishyGame;
	public FishyDude fishy;
	public ArrayList<Hook> hook;
	public ArrayList<Torpedo> torpedo;
	public ArrayList<SteamBubbles> steamBubbles;
	public boolean hasCollided;
	public GameTime gameTimer;
	//public GameTime graceTimer;
	public boolean transitioning = false;
	
	/*	 
	 *  Constructor signature: Collision(FishMain fishyGame, FishyDude fishy,
	 *			ArrayList<Hook> hook, ArrayList<Torpedo> torpedo, 
	 *			ArrayList<SteamBubbles> steamBubbles, GameTime gameTimer, GameTime graceTimer)
	 * 
	 *  Description: Initializes all the class variables with the corresponding variables that
	 *  			were already initialized in FishMain.
	 */
	public Collision(FishMain fishyGame, FishyDude fishy,
			ArrayList<Hook> hook, ArrayList<Torpedo> torpedo, 
			ArrayList<SteamBubbles> steamBubbles, GameTime gameTimer, GameTime graceTimer) {
		this.fishyGame = fishyGame;
		this.fishy = fishy;
		this.hook = hook;
		this.torpedo = torpedo;
		this.steamBubbles = steamBubbles;
		this.gameTimer = gameTimer;
		this.hasCollided = FishMain.hasCollided;
		//this.graceTimer = graceTimer;
	}
	
	/*	 
	 *  Method signature: boolean hasCollision(MyGraphicsObject object1, MyGraphicsObject object2)
	 * 
	 *  Description: Checks whether two MyGraphicsObjects have a collision. Returns true if they do and false
	 *  			otherwise.
	 */
	public static boolean hasCollision(MyGraphicsObject object1, MyGraphicsObject object2) {
		boolean has1DCollision = false;
		int[] object1XBounds = object1.getXBounds();
		int[] object2XBounds = object2.getXBounds();
		int[] object1YBounds = object1.getYBounds();
		int[] object2YBounds = object2.getYBounds();
		
		if (has1DCollision(object1XBounds, object2XBounds) && has1DCollision(object1YBounds, object2YBounds))
			has1DCollision = true;
		
		return has1DCollision;
	}
	
	/*	 
	 *  Method signature: boolean has1DCollision(int[] object1Bounds, int[] object2Bounds)
	 * 
	 *  Description: Checks whether the max or min of one of an object's dimensional arrays is in between the max
	 *  			and min of another objects corresponding dimensional array
	 */
	public static boolean has1DCollision(int[] object1Bounds, int[] object2Bounds) {
		boolean has1DCollision = false;
		
		if ( (object1Bounds[1] <= object2Bounds[1] && object1Bounds[1] >= object2Bounds[0])
				|| (object1Bounds[0] >= object2Bounds[0] && object1Bounds[0] <= object2Bounds[1]))
			has1DCollision = true;
		
		return has1DCollision;
	}
	
	/*	 
	 *  Method signature: void setTransitioning(boolean trans)
	 * 
	 *  Description: Sets the transitioning variable to true when the game is in transition
	 */
	public void setTransitioning(boolean trans) {
		this.transitioning = trans;
	}
	
	/*	 
	 *  Method signature: boolean checkConditions()
	 * 
	 *  Description: Returns true if the fish has collided with a harmful object and the
	 *  			fish is not in transition.
	 */
	public boolean checkConditions() {
		if (!transitioning)
		{
			if (gameTimer.getElapsedTimeInSeconds("int") < fishyGame.endStage1) {
				for (int i=0; i<FishMain.numberOfBoats; i++) {
					hasCollided = Collision.hasCollision(fishy, hook.get(i));
					if (hasCollided)
						break;
				}
			}
			else if (gameTimer.getElapsedTimeInSeconds("int") > fishyGame.endStage1
					&& gameTimer.getElapsedTimeInSeconds("int") < fishyGame.endStage2)
			{
				for (int i=0; i< FishMain.numberOfTorpedos; i++)
				{
					hasCollided = Collision.hasCollision(fishy, torpedo.get(i));
					if (hasCollided)
						break;
				}
			}
			else if (gameTimer.getElapsedTimeInSeconds("int") > fishyGame.endStage2)
			{
				for (int i=0; i<FishMain.numberOfSteamBubbles; i++)
				{
					hasCollided = Collision.hasCollision(fishy, steamBubbles.get(i));
					if (hasCollided)
						break;
				}
			}
		}
		return hasCollided;
	}
}
	

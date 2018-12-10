import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class SteamPattern {

	public ArrayList<SteamBubbles> steamBubbles;
	public GameTime gameTimer;
	public FishMain fishyGame;
	
	public SteamPattern(ArrayList<SteamBubbles> steamBubbles, GameTime gameTimer, FishMain fishyGame) {
		this.steamBubbles = steamBubbles;
		this.gameTimer = gameTimer;
		this.fishyGame = fishyGame;
	}

	
	
	public Graphics2D makeGraphic(Graphics g) {
		
		Graphics2D steamPattern = (Graphics2D) g;
		
		int interval = (gameTimer.getElapsedTimeInSeconds("int") - fishyGame.endStage2) % 10;
		
		if (interval < 1)
		{
			steamBubbles.removeAll(steamBubbles);
			int newInitialX = (int)(FishMain.WINDOW_LENGTH_X*Math.random());
			fishyGame.steamReleaseSize = FishMain.numberOfSteamBubbles;// GameUtility.plusOrMinusRandom(FishMain.numberOfSteamBubbles,
					//2*FishMain.numberOfSteamBubbles);
			for (int i=0; i<fishyGame.steamReleaseSize; i++) {
				if (steamBubbles.size() >= fishyGame.steamReleaseSize)
					break;
				steamBubbles.add(new SteamBubbles(newInitialX, 5));
			}
			fishyGame.regenerated = true;
			fishyGame.steamPresent = false;
		}
		
		if (interval >= 1 && interval < 9)
		{
			for (int i=0; i<fishyGame.steamReleaseSize; i++)
			{
				if (gameTimer.getElapsedTimeInMilliseconds() >= fishyGame.endStage2 + 50*( 5 +i) )
					steamBubbles.get(i).makeGraphic(g);
			}
			fishyGame.steamPresent = true;
		}
		
		if (interval >= 9) {
			fishyGame.regenerated = false;
			fishyGame.steamPresent = false;
		}
		
		
		return steamPattern;

	}
	
	
	
	
}

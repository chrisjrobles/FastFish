// Class for setDifficulty method that sets difficulty of game. Variables like
// the number of torpedoes, the number of boats, and the stage length are
// increased as the game gets harder.

public class Difficulty{
	
	// Sets difficulty of game. Input a char for difficulty, 'e' for easy, 
	// 'm' for medium, or 'h' for hard. Also input the FishMain object (i.e. 
	// fishyGame) so that the specific instance of fishMain is referenced.
	public static void setDifficulty(char difficulty) {
		FishMain game = FishMain.fishyGame;
		
		if (difficulty == 's') {
			FishMain.numberOfBoats = 3;
			FishMain.numberOfTorpedos = 4;
			FishMain.numberOfSteamBubbles = 200;
			// endStage variables refer to time in absolute seconds that each
			// stage lasts until
			game.endStage1 = 20 + FishMain.neutralStartTime;
			game.endStage2 = game.endStage1 + 28;
		} else if (difficulty == 'e') {
			FishMain.numberOfBoats = 5;
			FishMain.numberOfTorpedos = 9;
			FishMain.numberOfSteamBubbles = 250;
			// endStage variables refer to time in absolute seconds that each
			// stage lasts until
			game.endStage1 = 28 + FishMain.neutralStartTime;
			game.endStage2 = game.endStage1 + 25;
		} else if (difficulty == 'm') {
			FishMain.numberOfBoats = 9;
			FishMain.numberOfTorpedos = 12;
			FishMain.numberOfSteamBubbles = 300;
			// endStage variables refer to time in absolute seconds that each
			// stage lasts until
			game.endStage1 = 48;
			game.endStage2 = 80;
		} else if (difficulty == 'h') {
			FishMain.numberOfBoats = 13;
			FishMain.numberOfTorpedos = 18;
			FishMain.numberOfSteamBubbles = 350;
			// endStage variables refer to time in absolute seconds that each
			// stage lasts until
			game.endStage1 = 60;
			game.endStage2 = 100;
		}
		
		game.collisionChecker = new Collision(game,  game.fishy, game.hook, 
				game.torpedo, game.steamBubbles, game.gameTimer, game.graceTimer);
		
	}
}

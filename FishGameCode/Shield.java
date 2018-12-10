import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Shield extends MyGraphicsObject {
	
	
		
	
	public Shield(int x, int y, int size) {
		this.xPos=x;
		this.yPos=y;
		this.size=size;
	}
	
	@Override
	public int[] getXArray() {
		this.xArray[0] = this.xPos;
		this.xArray[1] = this.xPos + size;

		return xArray;
	}
	
	@Override
	public int[] getYArray() {
		this.yArray[0] = this.yPos;
		this.yArray[1] = this.yPos + size;
		
		return yArray;
	}
	


	
	
	public Graphics2D setGraphic(Graphics g) 
	{
		//FishMain.fishyGame.checkShieldOnFish();
		Graphics2D oneBubble = (Graphics2D) g;
		
		Color topColor = ColorChanger.ColorUpDown((int)(100*Math.random()),
												(int)(100*Math.random()), 
												(int)(100*Math.random()), 1, 255);
		Color bottomColor=topColor;
		
		//if (FishMain.gameCounter%4<2)
		//	bottomColor=topColor;
		
		//Make Current Color
		GradientPaint bubbleColor = new GradientPaint(xPos, yPos+size, topColor,
				xPos, this.yPos-2*size, bottomColor);
		
		//ColorChanger.ColorUpDown(30,40,50,1,100);
		oneBubble.setPaint(bubbleColor);
		
		
		
		oneBubble.fillOval(xPos, yPos, size, size);

		
		return oneBubble;
		
	}
	
	public int controlYSheild() {
		return GameUtility.Clipper(FishMain.oceanSurfaceHeight+size, FishMain.WINDOW_LENGTH_Y,
				GameUtility.upAndDown(4, FishMain.WINDOW_LENGTH_X));
	}
	
	
	public void makeGraphic(Graphics g, boolean shieldOnFish) {
		if (FishMain.gameTimer.getElapsedTimeInSeconds("int")%15<5 && !shieldOnFish)
		{
			this.setGraphic(g);
			this.size = 50;
			this.setXPos(GameUtility.Clipper(0, FishMain.WINDOW_LENGTH_X, this.getXPos()+1, 0));
			this.setYPos(FishMain.getWindowY()/2 +GameUtility.upAndDown(5, 60));
		}
		else if (FishMain.gameTimer.getElapsedTimeInSeconds("int")%15>=5 && !shieldOnFish) {
			this.setXPos(2*FishMain.getWindowX() + 2*this.size);
			this.setYPos(2*FishMain.getWindowY() + 2*this.size);
		}
		else if (shieldOnFish) {
			this.setGraphic(g);
			this.size=130;
			this.setXPos(FishMain.fishyGame.fishy.getXPos()-this.size/2+60);
			this.setYPos(FishMain.fishyGame.fishy.getYPos()-this.size/2);
		}
		
	}
	
	public void graceTimerBehavior() {
		if (!FishMain.fishyGame.gracePeriod && FishMain.hasCollided) {
			if (FishMain.fishyGame.shieldOnFish) { 
				FishMain.fishyGame.shieldOnFish=false; //removes the shield
				FishMain.shield.setYPos(FishMain.WINDOW_LENGTH_Y*3); //moves the shield off the screen
				FishMain.fishyGame.gracePeriod = true;
				FishMain.hasCollided = false;
			} else {
				System.out.println("YOU GOT CAUGHT\nGAME OVER :(");
				FishMain.timer1.stop();
			}
		}
		

		if (FishMain.fishyGame.gracePeriod) {
			if (FishMain.fishyGame.graceTimer == null)
				FishMain.fishyGame.graceTimer = new GameTime();
			FishMain.fishyGame.graceTime = FishMain.fishyGame.graceTimer.getElapsedTimeInSeconds("int");
			if (FishMain.fishyGame.graceTime > 1) {
				FishMain.fishyGame.graceTime = 0;
				FishMain.fishyGame.graceTimer = null;
				FishMain.fishyGame.gracePeriod = false;
			}
		}
	}
	
	
	
}

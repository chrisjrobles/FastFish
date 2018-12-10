import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class ScoreDisplay extends MyGraphicsObject {
	
	
	public String stringDisplay;
	public int nonTimeScore = 0;
	public int xPos;
	public int yPos = 40;
	public int boxWidth = 200;
	public int boxHeight = 40;
	double xPoint1;
	double xPoint2;
	double yPoint1 = yPos;
	double yPoint2 = yPos + 30;
	
	
	public ScoreDisplay(double score) {
		setScore(score);
		this.xPos = FishMain.getWindowX();
		this.yPos = FishMain.getWindowY();
		xPoint1 = xPos;
		xPoint2 = xPos + 20;
	}

	
	public Graphics2D makeGraphic(Graphics g) {
		
		Graphics2D scoreBox = (Graphics2D)g;
				
		//xPos and yPos define the start point of the BOX (not text)
		xPos = FishMain.getWindowX()-210;
		yPos = 40;
		
		xPoint1 = xPoint1 + 0.5;
		xPoint2 = xPoint2 + 0.5;

		
		GradientPaint boxPaint = new GradientPaint((int) xPoint1, (int) yPoint1, new Color(230, 255, 255),
				(int) xPoint2, (int) yPoint2, new Color(0, 51, 77), true);

		
		scoreBox.setPaint(boxPaint);
		//Create the rectangle/fill it
		scoreBox.fillRect(xPos, yPos, boxWidth, boxHeight);
	
		
		Graphics2D scoreString = (Graphics2D)g;
		
		scoreString.setFont(new Font("Courier", Font.BOLD, 20));
		scoreString.setPaint(new Color(0,0,0));
		scoreString.drawString("Score: " + stringDisplay, xPos+10, yPos+20);
		
		return scoreString;
	}

	
	public void setScore(double score) {
		stringDisplay = String.valueOf((int)(score + nonTimeScore));
	}
	
	
}

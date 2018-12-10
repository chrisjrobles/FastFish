/*
 * Class Name: StartDisplay
 * Extends: MyGraphicsObject
 * Implements: none
 * Team: Graphics
 * Primary Authors: Robert
 * 
 * This class creates the countdown box that marks the preliminary stage of the 
 * Fishy Game, notifying the player of the remaining time until play begins.
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;

public class StartDisplay extends MyGraphicsObject {
	
	
	public String stringDisplay;
	public int xPos;
	public int yPos = 40;
	public int boxWidth = 200;
	public int boxHeight = 120;
	double xPoint1;
	double xPoint2;
	double yPoint1 = yPos;
	double yPoint2 = yPos + 30;
	public int arcWidth = 10;
	public int arcHeight = 10;
	
	
	public StartDisplay(int time) {
		countdown(time);
		this.xPos = FishMain.getWindowX() / 2;
		this.yPos = FishMain.getWindowY() / 2;
		xPoint1 = xPos;
		xPoint2 = xPos + 20;
	}


	public Graphics2D makeGraphic(Graphics g) {
		
		Graphics2D startBox = (Graphics2D)g;
		
		GradientPaint boxPaint = new GradientPaint((int) xPoint1, (int) yPoint1, new Color(4, 52, 124),
				(int) xPoint2, (int) yPoint2, new Color(193, 25, 87), true);

		
		startBox.setPaint(boxPaint);
		//Create the rectangle/fill it
		startBox.fillRect(xPos - (boxWidth/2), yPos - (boxHeight/2), boxWidth, boxHeight);
	
		
		Graphics2D startString = (Graphics2D)g;
		
		startString.setFont(new Font("Courier", Font.BOLD, 20));
		startString.setPaint(new Color(255, 255, 255));
		startString.drawString("Game Begins In:", xPos - (boxWidth/2) + 10, yPos - (boxHeight/2) + 20);
		startString.setFont(new Font("Courier", Font.BOLD, 75));
		startString.drawString(stringDisplay, xPos - (boxWidth/2) + 80, yPos - (boxHeight/2) + 80);
		startString.setFont(new Font("Courier", Font.BOLD, 20));
		startString.drawString("Get Ready!!!!", xPos - (boxWidth/2) + 20, yPos - (boxHeight/2) + 110);
		
		return startString;
	}

	
	public void countdown(int time) {
		int timer = FishMain.fishyGame.neutralStartTime - time;
		if (timer >= 0)
			stringDisplay = String.valueOf(timer);
		else
			stringDisplay = "";

	}
	
}

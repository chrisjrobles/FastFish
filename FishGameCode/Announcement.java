import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.*;

/*
 * Class Name: Announcement
 * Extends MyGraphicsObject
 * Team: Graphics
 * Primary Authors: Chad, Ana
 * 
 * This class creates the text objects to display the levels of the game using 
 * Graphics2D, which provides better control over color and text layout.
 * 
 */
public class Announcement extends MyGraphicsObject {

	
	public String textToDisplay;
	public int width;
	public int height;
	public int arcWidth;
	public int arcHeight;
	public GradientPaint boxPaint;
	
	
	public Announcement(String textToDisplay) {
		this.textToDisplay = textToDisplay;
		
	}
	

public Graphics2D secondStage(Graphics two) {
	
	Graphics2D stageTwoMessage = (Graphics2D)two;
	
	
	makePaint();
	stageTwoMessage.setPaint(boxPaint);
	stageTwoMessage.setFont(new Font("Trebuchet MS", Font.ITALIC, 25));
	stageTwoMessage.setColor(new Color(225, 225, 225));
	stageTwoMessage.fillRoundRect(xPos, yPos, width, height, arcWidth, arcHeight);
	
	stageTwoMessage.drawString(textToDisplay, xPos + 862, yPos + 30);
	
	return stageTwoMessage;
}

public Graphics2D thirdStage(Graphics three) {
	
	
	Graphics2D stageThreeMessage = (Graphics2D)three;
	
	
	makePaint();
	stageThreeMessage.setPaint(boxPaint);
	stageThreeMessage.setFont(new Font("Trebuchet MS", Font.ITALIC, 25));
	stageThreeMessage.setColor(new Color(250, 104, 2));
	stageThreeMessage.fillRoundRect(xPos, yPos, width, height, arcWidth, arcHeight);
	
	stageThreeMessage.drawString(textToDisplay, xPos + 862, yPos + 30);
	
	return stageThreeMessage;

}
	
	
	public Graphics2D makeGraphic(Graphics g) {
		
		Graphics2D announcement = (Graphics2D)g;
		
		
		makePaint();
		announcement.setPaint(boxPaint);
		announcement.setFont(new Font("Trebuchet MS", Font.ITALIC, 25));
		announcement.fillRoundRect(xPos, yPos, width, height, arcWidth, arcHeight);
		
		announcement.setColor(new Color(250, 17, 63));
		announcement.drawString(textToDisplay, xPos + 862, yPos + 30);
		
		
		return announcement;
		
	}
	
	
	
	
	public void makePaint() {
		this.boxPaint = new GradientPaint(FishMain.WINDOW_LENGTH_X/2, FishMain.WINDOW_LENGTH_Y,
				new Color(0,0,0), FishMain.WINDOW_LENGTH_X/2, 0, new Color(255,255,255));
	}
	
	
	public void setText(String textToDisplay) {
		this.textToDisplay = textToDisplay;
	}
	
	
}

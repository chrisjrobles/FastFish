import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class SteamBubbles extends MyGraphicsObject {

	GradientPaint steamPaint;
	public static int initialX;
	public static int initialY;
	public int radius;
	public int addFactor = 0;
	public int a = 0;
	public int speed = 0;
	public int[] xBounds = new int[2];
	public int[] yBounds = new int[2];
	Color topColor;
	Color bottomColor;

	
	
	
	public SteamBubbles(int initialX, int radius) {
		SteamBubbles.initialX = initialX;
		this.xPos = initialX;
		
		SteamBubbles.initialY = FishMain.getWindowY();
		this.yPos = initialY;
		
		double rand = 100*Math.random();
		if (rand<33)
			this.a = -1;
		else if (rand > 33 && rand < 66)
			this.a = 1;
		
		if (a != 0)
			speed = 0;
		else
			speed = 2;
		
		speed+=3;
		
		setTopColor(0, 153, 204);
		setBottomColor(77, 0, 38);
		
		this.radius = radius;		
	}
	
	public Graphics2D makeGraphic(Graphics g) {
		
		Graphics2D steamBubbleGraphic = (Graphics2D)g;
		
		setTopColor(0, 153, 204);
		setBottomColor(77, 0, 38);
		setSteamPaint();
		//steamPaint.isCyclic();
		xPattern();
		yPattern();
		
		steamBubbleGraphic.setPaint(steamPaint);
		steamBubbleGraphic.fillOval(xPos, yPos, 2*radius, 2*radius);
		

			setTopColor(10, 160, 230);
			setBottomColor(100, 12, 60);
			setSteamPaint();
			
			steamBubbleGraphic.setPaint(steamPaint);		
			steamBubbleGraphic.fillOval(xPos, yPos-2, 2*radius-2, 2*radius-2);
	
		
		return steamBubbleGraphic;
	}
	
	
	public void xPattern() {
		int adder;
		if (a==-1)
			adder = GameUtility.plusOrMinusRandom(-12, 8);
		else if (a==1)
			adder = GameUtility.plusOrMinusRandom(-8, 12);
		else
			adder = GameUtility.plusOrMinusRandom(-10, 10);
		
		if (yPos < FishMain.getWindowY() - 20)
			this.xPos += GameUtility.sineWave(5, 10*Math.random(), 0, 0, 0)
							+ adder;
		
		int spread = FishMain.getWindowY() - yPos +10;
		if (xPos > initialX + spread/10 || xPos < initialX - spread/10)
			this.a*=-1;
	}
	
	public void yPattern() {
		if (100*Math.random() < 10)
			addFactor++;
		
		
		
		addFactor = GameUtility.Clipper(0, 10, addFactor);
		
		if (yPos < 10)
			yPos -=  a*GameUtility.sineWave(3*Math.random(), 3, 3.14, 0, 0)
					 + addFactor;
		else
			yPos -= a*GameUtility.sineWave(3*Math.random(), 3, 3.14, 0, 0)
						 + addFactor + speed;
	}
	
	public void resetPos() {
		yPos = FishMain.getWindowY();
		xPos = initialX;
	}
	
	public void setVerticalSpeed(int n) {
		this.speed = n;
	}
	
	public void setTopColor(int r, int g, int b) {
		this.topColor = ColorChanger.ClipperColor(r, g, b);
	}
	
	public void setBottomColor(int r, int g, int b) {
		this.bottomColor = ColorChanger.ClipperColor(r, g, b);
	}
	
	public void setSteamPaint() {
		steamPaint = new GradientPaint(initialX, 0, topColor,
				initialX, FishMain.getWindowY(), bottomColor);
	}
	
	@Override
	public int[] getXBounds() {
		this.xBounds[0] = xPos;
		this.xBounds[1] = xPos + 2*radius;
		return this.xBounds;
	}
	
	@Override
	public int[] getYBounds() {
		this.yBounds[0] = yPos;
		this.yBounds[1] = yPos + 2*radius;
		return this.yBounds;
	}
	
	
}

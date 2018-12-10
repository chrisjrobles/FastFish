import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Torpedo extends MyGraphicsObject {
	
	public boolean movingRight = true;
	public int speed;
	
	public int bodyWidth = 100;
	public int bodyHeight = 20;
	public int arcWidth = 80;
	public int arcHeight = 50;
	public int[] xArray, yArray;
	public int[] xBounds = new int[2];
	public int[] yBounds = new int[2];
	public Color tailColor;
	public static boolean wrapping = true;
	public static int oceanRiseFactor = 0;
	public double randomFactor;
	public double randomFactor2;
	
	public Torpedo(int queue) {
		
		if (Math.random()<0.5)
			setDirection(true);
		else
			setDirection(false);
		
		this.randomFactor = Math.random();
		this.randomFactor2 = Math.random();

		if (movingRight)
			this.xPos =  -bodyWidth*queue;
		else
			this.xPos = FishMain.WINDOW_LENGTH_X + bodyWidth*queue*speed;
		
		this.yPos = (int) (FishMain.WINDOW_LENGTH_Y*Math.random());
		
		
		resetConditions();
	}
		
		//Gives each torpedo a different color and assigns speed according to the color
	public void resetConditions() {
		int randomizer = (int) (6*Math.random());
		switch (randomizer) {
		case 5:
			tailColor  = Color.RED;
			this.speed = GameUtility.lowLimiter(18, (int) (25*Math.random()));
			break;
		case 4:
			tailColor = Color.BLUE;
			this.speed = GameUtility.lowLimiter(7, (int) (10*Math.random()));
			break;
		case 3:
			tailColor = Color.GREEN;
			this.speed = GameUtility.lowLimiter(7, (int) (13*Math.random()));
			break;
		case 2:
			tailColor = Color.GRAY;
			this.speed = GameUtility.lowLimiter(5, (int) (8*Math.random()));
			break;
		case 1:
			tailColor = Color.YELLOW;
			this.speed = GameUtility.lowLimiter(16, (int) (20*Math.random()));
			break;
		default:	
			tailColor = Color.YELLOW;
			this.speed = GameUtility.lowLimiter(14, (int) (20*Math.random()));
			break;
			
		}
		this.speed = GameUtility.lowLimiter(5, speed-5);
		
	}
	
	public void setDirection(boolean direction) {
		movingRight = direction;
	}
	
	
	public Graphics2D makeGraphic(Graphics g) {
		Graphics2D torpedoGraphic = (Graphics2D)g;
		
		setPos();
		//setSize();
		
		
		torpedoGraphic.setPaint(tailColor);
		if (movingRight) {
			torpedoGraphic.fillRect(xPos, yPos, 15, bodyHeight);
			torpedoGraphic.fillOval(xPos, yPos, 30, bodyHeight);
		}
		else {
			torpedoGraphic.fillRect(xPos+bodyWidth-15, yPos, 15, bodyHeight);
			torpedoGraphic.fillOval(xPos+bodyWidth-30, yPos, 30, bodyHeight);
		}
		
		
//		GradientPaint torpedoPaint = new GradientPaint(bodyWidth/2, yPos, new Color(0,0,0), 
//														bodyWidth/2,xPos+bodyHeight, new Color(100,100,100));									
		
		GradientPaint torpedoPaint = new GradientPaint((int) (FishMain.getWindowX()*randomFactor), 
				(int) ( FishMain.getWindowY()*randomFactor2), new Color(0,0,0), 
				bodyWidth/2,xPos+bodyHeight, new Color(100,100,100));									

		torpedoGraphic.setPaint(torpedoPaint);
		torpedoGraphic.fillRoundRect(xPos, yPos, bodyWidth, bodyHeight, arcWidth, arcHeight);
		
		return torpedoGraphic;
	}
	
	double adder;
	public void setSize() {
		adder+=0.01;
		if (this.adder > 10)
			adder = 0;
		this.bodyHeight += (int) adder;
		this.bodyWidth += (int) adder;
		this.arcHeight +=(int) adder;
		this.arcWidth +=(int) adder;
	}
	
	
	public void setPos() {
		if (movingRight)
			this.xPos = this.xPos + speed;
		else
			this.xPos = this.xPos - speed;
		if (wrapping) {
			if (this.xPos > FishMain.WINDOW_LENGTH_X
					|| this.xPos < 0 ) {
				this.yPos = (int) (FishMain.WINDOW_LENGTH_Y*Math.random());
				this.speed = GameUtility.lowLimiter(7, (int) (20*Math.random()));
			}
		}
		
		if (wrapping)
			this.xPos = GameUtility.Wrapper(0, FishMain.WINDOW_LENGTH_X, this.xPos);
		 
		yPos -= oceanRiseFactor;	
		
	}
	
	
	public int[] createXArray() {
		xArray[0] = this.xPos;
		xArray[1] = this.xPos + this.bodyWidth;
		
		return xArray;
	}
	
	public int[] createYArray() {
		yArray[0] = this.yPos;
		yArray[1] = this.yPos + this.bodyHeight;
		
		return yArray;
	}
	
	public int[] getXBounds() {
		xBounds[0] = xPos;
		xBounds[1] = xPos + bodyWidth;
		return xBounds;
	}
	
	
	public int[] getYBounds() {
		yBounds[0] = yPos;
		yBounds[1] = yPos + bodyHeight;
		return yBounds;
	}
	
	public static void setWrapping(boolean wrapped) {
		wrapping = wrapped;
	}
	

	
}

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
public class fishFoods extends MyGraphicsObject 
{
	public int x1,y1;
	public boolean justCollided = false;
	//public int[] xArray, yArray;
	//public int[] xBounds, yBounds;
	
	public fishFoods(int x, int y) 
	{
		this.xPos= x;
		this.yPos= y;
		this.xArray = new int[3];
		this.yArray = new int[3];
		x1 = xPos;
		y1 = yPos;
		this.xArray[0] = x - 10;
		this.xArray[1] = x;
		this.xArray[2] = x + 10;
		this.yArray[0] = y - 10;
		this.yArray[1] = y;
		this.yArray[2] = y + 10;
		
	}
	
	
	
	@Override
	public Graphics2D makeGraphic(Graphics g) 
	{
		Graphics2D food = (Graphics2D) g;
		food.setColor(Color.YELLOW);
		food.fillRect(xPos, yPos, 15, 20);
		return food;
	}
	
	public boolean hasEaten(MyGraphicsObject fishfood) {

		boolean ateFoods = false;
		
		if (Collision.hasCollision(fishfood, FishMain.fishyGame.fishy))
			ateFoods = true;
			
		return ateFoods;
		
	}

	public void setYArray(int offset) {
		for (int i = 0; i < yArray.length; i++) {
			this.yArray[i] += offset;
		}
	}
}

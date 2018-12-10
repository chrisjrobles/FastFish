import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Stars extends MyGraphicsObject {
	
	
	//public  int xPos=50+ (int) (FishMain.WINDOW_LENGTH_X*Math.random());
	//public  int yPos=(int) (FishMain.WINDOW_LENGTH_Y*Math.random());
	public  int directionSwitcher=1;
	public  int size=2;
	public  int speed=12-size;
	public int xPos=(int) (FishMain.WINDOW_LENGTH_X*Math.random());
	public int yPos=(int) (FishMain.oceanSurfaceHeight*Math.random());
	private int topColorTimeFactor=1;	//sub-color
	private int bottomColorTimeFactor=2; //twinkle
	public Color topColor;
	public Color bottomColor;
	
	
	public Stars(int size) {
		this.size=size;
		
		topColorTimeFactor=(int) (Math.random()*FishMain.numberOfStars/10);
		bottomColorTimeFactor=(int) (Math.random()*FishMain.numberOfStars/10);
		
		topColor = ColorChanger.ColorUpDown((int)(125*Math.random()),
											(int)(125*Math.random()), 
											(int)(155*Math.random()),
											topColorTimeFactor, 
											125, 100,255);
		bottomColor=ColorChanger.ColorUpDown(255-(int)(150*Math.random()),
											(int)(100*Math.random()), 
											(int)(255*Math.random()),
											bottomColorTimeFactor, 
											500, 150, 255);
	}
	

	public Graphics2D createStar(Graphics g) 
	{
		if (Math.random()*5<3)
		{
			topColorTimeFactor=(int) (Math.random()*FishMain.numberOfStars);
			bottomColorTimeFactor=(int) (Math.random()*FishMain.numberOfStars);
		}
		
		Graphics2D starGraphic = (Graphics2D) g;
		
		if (Math.random()*5<2) {
			 topColor = ColorChanger.ColorUpDown((int)(100*Math.random()),(int)(100*Math.random()), (int)(100*Math.random()),
													topColorTimeFactor, 250, 120,255);
			 bottomColor=ColorChanger.ColorUpDown((int)(150*Math.random()),(int)(130*Math.random()), (int)(150*Math.random()),
				bottomColorTimeFactor, 500, 120, 255);
		}
		
		if (FishMain.gameTimer.getElapsedTimeInSeconds("int")%4==0)
			bottomColor=topColor;
		
		//Make Current Color
		//GradientPaint starColor = new GradientPaint(xPos, yPos+size, topColor,
				//xPos, yPos-2*size, bottomColor);
		Color starColor = topColor;
		//ColorChanger.ColorUpDown(30,40,50,1,100);
		starGraphic.setPaint(starColor);
		
		//Here is where an if statement calling the logic method of bubbleAttached() would
		//go to switch the position from fixed, to equaling the position of the fish
		
		
//		if ((int)FishMain.gameTimer.getElapsedTimeInMilliseconds()%(int)(100*Math.random())<2)
//			this.xPos=(int) (FishMain.WINDOW_LENGTH_X*Math.random());
		
		
		starGraphic.fillOval(xPos, yPos, size,size);
		
		
		return starGraphic;
		
	}
	
	
}

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;


/*
 * Class Header: Murkey
 * 
 * This class overlays "floaties" on the ocean graphic to produce the effect
 * of small bits floating in the water. 
 */


public class Murkey {

	public Ocean ocean;
	public Graphics2D murkey;
	public GameTime gameTimer;
	public boolean resetTime;
	public int particles; //Note: particles refers to the number of total particles,
						 // 	particle (singular) refers to a specific particle
	//public int[] size;
	public int size = 1;
	public double[] xPos;
	public double[] yPos;
	public int[] alpha;
	public double[] seedArray;
	public ArrayList<Color> colors = new ArrayList<Color>();
	
	
	
	public Murkey(Ocean ocean, GameTime gameTimer, int particles) {
		this.ocean = ocean;
		this.gameTimer = gameTimer;
		this.particles = particles;
		
		this.xPos = new double[particles];
		this.yPos = new double[particles];
		this.alpha = new int[particles];
		//this.size = new int[particles];
		this.seedArray = new double[particles];
		colors.ensureCapacity(particles);
		setNewColors();
		for (int i=0; i<particles; i++) {
			xPos[i] = (FishMain.getWindowX()*Math.random());
			yPos[i] = (FishMain.getWindowY()*Math.random());
			seedArray[i] = GameUtility.randomSeed(i);
			//size[i] = (int) (seedArray[i] > 0 ? 1:2);
		}
		
		
		
	}
	
	private void setNewColors() {
		for (int i=0; i<particles; i++)
		{
			Color selectedColor;
			if (Math.random()<0.5)
				selectedColor = ocean.getBottomColor();
			else
				selectedColor = ocean.getTopColor();
			
			if (Math.random()<0.5)
				selectedColor.brighter();
			else {
				selectedColor.brighter(); selectedColor.brighter();
			}
			
			colors.add(i, selectedColor);
		}
	}
	
	private void setNewColor(int particle) {
		Color selectedColor;
		if (Math.random()<0.5)
			selectedColor = ocean.getBottomColor();
		else
			selectedColor = ocean.getTopColor();
		
		if (Math.random()<0.5)
			selectedColor.brighter();
		else {
			selectedColor.brighter(); selectedColor.brighter(); }
		
		colors.set(particle, selectedColor);
	}
	
	
	
	public void resetPos(int particle) {
		xPos[particle] = FishMain.getWindowX() * Math.random();
		yPos[particle] = FishMain.getWindowY() * Math.random();
	}

	
	public void updateXPos(int particle) {
		xPos[particle] += (GameUtility.randomSeed(particle*2) + GameUtility.plusOrMinusRandomDouble(-1, 1))/5;
		xPos[particle] = xPos[particle] < 0 ? -xPos[particle] : xPos[particle];
	}
	
	public void updateYPos(int particle) {
		double seed = seedArray[particle] < 0 ? -seedArray[particle] : seedArray[particle];
		yPos[particle] -= (seed)/4 + Bubbles.oceanRiseFactor;
		yPos[particle] = GameUtility.Clipper(FishMain.oceanSurfaceHeight, FishMain.getWindowY(),
				yPos[particle], FishMain.getWindowY()*Math.random());
		
	}
	
	public void updatePos(int particle) {
		updateXPos(particle);
		updateYPos(particle);
	}
		
	
	public Graphics2D makeGraphic(Graphics g) {	

		return setGraphic(g, particles);
	}
	
	
	
	public Color shiftColor(Color inColor, int particle) {
		int R = colors.get(particle).getRed();
		int G = colors.get(particle).getGreen();
		int B = colors.get(particle).getBlue();
		
		double speed = GameUtility.Clipper(-2, 2, (GameUtility.randomSeed(particle)*2));
		
		alpha[particle] = GameUtility.upAndDown(speed, 250);
		
		alpha[particle] = GameUtility.Clipper(0, 255, alpha[particle]);
		
		if (alpha[particle] < 50)
		{
			resetPos(particle);
			setNewColor(particle);
		}
			
		
		Color outColor = new Color(R, G, B, alpha[particle]);
		
		return outColor;
	}
	
	public Graphics2D setGraphic(Graphics g, int particles) {

		
		murkey = (Graphics2D) g;
		
		if (particles == 0)
			return murkey;
		particles--;
		
		updatePos(particles);
		colors.set(particles, shiftColor(colors.get(particles), particles));
		
		murkey.setColor(colors.get(particles));
		murkey.fillOval((int)xPos[particles], (int)yPos[particles], size, size);
		
		return setGraphic(g, particles-1);
		
	}
	
	
}

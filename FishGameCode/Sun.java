import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Sun extends MyGraphicsObject{


	public int diameterSun = 150;
	public int radiusSun=diameterSun / 2;
	
	public Sun() {
		this.xPos = 40;
		this.yPos = 20;
	}
	
	public Graphics2D makeGraphic(Graphics g) {
		Graphics2D sun = (Graphics2D)g;
		GradientPaint sunDayGrad = new GradientPaint(xPos, yPos, ColorChanger.ColorUpDown(275,255,9,8,100),
									xPos,FishMain.oceanSurfaceHeight+yPos, new Color(102, 0, 30));
		sun.setPaint(sunDayGrad);
		g.fillOval(xPos,yPos,diameterSun,diameterSun);
		
		return sun;
	}
	
}

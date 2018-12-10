import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Sky extends MyGraphicsObject {

	public Color leftColor;
	public Color rightColor;
	
	public Sky() {
		setColors();
	}
	
	public void setColors() {
		rightColor = ColorChanger.ColorUpDown(15,80,135,10,-100);
		leftColor = ColorChanger.ColorUpDown(102,0,51,12,-15,7,3,8,14);
	}
	
	public Graphics2D makeGraphic(Graphics g) {
		Graphics2D sky = (Graphics2D) g;
		GradientPaint skyDuskGrad = new GradientPaint(FishMain.getWindowX(), 
				FishMain.sun.getXPos()+2*FishMain.sun.radiusSun,
				rightColor,0, 4, 
				leftColor);
		setColors();
		sky.setPaint(skyDuskGrad);
		g.fillRect(0, 0, FishMain.getWindowX(), FishMain.getWindowY());
		
		return sky;
	}


}

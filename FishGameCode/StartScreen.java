import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class StartScreen {
	Ocean ocean;
	Sun sun;
	Sky sky;
	ArrayList<Bubbles> bubbles;
	Stars2 stars2;
	
	public Graphics2D makeGraphic(Graphics g){
		Graphics2D startScreen = (Graphics2D) g;
		
		sky.makeGraphic(g);
		stars2.makeGraphic(g);
		sun.makeGraphic(g);
		ocean.makeGraphic(g);
		for(int i = 0; i < bubbles.size(); i++)
			bubbles.get(i).makeGraphic(g);
		
		return startScreen;
	}
	public StartScreen(Ocean ocean, Sun sun, Sky sky, 
						ArrayList<Bubbles> bubbles, Stars2 stars2) {
		this.ocean = ocean;
		this.sun = sun;
		this.sky = sky;
		this.bubbles = bubbles;
		this.stars2 = stars2;
	}
}

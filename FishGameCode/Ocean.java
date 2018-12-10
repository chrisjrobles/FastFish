import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;


/*
 * Class Name: Ocean
 * Extends MyGraphicsObject
 * Team: Graphics
 * Primary Authors: Chad
 * 
 * This class creates the ocean graphics object that gets instantiated in the FishMain code.
 * The ocean is created with an X and Y array that define the coordinates along the boundary
 * of the ocean. These arrays are created in the createXWave() and createYWave() methods
 * The xArray is non-changing whereas the yArray does change over time. This is primarily what gives rise
 * to the animated pattern of the ocean surface.  
 * 
 */

public class Ocean extends MyGraphicsObject {
	
	
	
	int tide3=0;
	
	//Fields that control the shape and behavior of the ocean wave 
	private boolean dynamic = true;
	private int averageHeightWave = FishMain.oceanSurfaceHeight;

	
	//Corners go bottom right, top right, top left, bottom left, bottom right
	
	 int waveCorner1=0;
	 int waveCorner2=FishMain.WINDOW_LENGTH_Y-averageHeightWave;
	 int waveCorner3=FishMain.WINDOW_LENGTH_Y-averageHeightWave+1+FishMain.WINDOW_LENGTH_X;
	 int waveCorner4=2*(FishMain.WINDOW_LENGTH_Y-averageHeightWave)+FishMain.WINDOW_LENGTH_X;
	 int waveCorner5=2*(FishMain.WINDOW_LENGTH_Y-averageHeightWave)+2*FishMain.WINDOW_LENGTH_X;
	 int arrayLength=waveCorner1+waveCorner2+waveCorner3+waveCorner4+waveCorner5;
	 
	public  java.awt.Color topColorOcean;
	public  java.awt.Color bottomColorOcean;
	
	
	public int[] xArray = new int[arrayLength];
	public int[] yArray = new int[arrayLength];
	
	
	//CONTSTRUCTOR
	public Ocean() {
		this.topColorOcean =  ColorChanger.ColorUpDown(60,205,240,200,-15,100,-40,8,14);
		this.bottomColorOcean = ColorChanger.ColorUpDown(0, 61, 102 ,10,-100);

//		this.bottomColorOcean = ColorChanger.ColorUpDown(0,50,51,10,-100);
	}
	
	
	//--------------------------Method Section Start------------------------------//

	
	public Graphics2D makeGraphic(Graphics g) {
		
		setWaveCorners();
		topColorOcean = getTopColor();
		bottomColorOcean = getBottomColor();
		
		
		Graphics2D oceanObject = (Graphics2D)g;
		oceanObject.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_OFF);
				
		if (dynamic) {
			GradientPaint oceanBlue = new GradientPaint(FishMain.WINDOW_LENGTH_X/2, 
					FishMain.oceanSurfaceHeight, 
					topColorOcean, 
					FishMain.WINDOW_LENGTH_X/2,
					FishMain.WINDOW_LENGTH_Y,
					bottomColorOcean);
			oceanObject.setPaint(oceanBlue);

			Polygon oceanPolygon = new Polygon(createXWave(), createYWave(), waveCorner5);
			oceanObject.fillPolygon(oceanPolygon);
		}
		else {
			GradientPaint oceanBlue = new GradientPaint(FishMain.WINDOW_LENGTH_X/2, 
														FishMain.oceanSurfaceHeight,
														topColorOcean, 
														FishMain.WINDOW_LENGTH_X/2,
														FishMain.WINDOW_LENGTH_Y,
														bottomColorOcean);
			oceanObject.setPaint(oceanBlue);
			oceanObject.fillRect(0, 0, FishMain.WINDOW_LENGTH_X,
					FishMain.WINDOW_LENGTH_Y);
		}
	
		
		return oceanObject;
	}
	
	
	public void setDynamic(boolean bool) {
		this.dynamic = bool;
	}
	public boolean getDynamic() {
		return this.dynamic;
	}
	
	
	//These setter methods only increment the color values by the input value
	public void setTopColor(int r, int g, int b, int gain) {
		int[] RGB =  {
					this.getTopColor().getRed() + r,
					this.getTopColor().getGreen() + g,
					this.getTopColor().getBlue() + b 
					};
		int gain1 = -15+gain;
		int gain2 = -40+gain;
		int gain3 = 14+gain;
		
		if (this.dynamic)
			this.topColorOcean =  ColorChanger.ColorUpDown(RGB[0],RGB[1],RGB[2],
								200, gain1, 100, gain2, 8, gain3);
		else
			this.topColorOcean =  ColorChanger.ColorUpDown(RGB[0],RGB[1], RGB[2],10,1 );
	}
	
	
	public void setBottomColor(int r, int g, int b, int gain) {
		int[] RGB = {
					this.getBottomColor().getRed() + r,
					this.getBottomColor().getGreen() + g,
					this.getBottomColor().getBlue() +b
					};
		
		
		if (this.dynamic)
			this.bottomColorOcean = ColorChanger.ColorUpDown(RGB[0], RGB[1], RGB[2],10,-100+gain);
		else
			this.bottomColorOcean = ColorChanger.ColorUpDown(RGB[0], RGB[1], RGB[2], 10,1);
	
	}	
	
	double[] faderVar = new double[3];
	
	
	public void setFinalColor(double r, double g, double b) {
		
		faderVar[0] += r;
		faderVar[1] += g;
		faderVar[2] += b;
		
		topColorOcean = ColorChanger.ClipperColor( (int) faderVar[0],
												  (int) faderVar[1],
												  (int) faderVar[2]);
		bottomColorOcean = ColorChanger.ClipperColor((int) faderVar[0],
												   (int) faderVar[1],
												   (int) faderVar[2]);
	}
	
	public void startFade(int addValue) {
		//addValue*=-10;
		
		int r = this.getBottomColor().getRed() + addValue;
		int g = this.getBottomColor().getGreen() + addValue;
		int b = this.getBottomColor().getBlue() + addValue;
		
		bottomColorOcean = ColorChanger.ClipperColor(r, g, b);
		
		r = this.getTopColor().getRed() + addValue;
		g = this.getTopColor().getGreen() + addValue;
		b = this.getTopColor().getBlue() + addValue;
		
		topColorOcean = ColorChanger.ClipperColor(r, g, b);
	}
	
	
	public Color getTopColor() {
		return topColorOcean; 
	}
	
	public Color getBottomColor() {
		return bottomColorOcean; 
	}
	
	
	public void setWaveCorners() {
		 waveCorner1=0;
		 waveCorner2=FishMain.WINDOW_LENGTH_Y-averageHeightWave;
		 waveCorner3=FishMain.WINDOW_LENGTH_Y-averageHeightWave+1+FishMain.WINDOW_LENGTH_X;
		 waveCorner4=2*(FishMain.WINDOW_LENGTH_Y-averageHeightWave)+FishMain.WINDOW_LENGTH_X;
		 waveCorner5=2*(FishMain.WINDOW_LENGTH_Y-averageHeightWave)+2*FishMain.WINDOW_LENGTH_X;
		 arrayLength=waveCorner1+waveCorner2+waveCorner3+waveCorner4+waveCorner5;
	}
	
	
	
	//--------------------------Method Break------------------------------//

	
	public int[] createXWave()
	{
		// Side ---1---
		for (int i=waveCorner1;i<waveCorner2; i++) //for the indices 
				xArray[i]=0; //A CONSTANT 0
	
		// ---2----			
		for (int i=waveCorner2; i<waveCorner3; i++)
			xArray[i] = i-waveCorner2; //corrected
		
		// ---3---
		for (int i=waveCorner3; i<waveCorner4; i++)
			xArray[i]=FishMain.WINDOW_LENGTH_X; // A CONSTANT BC X=LENGTH OF X
		
		// ---4---
		for (int i=waveCorner4;i<waveCorner5;i++)
			xArray[i]=FishMain.WINDOW_LENGTH_X-i-waveCorner4;// Starts at window length, decreases

		return xArray;
	}

	
	//--------------------------Method Break------------------------------//
	
	public int[] createYWave()
	{
		//waveHeight controls the shape of the wave
		//Swells controls how much the entire waveform moves vertically up and down
		//tide controls how the waves move left to right
		double waveHeight = GameUtility.sineWave(6, 0.5, 50, 0, 0);
		double swells = GameUtility.sineWave(20, 0.5, 50, 0, 0);
		double tide = GameUtility.sineWave(200, 0.105, 1,0,0);
		
		double waveHeight2 = GameUtility.sineWave(6, 0.5, 30, -2, 0);
		double swells2 = GameUtility.sineWave(-5, 0.5, 50, 0, 0);
		double tide2 = GameUtility.sineWave(20, 0.105, 1,0,0);
		
		double waveHeight3 =  GameUtility.sineWave(-50, 0.05 , 30, -2, 0);

		double swells3 = GameUtility.sineWave(30, 0.1, 8.9, 0, 0);
		
		this.tide3 =GameUtility.sineWave(5, 0.001 , 30, -2, 0);//tide3+1;// GameUtility.sineWave(30, 0.105, 1,0,0);
		
		
		//Side ---1---
		for (int i=waveCorner1;i<waveCorner2;i++)
			yArray[i]=FishMain.WINDOW_LENGTH_Y-i;//bc java is dumb and backwards
		
		//Side ---2---
		for (double i=(double) waveCorner2; i<(double) waveCorner3; i++)
		{
			
			double holdValue1=(FishMain.oceanSurfaceHeight
								+(20+waveHeight)*Math.sin((i-waveCorner2+tide)/20)+swells);
			double holdValue2=(FishMain.oceanSurfaceHeight-2
								+(20+waveHeight2)*Math.sin((i-waveCorner2+tide2)/45)+swells2);
			double holdValue3=(FishMain.oceanSurfaceHeight-2
								+(waveHeight3)*Math.sin((i-waveCorner3+tide3)/400)+swells3);
			
			yArray[(int) i ] = (int) (holdValue1+holdValue2+holdValue3/2)/3;
			
			yArray[(int) i]=GameUtility.Clipper(0, FishMain.WINDOW_LENGTH_Y, yArray[(int) i]);
		}
		
		//Side ---3---
		for (int i=waveCorner3; i<waveCorner4; i++)
		{
			yArray[i]=averageHeightWave+i-waveCorner3;
			yArray[i]=GameUtility.Clipper(0, FishMain.WINDOW_LENGTH_Y, yArray[i]);
		}
		//Side ---4---
		for (int i=waveCorner4; i<waveCorner5; i++)
			yArray[i]=FishMain.WINDOW_LENGTH_Y;
		
		
		return yArray;
		
	//End creatYArray() method
	}

	
	//--------------------------Method Break------------------------------//

	

		

	
//======================================End of Ocean Class===============================//
}

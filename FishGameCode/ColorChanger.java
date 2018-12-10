import java.awt.Color;

/*
 * Class Name: ColorChanger
 * Extends: Color
 * Team: Graphics
 * Primary Authors: Chad
 * 
 * Similar to the GameUtility class, this class contains all the utility methods that relate to colors
 * that change over time. These methods are what enable the modulating colors of the ocean, sun, 
 * stars, and bubbles.
 *  
 */


public class ColorChanger  {
	
	
	//--------------------------Section Start------------------------------//

	//Objective of this method: Every Seconds seconds, the return value will increase to gain,
	//then decrease to zero
	public static int upAndDown(double Seconds, double gain) {
		double time = FishMain.gameTimer.getElapsedTimeInMilliseconds(); //gets current milliseconds of game-->time
		double zeroedTime = time%(Seconds*100); //puts the time into "Second" second intervals
		double halfTime = (Seconds*100)/2; //finds time of the midpoint
		
		
		if (zeroedTime<halfTime)
			return (int) (zeroedTime*gain/halfTime); //multiplier ensures max value is 9
		else
			return (int) (2*gain-gain/halfTime*zeroedTime);
		
	}

	
	//--------------------------Method Break------------------------------//

	//Changes each color value equally (essentially brightens with positive gain, darkens with negative)
	public static Color ColorUpDown(int R, int G, int B, double Seconds, double gain)
	{
		int increment=GameUtility.upAndDown(Seconds,gain);
		
		return new Color(ClipperColor(R, increment),
				ClipperColor(G, increment),
				ClipperColor(B,increment));
	}
	
	//--------------------------Method Break------------------------------//

	
	//Creates a new ColorChanger that gives control over the time and gain changes of each RGB color value
	public static Color ColorUpDown(int R, int G, int B,	double SecondsR, double gainR,
														double SecondsG, double gainG,
														double SecondsB, double gainB)
	{
		int incrementR=GameUtility.upAndDown(SecondsR,gainR);
		int incrementG=GameUtility.upAndDown(SecondsG,gainG);
		int incrementB=GameUtility.upAndDown(SecondsB,gainB);

		return new Color(
						ClipperColor(R, incrementR),
						ClipperColor(G, incrementG),
						ClipperColor(B,incrementB)
												);
	}
	
	//--------------------------Method Break------------------------------//

	/*
	 * Method Name: ColorUpDown
	 * 
	 * This method returns a color whose value oscillates between being brighter,
	 * or darker over a duration set by seconds, and an amount set by gain
	 */
	public static Color ColorUpDown(int R, int G, int B, double Seconds, double gain, int lowThreshold, int highThreshold)
	{
		
		int increment=GameUtility.upAndDown(Seconds,gain);
		
		//Format of the following lines explained
		//GameUtility.Clipper sets it so the color will not go below the argument thresholds.
		//
		R = ClipperColor(R, increment);
		G = ClipperColor(G, increment);
		B = ClipperColor(B, increment);
		
		
		
		return new Color(R,G, B);
	}
	
	
	//--------------------------Method Break------------------------------//

	
	//This method clips any color value above 255 and below 0. If the color value exceeds these values, they get
	// assigned to 255 and 0.
	//This is a more specific clipper.
	public static int ClipperColor(int baseColorVal, int gain)
	{
		if ((baseColorVal+gain) > 255)
			return 255;
		else if ((baseColorVal+gain) < 0)
			return 0;
		else
			return baseColorVal+gain;
	}

	public static Color ClipperColor(int r, int g, int b) {
		r = GameUtility.Clipper(0, 255, r);
		g = GameUtility.Clipper(0, 255, g);
		b = GameUtility.Clipper(0, 255, b);
		
		Color color = new Color(r, g, b);
		
		return color;
	}
	
//======================================End of ColorChanger Class===============================//
}

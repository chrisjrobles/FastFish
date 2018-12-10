
/* Class Name: GameUtility
 * Team: Graphics
 * Primary Author: Chad
 * 
 * This class contains many generic, though useful, methods that get frequently called
 * in methods where parameters change over time.
 * Mainly, these methods return number values that are used to set changing parameters
 * in other classes.
 * 
 */


public class GameUtility {
		

	
	
	public GameUtility() {}
	
	/*
	 * Method Name: randomSeed()
	 * 
	 * This method defines a function that outputs a pseudo-random double
	 * between -1 and 1. The key here is the one-to-one nature of the function.
	 * This allows this method to be used for assigning random numbers to objects in an array.
	 * 
	 *  Background: When trying to add randomness to every object in a set of objects (for example, 
	 *  the bubbles, stars, and murkiness), I needed a way for the random number to be individual
	 *  to each object. Math.random() would put out random numbers, but I didn't want a new random
	 *  number every time the method was called since that would cause new paramters for every object
	 *  every time paintComponent() was called. This provided a solution for me to have a unique
	 *  random number per array element
	 */
	public static double randomSeed(int iterator) {
		iterator*=(Math.PI*iterator);
		
		iterator = iterator < 30 ? (int)randomSeed(iterator*iterator+30) : iterator;
		
		return Math.sin(iterator*1000 + iterator);
	}
	
	
	
	/*
	 * Method Name: plusOrMinuesRandom
	 * Team: Graphics
	 * Primary Author: Chad
	 * 
	 * This method returns a uniformly distributed random number between the two limits
	 * It is should be used to add either a random, small positive or negative number
	 * to another number
	 */
	public static int plusOrMinusRandom(double low, double high) {
		double number = Math.random()*(1+high-low);
		if (number<0)
			number*=-1;
		return (int) (low+number);	
	}
	/*
	 * Method Name: plusOrMinuesRandomDouble
	 * Team: Graphics
	 * Primary Author: Chad
	 * 
	 * Same functionality as plusOrMinusRandom except this returns a double
	 */
	public static double plusOrMinusRandomDouble(double low, double high) {
		double number = Math.random()*(high-low);
		if (number<0)
			number*=-1;
		return (low+number);	
	}
	
	/* Method Name: upAndDown
	 * Team: Graphics
	 * Primary Author: Chad
	 * 
	 * This method returns an integer value that goes from 0->gain->0 over a period of time set by seconds
	 * Basically makes the top half of a triangle waveform
	 * 
	 */
	public static int upAndDown(double Seconds, double gain) {
		 double time = FishMain.gameTimer.getElapsedTimeInSeconds();//gets current milliseconds of game-->time
		 double zeroedTime = time%(Seconds); //puts the time into "Second" second intervals
		 double halfTime = (Seconds)/2; //finds time of the midpoint
		
		 
		
		if (zeroedTime<halfTime)
			return (int) (zeroedTime*gain/halfTime); //multiplier ensures max value is 9
		else
			return (int) (2*gain-gain/halfTime*zeroedTime);
		
	}
	
	/* Method Name: sineWave
	 * Team: Graphics
	 * Primary Author: Chad
	 * 
	 * This method generates a sine wave over time of the game. The input parameters are what form the
	 * shape of the wave.
	 * Gain is the highest value of the wave
	 * Frequency is the frequency of the oscillation in seconds (the method converts to radians)
	 * Phase is the phase offset of the wave
	 * horTrans is the horizontal translation of the waveform
	 * vertTrans is the vertical translation of the waveform
	 * 
	 */
	public static int sineWave(double gain, double frequency, double phase, double vertTrans, double horTrans)
	{
		frequency*=2*3.14159;
		double t =  FishMain.gameTimer.getElapsedTimeInMilliseconds()/1000;
		return (int) (gain*Math.sin(frequency*(t-horTrans)-phase) + vertTrans); 
	}
	
	
	
	/* Method Name: switcher
	 * Team: Graphics
	 * Primary Author: Chad
	 * 
	 * This method will return positive 1 or negative 1, switching at a random time during specified time interval
	 */
	public static int switcher(double specifiedLengthOfTime)
	{
		double interval = createNewInterval(specifiedLengthOfTime);
		if (interval<specifiedLengthOfTime/4)
		{
			int randomizer = (int) (Math.random()*100);
			if (randomizer<15)
				return (int)-1;
			else
				return (int)1;
		}
		return 1;
		
	}
	
	
	
	
	/* Method Name: createNewInterval
	 * Team: Graphics
	 * Primary Author: Chad
	 * 
	 * This method will return the time in the interval of length, 'intervalLength'
	 */
	public static double createNewInterval(double intervalLength)
	{
		double time = FishMain.gameTimer.getElapsedTimeInMilliseconds();//gets current milliseconds of game-->time
		double zeroedTime = time%(intervalLength*100); //puts the time into "Second" second intervals
		return zeroedTime;
	}
	
	
	
	
	/* Method Name: Clipper
	 * Team: Graphics
	 * Primary Author: Chad
	 * 
	 * This frequently used method performs hard clipping.
	 * Setting the lowThreshold and highThreshold sets the limits that the inVale will take on.
	 * For this version of the method, when the value passes a certain threshold, the value returned
	 * equals the threshold that it passed 
	 */
	public static int Clipper(int lowThreshold, int highThreshold, int inValue) {
		if (inValue<lowThreshold)
			return lowThreshold;
		else if (inValue>highThreshold)
			return highThreshold;
		else
			return inValue;
	}
	public static double Clipper(double lowThreshold, double highThreshold, double inValue) {
		if (inValue<lowThreshold)
			return lowThreshold;
		else if (inValue>highThreshold)
			return highThreshold;
		else
			return inValue;
	}
	
	
	/* Method Name: Clipper
	 * Team: Graphics
	 * Primary Author: Chad
	 * 
	 * This method overloads Clipper performing a similar function as above.
	 * The key difference is that when the inValue passes a threshold, the method returns
	 * the setValue input parameter.
	 */
	public static int Clipper(int lowThreshold, int highThreshold, int inValue, int setValue) {
		if (inValue<lowThreshold || inValue>highThreshold)
			return setValue;
		else
			return inValue;	
	}
	public static double Clipper(int lowThreshold, int highThreshold, double inValue, double setValue) {
		if (inValue<lowThreshold || inValue>highThreshold)
			return setValue;
		else
			return inValue;	
	}

	
	/*
	 * Method Name: lowLimiter
	 * 
	 * This limits the lowest possible number. If the number falls below
	 * the lowThreshold, the return value is the lowThreshold
	 */
	public static int lowLimiter(int lowThreshold, int inValue) {
		if (inValue<lowThreshold)
			return lowThreshold;
		else
			return inValue;
	}
	
	
	
	
	/*
	 * Method Name: Wrapper
	 * 
	 * This method is a specific type of Clipper where if the input value
	 * exceeds one of the thresholds, the output value is the other threshold
	 */
	public static int Wrapper(int lowThreshold, int highThreshold, int inValue) {
		if (inValue < lowThreshold)
			return highThreshold;
		else if (inValue > highThreshold)
			return lowThreshold;
		else
			return inValue;
	}
	
	
	/* Method Name: findMinAndMax
	 * Team: Graphics
	 * Primary Author: Chad
	 * 
	 * Creates an array with the lowest and highest point in an array.
	 * Output array = {min, max}
	 * 
	 */
	public static int[] findMinAndMax(int[] array) {
		int[] output = new int[2];
		
		//Put the min at index=0, max at index=1
		output[0] = findMin(array);
		output[1] = findMax(array);

		return output;
	}
	
	/* Method Name: findMax
	 * Team: Graphics
	 * Primary Author: Chad
	 * 
	 * Simple method to return the largest integer value of the array
	 * 
	 */
	public static int findMax(int[] array) {
		int max = array[0];
		for (int i=1; i<array.length; i++)
		{
			if (array[i]>array[i-1])
				max = array[i];
		}
		
		return max;
	}
	
	/* Method Name: findMin
	 * Team: Graphics
	 * Primary Author: Chad
	 * 
	 * Simple method to return the smallest integer value of the array
	 * 
	 */	public static int findMin(int[] array) {
		int min = array[0];
		for (int i=1; i<array.length; i++)
		{
			if (array[i]<array[i-1])
				min = array[i];
		}
		
		return min;
	}

	
}

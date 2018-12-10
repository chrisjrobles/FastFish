import java.time.Instant;

/*
 * Class Name: GameTime
 * Team: Graphics
 * Primary Authors: Chad
 * 
 * The purpose of this class is to allow for an accurate timer that can be referenced
 * throughout our code.
 * The motivation for creating this class is so that in our code we can have timed events
 * that are synchronized with actual time. Something similar to this was attempted in FishMain,
 * but it just didn't work.
 * 
 * How the class works:
 * When you construct a GameTime object, the epoch time is set as the zero
 * 
 */

/*Class Name: GameTime
 * Primary Author: Chad
 * 
 * Upon instantiation, GameTime gets the Epoch second and defines
 * that moment as a zero. So, for any GameTime object, it's methods, such as
 * getElapsedTimeInMilliseconds(), return the elapsed seconds since the 
 * instantiation.
 *
 */


public class GameTime {

	//DATA FIELDS
	
	public double timeMilliseconds;
	public double timeSeconds;
	public long epoch;
	public double nano;
	public static double nanoToMilliseconds =  Math.pow(10,-6);
	public Instant instant = Instant.now();
	
	/*
	 * Constructor: GameTime()
	 * 
	 * Establishes the zero of the GameTime object
	 */
	public GameTime() {
		epoch = instant.getEpochSecond();	
	}
	
	//Returns the elapsed milliseconds since instantiation
	public double getElapsedTimeInMilliseconds() {
		Instant currentInstant = Instant.now();
		timeSeconds = currentInstant.getEpochSecond() - epoch;
		nano = (double) currentInstant.getNano();
		timeMilliseconds = timeSeconds*1000+nano*nanoToMilliseconds;
				
		return timeMilliseconds;
	}
	
	//Returns the seconds since instantiation as a double
	public double getElapsedTimeInSeconds() {
		Instant currentInstant = Instant.now();
		nano = (double) currentInstant.getNano();
		nano*=nanoToMilliseconds/1000;
		
		timeSeconds = currentInstant.getEpochSecond() - epoch+nano;

		return timeSeconds;
	}
	
	
	//Returns the seconds since instantiation as an integer
	//The input string can be any string, though "int" is commonly used
	public int getElapsedTimeInSeconds(String type) {
		Instant currentInstant = Instant.now();
	
		timeSeconds = currentInstant.getEpochSecond() - epoch;

		return (int) timeSeconds;
	}


}

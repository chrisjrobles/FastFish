import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
 * Class Name: MyKeyListener
 * Implements KeyListener
 * Team: UI
 * Primary Authors: Vikram
 * 
 * This is a class to allow the fish to be moved in different positions on the frame. We have a constructor
 * to call the class and then three methods that are implemented with KeyListener. KeyPressed is used because 
 * anytime the arrow keys are pressed, the fish moves. It also works when held down and does not need to be 
 * tapped. The KeyTyped and KeyReleased are methods that come with KeyListener but we do not need to use them 
 * to have the fish move.
 */

public class MyKeyListener implements KeyListener{

	FishyDude fishy; //FishyDude object to get position of fish
	
	public MyKeyListener(FishyDude fishy) {
		this.fishy=fishy;
	}
	
	// Method to use keys to move fish
	public void keyPressed(KeyEvent e)
	{
		switch (e.getKeyCode()) {
        case KeyEvent.VK_DOWN:
            fishy.yPos += 1;
            break;
        case KeyEvent.VK_UP:
            fishy.yPos -= 1;
            break;
        case KeyEvent.VK_LEFT:
            fishy.xPos-= 1;
            break;
        case KeyEvent.VK_RIGHT:
            fishy.xPos+=1;
            break;
        default:
        	fishy.xPos+=0;
		}
    
	}
	//Only called because KeyListener is implemented, no use for program
	public void keyReleased(KeyEvent e) {}
	
	@Override
	public void keyTyped(KeyEvent e) {
		keyPressed(e);
	}
	
}

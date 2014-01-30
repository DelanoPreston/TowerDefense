import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class InputHandlerKeyboard extends KeyAdapter{
	
	Level level;
	
	public InputHandlerKeyboard(Level inLevel){
		level = inLevel;
	}
	public void keyPressed(KeyEvent e){
		level.keyPressed(e);
	}
	public void keyReleased(KeyEvent e){
		level.keyReleased(e);
	}
}

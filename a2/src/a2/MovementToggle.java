package a2;


import sage.input.action.AbstractInputAction;
import net.java.games.input.Event;

public class MovementToggle extends AbstractInputAction {
	private boolean moving = false;
	
	public boolean isMoving()
	{
		return moving;
	}
	public void performAction(float t, Event e)
	{
		moving = !moving;
	}

}

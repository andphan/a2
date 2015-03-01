package a2;

import sage.event.*;

public class CrashEvent extends AbstractGameEvent {
	
	private int numCrash;
	
	public CrashEvent(int n)
	{
		numCrash = n;
	}
	public int getCrash()
	{
		return numCrash;
	}
}

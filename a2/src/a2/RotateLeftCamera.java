package a2;


import graphicslib3D.Point3D;
import graphicslib3D.Vector3D;
import sage.camera.ICamera;
import sage.input.action.AbstractInputAction;

public class RotateLeftCamera extends AbstractInputAction {

	private ICamera camera;
	public RotateLeftCamera(ICamera c)
	{
		camera = c;
	}
	
	public void performAction(float timeElapsed, net.java.games.input.Event e)
	{
		
		// create Vector3D
		
		Vector3D curLoc = new Vector3D(camera.getLocation());
		Vector3D viewDir = camera.getUpAxis().normalize();
		Vector3D newLoc = curLoc.add(viewDir);
				
		// set to new vector
		double dy = newLoc.getY();
		Point3D newPoint = new Point3D(curLoc.getX(), dy, curLoc.getZ());
		camera.setLocation(newPoint);
		
		System.out.println("u " + newPoint.getX());
		System.out.println("v" + newPoint.getY());
		System.out.println("n " + newPoint.getZ());
		
	}
}
package a2;


import graphicslib3D.Point3D;
import graphicslib3D.Vector3D;
import sage.camera.ICamera;
import sage.input.action.AbstractInputAction;

public class RightCameraMovement extends AbstractInputAction {

	private ICamera camera;
	private MovementToggle m;
	public RightCameraMovement(ICamera c)
	{
		camera = c;
	}
	
	public void performAction(float timeElapsed, net.java.games.input.Event e)
	{
		
		// create Vector3D
		
		Vector3D curLoc = new Vector3D(camera.getLocation());
		Vector3D viewDir = camera.getRightAxis().normalize();
		Vector3D newLoc = curLoc.add(viewDir);
				
		// set to new vector
		double dx = newLoc.getX();
		double dy = newLoc.getY();
		double dz = newLoc.getZ();
		Point3D newPoint = new Point3D(dx, dy, dz);
		camera.setLocation(newPoint);
		
		System.out.println("u " + newPoint.getX());
		System.out.println("v" + newPoint.getY());
		System.out.println("n " + newPoint.getZ());
		
	}
}
package a2;



import graphicslib3D.Point3D;
import graphicslib3D.Vector3D;
import sage.camera.ICamera;
import sage.input.action.AbstractInputAction;

public class RYAxisMovement extends AbstractInputAction {
	
	private ICamera camera;
	private float speed;
	
	public RYAxisMovement(ICamera c, float s)
	{
		camera = c;
		speed = s;
	}
	
	public void performAction(float elapsedTime, net.java.games.input.Event e)
	{
		Vector3D curLoc = new Vector3D(camera.getLocation());
		Vector3D viewDir = camera.getRightAxis().normalize();
		Vector3D newLoc = curLoc.add(viewDir);
		
		 if (e.getValue() < -0.2)
		 { newLoc = curLoc.add(viewDir.mult(speed * elapsedTime)); }
		 else { if (e.getValue() > 0.2)
		 { newLoc = curLoc.minus(viewDir.mult(speed * elapsedTime)); }
		 else { newLoc = curLoc; }
		 }
		double newX = newLoc.getX();
		double newY = newLoc.getY();
		double newZ = newLoc.getZ();
		Point3D newLocation = new Point3D(newX, newY, newZ);
		camera.setLocation(newLocation);
		System.out.println("u " + newLocation.getX()); // increases U  
		System.out.println("v" + newLocation.getY());
		System.out.println("n " + newLocation.getZ());
		
	}
	

}

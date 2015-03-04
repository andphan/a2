package a2;

import net.java.games.input.Event;
import graphicslib3D.Point3D;
import graphicslib3D.Vector3D;
import sage.camera.ICamera;
import sage.input.IInputManager;
import sage.input.action.AbstractInputAction;
import sage.input.action.IAction;
import sage.scene.SceneNode;
import sage.util.MathUtils;



public class OrbitCamera {

	// initialize variables
	private ICamera camera;
	private SceneNode target;
	private float cameraYAxis;
	private float cameraHeight;
	private float distance;
	private Point3D avatarPosition;
	private Vector3D worldUpVec;
	
	public OrbitCamera(ICamera c, SceneNode t, IInputManager im, String cName)
	{
		this.camera = c;
		this.target = t;
		worldUpVec = new Vector3D(0,1,0);
		distance = 10f;
		cameraYAxis = 180;
		cameraHeight = 20f;
		updatePos(0.0f);
		setupInput(im, cName);
		
	}
	
	public void updatePos(float time)
	{
	//	 System.out.println("update called");
		updateTarget();
		updateCameraPos();
		camera.lookAt(avatarPosition, worldUpVec);

	}
	private void updateTarget()
	{
		avatarPosition = new Point3D(target.getWorldTranslation().getCol(3));
	//	 System.out.println("avatar Position " + avatarPosition);
	}
	private void updateCameraPos()
	{
		double theta = cameraYAxis;
		double phi = cameraHeight;
		double r = distance;
		
		Point3D currentPos = MathUtils.sphericalToCartesian(theta, phi, r);
		Point3D newLoc = currentPos.add(avatarPosition);
		camera.setLocation(newLoc);
//		 System.out.println("Camera loc" + newLoc);
	}
	private void setupInput(IInputManager im, String cn)
	{
		IAction orbitMovement = new OrbitMovementAction();
		IAction orbitZoom = new zoomFunction();
		
		im.associateAction(cn, net.java.games.input.Component.Identifier.Axis.RX, orbitMovement, IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
		im.associateAction(cn, net.java.games.input.Component.Identifier.Axis.RY, orbitZoom, IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
	//	 System.out.println("setup action called");
	}
	
	private class OrbitMovementAction extends AbstractInputAction
	{

		
		public void performAction(float time, Event e) {
			
			float amount;
			 if (e.getValue() < -0.2)
			 { amount = -0.1f; }
			 else { if (e.getValue() > 0.2)
			 { amount = 0.1f; }
			 else { amount = 0.0f; }
			 }
			 cameraYAxis += amount;
			 cameraYAxis = cameraYAxis % 360;
			 
		//	 System.out.println("perform action called");
		}
		
	}
	private class zoomFunction extends AbstractInputAction
	{

		@Override
		public void performAction(float arg0, Event arg1) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}

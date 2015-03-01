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

public class CameraOrbit {

	private ICamera camera;
	private SceneNode target;
	private float cameraYAxis;
	private float cameraElevation;
	private float distance;
	private Point3D position;
	private Vector3D worldUpVec;
	
	public CameraOrbit(ICamera c, SceneNode t, IInputManager im, String cName)
	{
		this.camera = c;
		this.target = t;
		distance = 5.0f;
		cameraYAxis = 180;
		cameraElevation = 20.0f;
		update(0.0f);
		setupInput(im, cName);
	}
	
	public void update(float time)
	{
		updateTarget();
		updateCameraPosition();
		camera.lookAt(position, worldUpVec);
	}
	public void updateTarget()
	{
		position = new Point3D(target.getWorldTranslation().getCol(3));
	}
	public void updateCameraPosition()
	{
		double theta = cameraYAxis;
		double phi = cameraElevation;
		double r = distance;
		
		Point3D relPos = MathUtils.sphericalToCartesian(theta, phi, r);
		Point3D newCameraLoc = relPos.add(position);
		camera.setLocation(newCameraLoc);
		
	}
	private void setupInput(IInputManager im, String cn)
	{
		IAction orbitAction = new OrbitAroundAction();
		im.associateAction(cn,  net.java.games.input.Component.Identifier.Axis.X, orbitAction, IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
		
	}
	private class OrbitAroundAction extends AbstractInputAction {
		
		public void performAction(float time, Event e)
		{
			float amt;
			 if (e.getValue() < -0.2) { 
				 amt=-0.1f;
				 }
			 else { 
				 if (e.getValue() > 0.2) { 
					 amt=0.1f; 
					 }
			 else { amt=0.0f; }
			 
			 cameraYAxis += amt;
			 cameraYAxis = cameraYAxis % 360;
		}
		}

	}

}

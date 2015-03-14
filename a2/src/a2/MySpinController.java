package a2;

import sage.scene.Controller;
import sage.scene.SceneNode;
import graphicslib3D.Matrix3D;

public class MySpinController extends Controller {

	private double cycle = 2000.0;
	private double time;
	
	public void setTime(double n)
	{
		cycle = n;
	}
	
	public void update(double t)
	{
		time += t;
	
		if (time > cycle)
		{
			time = 0.0;
		}
		
		
		Matrix3D newTrans = new Matrix3D();
		newTrans.rotateY(45);
		for (SceneNode node : controlledNodes)
		{
			Matrix3D curTrans = node.getLocalRotation();
			curTrans.rotateY(45);
			node.setLocalRotation(curTrans);
		}
	}
}

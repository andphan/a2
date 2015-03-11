package a2;

import sage.scene.Controller;
import sage.scene.SceneNode;
import graphicslib3D.Matrix3D;

public class MyZRotateController extends Controller {

	private double rate = .002;
	private double cycle = 2000.0;
	private double time;
	private double direction = 1.0;
	
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
		newTrans.rotateZ(180);
		for (SceneNode node : controlledNodes)
		{
			Matrix3D curTrans = node.getLocalRotation();
			curTrans.rotateY(180);
			node.setLocalRotation(curTrans);
		}
	}
}

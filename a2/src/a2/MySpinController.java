package a2;

import sage.scene.Controller;
import sage.scene.SceneNode;
import graphicslib3D.Matrix3D;

public class MySpinController extends Controller {

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
		double transA = rate*t;
		
		if (time > cycle)
		{
			direction = -direction;
			time = 0.0;
		}
		
		
		transA = direction * transA;
		
		Matrix3D newTrans = new Matrix3D();
		newTrans.translate(transA, transA, transA);

		for (SceneNode node : controlledNodes)
		{
			Matrix3D curTrans = node.getLocalTranslation();
			curTrans.concatenate(newTrans);
			node.setLocalTranslation(curTrans);
		}
	}
}

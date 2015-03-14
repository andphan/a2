package a2;


import graphicslib3D.Matrix3D;

import java.nio.*;

import sage.event.IEventListener;
import sage.event.IGameEvent;
import sage.scene.TriMesh;

public class myNewTriMesh extends TriMesh implements IEventListener{

	private static float[] vrts = new float[] {1,1,1,1,-1,0,1,1,0,-1,1,0,-1,-1,-1}; 
	private static float[] cl = new float[] {1,0,0,1,0,1,0,1,0,0,1,1,1,1,0,1,1,0,1,1};
	private static int[] tri = new int[] {4,4,4,4,4,3,4,3,1,4,1,1,1,1,2,4,1,4};

	int x = 1;
	int y = 1;
	int z = 1;

	public myNewTriMesh()
	{
		FloatBuffer vertBuf = com.jogamp.common.nio.Buffers.newDirectFloatBuffer(vrts);
		FloatBuffer cBuf = com.jogamp.common.nio.Buffers.newDirectFloatBuffer(cl);
		IntBuffer triBuf = com.jogamp.common.nio.Buffers.newDirectIntBuffer(tri);
	
		this.setVertexBuffer(vertBuf);
		this.setColorBuffer(cBuf);
		this.setIndexBuffer(triBuf);
		
	}
	

	public boolean handleEvent(IGameEvent e) {
		CrashEvent c = (CrashEvent) e;
		int crashInc = c.getCrash();
		Matrix3D tCS = this.getLocalScale();
		this.setLocalScale(tCS);
		if (crashInc % 2 == 0)
			System.out.println("no scale");
		else
			tCS.scale(x++, y++, z++);
		//	System.out.println("x: " + x);
		//	System.out.println("z: " + z);
		//	System.out.println(this.getLocalScale());
		
		return true;
	}
	
}

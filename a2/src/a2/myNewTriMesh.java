package a2;


import java.nio.*;

import sage.event.IEventListener;
import sage.event.IGameEvent;
import sage.scene.TriMesh;

public class myNewTriMesh extends TriMesh implements IEventListener{

	private static float[] vrts = new float[] {1,1,1,1,-1,0,1,1,0,-1,1,0,-1,-1,-1}; 
	private static float[] cl = new float[] {1,0,0,1,0,1,0,1,0,0,1,1,1,1,0,1,1,0,1,1};
	private static int[] tri = new int[] {4,4,4,4,4,3,4,3,1,4,1,1,1,1,2,4,1,4};

	int x = 10;
	int y = 10;
	int z = 10;

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
		
		if (crashInc % 2 == 0)
		{
			System.out.println("Scale up this trimesh!");
			this.scale(x++, y++, z++);
		}
		else
			System.out.println("Scale down this trimesh??");
		return true;
	}
	
}

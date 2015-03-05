package a2;

import java.awt.Canvas;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import sage.display.DisplaySystem;
import sage.display.IDisplaySystem;
import sage.renderer.IRenderer;
import sage.renderer.RendererFactory;

public class MyDisplaySystem implements IDisplaySystem {

	private JFrame frame;
	private GraphicsDevice device;
	private IRenderer myRenderer;
	private int width;
	private int height;
	private int depth;
	private int fpsRate;
	private Canvas rendererCanvas;
	private boolean created;
	private boolean isFullScreen;
	
	public MyDisplaySystem(int w, int h, int z, int fps, boolean fS, String rName)
	{
		width = w;
		height = h;
		depth = z;
		fpsRate = fps;
		this.isFullScreen = fS;
		
		myRenderer = RendererFactory.createRenderer(rName);
		if (myRenderer == null)
		{
			throw new RuntimeException("Renderer not found");
		}
		
		rendererCanvas = myRenderer.getCanvas();
		frame = new JFrame("Treasure Hunt 2015");
		frame.add(rendererCanvas);
		
		DisplayMode displayMode = 
				new DisplayMode(width, height, depth, fpsRate);
		initScreen(displayMode, isFullScreen);
		
		DisplaySystem.setCurrentDisplaySystem(this);
		frame.setVisible(true);
		created = true;
	}
	
	private void initScreen(DisplayMode dMode, boolean FSrequest)
	{
		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		device = environment.getDefaultScreenDevice();
		
		
		if (device.isFullScreenSupported() && FSrequest)
		{
			frame.setUndecorated(true);
			frame.setResizable(false);
			frame.setIgnoreRepaint(true);
		}
		
		 if (dMode != null && device.isDisplayChangeSupported())
		 { try
		 { device.setDisplayMode(dMode);
		 frame.setSize(dMode.getWidth(), dMode.getHeight());
		 } catch (Exception ex)
		 { System.err.println("Exception setting DisplayMode: " + ex ); }
		 } else
		 { System.err.println ("Cannot set display mode"); }
		// } else{
		// frame.setSize(dMode.getWidth(),dMode.getHeight());
		// frame.setLocationRelativeTo(null); //centers window on screen
		// } 
	}

}

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
	private int width, height, bitDepth, refresh;
	private Canvas myCanvas;
	private boolean isMade;
	private boolean fullScreenToggle;
	
	public MyDisplaySystem(int w, int h, int d, int r, boolean fs, String rName)
	{
		width = w;
		height = h;
		bitDepth = d;
		refresh = r;
		this.fullScreenToggle = fs;
		
		myRenderer = RendererFactory.createRenderer(rName);
		if (myRenderer == null)
		{
			throw new RuntimeException("Unable to find renderer");
		}
		
		myCanvas = myRenderer.getCanvas();
		frame = new JFrame("Treasure Hunt 2015");
		frame.add(myCanvas);
		
		DisplayMode dMode = new DisplayMode(width, height, bitDepth, refresh);
		initScreen(dMode, fullScreenToggle);
		
		DisplaySystem.setCurrentDisplaySystem(this);
		frame.setVisible(true);
		isMade = true;
	}
	
	private void initScreen(DisplayMode d, boolean f)
	{
		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		device = environment.getDefaultScreenDevice();
		
		if (device.isFullScreenSupported() && f)
		 { frame.setUndecorated(true); // suppress title bar, borders, etc.
		 frame.setResizable(false); // full-screen so not resizeable
		 frame.setIgnoreRepaint(true); // ignore AWT repaints
		 // Put device in full-screen mode. This must be done before attempting
		 // to change the DisplayMode; the application must first have FSEM
		 device.setFullScreenWindow(frame);
		 //try to set the full-screen device DisplayMode
		 if (d != null && device.isDisplayChangeSupported())
		 { try
		 { device.setDisplayMode(d);
		 frame.setSize(d.getWidth(), d.getHeight());
		 } catch (Exception ex)
		 { System.err.println("Exception setting DisplayMode: " + ex ); }
		 } else
		 { System.err.println ("Cannot set display mode"); }
		 } else
		 { //use windowed mode – set JFrame characteristics
		 frame.setSize(d.getWidth(),d.getHeight());
		 frame.setLocationRelativeTo(null); //centers window on screen
		}
	}

	@Override
	public void addKeyListener(KeyListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addMouseListener(MouseListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addMouseMotionListener(MouseMotionListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		frame.dispose();
	}

	@Override
	public void convertPointToScreen(Point arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getBitDepth() {
		// TODO Auto-generated method stub
		return bitDepth;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}

	@Override
	public int getRefreshRate() {
		// TODO Auto-generated method stub
		return refresh;
	}

	@Override
	public IRenderer getRenderer() {
		// TODO Auto-generated method stub
		return myRenderer;
	}
	

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public boolean isCreated() {
		// TODO Auto-generated method stub
		return isMade;
	}

	@Override
	public boolean isFullScreen() {
		// TODO Auto-generated method stub
		return fullScreenToggle;
	}

	@Override
	public boolean isShowing() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setBitDepth(int b) {
		bitDepth = b;
	}

	@Override
	public void setCustomCursor(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHeight(int h) {
		// TODO Auto-generated method stub
		height = h;
	}

	@Override
	public void setPredefinedCursor(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRefreshRate(int r) {
		// TODO Auto-generated method stub
		refresh = r;
	}

	@Override
	public void setTitle(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setWidth(int w) {
		// TODO Auto-generated method stub
		width = w;
	}
	
}

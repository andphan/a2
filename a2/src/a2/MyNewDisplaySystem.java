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

public class MyNewDisplaySystem implements IDisplaySystem {
	
	private JFrame frame;
	private GraphicsDevice device;
	private IRenderer renderer;
	private int width, height, bitDepth, refreshRate;
	private Canvas rendererCanvas;
	private boolean isMade;
	private boolean fullScreenToggle;
	
	public MyNewDisplaySystem(int w, int h, int d, int r, boolean fs, String render)
	{
		width = w;
		height = h;
		bitDepth = d;
		refreshRate = r;
		this.fullScreenToggle = fs;
		
		
		renderer = RendererFactory.createRenderer(render);
		frame = new JFrame("Treasure Hunt 2015");
		frame.add(rendererCanvas);
		
		DisplayMode displayMode = new DisplayMode(width, height, bitDepth, refreshRate);
		initScreen(displayMode, fullScreenToggle);
		
		DisplaySystem.setCurrentDisplaySystem((IDisplaySystem) this);
		frame.setVisible(true);
		isMade = true;
		
	}
	
	private void initScreen(DisplayMode dMode, boolean fs)
	{
		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		device = environment.getDefaultScreenDevice();
		
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setIgnoreRepaint(true);
		
		device.setDisplayMode(dMode);
		frame.setSize(dMode.getWidth(), dMode.getHeight());
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
		
	}

	@Override
	public void convertPointToScreen(Point arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getBitDepth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRefreshRate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IRenderer getRenderer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isCreated() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFullScreen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isShowing() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setBitDepth(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCustomCursor(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHeight(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPredefinedCursor(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRefreshRate(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTitle(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setWidth(int arg0) {
		// TODO Auto-generated method stub
		
	}

}

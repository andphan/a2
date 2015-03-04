package a2;

import sage.app.BaseGame;
import sage.camera.*;
import sage.display.*;
import sage.event.EventManager;
import sage.event.IEventListener;
import sage.event.IEventManager;
import sage.input.*;
import sage.input.action.*;
import sage.renderer.IRenderer;
import sage.scene.*;
import sage.scene.shape.*;
import graphicslib3D.Matrix3D;
import graphicslib3D.Point3D;
import graphicslib3D.Vector3D;

import java.util.Random;
import java.awt.Color;
import java.nio.*;

import net.java.games.input.Controller;
import net.java.games.input.*;


public class MyGame extends BaseGame implements IEventListener{
	// what type of objects are in the game
	
	OrbitCamera oc1, oc2;
	Rectangle rect1;
	Sphere sph;
	Cylinder cyl;
	myNewTriMesh myT;
	Cube cub;
	IDisplaySystem display;
	ICamera camera;
	ICamera p1Camera, p2Camera;
	IInputManager im;
	IEventManager em;
	private int numHit;
	private int score = 0;
	private SceneNode p1, p2;
	private IRenderer renderer;
	private HUDString p1ScoreDisplay;
	private HUDString p2ScoreDisplay;
	private HUDString timeDisplay;
	private String kbName;
	private String gpName;
	private float time = 0;
	int crashInc = 0;
	
		public void initGame() // override
		{	
			IInputManager im = getInputManager();
			em = EventManager.getInstance();
			renderer = getDisplaySystem().getRenderer();
			// initialize Managers
			System.out.println("initGame call");
			initGameObjects();	
			createPlayers();	

			kbName = im.getKeyboardName();
			gpName = im.getFirstGamepadName();
			
			
			oc1 = new OrbitCamera(p1Camera, p1, im, gpName);
			oc2 = new OrbitCamera(p2Camera, p2, im, gpName);
			
			// create keyboard actions
			MovementToggle movement = new MovementToggle();
			IAction quitGame = new QuitGameAction(this);
			IAction moveForward = new MoveForwardAction(p1);
		//	IAction moveForward = new ForwardCameraMovement(camera, movement);
			IAction moveBackward = new BackCameraMovement(camera, movement);
			IAction moveLeft = new LeftCameraMovement(camera);
			IAction moveRight = new RightCameraMovement(camera);
			IAction rotateUp = new RotateUpCamera(camera);
			IAction rotateDown = new RotateDownCamera(camera);
			IAction rotateLeft = new RotateLeftCamera(camera);
			IAction rotateRight = new RotateRightCamera(camera);
		
			// create game controller actions
			
			IAction controllerX = new XAxisMovement(camera, 0.01f);
			IAction controllerY = new YAxisMovement(camera, 0.01f);
			IAction controllerRX = new RXAxisMovement(camera, 0.01f);
			IAction controllerRY = new RYAxisMovement(camera, 0.01f);
		
			/* figure out why laptop cannot run with im.associateAction */
			
			// Associate actions with keyboard  PLAYER 1
		//	im.associateAction(kbName, net.java.games.input.Component.Identifier.Key.W, moveForward, 
		//			IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
		/*	im.associateAction(kbName, net.java.games.input.Component.Identifier.Key.S, moveBackward, 
					IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);			
			im.associateAction(kbName, net.java.games.input.Component.Identifier.Key.A, moveLeft, 
					IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);	
			im.associateAction(kbName, net.java.games.input.Component.Identifier.Key.D, moveRight, 
					IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);	
			im.associateAction(kbName, net.java.games.input.Component.Identifier.Key.LEFT, rotateLeft, 
					IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);	
			im.associateAction(kbName, net.java.games.input.Component.Identifier.Key.RIGHT, rotateRight, 
				IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);	
			im.associateAction(kbName, net.java.games.input.Component.Identifier.Key.UP, rotateUp, 
					IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);	
			im.associateAction(kbName, net.java.games.input.Component.Identifier.Key.DOWN, rotateDown, 
					IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);	
			*/
			im.associateAction(kbName, net.java.games.input.Component.Identifier.Key.ESCAPE, quitGame, 
					IInputManager.INPUT_ACTION_TYPE.ON_PRESS_ONLY);
			
			
			// associate actions with controllers PLAYER 2
		//    im.associateAction(gpName, net.java.games.input.Component.Identifier.Axis.RY, controllerRY,
		//			IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
			im.associateAction(gpName, net.java.games.input.Component.Identifier.Axis.RX, moveForward,
					IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
		//	im.associateAction(gpName, net.java.games.input.Component.Identifier.Axis.X, controllerX,
		//			IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
		//	im.associateAction(gpName, net.java.games.input.Component.Identifier.Axis.Y, controllerY,
		//			IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
		}
			
		public void initGameObjects()
		{
			display = getDisplaySystem();
			display.setTitle("Treasure Hunt 2015");
		
			
			// 	create new objects by using scale()
			Random rng = new Random();
			float ax = rng.nextFloat()-(float)0.5;
			float ay = rng.nextFloat()-(float)0.5;
			float bx = rng.nextFloat()-(float)0.5;
			float by = rng.nextFloat()-(float)0.5;
			float cx = rng.nextFloat()-(float)0.5;
			float cy = rng.nextFloat()-(float)0.5;
			float dx = rng.nextFloat()-(float)0.5;
			float dy = rng.nextFloat()-(float)0.5;
			float ex = rng.nextFloat()-(float)0.5;
			float ey = rng.nextFloat()-(float)0.5;
		
			// objects
			rect1 = new Rectangle();
			Matrix3D rectM = rect1.getLocalTranslation();
			rectM.translate(ax, 0, ay);
			rect1.setLocalTranslation(rectM);
			addGameWorldObject(rect1);
			System.out.println("rect x : " + ax + " rect y : " + ay);
			rect1.updateWorldBound();

			
			sph = new Sphere();
			Matrix3D sphM = sph.getLocalTranslation();
			sphM.translate(bx, 0, by);
			sph.setLocalTranslation(sphM);
			addGameWorldObject(sph);
			System.out.println("sph x : " + bx + " sph y : " + by);
			sph.updateWorldBound();

			
			cyl = new Cylinder();
			Matrix3D cylM = cyl.getLocalTranslation();
			cylM.translate(cx, 0, cy);
			cyl.setLocalTranslation(cylM);
			addGameWorldObject(cyl);
			System.out.println("cyl x : " + cx + " cyl y : " + cy);
			cyl.updateWorldBound();

			
			cub = new Cube();
			Matrix3D cubM = cub.getLocalTranslation();
			cubM.translate(ex, 0, ey);
			cub.setLocalTranslation(cubM);
			addGameWorldObject(cub);
			System.out.println("cub x : " + ex + " cub y : " + ey);
			cub.updateWorldBound();
			
		
			// triMesh treasurechest
			myT = new myNewTriMesh();
			Matrix3D myTM = myT.getLocalTranslation();
			myTM.translate(0, 0, 0);
			myT.setLocalTranslation(myTM);
			addGameWorldObject(myT);
			System.out.println("myT x : "  + " myT y : " + dy);
			myT.updateWorldBound();

			



			// add x, y, and z coordinates
			Point3D origin = new Point3D(0,0,0);
			Point3D xEnd = new Point3D(100,0,0);
			Point3D yEnd = new Point3D(0,100,0);
			Point3D zEnd = new Point3D(0,0,100);
			Line xAxis = new Line(origin, xEnd, Color.red, 1);
			Line yAxis = new Line(origin, yEnd, Color.green, 1);
			Line zAxis = new Line(origin, zEnd, Color.blue, 1);
			addGameWorldObject(xAxis);
			addGameWorldObject(yAxis);
			addGameWorldObject(zAxis);
			
		
			
			// add HUD
			p1ScoreDisplay = new HUDString("P1 Score = " + score);
			p1ScoreDisplay.setColor(Color.orange);
			addGameWorldObject(p1ScoreDisplay);
			p2ScoreDisplay = new HUDString("P2 Score = " + score);
			p2ScoreDisplay.setLocation(0, 0.050);
			p2ScoreDisplay.setColor(Color.GREEN);
			addGameWorldObject(p2ScoreDisplay);
			
			timeDisplay = new HUDString("Time = " + time);
			timeDisplay.setColor(Color.WHITE);
			timeDisplay.setLocation(0, 0.025);
			addGameWorldObject(timeDisplay);
			
			super.update(0.0f);
			
		}
		
		public void createPlayers()
		{
			p1 = new Cube("Player 1");
			Matrix3D p1T = p1.getLocalTranslation();
			p1T.translate(0, 0, 50);
			p1.setLocalTranslation(p1T);
			Matrix3D p1R = new Matrix3D();
			p1R.rotateY(45.0);
			p1.setLocalRotation(p1R);
			addGameWorldObject(p1);
			
			p1Camera = new JOGLCamera(renderer);
			p1Camera.setPerspectiveFrustum(60, 2, 1, 1000);
			p1Camera.setViewport(0, 1.0, 0.0, 0.45);
			
			
			p2 = new Cube("Player 2");
			Matrix3D p2T = p2.getLocalTranslation();
			p2T.translate(0, 0, 50);
			p2.setLocalTranslation(p2T);
			Matrix3D p2R = new Matrix3D();
			p2R.rotateY(45.0);
			p2.setLocalRotation(p2R);
			addGameWorldObject(p2);
			
			p2Camera = new JOGLCamera(renderer);
			p2Camera.setPerspectiveFrustum(60, 2, 1, 1000);
			p2Camera.setViewport(0, 1.0, 0.55, 1.00);
			
			
			// player HUDs
			
			HUDString p1ID = new HUDString("Player 1");
			p1ID.setName("Player1ID");
			p1ID.setLocation(0.01, 0.06);
			p1ID.setRenderMode(sage.scene.SceneNode.RENDER_MODE.OPAQUE);
			p1ID.setColor(Color.orange);
			p1ID.setCullMode(sage.scene.SceneNode.CULL_MODE.NEVER);
			p1Camera.addToHUD(p1ID);
			
			HUDString p2ID = new HUDString("Player 2");
			p2ID.setName("Player2ID");
			p2ID.setLocation(0.01, 0.06);
			p2ID.setRenderMode(sage.scene.SceneNode.RENDER_MODE.OPAQUE);
			p2ID.setColor(Color.green);
			p2ID.setCullMode(sage.scene.SceneNode.CULL_MODE.NEVER);
			p2Camera.addToHUD(p2ID);
			
		}
		
		public void update(float elapsedTimeMS)
		{
			
			oc1.updatePos(elapsedTimeMS);
			oc2.updatePos(elapsedTimeMS);

			// overwritten
			// update score
			p1ScoreDisplay.setText("P1 Score = " + score);
			p2ScoreDisplay.setText("P2 Score = " + score);
			
			time += elapsedTimeMS;
		
			
			timeDisplay.setText("Time = " + (time/1000));

			// collision 
/*			if (rect1.getWorldBound().contains(camera.getLocation()))
			{
				crashInc++;
				CrashEvent newCrash = new CrashEvent(crashInc);
				em.triggerEvent(newCrash);
				System.out.println("rectangle hit");
				score++;
				System.out.println("removing rectangle object.");
				removeGameWorldObject(rect1);
			}
			if (sph.getWorldBound().contains(camera.getLocation()))
			{
				crashInc++;
				CrashEvent newCrash = new CrashEvent(crashInc);
				em.triggerEvent(newCrash);
				System.out.println("sphere hit");
				score++;
				System.out.println("removing sphere object.");
				removeGameWorldObject(sph);
			}
			if (cyl.getWorldBound().contains(camera.getLocation()))
			{
				crashInc++;
				CrashEvent newCrash = new CrashEvent(crashInc);
				em.triggerEvent(newCrash);
				System.out.println("cylinder hit");
				score++;
				System.out.println("removing cylinder object.");
				removeGameWorldObject(cyl);
			}
			if (cub.getWorldBound().contains(camera.getLocation()))
			{
				crashInc++;
				CrashEvent newCrash = new CrashEvent(crashInc);
				em.triggerEvent(newCrash);
				System.out.println("cube hit");
				score++;
				System.out.println("removing rectangle object.");
				removeGameWorldObject(cub);
			}
	*/
		}
	protected void render()
	{
		renderer.setCamera(p1Camera);
		super.render();
		renderer.setCamera(p2Camera);
		super.render();
	}
}

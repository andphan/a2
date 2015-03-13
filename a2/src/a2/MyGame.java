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

import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import java.nio.*;

import net.java.games.input.Component.Identifier.Axis;
import net.java.games.input.Controller;
import net.java.games.input.*;


public class MyGame extends BaseGame implements IEventListener{
	// what type of objects are in the game
	
	OrbitCamera oc1, oc2;
	
	// treasures
	Rectangle rect1;

	Sphere sph;
	Cylinder cyl;
	myNewTriMesh myT;
	Cube cub;
	
	// plane
	Rectangle theGround;
	Plane thePlane;
	
	// bombs
	Pyramid pyrA, pyrB, pyrC, pyrD, pyrE;
	
	
	IDisplaySystem display;
	ICamera p1Camera, p2Camera;
	ICamera camera;
	IInputManager im;
	IEventManager em;
	private int numHit;
	private int p1Score = 0;
	private int p2Score = 0;
	private SceneNode p1, p2;
	private IRenderer renderer;
	private HUDString timeDisplay;
	private HUDString p1ID, p2ID;
	private HUDString p1ScoreString, p2ScoreString;
	private String kbName, mName;
	private String gpName, gpName2;
	private float time = 0;
	int crashInc = 0;
	Group treasures, bombs;
	
	
/*
 * 	(non-Javadoc)
 * @see sage.app.BaseGame#initGame()
 * 
 * 
 *  3-8-15 - fixed controller issues
 *  3-9-15 - plane needs to be fixed
 *  3-10-15 - going to try treasures - need to change up to do rotate/spin
 *  								 - need to add another group ... trees or rocks?
 */
	
		public void initGame() // override
		{	
		
			em = EventManager.getInstance();
			renderer = getDisplaySystem().getRenderer();
			// initialize Managers
			initGameObjects();	
			createPlayers();	
			initInputs();
			
			oc1 = new OrbitCamera(p1Camera, p1, im, mName);
			oc2 = new OrbitCamera(p2Camera, p2, im, gpName);
			
		//	super.update(0.0f);
			
		}
			
		public void initGameObjects()
		{
			display = getDisplaySystem();
			display.setTitle("Treasure Hunt 2015");
			
			
			// 	create new objects by using scale()
		
			// plane
			theGround = new Rectangle();
			Matrix3D theGroundM = theGround.getLocalTranslation();
			theGroundM.translate(0, 0, 0);
			theGround.setLocalTranslation(theGroundM);
			theGround.setColor(Color.LIGHT_GRAY);
			theGround.scale(1000, 1000, 0);
			Matrix3D theGR = new Matrix3D();
			theGR.rotateX(90.0);
			theGround.setLocalRotation(theGR);
			
			addGameWorldObject(theGround);
	
			// treasures
			rect1 = new Rectangle();
			Matrix3D rectM = rect1.getLocalTranslation();
			rectM.translate(Math.random()*150, 0, Math.random()*150);
			rect1.setLocalTranslation(rectM);
			Matrix3D rectS = rect1.getLocalScale();
			rectS.scale(10, 10, 10);
			rect1.setLocalScale(rectS);
		//	System.out.println("rect x : " + ax + " rect y : " + ay);
			rect1.updateWorldBound();

			
			sph = new Sphere();
			Matrix3D sphM = sph.getLocalTranslation();
			sphM.translate(Math.random()*150, 0, Math.random()*150);
			sph.setLocalTranslation(sphM);
			Matrix3D sphS = sph.getLocalScale();
			sphS.scale(20, 20, 20);
			sph.setLocalScale(sphS);
			sph.setColor(Color.black);
		//	System.out.println("sph x : " + bx + " sph y : " + by);
			sph.updateWorldBound();

			
			cyl = new Cylinder();
			Matrix3D cylM = cyl.getLocalTranslation();
			cylM.translate(Math.random()*150, 0, Math.random()*150);
			cyl.setLocalTranslation(cylM);
			cyl.setColor(Color.black);
		//	System.out.println("cyl x : " + cx + " cyl y : " + cy);
			cyl.updateWorldBound();

			
			cub = new Cube();
			Matrix3D cubM = cub.getLocalTranslation();
			cubM.translate(Math.random()*150, 0, Math.random()*150);
			cub.setLocalTranslation(cubM);
		//	System.out.println("cub x : " + ex + " cub y : " + ey);
			cub.updateWorldBound();
			
		
			// triMesh treasurechest
			myT = new myNewTriMesh();
			Matrix3D myTM = myT.getLocalTranslation();
			myTM.translate(100, 0, 100);
			myT.setLocalTranslation(myTM);
		//	System.out.println("myT x : "  + " myT y : " + dy);
			myT.updateWorldBound();
			
			
			// bomb creation
			pyrA = new Pyramid();
			Matrix3D pyrAM = pyrA.getLocalTranslation();
			pyrAM.translate(Math.random()*150, 0, Math.random()*150);
			pyrA.setLocalTranslation(pyrAM);
			pyrA.updateWorldBound();
			
			pyrB = new Pyramid();
			Matrix3D pyrBM = pyrB.getLocalTranslation();
			pyrBM.translate(Math.random()*150, 0, Math.random()*150);
			pyrB.setLocalTranslation(pyrBM);
			pyrB.updateWorldBound();
			
			pyrC = new Pyramid();
			Matrix3D pyrCM = pyrC.getLocalTranslation();
			pyrCM.translate(Math.random()*150, 0, Math.random()*150);
			pyrC.setLocalTranslation(pyrCM);
			pyrC.updateWorldBound();
			
			pyrD = new Pyramid();
			Matrix3D pyrDM = pyrD.getLocalTranslation();
			pyrDM.translate(Math.random()*150, 0, Math.random()*150);
			pyrD.setLocalTranslation(pyrDM);
			pyrD.updateWorldBound();
			
			
			pyrE = new Pyramid();
			Matrix3D pyrEM = pyrE.getLocalTranslation();
			pyrEM.translate(Math.random()*150, 0, Math.random()*150);
			pyrE.setLocalTranslation(pyrEM);
			pyrE.updateWorldBound();
			
			

			// group creation
			treasures = new Group("base");
			treasures.addChild(rect1);
			treasures.addChild(sph);
			treasures.addChild(cyl);
			treasures.addChild(cub);
			treasures.translate(2, 0, 0);
			addGameWorldObject(treasures);
			
			MySpinController msc = new MySpinController();
			msc.addControlledNode(treasures);
			treasures.addController(msc);
			
			bombs = new Group("bombs");
			bombs.addChild(pyrA);
			bombs.addChild(pyrB);
			bombs.addChild(pyrC);
			bombs.addChild(pyrD);
			bombs.addChild(pyrE);
			addGameWorldObject(bombs);
			
			MyZRotateController mzr = new MyZRotateController();
			mzr.addControlledNode(bombs);
			bombs.addController(mzr);
			
			


			// add x, y, and z coordinates
			Point3D origin = new Point3D(0,0,0);
			Point3D xEnd = new Point3D(1000,0,0);
			Point3D yEnd = new Point3D(0,1000,0);
			Point3D zEnd = new Point3D(0,0,1000);
			Line xAxis = new Line(origin, xEnd, Color.red, 1);
			Line yAxis = new Line(origin, yEnd, Color.green, 1);
			Line zAxis = new Line(origin, zEnd, Color.blue, 1);
			addGameWorldObject(xAxis);
			addGameWorldObject(yAxis);
			addGameWorldObject(zAxis);
			
		
			
			// add HUD
			timeDisplay = new HUDString("Time = " + time);
			timeDisplay.setColor(Color.WHITE);
			timeDisplay.setLocation(0, 0.025);
			addGameWorldObject(timeDisplay);

		}
		
		public void createPlayers()
		{
			
			p1Camera = new JOGLCamera(renderer);
			p1Camera.setPerspectiveFrustum(60, 2, 1, 1000);
			p1Camera.setViewport(0, 1.0, 0.0, 0.45);
			
			p1 = new Cube("Player 1");
			Matrix3D p1T = p1.getLocalTranslation();
			p1T.translate(0, 0, 0);
			p1.setLocalTranslation(p1T);
			Matrix3D p1R = new Matrix3D();
			p1R.rotateY(45.0);
			p1.setLocalRotation(p1R);
			addGameWorldObject(p1);
			

			
			p2Camera = new JOGLCamera(renderer);
			p2Camera.setPerspectiveFrustum(60, 2, 1, 1000);
			p2Camera.setViewport(0, 1.0, 0.55, 1.00);
			

				
			
			p2 = new Cube("Player 2");
			p2.translate(0, 0, 0);
			p2.rotate(-90, new Vector3D(0,1,0));
			Matrix3D p2T = p2.getLocalTranslation();
			p2T.translate(0, 0, 50);
			p2.setLocalTranslation(p2T);
			Matrix3D p2R = new Matrix3D();
			p2R.rotateY(45.0);
			p2.setLocalRotation(p2R);
			addGameWorldObject(p2);
			
			createHUD();

		}
		
		private void createHUD()
		{
		// player HUDs
		
		p1ID = new HUDString("Player 1");
		p1ID.setName("Player1ID");
		p1ID.setLocation(0.01, 0.06);
		p1ID.setRenderMode(sage.scene.SceneNode.RENDER_MODE.OPAQUE);
		p1ID.setColor(Color.orange);
		p1ID.setCullMode(sage.scene.SceneNode.CULL_MODE.NEVER);
		p1Camera.addToHUD(p1ID);
		p1ScoreString = new HUDString("P1 Score: ");
		p1ScoreString.setName("Player1IDScore");
		p1ScoreString.setLocation(0.05, 0.08);
		p1ScoreString.setRenderMode(sage.scene.SceneNode.RENDER_MODE.OPAQUE);
		
		
		
		p2ID = new HUDString("Player 2");
		p2ID.setName("Player2ID");
		p2ID.setLocation(0.01, 0.06);
		p2ID.setRenderMode(sage.scene.SceneNode.RENDER_MODE.OPAQUE);
		p2ID.setColor(Color.green);
		p2ID.setCullMode(sage.scene.SceneNode.CULL_MODE.NEVER);
		p2Camera.addToHUD(p2ID);
		p2ScoreString = new HUDString("P2 Score: ");
		p2ScoreString.setName("Player2IDScore");
		p2ScoreString.setLocation(0.05, 0.08);
		p2ScoreString.setRenderMode(sage.scene.SceneNode.RENDER_MODE.OPAQUE);
		
		}
		public void initInputs()
		{
			im = getInputManager();
			kbName = im.getKeyboardName();
			mName = im.getMouseName();
			gpName = im.getFirstGamepadName();
			System.out.println("first controller: " + gpName);
			gpName2 = im.getSecondGamepadName();
			System.out.println("second controller: " + gpName2);
			
			// create keyboard actions
			MovementToggle movement = new MovementToggle();
			IAction quitGame = new QuitGameAction(this);
			IAction moveForwardP1 = new MoveForwardAction(p1); // w kb
			IAction moveBackwardP1 = new MoveBackwardAction(p1); // s kb
			IAction moveLeftP1 = new MoveLeftAction(p1); // a kb
			IAction moveRightP1 = new MoveRightAction(p1); // d kb
			IAction moveForwardP2 = new MoveForwardAction(p2); // a
			IAction moveBackwardP2 = new MoveBackwardAction(p2); // y
			IAction moveLeftP2 = new MoveLeftAction(p2); // x
			IAction moveRightP2 = new MoveRightAction(p2); // b
			
		/*	IAction moveBackward = new BackCameraMovement(camera, movement);
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
		*/
			/* figure out why laptop cannot run with im.associateAction */
			
			// Associate actions with keyboard  PLAYER 1
			im.associateAction(kbName, net.java.games.input.Component.Identifier.Key.W, moveForwardP1, // do two controllers instead
					IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
			im.associateAction(kbName, net.java.games.input.Component.Identifier.Key.S, moveBackwardP1, // do two controllers instead
					IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
			im.associateAction(kbName, net.java.games.input.Component.Identifier.Key.A, moveLeftP1, // do two controllers instead
					IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
			im.associateAction(kbName, net.java.games.input.Component.Identifier.Key.D, moveRightP1, // do two controllers instead
					IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
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
			im.associateAction(gpName, net.java.games.input.Component.Identifier.Button._3, moveBackwardP2,
					IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
			im.associateAction(gpName, net.java.games.input.Component.Identifier.Button._0, moveForwardP2,
					IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
			im.associateAction(gpName, net.java.games.input.Component.Identifier.Button._1, moveRightP2,
					IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
			im.associateAction(gpName, net.java.games.input.Component.Identifier.Button._2, moveLeftP2,
					IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
		//	im.associateAction(gpName, net.java.games.input.Component.Identifier.Axis.X, controllerX,
		//			IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
		//	im.associateAction(gpName, net.java.games.input.Component.Identifier.Axis.Y, controllerY,
		//			IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
			
			// associate actions with controllers PLAYER 2
		//  im.associateAction(gpName, net.java.games.input.Component.Identifier.Axis.RY, controllerRY,
		//			IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
		//	im.associateAction(gpName2, Axis.X, moveForwardP2,
		//			IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
		//	im.associateAction(gpName, net.java.games.input.Component.Identifier.Axis.X, controllerX,
		//			IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
		//	im.associateAction(gpName, net.java.games.input.Component.Identifier.Axis.Y, controllerY,
		//			IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
			
			
			
			
		}
		public void update(float elapsedTimeMS)
		{
			
			
			super.update(elapsedTimeMS);
			
			oc1.updatePos(elapsedTimeMS);
			oc2.updatePos(elapsedTimeMS);

			// overwritten
			// update score
			p1ScoreString.setText("P1 Score = " + p1Score);
			p2ScoreString.setText("P2 Score = " + p2Score);
			
			time += elapsedTimeMS;
		
			
			timeDisplay.setText("Time = " + (time/1000));

			// collision 
			if (treasures.getWorldBound().intersects(p1.getWorldBound()))
			{
			//	crashInc++;
			//	CrashEvent newCrash = new CrashEvent(crashInc);
			//	em.triggerEvent(newCrash);
				p1Score++;
				System.out.println("removing object.");
		//		removeGameWorldObject(treasures);
			}
			if (treasures.getWorldBound().equals(p2.getWorldBound()))
			{
				p2Score++;
				System.out.println("removing object.");
			//	removeGameWorldObject(treasures);
			}
			if (bombs.getWorldBound().equals(p1.getWorldBound()))
			{
			//	crashInc++;
			//	CrashEvent newCrash = new CrashEvent(crashInc);
			//	em.triggerEvent(newCrash);
				p1Score--;
				System.out.println("removing object.");
			//	removeGameWorldObject(bombs);
			}
			if (bombs.getWorldBound().equals(p2.getWorldBound()))
			{
			//	crashInc++;
			//	CrashEvent newCrash = new CrashEvent(crashInc);
			//	em.triggerEvent(newCrash);
				p2Score--;
				System.out.println("removing object.");
			//	removeGameWorldObject(bombs);
			}
			
	
		}
	protected void render()
	{
		renderer.setCamera(p1Camera);
		super.render();
		renderer.setCamera(p2Camera);
		super.render();
	}
	
	
/*	private IDisplaySystem createDisplaySystem()
	{
		
		IDisplaySystem display = new MyNewDisplaySystem(500, 500, 24, 30, true, "sage.renderer.jogl.JOGL.Renderer");
		
		System.out.println("waiting for display creation");
		
		int c = 0;
		 while (!display.isCreated())
		 {
		 try
		 { Thread.sleep(10); }
		 catch (InterruptedException e)
		 { throw new RuntimeException("Display creation interrupted"); }
		 c++;
		 System.out.print("+");
		 if (c % 80 == 0) { System.out.println(); }
		 if (c > 2000) // 20 seconds (approx.)
		 { throw new RuntimeException("Unable to create display");
		 }
		 }
		 System.out.println();
		 return display ;
		 
	}
	
	*/
}

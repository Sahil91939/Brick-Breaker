/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels.  IMPORTANT NOTE:
  * ON SOME PLATFORMS THESE CONSTANTS MAY **NOT** ACTUALLY BE THE DIMENSIONS
  * OF THE GRAPHICS CANVAS.  Use getWidth() and getHeight() to get the 
  * dimensions of the graphics canvas. */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board.  IMPORTANT NOTE: ON SOME PLATFORMS THESE 
  * CONSTANTS MAY **NOT** ACTUALLY BE THE DIMENSIONS OF THE GRAPHICS
  * CANVAS.  Use getWidth() and getHeight() to get the dimensions of
  * the graphics canvas. */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
//Delay Time in milliseconds
	private static final int Delay =9;
	
/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		/* You fill this in, along with any subsidiary methods */
		setup();
		addMouseListeners();
		ball =new GOval(2*BALL_RADIUS,2*BALL_RADIUS);
		ball.setColor(Color.black);
		ball.setFilled(true);
		add(ball,getWidth()/2-BALL_RADIUS/2,getHeight()/2-BALL_RADIUS/2);
		vx = rgen.nextDouble(1.0,3.0);
		if(rgen.nextBoolean(0.5)) vx = -vx;
		while(ball.getY()<getHeight()-2*BALL_RADIUS){
			ball.move(vx, vy);
			checkForCollision();
			pause(Delay);
		}
	}

	private void setup(){
		
		for(int j = 0 ; j < NBRICK_ROWS;j++){
			for(int i = 0; i < NBRICKS_PER_ROW ; i++){
				brick = new GRect((getWidth()-WIDTH)/2 + i*(BRICK_WIDTH+BRICK_SEP),BRICK_Y_OFFSET+ j*(BRICK_HEIGHT+BRICK_SEP),BRICK_WIDTH,BRICK_HEIGHT);
				brick.setFilled(true);
				if(j%2==0)rainbow(j);
				brick.setColor(color);
				add(brick);
			}
		}	
	
		paddle = new GRect(PADDLE_WIDTH,PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle,getWidth()/2-PADDLE_WIDTH/2,getHeight()-PADDLE_Y_OFFSET);
		
	}
	
	/*The rainbow method switches the color of the rows of bricks every second row */
	private void rainbow(int j){
		switch(j){
		case 0:color =Color.red;break;
		case 2:color = Color.orange;break;
		case 4:color = Color.YELLOW;break;
		case 6:color = Color.green;break;
		case 8:color = Color.cyan;break;
		}
	}
	
	private void checkForCollision(){
		if(ball.getX()<=0||ball.getX()>=(getWidth()-2*BALL_RADIUS)) vx=-vx;
		if(ball.getY()<=0)vy=-vy;
		
		gobj = getElementAt(ball.getX(),ball.getY());
		if(gobj == null) gobj = getElementAt( ball.getX() + 2*BALL_RADIUS , ball.getY() );
		if(gobj == null) gobj = getElementAt(ball.getX(),ball.getY()+2*BALL_RADIUS);
		if(gobj == null) gobj = getElementAt(ball.getX()+2*BALL_RADIUS,ball.getY()+2*BALL_RADIUS);
		
		
		GObject collider = getCollidingObject();
		
		
		if(collider!=null){
			vy=-vy;
		}
		
		if(collider==brick){
			remove(gobj);
		}
		
	}
	
	public void mouseMoved(MouseEvent e){
		if(e.getX()>0 && e.getX()<(getWidth()-PADDLE_WIDTH)){
			paddle.setLocation(e.getX(),paddle.getY());
		}
	}
	
	//this method return the object on which the ball collides
	
	private GObject getCollidingObject(){
		if (gobj!=null&&ball.getY()<getHeight()/2){
			return brick;
		}else if(gobj!=null&&ball.getY()>getHeight()/2){
			return paddle;
		}else{
			return null;
		}
	}

	
	//PRIVATE INSTANCE VARIABLES
	private GRect paddle,brick ;
	private Color color;
	private GOval ball ;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	//private instance variables for the velocities of the ball in both x & y direction
	private double vx,vy=3;
	private GObject gobj;
	private GObject last ;
}


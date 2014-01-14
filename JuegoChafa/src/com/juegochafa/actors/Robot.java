package com.juegochafa.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public class Robot {
	private float x, y, speedX, speedY, rotation;
	private float stateX = 1, stateY = 1;
	private float speedGlobal = 0.1f;
	private float spriteWidth = 10, spriteHeight = 10;
	private float worldWidth=80, worldHeight=38;
	private boolean robotTouched = false;
	private boolean robotElected = false;
	private int id;
	
	private Vector3 position, origin;
	private Texture texture;
	private Sprite sprite;
	
	private Vector3 touchpoint;
	
	
	
	public Robot(float x, float y, float rotation, int id){
		//propiedades robot
		
		this.x = x; this.y = y;
		this.id = id;
		setSpeedX(rotation, speedGlobal);
		setSpeedY(rotation, speedGlobal);
		
		
		//Texturas
		texture = new Texture(Gdx.files.internal("robot3.png"));
		sprite = new Sprite(texture);
		
		//Sprite
		sprite.setSize(spriteWidth, spriteHeight);
		origin = new Vector3(spriteWidth/2,spriteHeight/2,0);
		position = new Vector3(x+origin.x,y+origin.y,0);

		touchpoint =  new Vector3();	
	}
	
	public void live(Camera camera){
		setRobotTouched(false);
		
		if(Gdx.input.justTouched()){
			camera.unproject(touchpoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			setRobotTouched(justTouch(touchpoint));	
		}
		
		if(getRobotElected()){
			setRobotElected(Gdx.input.isTouched());
			camera.unproject(touchpoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			setX(touchpoint.x); 
			setY(touchpoint.y);
			
		}
		else{
			run();
		}
		
		x=position.x-origin.x;
		y=position.y-origin.y;
		
		sprite.setOrigin(origin.x, origin.y);
		sprite.setPosition(x, y);
	}
	
	public void run(){
		//Choque en eje X
		if(position.x >= worldWidth||position.x < 0){
			speedX*=-1;
			stateX*=-1;
			rotation+=(-90*(stateY*stateX));
		}
		
		//Choque en eje Y
		if(position.y >= worldHeight||position.y < 0){
			speedY*=-1;
			stateY*=-1;
			rotation+=(90*(stateX*stateY));
		}
		
		if(getRobotTouched()){
			
		}
		else{
			//Movimiento constante
			position.y+=speedY;
			position.x+=speedX;
		}
	
	}
	
	public boolean justTouch(Vector3 vector){
		if(position.dst(vector)<=5){
			return true;
		}
		else
			return false;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public float getRotation(){
		return rotation;
	}
	
	public int getId(){
		return id;
	}
	
	public Sprite getSprite(){
		return sprite;
	}

	public void setSpeedX(float rotation, float speed){
		speedX = MathUtils.cosDeg(rotation)*speed;
	}
	
	public void setSpeedY(float rotation, float speed){
		speedY = MathUtils.sinDeg(rotation)*speed;
	}

	public void setX(float x){
		//x += origin.x;
		if(x <= worldWidth && x >= 0)
		position.x = x;
	}
	
	public void setY(float y){
		//y += origin.y;
		if(y <= worldHeight && y >= 0)
		position.y= y;
	}
	
	public void setSpeedGlobal(float speed){
		speedGlobal=speed;
	}
	public void setRobotTouched(boolean robotTouched){
		this.robotTouched = robotTouched;
	}
	
	public boolean getRobotTouched(){
		return robotTouched;
	}
	public void setRobotElected(boolean robotElected){
		this.robotElected = robotElected;
	}
	
	public boolean getRobotElected(){
		return robotElected;
	}
}

package com.juegochafa.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public class Robot {
	private float x, y, speedX, speedY, rotation;
	private float speedGlobal = 5f;
	private float spriteWidth = 10, spriteHeight = 10;
	private float worldWidth=80, worldHeight=38;
	private boolean robotTouched = false;
	private boolean robotElected = false;
	private boolean robotDropped = false;
	private boolean switchDropped= false;
	
	private float deltaTime;
	private Vector3 position, origin;
	private Texture texture;
	private Sprite sprite;
	private Sound touchRobot;
	
	private Vector3 touchpoint = new Vector3();
	
	
	
	public Robot(float x, float y, float rotation, AssetManager manager){
		//propiedades robot
		
		this.x = x; this.y = y;
		setSpeedX(rotation, speedGlobal);
		setSpeedY(rotation, speedGlobal);
		
		//Texturas
		texture = manager.get("robot3.png");
		sprite = new Sprite(texture);
		touchRobot = manager.get("audio/robot_jump.ogg");
		
		//Sprite
		sprite.setSize(spriteWidth, spriteHeight);
		origin = new Vector3(spriteWidth/2,spriteHeight/2,0);
		position = new Vector3(x+origin.x,y+origin.y,0);
	}
	
	public void live(Camera camera){
		setRobotTouched(false);
		setRobotDropped(false);
		
		if(Gdx.input.justTouched()){
			camera.unproject(touchpoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			setRobotTouched(justTouch(touchpoint));	
		}
		// <-- Sale a avisar que fue tocado
		// --> Regresa si es el unico
		if(getRobotElected()){
			setRobotElected(Gdx.input.isTouched());
			camera.unproject(touchpoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			setX(touchpoint.x); 
			setY(touchpoint.y);
			switchDropped = true;
		}
		else{
			if(switchDropped){
				setRobotDropped(true);
				System.out.println("Este es el valor "+ switchDropped);
				switchDropped=false;
			}
			run();
		}
		
		x=position.x-origin.x;
		y=position.y-origin.y;
		
		sprite.setOrigin(origin.x, origin.y);
		sprite.setPosition(x, y);
	}
	
	public void run(){
		deltaTime = Gdx.graphics.getDeltaTime();
		//Choque en eje X
		if((position.x >= worldWidth||position.x < 0)){
			speedX*=-1;
		}
		
		/* |           Choque en eje Y               |*/
		if((position.y >= worldHeight||position.y < 0) ){
			speedY*=-1;
		}
		
		if(getRobotTouched()){
			
		}
		else{
			//Movimiento constante
			position.y+=(speedY*deltaTime);
			position.x+=(speedX*deltaTime);
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
	
	public Vector3 getPosition(){
		return position;
	}
	
	public float getRotation(){
		return rotation;
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
		if(x <= worldWidth && x >= 0)
		position.x = x;
	}
	
	public void setY(float y){
		if(y <= worldHeight && y >= 0)
		position.y= y;
	}
	
	public void setSpeedGlobal(float speed){
		speedGlobal=speed;
	}
	
	public void collisionX(){
		speedX*=-1;
	}
	
	public void collisionY(){
		speedY*=-1;
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
	
	public void setRobotDropped(boolean robotDropped){
		this.robotDropped = robotDropped;
	}
	
	public boolean getRobotDropped(){
		return robotDropped;
	}
	
	public void soundSelectRobot(){
		touchRobot.play(1.0f);
	}
}

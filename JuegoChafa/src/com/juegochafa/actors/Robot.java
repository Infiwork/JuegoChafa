package com.juegochafa.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

public class Robot {
	private float x, y, speedX, speedY, stateX, stateY, rotation;
	private float spriteWidth, spriteHeight;
	private float worldWidth=80, worldHeight=45;
	
	private Vector3 position, origin;
	private Texture texture;
	private Sprite sprite;
	
	private Vector3 touchpoint;
	
	private boolean robotTouched;
	
	public Robot(float x, float y){
		//propiedades robot
		speedX=0.2f; speedY=0.2f;
		stateX=1; stateY=1;
		rotation=-45;
		spriteWidth=5; spriteHeight=5;
		
		//Texturas
		texture = new Texture(Gdx.files.internal("droid.png"));
		sprite = new Sprite(texture);
		
		//Sprite
		sprite.setSize(spriteWidth, spriteHeight);
		origin = new Vector3(spriteWidth/2,spriteHeight/2,0);
		position = new Vector3(x+origin.x,y+origin.y,0);

		robotTouched = false;
		touchpoint =  new Vector3();
		
		
	}
	public void live(Camera camera){
		if(Gdx.input.justTouched()){
			camera.unproject(touchpoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			setRobotTouched(justTouch(touchpoint));	
		}
		if(getRobotTouched()){
			camera.unproject(touchpoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			setX(touchpoint.x); 
			setY(touchpoint.y);
			setRobotTouched(Gdx.input.isTouched());
		}
		
		run();
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
		x=position.x-origin.x;
		y=position.y-origin.y;
		
		sprite.setOrigin(origin.x, origin.y);
		sprite.setPosition(x, y);
		sprite.setRotation(rotation);
		
		
	}
	
	public boolean justTouch(Vector3 vector){
		if(position.dst(vector)<=3){
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
	
	public Sprite getSprite(){
		return sprite;
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
	
	public void setRobotTouched(boolean robotTouched){
		this.robotTouched = robotTouched;
	}
	
	public boolean getRobotTouched(){
		return robotTouched;
	}
}

package com.juegochafa.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

public class Robot {
	private float x, y, speedX, speedY, stateX, stateY, rotation;
	private float spriteWidth, spriteHeight;
	private float worldWidth=80, worldHeight=45;
	
	private Vector3 position;
	private Texture texture;
	private Sprite sprite;
	
	private boolean robotTouched;
	
	public Robot(){
		//propiedades robot
		x=30; y=1;
		speedX=0.2f; speedY=0.2f;
		stateX=1; stateY=1;
		rotation=-45;
		
		position = new Vector3(x,y,0);
		
		robotTouched = false;
		
		//Texturas
		texture = new Texture(Gdx.files.internal("droid.png"));
		sprite = new Sprite(texture);
		
		//Medididas del sprite
		spriteWidth = 5;
		spriteHeight= 5;
		
		sprite.setSize(spriteWidth, spriteHeight);
		
	}
	
	
	
	public void run(){
		//Choque en eje X
		if(x >= worldWidth-spriteWidth ||x <= 0){
			speedX*=-1;
			stateX*=-1;
			rotation+=(-90*(stateY*stateX));
		}
		
		//Choque en eje Y
		if(y >= worldHeight-spriteHeight||y <= 0){
			speedY*=-1;
			stateY*=-1;
			rotation+=(90*(stateX*stateY));
		}
		
		if(getRobotTouched()){
			
		}
		else{
			//Movimiento constante
			y+=speedY;
			x+=speedX;
		}
		position.set(x+(spriteWidth/2), y+(spriteHeight/2), 0);
		
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
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
		x -= spriteWidth/2;
		if(x <= worldWidth-(spriteWidth) && x >= 0)
		this.x = x;
	}
	
	public void setY(float y){
		y -= spriteHeight/2;
		if(y <= worldHeight-(spriteHeight) && y >= 0)
		this.y= y;
	}
	
	public void setRobotTouched(boolean robotTouched){
		this.robotTouched = robotTouched;
	}
	
	public boolean getRobotTouched(){
		return robotTouched;
	}
}

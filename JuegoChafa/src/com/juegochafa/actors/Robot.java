package com.juegochafa.actors;

public class Robot {
	private float x=30, y=1, speedX=0.2f, speedY=0.2f, stateX=1, stateY=1, rotation=-45;
	private float worldWidth=80, worldHeight=45;
	
	public Robot(){
		
	}
	
	public void run(){
		//Choque en eje X
		if(x >= worldWidth ||x <= 0){
			speedX*=-1;
			stateX*=-1;
			rotation+=(-90*(stateY*stateX));
		}
		
		//Choque en eje Y
		if(y >= worldHeight||y <= 0){
			speedY*=-1;
			stateY*=-1;
			rotation+=(90*(stateX*stateY));
		}
		System.out.println(rotation);
		//Movimiento constante
		y+=speedY;
		x+=speedX;
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

}

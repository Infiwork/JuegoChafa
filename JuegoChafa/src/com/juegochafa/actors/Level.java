package com.juegochafa.actors;

import java.util.ArrayList;
import java.util.Stack;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Level {
	
	Robot robot;
	Robot[] robotArray;
	Teleport tele;
	
	Stack<Integer> selectedTemp;
	ArrayList<Robot> robots;
	
	int n = 8;
	
	public Level(){
		robotArray = new Robot[n];
		robots = new ArrayList<Robot>();
	    for (int i = 0; i< n ; i++){
	    	robotArray[i] = new Robot(MathUtils.random(10, 70),MathUtils.random(10, 35),MathUtils.random(35, 335)); //* 2 *
	    	robots.add(robotArray[i]);
	    }

		tele = new Teleport();
		
		selectedTemp = new Stack<Integer>();
	}
	
	public void render(Camera camera, SpriteBatch batch){
		tele.getSprite().draw(batch);	
		// Codigo para tocar solo un robot
		 for (int i = 0; i< robots.size() ; i++){
			 	robots.get(i).live(camera);
			 	robots.get(i).getSprite().draw(batch);
				//Codigo de colisiones para los paneles
				if(robots.get(i).getPosition().dst(tele.getPosition())<=7){
					robots.get(i).collisionX();
					robots.get(i).collisionY();
				}
				//	Guardar robots presionados temporalmente
				if(robots.get(i).getRobotTouched()){
					selectedTemp.push(i);
				}
				// Eliminar robot al solarlo cerca del teleporter
				if(robots.get(i).getRobotDropped())
				if(robots.get(i).getPosition().dst(tele.getPosition())<=6){
					robots.remove(i);
				}
		 }
		if(!selectedTemp.empty())
			robotSelected();
		
		if(robots.size()==5) respawnRobots();
		
	}
	
	public void respawnRobots(){
		robot = new Robot(40,0,MathUtils.random(35, 165));
		robots.add(robot);
	}
	
	public void robotSelected(){
		int x=-1, temp;
		 while(!selectedTemp.empty()){
			 temp=selectedTemp.pop();
			 System.out.println(temp);
			 if(temp>x){
				 x=temp;
			 }
		 }
		 robots.get(x).setRobotElected(true);
	}

	
}

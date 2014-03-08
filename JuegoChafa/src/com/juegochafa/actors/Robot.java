package com.juegochafa.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public class Robot {
	private float x, y, speedX, speedY, rotation;
	private float speedGlobal = 5f;
	private float spriteWidth = 10, spriteHeight = 10;
	private float countDown = 20;
	private float worldWidth=80, worldHeight=38;
	private boolean robotTouched = false;
	private boolean robotElected = false;
	private boolean robotDropped = false;
	private boolean switchDropped= false;
	private boolean robotExplosion = false;
	
	private float deltaTime;
	private float timeFrames = 0;
	private Vector3 position, origin;
	
	private Animation runAnimation;
	private Texture runTexture;
	private TextureRegion[] runTextureRegion;
	private TextureRegion runFrame;
	
	//private TextureRegion t;
	
	private Sound touchRobot;
	private Sound explosionRobot;
	
	private Vector3 touchpoint = new Vector3();
	
	
	
	
	public Robot(float x, float y, float rotation, AssetManager manager){
		//propiedades robot
		
		this.x = x; this.y = y;
		setSpeedX(rotation, speedGlobal);
		setSpeedY(rotation, speedGlobal);
		
		touchRobot = manager.get("audio/robot_jump.ogg");
		explosionRobot = manager.get("audio/button.ogg");
		
		origin = new Vector3(spriteWidth/2,spriteHeight/2,0);
		position = new Vector3(x+origin.x,y+origin.y,0);
		
		createRunAnimation(manager.get("sprite_robot_azul.png", Texture.class));
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
				switchDropped=false;
			}
			run();
		}
		
		x=position.x-origin.x;
		y=position.y-origin.y;
		
	}
	
	public void run(){
		deltaTime = Gdx.graphics.getDeltaTime();
		// Choque en eje X
		if((position.x >= worldWidth||position.x < 0)){
			speedX*=-1;
		}
		
		// Choque en eje Y 
		if((position.y >= worldHeight||position.y < 0) ){
			speedY*=-1;
		}
		
		if(getRobotTouched()){
			
		}
		else{
			//Movimiento constante
			position.y+=(speedY*deltaTime);
			position.x+=(speedX*deltaTime);
			
			if(!robotExplosion)
			explosionCountDown(deltaTime);
			
			timeFrames += (deltaTime/8);
			runFrame = runAnimation.getKeyFrame(timeFrames,true);
		}
	
	}
	
	public void createRunAnimation(Texture texture){
		runTexture = texture;
		TextureRegion[][] tmp = TextureRegion.split(runTexture,200, 290);
		runTextureRegion = new TextureRegion[5];
		for (int i = 0; i < 5; i++) {
			runTextureRegion[i] = tmp[0][i];
		}
		runAnimation = new Animation (0.025f, runTextureRegion);
	}
	
	public void collisionX(){
		speedX*=-1;
	}
	
	public void collisionY(){
		speedY*=-1;
	}
	
	public void disposeAssets(){
		runTexture.dispose();
	}
	
	float temp=0;
	public void explosionCountDown(float delta){
		countDown-=delta;
		temp+=delta;
		//System.out.println(countDown+" temoral "+temp+" division "+ (countDown/10));
		if(countDown<=15){
			if(temp > (countDown/10)){
				soundSelectRobot();
				temp=0;
			}
			if(countDown<=0) robotExplosion = true;
		}
		
	}

	public TextureRegion getFrameRun(){
		return runFrame;
	}

	public Vector3 getPosition(){
		return position;
	}
	
	public boolean getRobotDropped(){
		return robotDropped;
	}
	
	public boolean getRobotElected(){
		return robotElected;
	}
	
	public boolean getRobotExplosion(){
		return robotExplosion;
	}
	
	public boolean getRobotTouched(){
		return robotTouched;
	}
	
	public float getRotation(){
		return rotation;
	}
	
	public TextureRegion getRun(){
		return runTextureRegion[2];
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public boolean justTouch(Vector3 vector){
		if(position.dst(vector)<=5){
			return true;
		}
		else
			return false;
	}
	
	public void setRobotDropped(boolean robotDropped){
		this.robotDropped = robotDropped;
	}
	
	public void setRobotElected(boolean robotElected){
		this.robotElected = robotElected;
	}
	
	public void setRobotTouched(boolean robotTouched){
		this.robotTouched = robotTouched;
	}
	
	
	public void setSpeedGlobal(float speed){
		speedGlobal=speed;
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
	
	public void soundSelectRobot(){
		touchRobot.play(1.0f);
	}
	
	public void soundExplosionRobot(){
		explosionRobot.play(1.0f);
	}
}

package com.juegochafa.game;

import java.util.Stack;

import com.juegochafa.actors.Robot;
import com.mi.superjuego.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public class screenGame implements Screen{
	Game game;
	Robot robot;
	Robot[] robotArray;
	Vector3 touchpoint;
	
	Texture textfondo;
	TextureRegion textReg;
	Sprite fondo;
	
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private float worldWidth=80, worldHeight=45;

	int n =20;
	Stack<Integer> cola;
	
	public screenGame(Game game){
		this.game=game;	
		create();
	}
	private void create() {
		
		camera = new OrthographicCamera(worldWidth, worldHeight);
		camera.position.set(worldWidth/2, worldHeight/2, 0);
		batch = new SpriteBatch();
		
		
		textfondo = new Texture(Gdx.files.internal("fondo.png"));
		textReg =  new TextureRegion(textfondo, 1024, 700);
		
		fondo= new Sprite(textReg);
		
		cola = new Stack<Integer>();
		
	    robotArray = new Robot[n];
	    
	    for (int i = 0; i< n ; i++){
	    	robotArray[i] = new Robot(MathUtils.random(10, 70),MathUtils.random(10, 35),MathUtils.random(25, 345),i); //* 2 *
	    }
		
		//robot = new Robot (MathUtils.random(10, 70),MathUtils.random(10, 35),MathUtils.random(25, 345));
		
		touchpoint = new Vector3();
		
	}
	@Override
	public void render(float delta) {
		
		camera.update();
		Gdx.gl.glClearColor( 0.5f, 0.5f, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		batch.enableBlending();
		batch.begin();
		fondo.setSize(worldWidth, worldHeight);
		fondo.draw(batch);
		
		 for (int i = 0; i< n ; i++){
			 	robotArray[i].live(camera);
				robotArray[i].getSprite().draw(batch);
				if(robotArray[i].getRobotTouched()){
					cola.push(i);
				}	
		 }
		int x=-1, temp;
		 while(!cola.empty()){
			 temp=cola.pop();
			 System.out.println(temp);
			 if(temp>x){
				 x=temp;
			 }
		 }
		 if(x>=0){
			 System.out.println("selecionado "+x);
			 robotArray[x].setRobotElected(true);
		 }
		 //System.out.println("este es:" + x);
		// robotArray[i].setRobotElected(true);
		//for(int j =0; j<w.length;j++){
			//
		//}
		 
		//Dibujar robot
		//robot.run();
		//robot.getSprite().draw(batch);
		//robot.live(camera);
		
		batch.end();	
	}
		
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
	
			
			
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		batch.dispose();
		
	}

}

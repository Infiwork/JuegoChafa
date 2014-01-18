package com.juegochafa.game;

import com.juegochafa.actors.Level;
import com.mi.superjuego.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class screenGame implements Screen{
	Game game;
	
	Vector3 touchpoint;
	
	Texture textfondo;
	TextureRegion textReg;
	Sprite fondo;
	
	Level level;
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private float worldWidth=80, worldHeight=45;
	
	public screenGame(Game game){
		this.game=game;	
		create();
	}
	private void create() {
		camera = new OrthographicCamera(worldWidth, worldHeight);
		camera.position.set(worldWidth/2, worldHeight/2, 0);
		batch = new SpriteBatch();
		
		level = new Level();
		textfondo = new Texture(Gdx.files.internal("fondo.png"));
		textReg =  new TextureRegion(textfondo, 1024, 700);
		
		fondo= new Sprite(textReg);
		
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
		level.render(camera, batch);
		 	
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

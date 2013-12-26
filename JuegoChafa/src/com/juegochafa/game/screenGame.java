package com.juegochafa.game;

import com.juegochafa.actors.Robot;
import com.mi.superjuego.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class screenGame implements Screen{
	Game game;
	Robot robot;
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
	private float worldWidth=80, worldHeight=45;

	public screenGame(Game game){
		this.game=game;	
		create();
	}
	private void create() {
		
		camera = new OrthographicCamera(worldWidth, worldHeight);
		camera.position.set(worldWidth/2, worldHeight/2, 0);
		batch = new SpriteBatch();
		
		robot = new Robot();
		
		texture = new Texture(Gdx.files.internal("droid.png"));
		sprite = new Sprite(texture);
		
	}
	@Override
	public void render(float delta) {
		robot.run();
		
		camera.update();
		Gdx.gl.glClearColor( 0.5f, 0.5f, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		batch.enableBlending();
		batch.begin();
		
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setSize(5, 5);
		sprite.setPosition(robot.getX(), robot.getY());
		sprite.setRotation(robot.getRotation());
		
		sprite.draw(batch);
		
		
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
		texture.dispose();
		
	}

}

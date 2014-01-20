package com.juegochafa.game;

import com.badlogic.gdx.Screen;
import com.mi.superjuego.ChafaGame;

	/**
	 * @author Uriel
	 */
	public abstract class AbstractScreen implements Screen {

	    protected ChafaGame game;

	    public AbstractScreen(ChafaGame game) {
	        this.game = game;
	    }

	    @Override
	    public void pause() {
	    }

	    @Override
	    public void resume() {
	    }

	    @Override
	    public void dispose() {
	    }
	}
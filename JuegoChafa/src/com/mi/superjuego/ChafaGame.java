package com.mi.superjuego;

import com.juegochafa.game.ScreenLoading;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;

public class ChafaGame extends Game {

    /**
     * Holds all our assets
     */
    public AssetManager manager = new AssetManager();

    @Override
    public void create() {
    	System.out.println("hola");
        setScreen(new ScreenLoading(this));
    }
}
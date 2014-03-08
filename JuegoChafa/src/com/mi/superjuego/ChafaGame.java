package com.mi.superjuego;

import com.juegochafa.game.ScreenLoading;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;

public class ChafaGame extends Game {

    /**
     * Holds all our assets
     */
    public AssetManager manager = new AssetManager();

    public static IReqHandler ExternalHandler;

	public ChafaGame(IReqHandler irh){
		ChafaGame.ExternalHandler = irh;
	}
    @Override
    public void create() {
    //	ExternalHandler.showAd(true); //shows banner ad
  
        setScreen(new ScreenLoading(this));
    }
}
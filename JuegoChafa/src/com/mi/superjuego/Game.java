package com.mi.superjuego;

import com.juegochafa.game.screenGame;
import com.juegochafa.game.screenMenu;
import com.badlogic.gdx.Screen;
public class Game extends com.badlogic.gdx.Game{
	public Screen screenM;
	public Screen screenG;

	public void create() {
		screenM = new screenMenu(this);
		screenG = new screenGame(this);	
		setScreen(screenM);
	}

}

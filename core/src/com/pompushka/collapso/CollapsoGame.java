package com.pompushka.collapso;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pompushka.collapso.screens.StartScreen;

public class CollapsoGame extends Game {
	
	public SpriteBatch batch;
	public StartScreen startScreen;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		
		startScreen = new StartScreen(this);
		setScreen(startScreen);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose(){
		Gdx.app.log("GameClass", "Disposed");
	}
}

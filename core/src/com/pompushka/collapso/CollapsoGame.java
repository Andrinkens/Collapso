package com.pompushka.collapso;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pompushka.collapso.screens.GameScreen;
import com.pompushka.collapso.screens.StartScreen;

public class CollapsoGame extends Game {
	
	public SpriteBatch batch;
	public StartScreen startScreen;
	public GameScreen gameScreen;
	
	public MessageDispatcher msgDispatcher;
	
	@Override
	public void create () {
		Core.game = this;
		Assets.load();
		
		batch = new SpriteBatch();
		
		msgDispatcher = new MessageDispatcher();
		
		startScreen = new StartScreen();
		gameScreen = new GameScreen();
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

package com.pompushka.collapso.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pompushka.collapso.CollapsoGame;
import com.pompushka.collapso.Core;

public class StartScreen  implements Screen{
	private SpriteBatch batch;
	
	private Texture img;
	
	public StartScreen(){
		this.batch = Core.game.batch;
	}
	
	@Override
	public void show() {
		img = new Texture("badlogic.jpg");
		Gdx.app.log("StartScreen", "Showed");
	}

	@Override
	public void render(float delta) {/*
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//batch.draw(img, 0, 0);
		batch.end();*/
		
		if (Gdx.input.isTouched())	Core.game.setScreen(Core.game.gameScreen);
	}

	@Override
	public void resize(int width, int height) {
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
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		Gdx.app.log("StartScreen", "Disposed");
	}

}

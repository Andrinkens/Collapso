package com.pompushka.collapso.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.pompushka.collapso.CollapsoGame;
import com.pompushka.collapso.Core;
import com.pompushka.collapso.stages.CollapsoStage1;

public class GameScreen implements Screen{

	private CollapsoGame game;
	private SpriteBatch batch;
	
	private OrthographicCamera camera;
	private Stage stage1;
	
	public GameScreen(final CollapsoGame game){
		this.game = game;
		this.batch = game.batch;
		
		camera = new OrthographicCamera(Core.viewPortWidth, Core.viewPortHeight);
		camera.position.set(Core.viewPortWidth*0.5f, Core.viewPortHeight*0.5f, 0f);
		camera.update();
		
		stage1 = new CollapsoStage1(new ScreenViewport(camera), batch, game);
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		stage1.draw();
		stage1.act(delta);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		Core.resize(width, height);
		//gameStage.resize(width, height);
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
		Gdx.app.log("GameScreen", "Disposed");
	}

}

package com.pompushka.collapso.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pompushka.collapso.Assets;
import com.pompushka.collapso.CollapsoGame;
import com.pompushka.collapso.Core;
import com.pompushka.collapso.stages.CollapsoStage;
import com.pompushka.collapso.stages.HUDStage;

public class GameScreen implements Screen{

	private SpriteBatch batch;
	
	private OrthographicCamera camera1, camera2;
	private Stage stage;
	private Stage hudStage;
	private Viewport vp1,vp2;
	public GameScreen(){
		this.batch = Core.game.batch;
		
		//camera = new OrthographicCamera(Core.applicationWidth, Core.applicationHeight);
		//camera.position.set(Core.applicationWidth*0.5f, Core.applicationHeight*0.5f, 0f);
		camera1 = new OrthographicCamera(Core.viewPortWidth, Core.viewPortHeight);
		camera1.position.set(Core.viewPortWidth*0.5f, Core.viewPortHeight*0.5f, 0f);
		camera1.update();
		
		camera2 = new OrthographicCamera(Core.viewPortWidth, Core.viewPortHeight);
		camera2.position.set(Core.viewPortWidth*0.5f, Core.viewPortHeight*0.5f, 0f);
		camera2.update();
		
		vp1 = new StretchViewport(Core.viewPortWidth,Core.viewPortHeight,camera1);
		vp2 = new ScreenViewport(camera2);
		
		stage = new CollapsoStage(vp1, batch);
		hudStage = new HUDStage(vp2, batch);
		
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(hudStage);
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera1.combined);
		
		vp1.apply();
		stage.draw();
		
		batch.setProjectionMatrix(camera2.combined);
		vp2.apply();
		hudStage.draw();
		
		stage.act(delta);
		hudStage.act(delta);
		
		Core.game.msgDispatcher.update(delta);//??
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		Core.resize(width, height);
		//vp.update(width, height);
		//stage.resize(width, height);
		vp1.update(width, height);
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

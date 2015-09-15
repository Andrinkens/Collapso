package com.pompushka.collapso.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pompushka.collapso.CollapsoGame;
import com.pompushka.collapso.Core;
import com.pompushka.collapso.stages.CollapsoStage1;
import com.pompushka.collapso.stages.HUDStage;

public class GameScreen implements Screen{

	private SpriteBatch batch;
	
	private OrthographicCamera camera;
	private CollapsoStage1 stage1;
	private Stage hudStage;
	Viewport vp;
	public GameScreen(){
		this.batch = Core.game.batch;
		
		camera = new OrthographicCamera(Core.viewPortWidth, Core.viewPortHeight);
		camera.position.set(Core.viewPortWidth*0.5f, Core.viewPortHeight*0.5f, 0f);
		camera.update();
		vp = new FillViewport(Core.viewPortWidth, Core.viewPortHeight, camera);
		
		//Gdx.app.log("width",camera. + "");
		//Gdx.app.log("height",this.getHeight() + "");
		
		stage1 = new CollapsoStage1(vp, batch);
		hudStage = new HUDStage(new ScreenViewport(camera), batch);
		
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
		
		batch.setProjectionMatrix(camera.combined);
		
		stage1.draw();
		hudStage.draw();
		
		stage1.act(delta);
		hudStage.act(delta);
		
		Core.game.msgDispatcher.update(delta);//??
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		Core.resize(width, height);
		
		//stage1.resize(Core.viewPortWidth, Core.viewPortHeight);
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

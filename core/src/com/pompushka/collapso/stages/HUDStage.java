package com.pompushka.collapso.stages;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pompushka.collapso.Core;
import com.pompushka.collapso.actors.Scores;

public class HUDStage extends Stage{

	private Scores scores;
	
	public HUDStage(Viewport viewport, SpriteBatch batch) {
		super(viewport, batch);
			
		scores = new Scores();
		Core.game.msgDispatcher.addListener(scores, Core.Messages.SCORE);
		this.addActor(scores);
		
		this.setDebugAll(Core.GUI_RENDERER_STATE);
	}
	
	@Override
	public void act (float delta){
		super.act(delta);
	}
	
	public void resize(int width, int height){
		//this.getViewport().update(width, height, true);
	}
	
}



package com.pompushka.collapso.stages;

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

	Scores scores;
	
	public HUDStage(Viewport viewport, SpriteBatch batch) {
		super(viewport, batch);
			
		scores = new Scores();
		Core.game.msgDispatcher.addListener(scores, Core.Messages.SCORE);
		this.addActor(scores);
		
		Actor leftPad = new Actor();
		leftPad.setBounds(0, 0, 320, 300);
		leftPad.addListener(new PadListener());
		leftPad.setName("PADLEFT");
		
		Actor rightPad = new Actor();
		rightPad.setBounds(320, 0, 320, 300);
		rightPad.addListener(new PadListener());
		rightPad.setName("PADRIGHT");
		
		this.addActor(leftPad);
		this.addActor(rightPad);
		
		this.setDebugAll(true);
	}
	
	@Override
	public void act (float delta){
		super.act(delta);
	}
	
	public void resize(int width, int height){
		this.getViewport().update(width, height, true);
	}
}

class PadListener extends InputListener implements Telegraph{

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
    	Core.game.msgDispatcher.dispatchMessage(this, Core.Messages.PADS, event.getListenerActor().getName());
    	Core.game.msgDispatcher.dispatchMessage(this, Core.Messages.SCORE, 100);
        return true;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
    }

	@Override
	public boolean handleMessage(Telegram msg) {
		return false;
	}
}

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
	
	public static class PadState{
		public static final int PAD_LEFT_DN = 1;
		public static final int PAD_LEFT_UP = 2;
		public static final int PAD_RIGHT_DN = 3;
		public static final int PAD_RIGHT_UP = 4;
	}

	
	public HUDStage(Viewport viewport, SpriteBatch batch) {
		super(viewport, batch);
			
		scores = new Scores();
		Core.game.msgDispatcher.addListener(scores, Core.Messages.SCORE);
		this.addActor(scores);
		
		PadListener pl = new PadListener();
		
		Actor leftPad = new Actor();
		leftPad.setBounds(0, 0, Core.applicationWidth/2, Core.applicationHeight/2);
		leftPad.addListener(pl);
		leftPad.setName("PADLEFT");
		
		Actor rightPad = new Actor();
		rightPad.setBounds(Core.applicationWidth/2, 0, Core.applicationWidth, Core.applicationHeight/2);
		rightPad.addListener(pl);
		rightPad.setName("PADRIGHT");
		
		this.addActor(leftPad);
		this.addActor(rightPad);
		
		this.setKeyboardFocus(leftPad);
		
		this.setDebugAll(Core.GUI_RENDERER_STATE);
	}
	
	@Override
	public void act (float delta){
		super.act(delta);
	}
	
	public void resize(int width, int height){
		//this.getViewport().update(width, height, true);
	}
	
	class PadListener extends InputListener implements Telegraph{

	    @Override
	    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
	    	if (event.getListenerActor().getName() == "PADLEFT")
		    	Core.game.msgDispatcher.dispatchMessage(this, Core.Messages.PADS, PadState.PAD_LEFT_DN);
	    	else 
	    		Core.game.msgDispatcher.dispatchMessage(this, Core.Messages.PADS, PadState.PAD_RIGHT_DN);
	        return true;
	    }

	    @Override
	    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
	    	if (event.getListenerActor().getName() == "PADLEFT")
		    	Core.game.msgDispatcher.dispatchMessage(this, Core.Messages.PADS, PadState.PAD_LEFT_UP);
	    	else 
	    		Core.game.msgDispatcher.dispatchMessage(this, Core.Messages.PADS, PadState.PAD_RIGHT_UP);
	    }
	    
	    @Override
        public boolean keyDown(InputEvent event, int keycode) {
            switch(keycode) {
            	case Keys.LEFT:
            		Core.game.msgDispatcher.dispatchMessage(this, Core.Messages.PADS, PadState.PAD_LEFT_DN);break;
            	case Keys.RIGHT:
            		Core.game.msgDispatcher.dispatchMessage(this, Core.Messages.PADS, PadState.PAD_RIGHT_DN);break;
            }
            return true;
        }
	    @Override
        public boolean keyUp(InputEvent event, int keycode) {
            switch(keycode) {
            	case Keys.LEFT:
            		Core.game.msgDispatcher.dispatchMessage(this, Core.Messages.PADS, PadState.PAD_LEFT_UP);break;
            	case Keys.RIGHT:
            		Core.game.msgDispatcher.dispatchMessage(this, Core.Messages.PADS, PadState.PAD_RIGHT_UP);break;
            }
            return true;
        }	    
	    

		@Override
		public boolean handleMessage(Telegram msg) {
			return false;
		}
	}
}



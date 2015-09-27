package com.pompushka.collapso.actors;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.pompushka.collapso.Core;

public class InvisiblePads extends Group{

	public InvisiblePads(){
		PadListener padListener = new PadListener();
		
		Actor leftPad = new Actor();
		leftPad.setBounds(0, 0, Core.viewPortWidth/2, Core.viewPortHeight/2);
		leftPad.addListener(padListener);
		leftPad.setName("PADLEFT");
		
		Actor rightPad = new Actor();
		rightPad.setBounds(Core.viewPortWidth/2, 0, Core.viewPortWidth, Core.viewPortHeight/2);
		rightPad.addListener(padListener);
		rightPad.setName("PADRIGHT");
		
		this.addActor(leftPad);
		this.addActor(rightPad);
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
	
	public static class PadState{
		public static final int PAD_LEFT_DN = 1;
		public static final int PAD_LEFT_UP = 2;
		public static final int PAD_RIGHT_DN = 3;
		public static final int PAD_RIGHT_UP = 4;
	}

	
}

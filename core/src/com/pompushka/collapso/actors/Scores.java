package com.pompushka.collapso.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.pompushka.collapso.Assets;
import com.pompushka.collapso.Core;

public class Scores extends Group implements Telegraph{

	Label label;
	HealthBar healthBar;
	int score = 0;
	
	public Scores(){
		BitmapFont font = Assets.font;
		
		LabelStyle ls = new LabelStyle (font,Color.WHITE);
		label = new Label("0", ls); 
		
		label.setWidth(200f);
		label.setHeight(20f);
		label.setPosition(5,Core.applicationHeight-5-label.getHeight());		
		
		healthBar = new HealthBar(Core.applicationWidth - 5 - 30, Core.applicationHeight-20);
		
		this.addActor(label);
		this.addActor(healthBar);
		
		Core.game.msgDispatcher.addListener(this, Core.Messages.SCORE);
		Core.game.msgDispatcher.addListener(this, Core.Messages.LIVE);
		
	}

    @Override
    public void act(float delta) {
    	super.act(delta);  
    }
    
    public void setScore(int newScore){
    	if (score != newScore){
    		score = newScore;
        	label.setText(score+" ");
    	}
    }
    
    public void addScore(int newScore){
    	score += newScore;
        label.setText(score+" ");
    }
    
	@Override
	public boolean handleMessage(Telegram msg) {
		if (msg.message == Core.Messages.SCORE){
			addScore((Integer)msg.extraInfo);
		}
		
		if (msg.message == Core.Messages.LIVE){
			if ((Integer)msg.extraInfo == 1)
				healthBar.addLive();
			if ((Integer)msg.extraInfo == -1)
				healthBar.subLive();
		}
		return false;
	}
}

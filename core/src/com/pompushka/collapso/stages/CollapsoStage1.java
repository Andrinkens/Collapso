package com.pompushka.collapso.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pompushka.collapso.CollapsoGame;
import com.pompushka.collapso.Core;
import com.pompushka.collapso.actors.EnemyBasic;
import com.pompushka.collapso.actors.Hero;
import com.pompushka.collapso.actors.Scores;

public class CollapsoStage1 extends Stage implements Telegraph{

	Actor hero, enemy;
	//Scores scores;
	
	public CollapsoStage1(Viewport viewport, SpriteBatch batch) {
		super(viewport, batch);
		
		hero = new Hero();
		this.addActor(hero);

		enemy = new EnemyBasic();
		this.addActor(enemy);
		
		Core.game.msgDispatcher.addListener(this, Core.Messages.PADS);
	}
	
	@Override
	public void act (float delta){
		super.act(delta);
	}
	
	public void resize(int width, int height){
		this.getViewport().update(width, height, true);
	}

	@Override
	public boolean handleMessage(Telegram msg) {
		if (msg.extraInfo == "PADLEFT")
			hero.setPosition(1, 1);
		if (msg.extraInfo == "PADRIGHT")
			hero.setPosition(500, 1);
		return false;
	}

}

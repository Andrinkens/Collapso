package com.pompushka.collapso.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pompushka.collapso.CollapsoGame;
import com.pompushka.collapso.actors.Hero;

public class CollapsoStage1 extends Stage{
	
	CollapsoGame game;
	Actor hero;
	
	public CollapsoStage1(Viewport viewport, SpriteBatch batch, final CollapsoGame game) {
		super(viewport, batch);
		this.game = game;
		
		hero = new Hero();
		this.addActor(hero);
		
		/*******************************************/
		BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/font1.fnt"));
		
		LabelStyle ls = new LabelStyle (font,Color.WHITE);
		Label label = new Label("This is a label", ls); 
		
		label.setWidth(200f);
		label.setHeight(20f);
		label.setPosition(1,100);		
		
		this.addActor(label);
		/*******************************************/
	}
	
	public void resize(int width, int height){
		this.getViewport().update(width, height, true);
	}

}

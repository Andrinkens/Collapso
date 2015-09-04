package com.pompushka.collapso.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class Scores extends Actor{

	public Scores(){
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
	
}

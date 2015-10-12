package com.pompushka.collapso.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.pompushka.collapso.Assets;

public class HealthBar extends Actor{

	private TextureRegion tRegion = new TextureRegion(Assets.heroTex, 100,100,1,1);
	
	private int health = 100;
	
	public HealthBar(float X, float Y){
		this.setBounds(X, Y, health, 10);
	}
	
    @Override
    public void act(float delta) {
    	super.act(delta);  
    }
    
	@Override
	public void draw (Batch batch, float parentAlpha) {
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha * 0.5f);
		batch.draw(tRegion, getX(), getY(), getWidth(), getHeight());
		batch.setColor(color.r, color.g, color.b, color.a);
	}
	
	public void setHealth(int health){
		if (health < 0)	health = 0;
		this.health = health;
		this.setWidth(health);
	}

}

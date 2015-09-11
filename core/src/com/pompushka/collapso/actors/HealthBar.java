package com.pompushka.collapso.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class HealthBar extends Actor{

	private TextureRegion tRegion = new TextureRegion(new Texture("hero.png"), 100,100,1,1);
	
	private int lives = 3;
	
	public HealthBar(float X, float Y){
		this.setBounds(X, Y, 25, 10);
	}
	
    @Override
    public void act(float delta) {
    	super.act(delta);  
    }
    
	@Override
	public void draw (Batch batch, float parentAlpha) {
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha * 0.5f);
		for (int i=0;i<lives;i++)
			batch.draw(tRegion, getX()-(this.getWidth()+5)*i, getY(), getWidth(), getHeight());
		batch.setColor(color.r, color.g, color.b, color.a);
	}
	
	public int getLives(){
		return lives;
	}
	
	public void addLive(){
		lives++;
	}
	
	public void subLive(){
		lives--;
	}

}

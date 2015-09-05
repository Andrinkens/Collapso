package com.pompushka.collapso.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class EnemyBasic extends Actor{
	
	private TextureRegion tRegion = new TextureRegion(new Texture("enemy_basic.png"), 0,0,200,200);
	
	private Rectangle bounds;
	
	public EnemyBasic(){
		this.setSize(100, 100);
		this.setPosition(320, 200);
		bounds = new Rectangle(getX(),getY(), getWidth(), getHeight());		
	}
	
	public Rectangle getBounds() {
        return bounds;
    }
	
	@Override
	public void act (float delta){
		super.act(delta);
	}
	
	@Override
	public void draw (Batch batch, float parentAlpha) {
			//Color color = getColor();
			//batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
			batch.draw(tRegion, getX(), getY(), getWidth(), getHeight());
			//batch.setColor(color.r, color.g, color.b, color.a);
	}
}

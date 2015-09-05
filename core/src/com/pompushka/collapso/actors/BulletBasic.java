package com.pompushka.collapso.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.pompushka.collapso.Core;

public class BulletBasic extends Actor implements Poolable{

	private TextureRegion tRegion = new TextureRegion(new Texture("bullet_basic.png"), 0,0,10,20);
	
	private int speed=10;
	private boolean active = false;
	private Rectangle bounds;
	
	public BulletBasic(){
		bounds = new Rectangle(getX(),getY(), getWidth(), getHeight());	
	}
	
	public void init(float X, float Y){
		this.setBounds(X, Y, 10, 20);
		active = true;
		bounds.set(X, Y, 10, 20);
	}
	
	@Override
	public void act (float delta){
		super.act(delta);
		this.setY(getY()+speed);
		if (this.getY()>Core.applicationHeight) active = false;
		bounds.setPosition(getX(), getY());	
	}
	
	@Override
	public void draw (Batch batch, float parentAlpha) {
			//Color color = getColor();
			//batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
			batch.draw(tRegion, getX(), getY(), getWidth(), getHeight());
			//batch.setColor(color.r, color.g, color.b, color.a);
	}
	
	@Override
	public void reset() {
		if (this.getParent()!=null)	this.getParent().removeActor(this);
		active = false;
	}
	
	public boolean getState(){
		return active;
	}
	
	public Rectangle getBounds() {
        return bounds;
    }

}

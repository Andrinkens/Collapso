package com.pompushka.collapso.actors.bullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.pompushka.collapso.Assets;
import com.pompushka.collapso.Core;

public abstract class BulletBasic extends Actor implements Poolable, Telegraph{

	private TextureRegion tRegion = Assets.bullet[0];
	
	private float speed=Core.BULLET_SPEED;
	private boolean active = false;
	private Rectangle bounds;
	private int damage = 0;
	public Color color;
	private float width = 0.2f;
	private float height = 0.3f;
	private int type = 0;
	
	public BulletBasic(){
		bounds = new Rectangle();
	}
	
	public void init(float X, float Y){
		this.setBounds(X, Y, width, height);
		bounds.set(X, Y, width, height);
		active = true;
		Assets.playSound(Assets.shotSound);
	}
	
	@Override
	public void act (float delta){
		super.act(delta);
		this.setY(getY()+speed*delta);
		if (this.getY()>Core.applicationHeight) {
			setState(false);
		}
		bounds.setPosition(getX(), getY());	
	}
	
	@Override
	public void draw (Batch batch, float parentAlpha) {
		color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		batch.draw(tRegion, getX(), getY(), getWidth(), getHeight());
		batch.setColor(color.r, color.g, color.b, color.a);
	}
	
	@Override
	public void reset() {
		if (this.getParent()!=null)	this.getParent().removeActor(this);
		active = false;
	}
	
	public boolean getState(){
		return active;
	}
	
	public void setState(boolean state){
		active = state;
		if (!state)
			Core.game.msgDispatcher.dispatchMessage(this, Core.Messages.BULLET_FREE, this);
	}
	
	public Rectangle getBounds() {
        return bounds;
    }
	
	public int getDamage(){
		return damage;
	}
	
	public int getType(){
		return type;
	}

	public void setType(int type){
		this.type = type;
	}
	
	public void setDamage(int damage){
		this.damage = damage;
	}
	
	public void setTextureRegion(TextureRegion tr){
		tRegion = tr;
	}
	
	@Override
	public boolean handleMessage(Telegram msg) {
		// TODO Auto-generated method stub
		return false;
	}

}

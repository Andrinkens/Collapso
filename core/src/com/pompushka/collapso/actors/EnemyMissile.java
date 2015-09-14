package com.pompushka.collapso.actors;

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

public class EnemyMissile extends Actor implements Poolable, Telegraph{

	private TextureRegion tRegion = Assets.missile;
	
	
	private int speed=Core.BULLET_BASIC_SPEED/4;
	private Color color;
	private Rectangle bounds;
	private int damage = 50;
	private boolean alive = false;
	
	public EnemyMissile(){
		Gdx.app.log("EnemyMissile", "Missile creates!");
		bounds = new Rectangle();
	}
	
	public void init(float X, float Y){
		Gdx.app.log("EnemyMissile", "Missile spawn!");
		this.setBounds(X, Y, 10, 15);
		bounds.set(X, Y, 10, 15);
		alive = true;
	}
	
	@Override
	public void act (float delta){
		super.act(delta);
		this.setY(getY()-speed);
		if (this.getY()<0) {
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
	
	public void setState(boolean state){
		alive = state;
		if (!state)
			Core.game.msgDispatcher.dispatchMessage(this, Core.Messages.MISSILE_FREE, this);
	}
	
	public Rectangle getBounds() {
        return bounds;
    }
	
	@Override
	public void reset() {
		if (this.getParent()!=null)	this.getParent().removeActor(this);
		this.clearActions();
		alive = false;
	}

	@Override
	public boolean handleMessage(Telegram msg) {
		// TODO Auto-generated method stub
		return false;
	}

}

package com.pompushka.collapso.actors;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.color;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool.Poolable;

public class EnemyBasic extends Actor implements Poolable{
	
	private TextureRegion tRegion = new TextureRegion(new Texture("enemy_basic_red.png"), 0,0,140,110);
	
	private Rectangle bounds;
	private boolean active = false;
	private int health = 100;
	private int score = 50;
	
	private Color basicColor;
	private Color hitColor;
	
	public EnemyBasic(){
		bounds = new Rectangle();		
	}
	
	public void init(float X, float Y){
		this.setBounds(X, Y, 80, 60);
		bounds.set(X, Y, 80, 60);
		health = 100;
		active = true;
		
		basicColor = getColor();
		hitColor = new Color(1,0,0,1);
		this.setColor(basicColor.r, basicColor.g, basicColor.b, 1.0f);
	}
	
	public boolean hit(int damage){
		return applyDamage(damage) ? true:false;
	}
	
	private boolean applyDamage(int damage){
		health -= damage;
		if  (health > 0) 
			this.addAction(sequence(color(hitColor, 0.1f),color(basicColor, 0.1f)));
		else 
			return true;
		return false;
	}
	
	@Override
	public void act (float delta){
		super.act(delta);
		bounds.setPosition(getX(), getY());	
	}
	
	@Override
	public void draw (Batch batch, float parentAlpha) {
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		batch.draw(tRegion, getX(), getY(), getWidth(), getHeight());
		batch.setColor(color.r, color.g, color.b, color.a);
	}

	@Override
	public void reset() {
		if (this.getParent()!=null)	this.getParent().removeActor(this);
		this.clearActions();
		active = false;
	}
	
	public void setState(boolean state){
		active = state;
	}
	
	public boolean getState(){
		return active;
	}
	
	public Rectangle getBounds() {
        return bounds;
    }
	
	public int getScore(){
		return score;
	}
}

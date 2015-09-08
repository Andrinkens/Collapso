package com.pompushka.collapso.actors;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.color;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.pompushka.collapso.Core;

public class EnemyBasic extends Actor implements Poolable, Telegraph{
	
	private TextureRegion tRegion1 = new TextureRegion(new Texture("enemy_basic_red.png"), 0,0,140,110);
	private TextureRegion tRegion2 = new TextureRegion(new Texture("enemy_basic_explo_1.png"), 0,0,140,110);
	private TextureRegion tRegion3 = new TextureRegion(new Texture("enemy_basic_explo_2.png"), 0,0,140,110);
	
	private TextureRegion currentFrame;
	private Animation currentAnimation;
	private Animation routineAnimation;
	private Animation explodeAnimation;
	
	private float elapsedTime;
	private float explosionDuration;
	
	private Rectangle bounds;
	private int health = 100;
	private int score = 50;
	
	private Color basicColor;
	private Color hitColor;
	
	private boolean alive = false;
	//private CurrentState state;
	
	
	Random random = new Random();
	
	public EnemyBasic(){
		bounds = new Rectangle();
		routineAnimation = new Animation(0.5f, tRegion1);
		explodeAnimation = new Animation(0.5f, tRegion2, tRegion3);
		//explodeAnimation.setPlayMode(PlayMode.LOOP);
		explosionDuration = explodeAnimation.getAnimationDuration();
	}
	
	public void init(float X, float Y){
		this.setBounds(X, Y, 80, 60);
		bounds.set(X, Y, 80, 60);
		health = 100;
		alive = true;
		
		basicColor = getColor();
		hitColor = new Color(1,0,0,1);
		this.setColor(basicColor.r, basicColor.g, basicColor.b, 1.0f);
		currentAnimation = routineAnimation;
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
		elapsedTime += Gdx.graphics.getDeltaTime();
		currentFrame = currentAnimation.getKeyFrame(elapsedTime);
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		batch.draw(currentFrame, getX(), getY(), getWidth(), getHeight());
		batch.setColor(color.r, color.g, color.b, color.a);
		
		if (!alive && elapsedTime>=explosionDuration)
			Core.game.msgDispatcher.dispatchMessage(this, Core.Messages.ENEMY_FREE, this);
	}

	@Override
	public void reset() {
		if (this.getParent()!=null)	this.getParent().removeActor(this);
		this.clearActions();
		alive = false;
	}
	
	public boolean getState(){
		return alive;
	}
	
	public void explode(){
		//state = CurrentState.EXPLODING;
		currentAnimation = explodeAnimation;
		elapsedTime = 0;
		alive = false;
	}
	
	public Rectangle getBounds() {
        return bounds;
    }
	
	public int getScore(){
		return score;
	}

	@Override
	public boolean handleMessage(Telegram msg) {
		// TODO Auto-generated method stub
		return false;
	}
}

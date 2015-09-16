package com.pompushka.collapso.actors;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.pompushka.collapso.Assets;
import com.pompushka.collapso.Core;
import com.pompushka.collapso.stages.HUDStage;

public class Hero extends Actor implements Telegraph{
	
	private TextureRegion tRegion = Assets.hero;
	private float velocity = Core.HERO_SPEED;
	private int direction = 0;
	private Color color;
	private Gun gun;
	private Rectangle bounds;
	
	public Hero(){
		bounds = new Rectangle();
		this.setBounds(Core.viewPortWidth*0.5f, 0, 1, 1);
		bounds.set(Core.viewPortWidth*0.5f, 0, 1, 1);
		gun = new Gun();
		Core.game.msgDispatcher.addListener(this, Core.Messages.PADS);
	}
	
	@Override
	public void act (float delta){
		super.act(delta);
		gun.act();
		if (direction!=0){
			float newPos = getX()+velocity*direction;
			if (newPos > 0 && newPos < Core.viewPortWidth-getWidth())
				setX(newPos);
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
	
	public void moveLeft(){
		direction = -1;
	}
	public void moveRight(){
		direction = 1;
	}
	public void moveStop(){
		direction = 0;
	}
	
	public Rectangle getBounds() {
        return bounds;
    }	
	
	public void setDirection(int dir){
		direction = dir;
	}
	
	public int getDirection(){
		return direction;
	}
	
	public Gun getGun(){
		return gun;
	}
	
	@Override
	public boolean handleMessage(Telegram msg) {
		if (msg.message == Core.Messages.PADS){
			int state = (Integer)msg.extraInfo;

			switch (state){
				case HUDStage.PadState.PAD_LEFT_DN:
					setDirection(getDirection()-1);break;
				case HUDStage.PadState.PAD_LEFT_UP:
					setDirection(getDirection()+1);break;
				case HUDStage.PadState.PAD_RIGHT_DN:
					setDirection(getDirection()+1);break;
				case HUDStage.PadState.PAD_RIGHT_UP:
					setDirection(getDirection()-1);break;
			}
		}
		

		
		return false;
	}
	
}

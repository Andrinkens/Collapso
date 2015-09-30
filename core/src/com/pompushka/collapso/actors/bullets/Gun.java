package com.pompushka.collapso.actors.bullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.pompushka.collapso.Assets;
import com.pompushka.collapso.Core;

public abstract class Gun extends Actor implements Telegraph{

	public class BulletSpawnDef{
		public float X;
		public float Y;	
		public int BulletType;
		
		public BulletSpawnDef(float X, float Y, int BulletType){
			this.X = X;
			this.Y = Y;
			this.BulletType = BulletType;
		}
	}
	
	private float shotingDelay = 0.2f;
	private float reloadDelay = 1.0f;
	private int magazineSize = 5;
	private int currentBullet = 0;
	private boolean isReloading = true;

	private float elapsedTime = 0;
	//private Vector2 offset;
	private int currentBulletType = 0;
	
	public Gun(float X, float Y){
		this.setSize(0.1f, 0.1f);
		this.setPosition(X, Y);
	}
	
	public void setBulletType(int bulletType){
		currentBulletType = bulletType;
	}
	
	public void setRate(float rate){
		this.shotingDelay = rate;
	}
	
	public float getRate(){
		return shotingDelay;
	}
	
	public void setDelay(float delay){
		reloadDelay = delay;
	}
	
	public float getDelay(){
		return reloadDelay;
	}
	
	public void setMagazineSize(int size){
		magazineSize = size;
	}
	
	public int getMagazineSize(){
		return magazineSize;
	}
	
	@Override
	public void act (float delta){
		super.act(delta);
		elapsedTime+=delta;
		if (isReloading){
			if (elapsedTime>=reloadDelay){
				isReloading = false;
				elapsedTime = 0;
				shoot(this.getParent().getX()+getX(), this.getParent().getY()+getY(), currentBulletType);
			}
		}
		else 
			if (elapsedTime>=shotingDelay){
				elapsedTime = 0;
				shoot(this.getParent().getX()+getX(), this.getParent().getY()+getY(), currentBulletType);
			}
	}
	
	public boolean shoot(float ownerX, float ownerY, int bulletType){
		Core.game.msgDispatcher.dispatchMessage(this, Core.Messages.BULLET_SHOT, new BulletSpawnDef(ownerX,ownerY,bulletType));
		currentBullet++;
		if (currentBullet == magazineSize){
			currentBullet = 0;
			isReloading = true;
		}	
		return true;
	}

	@Override
	public boolean handleMessage(Telegram msg) {
		return false;
	}
	
}

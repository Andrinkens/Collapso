package com.pompushka.collapso.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.pompushka.collapso.Assets;
import com.pompushka.collapso.Core;

public class Gun implements Telegraph{

	public class BulletInfo{
		public float X;
		public float Y;	
		public int BulletType;
		
		public BulletInfo(float X, float Y, int BulletType){
			this.X = X;
			this.Y = Y;
			this.BulletType = BulletType;
		}
	}
	
	private Hero hero;
	
	private float rate = 0.5f;
	private float elapsedTime = 0;
	private boolean ready = true;
	private float X;
	private float Y;
	public float offsetX;
	public float offsetY;
	private int currentBulletType = 1;
	
	public Gun(Hero hero){
		this.hero = hero;
		this.offsetX = this.hero.getWidth()/2;
		this.offsetY = this.hero.getHeight();
	}
	
	public boolean shoot(int bulletType){
		Core.game.msgDispatcher.dispatchMessage(this, Core.Messages.BULLET_SHOT, new BulletInfo(hero.getX()+offsetX,hero.getY()+offsetY,bulletType));
		ready = false;
		return true;
	}
	
	public void update(float delta){
		elapsedTime+=delta;
		if (elapsedTime>=rate){
			elapsedTime = 0;
			ready = true;
			shoot(currentBulletType);
		}
	}

	@Override
	public boolean handleMessage(Telegram msg) {
		return false;
	}
	
}

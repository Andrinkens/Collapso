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
	
	private float rate = 2f;
	private int counter = 0;
	private boolean ready = true;
	private float X;
	private float Y;
	public float offsetX;
	public float offsetY;	
	
	public Gun(float offsetX, float offsetY){
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}
	
	public boolean shoot(){
		Core.game.msgDispatcher.dispatchMessage(this, Core.Messages.BULLET_SHOT, new BulletInfo(X,Y,0));
		ready = false;
		return true;
	}
	
	public void update(float X, float Y){
		this.X = offsetX+X;
		this.Y = offsetY+Y;
		counter++;
		if (counter>=rate*20){
			counter = 0;
			ready = true;
			shoot();
		}
	}

	@Override
	public boolean handleMessage(Telegram msg) {
		return false;
	}
	
}

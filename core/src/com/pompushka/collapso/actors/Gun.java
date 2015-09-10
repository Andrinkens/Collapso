package com.pompushka.collapso.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.pompushka.collapso.Core;

public class Gun implements Telegraph{

	private float rate = 2f;
	private int counter = 0;
	private boolean ready = true;
	
	private BulletBasic bullet;
	
    private final Array<BulletBasic> activeBullets = new Array<BulletBasic>();
    
	private final Pool<BulletBasic> bulletPool = new Pool<BulletBasic>() {
        @Override
        protected BulletBasic newObject() {
            return new BulletBasic();
        }
    };
	
	public Gun(){
		Core.game.msgDispatcher.addListener(this, Core.Messages.BULLET_FREE);
	}
	
	public boolean shoot(){
		bullet = bulletPool.obtain();
		activeBullets.add(bullet);
		Core.game.msgDispatcher.dispatchMessage(this, Core.Messages.SHOOT, bullet);
		ready = false;
		return true;
	}
	
	public void act(){
		counter++;
		if (counter>=rate*20){
			counter = 0;
			ready = true;
			shoot();
		}
	}
	
	public Array<BulletBasic> getBullets(){
		return activeBullets;
	}

	@Override
	public boolean handleMessage(Telegram msg) {
		if (msg.message == Core.Messages.BULLET_FREE){
			bullet = (BulletBasic) msg.extraInfo;
			bulletPool.free(bullet);
			activeBullets.removeValue(bullet, true);
		}
		return false;
	}
	
}

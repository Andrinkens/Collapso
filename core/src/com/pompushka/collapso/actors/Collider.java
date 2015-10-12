package com.pompushka.collapso.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.utils.Array;
import com.pompushka.collapso.Assets;
import com.pompushka.collapso.Core;
import com.pompushka.collapso.actors.bullets.BulletBasic;

public class Collider implements Telegraph{

	private PoolAdaptor pool;
	private Hero hero;
	
	public Collider(PoolAdaptor pool, Hero hero){
		this.pool = pool;
		this.hero = hero;
	}
	
	public void update(){
		checkBulletHits(pool.getBullets(), pool.getEnemies());
		checkMissileHits(hero, pool.getMissiles());
	}
	
	public void checkBulletHits(Array<BulletBasic> bullets, Array<EnemyBasic> enemies){
        for (int i = enemies.size; --i >= 0;) {
            EnemyBasic enemy = enemies.get(i);
            if (enemy.getState()){
                for (int j = bullets.size; --j >= 0;) {
                    BulletBasic bullet = bullets.get(j);
                    if (enemy.getState() && enemy.getBounds().overlaps(bullet.getBounds())){
                    	bullet.setState(false);
                    	if (enemy.hit(bullet.getDamage())){
                    		enemy.explode();
                    		Core.game.msgDispatcher.dispatchMessage(this, Core.Messages.SCORE, enemy.getScore());
                    	}
                    }
            	}
            }
        }
	}

	public void checkMissileHits(Hero hero, Array<EnemyMissile> missiles){
        for (int i = missiles.size; --i >= 0;) {
            EnemyMissile missile = missiles.get(i);
            if (missile.getBounds().overlaps(hero.getBounds())){
            	missile.setState(false);
            	int health = hero.applyDamage(missile.getDamage());
            	Core.game.msgDispatcher.dispatchMessage(this, Core.Messages.LIVE, health);
            }
        }
	}
	
	@Override
	public boolean handleMessage(Telegram msg) {
		// TODO Auto-generated method stub
		return false;
	}
	
}

package com.pompushka.collapso.actors;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.pompushka.collapso.Core;
import com.pompushka.collapso.actors.Gun.BulletSpawnDef;
import com.pompushka.collapso.actors.bullets.Bullet;
import com.pompushka.collapso.actors.bullets.BulletBasic;
import com.pompushka.collapso.actors.bullets.Weapon_1;
import com.pompushka.collapso.actors.bullets.Weapon_2;

public class PoolAdaptor implements Telegraph{

	private Stage stage;
	
	private final Array<EnemyBasic> activeEnemies = new Array<EnemyBasic>();
	private final Pool<EnemyBasic> enemyPool = new Pool<EnemyBasic>() {
        @Override
        protected EnemyBasic newObject() {
            return new EnemyBasic();
        }
    };
    
	private final Array<EnemyMissile> activeMissiles = new Array<EnemyMissile>();
	private final Pool<EnemyMissile> missilePool = new Pool<EnemyMissile>() {
        @Override
        protected EnemyMissile newObject() {
            return new EnemyMissile();
        }
    };
    
    private Array<BulletBasic> activeBullets = new Array<BulletBasic>();
	private Pool<BulletBasic> bulletPool[] = new Pool[Core.BULLETS_TYPE_NUM];
    
	public PoolAdaptor(final Stage stage){
		this.stage = stage;
		
    	Core.game.msgDispatcher.addListener(this, Core.Messages.ENEMY_FREE);
    	Core.game.msgDispatcher.addListener(this, Core.Messages.MISSILE_FREE);
    	Core.game.msgDispatcher.addListener(this, Core.Messages.BULLET_FREE);
    	
    	Core.game.msgDispatcher.addListener(this, Core.Messages.BULLET_SHOT);
    	Core.game.msgDispatcher.addListener(this, Core.Messages.MISSILE_SHOT);
    	
		bulletPool[0] = new Pool<BulletBasic>() {
	        @Override
	        protected BulletBasic newObject() {
	            return new Weapon_1();
	        }
	    };
		bulletPool[1] = new Pool<BulletBasic>() {
	        @Override
	        protected BulletBasic newObject() {
	            return new Weapon_2();
	        }
	    };
	}
    
	public EnemyBasic spawnEnemy(float X, float Y){
		EnemyBasic enemy = enemyPool.obtain();
		enemy.init(X, Y);
		activeEnemies.add(enemy);
		stage.addActor(enemy);
		return enemy;
	}
	
	public EnemyMissile spawnMissile(float X, float Y){
		EnemyMissile missile = missilePool.obtain();
		missile.init(X,Y);
		activeMissiles.add(missile);
		stage.addActor(missile);
		return missile;
	}
	
	public BulletBasic spawnBullet(float X, float Y, int type){
		BulletBasic bullet = bulletPool[type].obtain();
		bullet.init(X,Y);
		activeBullets.add(bullet);
		stage.addActor(bullet);
		return bullet;
	}
	
	public void removeEnemy(EnemyBasic enemy){
		enemyPool.free(enemy);
		activeEnemies.removeValue(enemy, true);
	}
	
	public void removeMissile(EnemyMissile missile){
		missilePool.free(missile);
		activeMissiles.removeValue(missile, true);
	}
	
	public void removeBullet(BulletBasic bullet){
		bulletPool[bullet.getType()].free(bullet);
		activeBullets.removeValue(bullet, true);
	}
	
	public Array<EnemyBasic> getEnemies(){
		return activeEnemies;
	}
	
	public Array<EnemyMissile> getMissiles(){
		return activeMissiles;
	}
	
	public Array<BulletBasic> getBullets(){
		return activeBullets;
	}

	@Override
	public boolean handleMessage(Telegram msg) {
		if (msg.message == Core.Messages.ENEMY_FREE){
			EnemyBasic enemy = (EnemyBasic) msg.extraInfo;
			removeEnemy(enemy);
		}
		
		if (msg.message == Core.Messages.MISSILE_FREE){
			EnemyMissile missile = (EnemyMissile) msg.extraInfo;
			removeMissile(missile);
		}
		
		if (msg.message == Core.Messages.BULLET_FREE){
			BulletBasic bullet = (BulletBasic) msg.extraInfo;
			removeBullet(bullet);
		}
		
		if (msg.message == Core.Messages.MISSILE_SHOT){
			EnemyBasic enemy = (EnemyBasic) msg.extraInfo;
			spawnMissile(enemy.getX()+enemy.getBounds().width*0.5f, enemy.getY());
		}
		
		if (msg.message == Core.Messages.BULLET_SHOT){
			BulletSpawnDef bi = (BulletSpawnDef) msg.extraInfo;
			spawnBullet(bi.X, bi.Y, bi.BulletType);
		}
		
		return false;
	}
	
}

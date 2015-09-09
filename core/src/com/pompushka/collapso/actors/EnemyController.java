package com.pompushka.collapso.actors;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.pompushka.collapso.Core;

public class EnemyController implements Telegraph{
	
	private EnemyBasic enemy;
	private EnemyMissile missile;
	private Stage stage;
	private Random random = new Random();
	
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
    
    public EnemyController(Stage stage){
    	this.stage = stage;
    	Core.game.msgDispatcher.addListener(this, Core.Messages.ENEMY_FREE);
    	Core.game.msgDispatcher.addListener(this, Core.Messages.MISSILE_FREE);
    	spawnEnemies();
    	startShoting();
    }
    
	public void update(){
		
	}
	
	private void startShoting(){
    	Timer.schedule(new Task(){
            @Override
            public void run() {
            	shot(activeEnemies.get(random.nextInt(activeEnemies.size)));
            }
        	}, 1        //    (delay)
        	 , 1     //    (seconds)
    	);
	}
	
	private void spawnEnemies(){
		for (int i=0;i<4;i++)
			for (int j=1;j<7;j++){
				enemy = enemyPool.obtain();
				enemy.init(j*stage.getWidth()/8, stage.getHeight()-stage.getHeight()/8-i*(stage.getHeight()/8));
				activeEnemies.add(enemy);
				stage.addActor(enemy);
			}
	}
	
	public Array<EnemyBasic> getEnemies(){
		return activeEnemies;
	}
	
	public Array<EnemyMissile> getMissiles(){
		return activeMissiles;
	}
	
	private void shot(EnemyBasic enemy){
		missile = missilePool.obtain();
		missile.init(enemy.getX()+enemy.getBounds().width/2-missile.getWidth(), enemy.getY());
		activeMissiles.add(missile);
		stage.addActor(missile);
	}

	@Override
	public boolean handleMessage(Telegram msg) {
		if (msg.message == Core.Messages.ENEMY_FREE){
			enemy = (EnemyBasic) msg.extraInfo;
			enemyPool.free(enemy);
			activeEnemies.removeValue(enemy, true);
	        if (activeEnemies.size == 0)
	            spawnEnemies();
		}
		
		if (msg.message == Core.Messages.MISSILE_FREE){
			missile = (EnemyMissile) msg.extraInfo;
			missilePool.free(missile);
			activeMissiles.removeValue(missile, true);
		}
		
		return false;
	}
    
}

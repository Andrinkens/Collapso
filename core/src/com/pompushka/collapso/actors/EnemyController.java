package com.pompushka.collapso.actors;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.math.Interpolation;
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
    	Core.game.msgDispatcher.addListener(this, Core.Messages.MISSILE_SHOT);
    	spawnEnemies();
    	//startShoting();
    }
    
	public void update(){
		
	}
	
	private void spawnEnemies(){/*
		for (int i=0;i<4;i++)
			for (int j=1;j<7;j++){
				enemy = enemyPool.obtain();
				enemy.init(j*stage.getWidth()/8, stage.getHeight()-i*(stage.getHeight()/8)+300);
				activeEnemies.add(enemy);
				stage.addActor(enemy);
				enemy.addAction(parallel(Actions.moveBy(0,-900, 5f),sequence(Actions.moveBy(-50,0, 2.5f,Interpolation.sine),Actions.moveBy(50,0, 2.5f,Interpolation.sine))));
			}*/
		enemy = spawnEnemy(100, stage.getHeight()+300);
		enemy.addAction(parallel(Actions.moveBy(0,-9000, 100f),forever(sequence(Actions.moveBy(100,0, 2.5f,Interpolation.sine),Actions.moveBy(-100,0, 2.5f,Interpolation.sine)))));

		enemy = spawnEnemy(stage.getWidth()-100, stage.getHeight()+300);
		enemy.addAction(parallel(Actions.moveBy(0,-9000, 100f),forever(sequence(Actions.moveBy(-50,0, 2.5f,Interpolation.sine),Actions.moveBy(50,0, 2.5f,Interpolation.sine)))));

	}
	
	private EnemyBasic spawnEnemy(float X, float Y){
		EnemyBasic enm = enemyPool.obtain();
		enm.init(X, Y);
		activeEnemies.add(enm);
		stage.addActor(enm);
		return enm;
	}
	
	private void removeEnemy(EnemyBasic enm){
		enemyPool.free(enm);
		activeEnemies.removeValue(enm, true);
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
			removeEnemy(enemy);
	        if (activeEnemies.size == 0)
	            spawnEnemies();
		}
		
		if (msg.message == Core.Messages.MISSILE_FREE){
			missile = (EnemyMissile) msg.extraInfo;
			missilePool.free(missile);
			activeMissiles.removeValue(missile, true);
		}
		
		if (msg.message == Core.Messages.MISSILE_SHOT){
			enemy = (EnemyBasic) msg.extraInfo;
			shot(enemy);
		}
		
		return false;
	}
	
	
    
}

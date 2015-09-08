package com.pompushka.collapso.actors;

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
import com.pompushka.collapso.Core;

public class EnemyController implements Telegraph{
	
	private EnemyBasic enemy;
	private Stage stage;
	
	private final Array<EnemyBasic> activeEnemies = new Array<EnemyBasic>();
	private final Pool<EnemyBasic> enemyPool = new Pool<EnemyBasic>() {
        @Override
        protected EnemyBasic newObject() {
            return new EnemyBasic();
        }
    };
    
    public EnemyController(Stage stage){
    	this.stage = stage;
    	Core.game.msgDispatcher.addListener(this, Core.Messages.ENEMY_FREE);
    	spawnEnemies();
    }
    
	public void update(){

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

	@Override
	public boolean handleMessage(Telegram msg) {
		if (msg.message == Core.Messages.ENEMY_FREE){
			enemy = (EnemyBasic) msg.extraInfo;
			enemyPool.free(enemy);
			activeEnemies.removeValue(enemy, true);
		}
        if (activeEnemies.size == 0)
        spawnEnemies();
		return false;
	}
    
}

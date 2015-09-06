package com.pompushka.collapso.actors;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class EnemyController{
	
	private EnemyBasic enemy;
	private Stage stage;
	
	private MoveByAction mbAction1 = new MoveByAction();
	private MoveByAction mbAction2 = new MoveByAction();
	
	private final Array<EnemyBasic> activeEnemies = new Array<EnemyBasic>();
	private final Pool<EnemyBasic> enemyPool = new Pool<EnemyBasic>() {
        @Override
        protected EnemyBasic newObject() {
            return new EnemyBasic();
        }
    };
    
    public EnemyController(Stage stage){
    	this.stage = stage;
    }
    
	public void update(){
		
        for (int i = activeEnemies.size; --i >= 0;) {
        	enemy = activeEnemies.get(i);
			if (!enemy.getState())	{
				enemyPool.free(enemy);
				activeEnemies.removeIndex(i);
			}
        }
        
        if (activeEnemies.size == 0)
        	spawnEnemies();
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
    
}

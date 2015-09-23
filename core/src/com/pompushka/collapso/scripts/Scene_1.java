package com.pompushka.collapso.scripts;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.pompushka.collapso.Core;
import com.pompushka.collapso.actors.EnemyBasic;
import com.pompushka.collapso.actors.PoolAdaptor;

public class Scene_1 extends Scenario{
	
	private PoolAdaptor pool;
	private Script currentAction;	
	private Array<Script> scripts = new Array<Script>();
	
	public Scene_1(PoolAdaptor pool){
		this.pool = pool;
		scripts.add(new Script_1());
		scripts.add(new Script_1());
	}
	
	public void start(){
		currentAction = scripts.first();
		currentAction.start();
	}
	
	public boolean checkEnd(){
		if (currentAction.checkEnd())
			if (nextScript()) return true;
		return false;
	}
	
	private boolean nextScript(){
		if (scripts.indexOf(currentAction, true) < scripts.size-1){
			currentAction = scripts.get(scripts.indexOf(currentAction, false) + 1);
			currentAction.start();
			return false;
		}
		else
			return true;	
	}
	
	private class Script_1 extends Script{
		
		public void start(){
			for (int i = 0;i<5;i++)
				for (int j = 0;j<3;j++){
					EnemyBasic enemy = pool.spawnEnemy(1f+i*1.5f, Core.viewPortHeight-1-j);
					//enemy.addAction(forever(sequence(Actions.moveBy(1,0, 2.5f,Interpolation.sine),Actions.moveBy(-1,0, 2.5f,Interpolation.sine))));
					enemy.addAction(forever(sequence(Actions.moveBy(1,0, 2.5f),Actions.moveBy(-1,0, 2.5f))));
				}
				
			

			
			//EnemyBasic enemy = pool.spawnEnemy(1, Core.viewPortHeight-1);
			//enemy.addAction(parallel(Actions.moveBy(0,-90, 100f),forever(sequence(Actions.moveBy(1,0, 2.5f,Interpolation.sine),Actions.moveBy(-1,0, 2.5f,Interpolation.sine)))));

			//enemy = pool.spawnEnemy(Core.viewPortWidth-1-1, Core.viewPortHeight-1);
			//enemy.addAction(parallel(Actions.moveBy(0,-90, 100f),forever(sequence(Actions.moveBy(-0.5f,0, 2.5f,Interpolation.sine),Actions.moveBy(0.5f,0, 2.5f,Interpolation.sine)))));
		}
		
		public boolean checkEnd(){
			if (pool.getEnemies().size == 0)
				return true;
			return false;
			
		}
	}

	private class Script_2 extends Script{
		
		public void start(){
			EnemyBasic enemy = pool.spawnEnemy(1, Core.viewPortHeight);
			enemy.addAction(parallel(Actions.moveBy(0,-90, 100f),forever(sequence(Actions.moveBy(1,0, 2.5f,Interpolation.sine),Actions.moveBy(-1,0, 2.5f,Interpolation.sine)))));

			enemy = pool.spawnEnemy(Core.viewPortWidth-1-1, Core.viewPortHeight);
			enemy.addAction(parallel(Actions.moveBy(0,-90, 100f),forever(sequence(Actions.moveBy(-0.5f,0, 2.5f,Interpolation.sine),Actions.moveBy(0.5f,0, 2.5f,Interpolation.sine)))));
		}
		
		public boolean checkEnd(){
			if (pool.getEnemies().size == 0)
				return true;
			return false;
			
		}
	}

	
}

package com.pompushka.collapso.stages;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pompushka.collapso.Core;
import com.pompushka.collapso.actors.BulletBasic;
import com.pompushka.collapso.actors.Collider;
import com.pompushka.collapso.actors.EnemyBasic;
import com.pompushka.collapso.actors.Hero;
import com.pompushka.collapso.actors.PoolAdaptor;


public class CollapsoStage extends Stage{

	private Hero hero;
	private Collider collider;
	private PoolAdaptor pool;
    
	public CollapsoStage(Viewport viewport, SpriteBatch batch) {
		super(viewport, batch);
		
		pool = new PoolAdaptor(this);
		hero = new Hero();addActor(hero);
		collider = new Collider(pool, hero);
		
		setDebugAll(Core.GAME_RENDERER_STATE);
		
		spawnEnemies();
	}
	
	@Override
	public void act (float delta){
		super.act(delta);
		collider.update();
	}
		
	public void resize(int width, int height){
		this.getViewport().update(width, height);
	}
	
	private void spawnEnemies(){
		EnemyBasic enemy = pool.spawnEnemy(1, Core.viewPortHeight);
		enemy.addAction(parallel(Actions.moveBy(0,-90, 100f),forever(sequence(Actions.moveBy(1,0, 2.5f,Interpolation.sine),Actions.moveBy(-1,0, 2.5f,Interpolation.sine)))));

		enemy = pool.spawnEnemy(Core.viewPortWidth-1-1, Core.viewPortHeight);
		enemy.addAction(parallel(Actions.moveBy(0,-90, 100f),forever(sequence(Actions.moveBy(-0.5f,0, 2.5f,Interpolation.sine),Actions.moveBy(0.5f,0, 2.5f,Interpolation.sine)))));
	}
	

}


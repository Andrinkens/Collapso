package com.pompushka.collapso.stages;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pompushka.collapso.Core;
import com.pompushka.collapso.actors.BulletBasic;
import com.pompushka.collapso.actors.Collider;
import com.pompushka.collapso.actors.EnemyController;
import com.pompushka.collapso.actors.Hero;

public class CollapsoStage1 extends Stage implements Telegraph{

	private Hero hero;
	private EnemyController enemyController;
	private Collider collider;
    
	public CollapsoStage1(Viewport viewport, SpriteBatch batch) {
		super(viewport, batch);
		
		collider = new Collider();
		hero = new Hero();
		enemyController = new EnemyController(this);
		addActor(hero);

		Core.game.msgDispatcher.addListener(this, Core.Messages.SHOOT);
		
		setDebugAll(Core.GAME_RENDERER_STATE);
	}
	
	@Override
	public void act (float delta){
		super.act(delta);
		enemyController.update();
		collider.checkHits(hero.getGun().getBullets(), enemyController.getEnemies());
	}
		
	public void resize(int width, int height){
		this.getViewport().update(width, height, true);
	}
	
	public boolean handleMessage(Telegram msg) {
		if (msg.message == Core.Messages.SHOOT){
			BulletBasic bullet = (BulletBasic) msg.extraInfo;
			bullet.init(hero.getX()+hero.getWidth()/2, hero.getHeight());
			this.addActor(bullet);
		}
		return false;
	}

}

package com.pompushka.collapso.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pompushka.collapso.CollapsoGame;
import com.pompushka.collapso.Core;
import com.pompushka.collapso.actors.BulletBasic;
import com.pompushka.collapso.actors.EnemyBasic;
import com.pompushka.collapso.actors.Hero;
import com.pompushka.collapso.actors.Scores;
import com.pompushka.collapso.stages.HUDStage.PadState;

public class CollapsoStage1 extends Stage implements Telegraph{

	private Hero hero;
	private EnemyBasic enemy;
	private BulletBasic bullet;
	
	private final Pool<BulletBasic> bulletPool = new Pool<BulletBasic>() {
        @Override
        protected BulletBasic newObject() {
            return new BulletBasic();
        }
    };
	
	public CollapsoStage1(Viewport viewport, SpriteBatch batch) {
		super(viewport, batch);
		
		hero = new Hero();
		this.addActor(hero);

		enemy = new EnemyBasic();
		this.addActor(enemy);
		
		bullet = bulletPool.obtain();
		bullet.init(hero.getX()+hero.getWidth()/2, hero.getHeight());
		this.addActor(bullet);
		
		Core.game.msgDispatcher.addListener(this, Core.Messages.PADS);
		
		this.setDebugAll(true);
	}
	
	@Override
	public void act (float delta){
		super.act(delta);
		if (bullet!=null)
			if (!bullet.getState())	{
				bulletPool.free(bullet);
				bullet = bulletPool.obtain();
				bullet.init(hero.getX()+hero.getWidth()/2, hero.getHeight());
				this.addActor(bullet);
			}
		
		if (enemy.getBounds().overlaps(bullet.getBounds())){
			bulletPool.free(bullet);
			bullet = bulletPool.obtain();
			bullet.init(hero.getX()+hero.getWidth()/2, hero.getHeight());
			this.addActor(bullet);
		}
	}
	
	public void resize(int width, int height){
		this.getViewport().update(width, height, true);
	}

	@Override
	public boolean handleMessage(Telegram msg) {
		
		int state = (Integer)msg.extraInfo;

		switch (state){
			case HUDStage.PadState.PAD_LEFT_DN:
				hero.setDirection(hero.getDirection()-1);break;
			case HUDStage.PadState.PAD_LEFT_UP:
				hero.setDirection(hero.getDirection()+1);break;
			case HUDStage.PadState.PAD_RIGHT_DN:
				hero.setDirection(hero.getDirection()+1);break;
			case HUDStage.PadState.PAD_RIGHT_UP:
				hero.setDirection(hero.getDirection()-1);break;
		}
		
		return false;
	}

}

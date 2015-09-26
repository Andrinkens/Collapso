package com.pompushka.collapso.stages;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pompushka.collapso.Core;
import com.pompushka.collapso.actors.Background;
import com.pompushka.collapso.actors.BulletBasic;
import com.pompushka.collapso.actors.Collider;
import com.pompushka.collapso.actors.EnemyBasic;
import com.pompushka.collapso.actors.Hero;
import com.pompushka.collapso.actors.PoolAdaptor;
import com.pompushka.collapso.scripts.Scenario;
import com.pompushka.collapso.scripts.Scene_1;


public class CollapsoStage extends Stage{

	private Background bkgnd;
	private Hero hero;
	private Collider collider;
	private PoolAdaptor pool;
	
	Scene_1 scene;
    
	public CollapsoStage(Viewport viewport, SpriteBatch batch) {
		super(viewport, batch);
		
		bkgnd = new Background();this.addActor(bkgnd);
		pool = new PoolAdaptor(this);
		hero = new Hero();addActor(hero);
		hero.addListener(new HeroTouchListener());
		collider = new Collider(pool, hero);
		
		setDebugAll(Core.GAME_RENDERER_STATE);
		
		scene = new Scene_1(pool);
		scene.start();
		
		Gdx.input.setInputProcessor(this);
	}
	
	@Override
	public void act (float delta){
		if (!Core.isPaused){
			super.act(delta);
			collider.update();
			scene.checkEnd();
		}
	}
		
	public void resize(int width, int height){
		this.getViewport().update(width, height);
	}
	
	public class HeroTouchListener extends InputListener{
	    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	        System.out.println("Hero down");
	        return true;
	    }

	    public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        System.out.println("Hero up");
	    }

	}
}


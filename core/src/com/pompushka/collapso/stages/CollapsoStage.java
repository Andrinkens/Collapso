package com.pompushka.collapso.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pompushka.collapso.Core;
import com.pompushka.collapso.actors.Background;
import com.pompushka.collapso.actors.Collider;
import com.pompushka.collapso.actors.Hero;
import com.pompushka.collapso.actors.InvisiblePads;
import com.pompushka.collapso.actors.PoolAdaptor;
import com.pompushka.collapso.scripts.Scene_1;

public class CollapsoStage extends Stage{

	private Background bkgnd;
	private Hero hero;
	private Collider collider;
	private PoolAdaptor pool;
	private InvisiblePads pads;
	
	Scene_1 scene;
    
	public CollapsoStage(Viewport viewport, SpriteBatch batch) {
		super(viewport, batch);
		setDebugAll(Core.GAME_RENDERER_STATE);
		
		bkgnd = new Background();
		pool = new PoolAdaptor(this);
		hero = new Hero();
		pads = new InvisiblePads();
		
		this.addActor(bkgnd);
		this.addActor(pads);
		addActor(hero);

		collider = new Collider(pool, hero);

		Gdx.input.setInputProcessor(this);
		this.setKeyboardFocus(pads.findActor("PADLEFT"));
		
		scene = new Scene_1(pool);
		scene.start();
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
}


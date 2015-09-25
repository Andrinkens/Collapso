package com.pompushka.collapso.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.pompushka.collapso.Assets;
import com.pompushka.collapso.Core;

public class Background extends Actor{
	
	private Texture bkgnd1 = Assets.background1;
	private Texture bkgnd2 = Assets.background2;
	
	private int regionMaskPos1;
	private int regionMaskPos2;
	
	public Background(){
		this.setBounds(0, 0, Core.viewPortWidth, Core.viewPortHeight);
	}
	
	@Override
	public void act (float delta){
		super.act(delta);
		regionMaskPos1-=1;
		regionMaskPos2-=2;
	}
	
	@Override
	public void draw (Batch batch, float parentAlpha) {
		batch.draw(bkgnd1, getX(), getY(),getWidth(), getHeight(),0,regionMaskPos1,bkgnd1.getWidth(),bkgnd1.getHeight(),false,false);
		batch.draw(bkgnd2, getX(), getY(),getWidth(), getHeight(),0,regionMaskPos2,bkgnd2.getWidth(),bkgnd2.getHeight(),false,false);
		//batch.draw(regionMask2, getX()+regionMaskPos1, getY(), getWidth(), getHeight());
	}
}

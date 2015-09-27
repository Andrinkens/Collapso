package com.pompushka.collapso.actors.bullets;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.pompushka.collapso.Assets;

public class Bullet extends BulletBasic{
	
	private TextureRegion tRegion = Assets.bulletRed;
	
	@Override
	public void draw (Batch batch, float parentAlpha) {
		this.color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		batch.draw(tRegion, getX(), getY(), getWidth(), getHeight());
		batch.setColor(color.r, color.g, color.b, color.a);
	}

}

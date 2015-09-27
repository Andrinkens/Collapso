package com.pompushka.collapso.actors.bullets;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pompushka.collapso.Assets;

public class Weapon_1 extends BulletBasic{

	public Weapon_1(){
		this.setType(0);
		this.setTextureRegion(Assets.bullet[0]);
		this.setDamage(50);
	}

}

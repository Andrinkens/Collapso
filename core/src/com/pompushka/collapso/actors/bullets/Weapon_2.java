package com.pompushka.collapso.actors.bullets;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pompushka.collapso.Assets;

public class Weapon_2 extends BulletBasic{

	public Weapon_2(){
		this.setType(1);
		this.setTextureRegion(Assets.bullet[1]);
		this.setDamage(100);
	}

}

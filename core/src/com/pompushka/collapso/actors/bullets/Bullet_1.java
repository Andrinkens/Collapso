package com.pompushka.collapso.actors.bullets;

import com.pompushka.collapso.Assets;

public class Bullet_1 extends BulletBasic{
	public Bullet_1(){
		this.setType(0);
		this.setSize(0.2f,  0.3f);
		this.setTextureRegion(Assets.bullet[0]);
		this.setDamage(50);
	}
}

package com.pompushka.collapso.actors.bullets;

import com.pompushka.collapso.Assets;

public class Bullet_2 extends BulletBasic{
	public Bullet_2(){
		this.setType(1);
		this.setSize(0.2f,  0.1f);
		this.setTextureRegion(Assets.bullet[1]);
		this.setDamage(25);
	}
}
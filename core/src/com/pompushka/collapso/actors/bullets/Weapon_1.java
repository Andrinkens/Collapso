package com.pompushka.collapso.actors.bullets;

public class Weapon_1 extends Gun{
	public Weapon_1(float X, float Y){
		super(X,Y);
		setRate(1f);
		setDelay(1f);
		setMagazineSize(1);
		setBulletType(0);
	}	
}

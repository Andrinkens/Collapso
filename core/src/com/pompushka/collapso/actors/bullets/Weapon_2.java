package com.pompushka.collapso.actors.bullets;

public class Weapon_2 extends Gun{
	
	public Weapon_2(float X, float Y){
		super(X,Y);
		setRate(0.05f);
		setDelay(1f);
		setMagazineSize(8);
		setBulletType(1);
	}	
	
}

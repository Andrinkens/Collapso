package com.pompushka.collapso.actors.bullets;

public class Weapon_2 extends Gun{
	
	public Weapon_2(float X, float Y){
		super(X,Y);
		setRate(0.2f);
		setDelay(1f);
		setMagazineSize(4);
		setBulletType(1);
	}	
	
}

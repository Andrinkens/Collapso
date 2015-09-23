package com.pompushka.collapso.actors;

import java.util.Random;

public class EnemyLauncher{
	
	Random random = new Random();
	private float elapsedTime = 0;
	private float reloadTime = 0;
	private EnemyBasic enemy;
	private boolean state = false;
	
	public EnemyLauncher(EnemyBasic enemy){
		this.enemy = enemy;
		elapsedTime = 0;
		reloadTime = random.nextFloat()*10+1;
	}
	
	public void start(){
		state = true;
	}
	
	public void stop(){
		state = false;
	}
	
	public void update(float delta){
		if (!state) return;
		elapsedTime+=delta;
		if (elapsedTime>=reloadTime){
			elapsedTime = 0;
			shot();
		}
	}
	
	public void shot(){
		enemy.shot();
	}
	
}

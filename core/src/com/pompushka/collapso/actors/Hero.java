package com.pompushka.collapso.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.sun.javafx.scene.traversal.Direction;

public class Hero extends Actor{
	
	private TextureRegion tRegion = new TextureRegion(new Texture("hero.png"), 0,0,200,200);
	private int velocity = 5;
	private int direction = 0;
	
	
	public Hero(){
		this.setSize(50, 50);
		this.setPosition(320, 1);
	}
	
	@Override
	public void act (float delta){
		super.act(delta);
		if (direction!=0){
			setX(getX()+velocity*direction);
		}
	}
	
	@Override
	public void draw (Batch batch, float parentAlpha) {
			//Color color = getColor();
			//batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
			batch.draw(tRegion, getX(), getY(), getWidth(), getHeight());
			//batch.setColor(color.r, color.g, color.b, color.a);
	}
	
	public void moveLeft(){
		direction = -1;
	}
	public void moveRight(){
		direction = 1;
	}
	public void moveStop(){
		direction = 0;
	}
	
	public void setDirection(int dir){
		direction = dir;
	}
	
	public int getDirection(){
		return direction;
	}
	
}

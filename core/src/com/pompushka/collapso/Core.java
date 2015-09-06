package com.pompushka.collapso;

import com.badlogic.gdx.Gdx;

public abstract class Core {
	
	public static CollapsoGame game;
	
	public static final boolean GAME_RENDERER_STATE = false;
	public static final boolean GUI_RENDERER_STATE = false;
	
	public static final int HERO_SPEED = 5;
	public static final int BULLET_BASIC_SPEED = 10;
	
	public static class Messages {
		public static final int PADS = 1;
		public static final int SCORE = 2;
		public static final int SHOOT = 3;
		public static final int BULLET_FREE = 4;
	}
	
	
	
/********************************************************************************
 * Basic graphics settings    
 */
	public static int applicationHeight = Gdx.graphics.getHeight();
	public static int applicationWidth = Gdx.graphics.getWidth();
	
	public static float ratio = 1.0f * applicationWidth/applicationHeight;
    public static float viewPortWidth = 10.0f;
    public static float viewPortHeight = viewPortWidth / ratio;
    
/********************************************************************************/
    public static void resize(int width, int height){
    	applicationHeight = height;
    	applicationWidth = width;
    	
    	ratio = 1.0f * applicationWidth/applicationHeight;
    	viewPortHeight = viewPortWidth / ratio;
    }
/********************************************************************************/
    
}
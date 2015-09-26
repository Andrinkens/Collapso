package com.pompushka.collapso;

import com.badlogic.gdx.Gdx;

public abstract class Core {
	
	public static CollapsoGame game;
	
	public static final boolean isPaused = false;
	
	public static final boolean GAME_RENDERER_STATE = false;
	public static final boolean GUI_RENDERER_STATE = false;
	
	public static final float HERO_SPEED = 0.1f;
	public static final float BULLET_BASIC_SPEED = 0.15f;
	
	public static class Messages {
		public static final int PADS = 1;//Hero
		public static final int SCORE = 2;//Collider,Scores
		public static final int BULLET_SHOT = 3;//Gun,PoolAdaptor
		public static final int BULLET_FREE = 4;//PoolAdaptor
		public static final int ENEMY_FREE = 5;//PoolAdaptor
		public static final int MISSILE_SHOT = 6;//EnemyBasic, PoolAdaptor
		public static final int MISSILE_FREE = 7;//PoolAdaptor
		public static final int LIVE = 8;//Collider,Scores
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
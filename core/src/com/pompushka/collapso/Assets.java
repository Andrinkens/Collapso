package com.pompushka.collapso;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	
	public static Texture heroTex;
	public static Texture bulletTex;
	public static Texture enemyTex[] = new Texture[4];
	
	public static TextureRegion hero;
	public static TextureRegion bullet;
	public static TextureRegion missile;
	public static Animation enemyAnim;
	public static Animation enemyblows;
	
	public static BitmapFont font;
	
	public static Sound shotSound;
	
	public static Texture loadTexture (String file) {
		return new Texture(Gdx.files.internal(file),true);
	}
	
	public static void load () {
		heroTex = loadTexture("hero.png");
		bulletTex = loadTexture("bullet_basic.png");
		enemyTex[0] = loadTexture("enemy_basic_red.png");
		enemyTex[1] = loadTexture("enemy_basic_red_1.png");
		enemyTex[2] = loadTexture("enemy_basic_explo_1.png");
		enemyTex[3] = loadTexture("enemy_basic_explo_2.png");
		
		hero = new TextureRegion(heroTex, 0,0,200,200);
		bullet = new TextureRegion(bulletTex, 0,0,10,20);
		missile = new TextureRegion(bulletTex, 0,0,10,20);
		
		enemyTex[0].setFilter(TextureFilter.MipMap, TextureFilter.Nearest);
		enemyTex[1].setFilter(TextureFilter.MipMap, TextureFilter.Nearest);
		
		enemyAnim = new Animation(0.5f, new TextureRegion(enemyTex[0], 0,0,140,110), new TextureRegion(enemyTex[1], 0,0,140,110));
		enemyblows = new Animation(0.5f, new TextureRegion(enemyTex[2], 0,0,140,110), new TextureRegion(enemyTex[3], 0,0,140,110));
		
		font = new BitmapFont(Gdx.files.internal("fonts/font1.fnt"));

		shotSound = Gdx.audio.newSound(Gdx.files.internal("shot.wav"));
	}
	
	public static void playSound (Sound sound) {
		if (Settings.soundOn) sound.play(1);
	}
}

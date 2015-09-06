package com.pompushka.collapso.actors;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.utils.Array;
import com.pompushka.collapso.Core;

public class Collider implements Telegraph{

	public void checkHits(Array<BulletBasic> bullets, Array<EnemyBasic> enemies){
        for (int i = enemies.size; --i >= 0;) {
            EnemyBasic enemy = enemies.get(i);
            for (int j = bullets.size; --j >= 0;) {
                BulletBasic bullet = bullets.get(j);
                if (enemy.getBounds().overlaps(bullet.getBounds())){
                	bullet.setState(false);
                	if (enemy.hit(bullet.getDamage())){
                		enemy.setState(false);
                		Core.game.msgDispatcher.dispatchMessage(this, Core.Messages.SCORE, enemy.getScore());
                	}
                }
        	}
        }
	}

	@Override
	public boolean handleMessage(Telegram msg) {
		// TODO Auto-generated method stub
		return false;
	}
	
}

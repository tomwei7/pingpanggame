package com.tomwei7.pingpang;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Timer extends Actor{
	private Boolean isEnd;
	
	private int x;
	private int y;
	
	private int t;
	private int time;
	
	private float tem;
	
	private Texture bg;
	private BitmapFont font;
	
	public Timer(int x, int y, int t) {
		this.x = x;
		this.y = y;
		this.t = t;
		time = this.t;
		isEnd = false;
		bg = new Texture(Gdx.files.internal("data/timer.png"));
		font = new BitmapFont(Gdx.files.internal("data/font.fnt"), Gdx.files.internal("data/font.png"), false);
		
	}
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		if(time>0){
			tem += Gdx.graphics.getDeltaTime();
			time = t-(int)tem;
		}
		else
			isEnd = true;
		batch.draw(bg, x, y);
		font.draw(batch, " "+Integer.toString(time), x+100, y+45);
		super.draw(batch, parentAlpha);
	}
	
	public Boolean isEnd(){
		return this.isEnd;
	}
}

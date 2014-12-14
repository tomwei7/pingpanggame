package com.tomwei7.pingpang;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Scorer extends Actor{
	private int x;
	private int y;
	
	private Texture bg;
	private BitmapFont font;
	
	public Scorer(int x, int y) {
		this.x = x;
		this.y = y;
		bg = new Texture("data/scorer.png");
		font = new BitmapFont(Gdx.files.internal("data/font.fnt"), Gdx.files.internal("data/font.png"), false);
	}
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(bg, x, y);
		font.draw(batch, " "+GameData.SCORE_L+" : "+GameData.SCORE_R, x+150,y+60);
		super.draw(batch, parentAlpha);
	}
}

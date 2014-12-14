package com.tomwei7.pingpang;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Bat extends Actor{
	private float x;
	private float y;
	private float vx;
	private float vy;
	private float temx;
	private Texture bat_img;
	
	public float getVx() {
		return vx;
	}

	public float getVy() {
		return vy;
	}
	
	public Bat() {
		this.x = 640f;
		this.y = 130f;
		this.temx = -1;
		bat_img = AssetLoader.bat;
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		this.update();
		this.check();
		batch.draw(bat_img, this.x, this.y);
		super.draw(batch, parentAlpha);
	}

	private void check() {
		if(this.x < 0)
			this.x = 0;
		if(this.x > 1080)
			this.x = 1080;
		if(this.y < 0)
			this.y = 0;
		if(this.y > 520)
			this.y = 520;
	}
	
	private void update() {
		if(Gdx.input.isTouched()){
			if((getTouch.getTouchX()>getRealX()-180&&getTouch.getTouchX()<getRealX()+180)&&(getTouch.getTouchY()>getRealY()-100&&getTouch.getTouchY()<getRealY()+100)){
				this.x = getTouch.getTouchX()-100;
				if(this.temx != -1){
					this.vx = getTouch.getTouchX() - this.temx;
					this.temx = getTouch.getTouchX();
				}
				else{
					this.temx = getTouch.getTouchX();
				}
			}
		}
	}

	public float getRealY() {
		return this.y+25;
	}

	public float getRealX() {
		return this.x+172;
	}
	
	
}

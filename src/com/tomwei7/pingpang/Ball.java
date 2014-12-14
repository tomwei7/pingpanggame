package com.tomwei7.pingpang;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Ball  extends Actor {
	private float x;
	private float y;
	private float vx;
	private float vy;
	private GameScreen mGameScreen;
	
	private Texture ball_img;
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getVx() {
		return vx;
	}

	public void setVx(float vx) {
		this.vx = vx;
	}

	public float getVy() {
		return vy;
	}

	public void setVy(float vy) {
		this.vy = vy;
	}

	public Ball(float x, float y, GameScreen gameScreen) {
		this.x = x;
		this.y = y;
		this.vx = 0;
		this.vy = 5;
		mGameScreen = gameScreen;
		ball_img = AssetLoader.ball;
		if(GameData.INVITER == false)
		{
			this.vy = 0;
			this.setVisible(false);
		}
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		this.update();
		this.check();
		batch.draw(ball_img,this.x,this.y);
		super.draw(batch, parentAlpha);
	}


	private void update() {
		this.x += this.vx;
		this.y += this.vy;
		if(Math.abs(this.vx) > 2){
//			if(this.vx > 0)
//				this.vx -= GameData.FK;
//			else
//				this.vx += GameData.FK;
			this.vx = this.vx*GameData.FKC;
		}
		if(Math.abs(this.vy) < 20){
			if(this.vy>0)
				this.vy += 0.05;
			else
				this.vy -= 0.05;
		}
	}

	public float getRealX(){
		return this.x+40;
	}
	
	public float getRealY(){
		return this.y+40;
	}
	
	
	private void check() {
		if (getRealX()<40){
			this.x = 40;
			this.vx = -this.vx;
			if(GameData.VIOCE)
				AssetLoader.hit.play();
		}
		else if(getRealX()>1240){
			this.x = 1200;
			this.vx = -this.vx;
			if(GameData.VIOCE)
				AssetLoader.hit.play();
		}
		if (getRealY()>720)
			this.send();	
		if (getRealY()<25){
			if(getRealX()>410&&getRealX()<880){
				lose();
			}
			else
				this.vy = -this.vy;
		}
	}
	
	private void send() {
		mGameScreen.send((int)this.x, (int)this.vx, (int)this.vy);
		this.vx = 0;
		this.vy = 0;
		this.setVisible(false);
	}

	private void lose(){
		mGameScreen.lose();
	}

	public void receive(float x2, float vx2, float vy2) {
		this.x = 1280 - x2;
		this.y = 600;
		this.vx = -vx2;
		this.vy = -vy2;
		this.setVisible(true);
	}
}

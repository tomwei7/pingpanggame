package com.tomwei7.pingpang;

import com.badlogic.gdx.Gdx;

public class getTouch {
	public static int getTouchX(){
		return (int)(Gdx.input.getX()*1280/Gdx.graphics.getWidth());
	}
	public static int getTouchY(){
		return (int)((Gdx.graphics.getHeight()-Gdx.input.getY())*720/Gdx.graphics.getHeight());
	}
}

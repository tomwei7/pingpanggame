package com.tomwei7.pingpang;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.tomwei7.pingpang.GameData.GameState;
import com.tomwei7.pingpang.GameData.GameType;

public class PreparScreen implements Screen{
	private AirHockey mgame;
	
	private Image mprepar;
	private Image mbg;

	private Stage mstage;
	
	
	public PreparScreen(AirHockey game) {
		this.mgame = game;
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		mstage.act();
		mstage.draw();
		if(GameData.PREPAR_L&&GameData.PREPAR_R&&GameData.TYPE!=GameType.no){
			mgame.setScreen(mgame.mGameScreen);
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		GameData.STATE = GameState.prepar;
		GameData.GETWIN = false;
		GameData.SCORE_L = 0;
		GameData.SCORE_R = 0;
		
		mstage = new Stage(1280,720,false);
		
		
		mprepar = new Image(AssetLoader.prepar);
		mprepar.setPosition(234, 227);
		mbg = new Image(AssetLoader.bg);
		mbg.setPosition(0, 0);
		
		mstage.addActor(mbg);
		mstage.addActor(mprepar);
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		mstage.dispose();
	}
	public void start() {
		System.out.println("调用prepar,start函数");
		GameData.PREPAR_R = true;
	}

}

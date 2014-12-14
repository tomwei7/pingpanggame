package com.tomwei7.pingpang;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class HelpScreen implements Screen {

	private AirHockey mgame;
	private Stage mStage;
	private Image mbg;
	public HelpScreen(AirHockey game) {
		mgame = game;
	}
	
	@Override
	public void dispose() {
		mStage.dispose();
		
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
	public void render(float arg0) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		mStage.act();
		mStage.draw();
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		mbg = new Image(AssetLoader.helpscreen);
		mbg.setPosition(0, 0);
		mbg.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
					mgame.setScreen(mgame.mReadyScreen);
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		
		mStage = new Stage(1280, 720, false);
		mStage.addActor(mbg);
		Gdx.input.setInputProcessor(mStage);
	}
	
}

package com.tomwei7.pingpang;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.lenovo.game.GameMessage;
import com.lenovo.game.GameUserInfo;
import com.tomwei7.pingpang.GameData.GameState;
import com.tomwei7.pingpang.GameMessages.PreparGameMessage;

public class OverScreen implements Screen{
	private Image mbg;
	private Image youWin;
	private Image youLose;
	
	private ImageButton quit;
	private ImageButton restart;
	
	private Stage mStage;

	private AirHockey mgame;
	
	public OverScreen(AirHockey game) {
		mgame = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		mStage.act();
		mStage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		GameData.STATE = GameState.overgame;
		mbg = new Image(AssetLoader.bg);
		mbg.setPosition(0, 0);
		youWin = new Image(AssetLoader.youwin);
		youWin.setPosition(213, 245);
		
		youLose = new Image(AssetLoader.youlose);
		youLose.setPosition(213, 245);
		
		restart = new ImageButton(new TextureRegionDrawable(AssetLoader.osrestart));
		restart.setPosition(270, 100);
		restart.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				GameData.PREPAR_L = true;
				sendPreparMsg();
				mgame.setScreen(mgame.mPreparScreen);
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		
		quit = new ImageButton(new TextureRegionDrawable(AssetLoader.osquit));
		quit.setPosition(642, 100);
		quit.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.exit(0);
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		mStage = new Stage(1280,720,false);
		
		mStage.addActor(mbg);
		mStage.addActor(restart);
		mStage.addActor(quit);
		
		if(GameData.WIN)
			mStage.addActor(youWin);
		else
			mStage.addActor(youLose);
		
		Gdx.input.setInputProcessor(mStage);
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		mStage.dispose();
		
	}

	private void sendPreparMsg(){
		if(MainActivity.mRemoteUser.size() == 0)
			return;
		GameUserInfo remoteUser = MainActivity.mRemoteUser.get(0);
		PreparGameMessage message = new PreparGameMessage(MainActivity.mLocalUser.id, remoteUser.id);
		GameMessage msg = message.toGameMessage();
		MainActivity.mGameShare.sendMessage(msg);
	}
	public void prepar() {
		GameData.PREPAR_R = true;
	}
}

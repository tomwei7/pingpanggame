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
import com.tomwei7.pingpang.GameMessages.StartGameMessage;

public class PauseScreen implements Screen{
	private Stage pauseStage;
	private Image pauseBg;
	private ImageButton mPauseButton;
	private ImageButton mQuitButton;
	
	private AirHockey mgame;
	
	public PauseScreen(AirHockey game) {
		mgame = game;
	}
	
	@Override
	public void dispose() {
		pauseStage.dispose();
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void render(float arg0) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		if(GameData.STATE == GameState.gameing)
			mgame.setScreen(mgame.mGameScreen);
		pauseStage.act();
		pauseStage.draw();
	}

	@Override
	public void resize(int arg0, int arg1) {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void show() {
		pauseStage = new Stage(1280,720,false);
		
		pauseBg = new Image(AssetLoader.pauseBg);
		pauseBg.setPosition(0, 0);
		
		mPauseButton = new ImageButton(new TextureRegionDrawable(AssetLoader.pauseButton_go));
		mPauseButton.setPosition(340, 100);
		mPauseButton.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if(GameData.STATE == GameState.pause){
					GameData.STATE = GameState.gameing;
					sendstart();
					System.out.println("restart");
					}
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		mQuitButton = new ImageButton(new TextureRegionDrawable(AssetLoader.pauseButton_quit));
		mQuitButton.setPosition(670, 100);
		mQuitButton.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if(GameData.STATE == GameState.pause){
					mgame.setScreen(mgame.mReadyScreen);
				}
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		pauseStage.addActor(pauseBg);
		pauseStage.addActor(mPauseButton);
		pauseStage.addActor(mQuitButton);
		Gdx.input.setInputProcessor(pauseStage);
	}
	public void sendstart(){
		if(MainActivity.mRemoteUser.size() == 0)
			return;
		GameUserInfo remoteUser = MainActivity.mRemoteUser.get(0);
		StartGameMessage dmseeage = new StartGameMessage(MainActivity.mLocalUser.id, remoteUser.id);
		GameMessage msg = dmseeage.toGameMessage();
		MainActivity.mGameShare.sendMessage(msg);
	}
	
	public void start() {
		GameData.STATE = GameState.gameing;
	}

}

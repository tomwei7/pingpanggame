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
import com.tomwei7.pingpang.GameData.GameType;
import com.tomwei7.pingpang.GameMessages.GameTypeMessage;
import com.tomwei7.pingpang.GameMessages.PreparGameMessage;

public class ChangeScreen implements Screen {
	private AirHockey mgame;
	
	private Image bg;
	private Image title;
	private Image moreButton;
	
	private ImageButton timeButton;
	private ImageButton foreverButton;
	
	Stage mStage;
	
	public ChangeScreen(AirHockey game) {
		this.mgame = game;
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
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
		GameData.STATE = GameState.change;
		bg = new Image(AssetLoader.bg);
		bg.setPosition(0, 0);
		
		title = new Image(AssetLoader.title);
		title.setPosition(470, 600);
		
		timeButton = new ImageButton(new TextureRegionDrawable(AssetLoader.button_change_time));
		timeButton.setPosition(130, 230);
		timeButton.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				GameData.TYPE = GameType.time;
				sendTypeMsg(1);
				sendPreparMsg();
				changescreen();
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		
		foreverButton = new ImageButton(new TextureRegionDrawable(AssetLoader.button_change_forever));
		foreverButton.setPosition(535, 230);
		foreverButton.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				GameData.TYPE = GameType.forever;
				sendTypeMsg(2);
				sendPreparMsg();
				changescreen();
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		
		moreButton = new Image(AssetLoader.button_change_more);
		moreButton.setPosition(940, 230);
		
		mStage = new Stage(1280, 720, false);
		
		mStage.addActor(bg);
		mStage.addActor(title);
		mStage.addActor(timeButton);
		mStage.addActor(foreverButton);
		mStage.addActor(moreButton);
		Gdx.input.setInputProcessor(mStage);
	}
	protected void changescreen() {
		System.out.println(GameData.PREPAR_R);
		if(GameData.PREPAR_R)
			mgame.setScreen(mgame.mGameScreen);
		else
			mgame.setScreen(mgame.mPreparScreen);
		
	}
	private void sendTypeMsg(int value){
		if(MainActivity.mRemoteUser.size() == 0)
			return;
		GameUserInfo remoteUser = MainActivity.mRemoteUser.get(0);
		GameTypeMessage message = new GameTypeMessage(MainActivity.mLocalUser.id, remoteUser.id, value);
		GameMessage msg = message.toGameMessage();
		MainActivity.mGameShare.sendMessage(msg);
	}
	public void prepar() {
		GameData.PREPAR_R = true;
	}
	
	private void sendPreparMsg(){
		if(MainActivity.mRemoteUser.size() == 0)
			return;
		GameUserInfo remoteUser = MainActivity.mRemoteUser.get(0);
		PreparGameMessage message = new PreparGameMessage(MainActivity.mLocalUser.id, remoteUser.id);
		GameMessage msg = message.toGameMessage();
		MainActivity.mGameShare.sendMessage(msg);
	}
}

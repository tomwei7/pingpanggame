package com.tomwei7.pingpang;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.lenovo.game.GameMessage;
import com.lenovo.game.GameUserInfo;
import com.tomwei7.pingpang.GameData.GameState;
import com.tomwei7.pingpang.GameData.GameType;
import com.tomwei7.pingpang.GameMessages.PreparGameMessage;

public class ReadySreen implements Screen{
	private AirHockey mgame;
	private Image mlogo;
	private Image mbg;
	
	private ImageButton mstart, mquit, mhelp, btn_ok, btn_no;
	
	private BitmapFont font;
	
	private Stage mstage;
	
	private Window windows;
	
	public ReadySreen(AirHockey game) {
		this.mgame = game;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		mstage.act();
		mstage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		GameData.STATE = GameData.GameState.ready;
		
		mstage = new Stage(1280, 720, false);
		
		mlogo = new Image(AssetLoader.logo);
		mlogo.setPosition(150, 300);
		
		mbg = new Image(AssetLoader.rbg);
		mbg.setPosition(0, 0);
		
		mstart = new ImageButton(new TextureRegionDrawable(AssetLoader.button_begin_start));
		mquit = new ImageButton(new TextureRegionDrawable(AssetLoader.button_begin_quit));
		mhelp = new ImageButton(new TextureRegionDrawable(AssetLoader.button_begin_help));
		
		btn_ok = new ImageButton(new TextureRegionDrawable(AssetLoader.windowok));
		btn_no = new ImageButton(new TextureRegionDrawable(AssetLoader.windowno));
		
		font = new BitmapFont(Gdx.files.internal("data/font.fnt"), Gdx.files.internal("data/font.png"), false);
		
		mstart.setPosition(35, 120);
		mquit.setPosition(841, 120);
		mhelp.setPosition(438, 120);
		
		mstart.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				GameData.PREPAR_L = true;
				if(GameData.INVITER)
					mgame.setScreen(mgame.mChangeScreen);
				else{
					sendPreparMsg();
					if(GameData.PREPAR_R == false && GameData.TYPE == GameType.no)
						mgame.setScreen(mgame.mPreparScreen);
					else{
						mgame.setScreen(mgame.mGameScreen);
					}
				}
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		
		mquit.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				mstage.addActor(windows);
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		
		mhelp.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if(GameData.STATE == GameState.ready)
					mgame.setScreen(mgame.mHelpScreen);
				System.out.println("点击帮助");
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		btn_ok.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				Gdx.app.exit();
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		
		btn_no.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				windows.remove();
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		
		setWindow();
		mstage.addActor(mbg);
		mstage.addActor(mlogo);
		mstage.addActor(mstart);
		mstage.addActor(mquit);
		mstage.addActor(mhelp);
		
		Gdx.input.setInputProcessor(mstage);
	}
	
	
	@Override
	public void hide() {
		mstart.remove();
		mquit.remove();
		mhelp.remove();
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
		System.out.println("ready dispose");
	}

	public void prepar() {
		GameData.PREPAR_R = true;
		System.out.println(GameData.PREPAR_R);
	}
	
	private void sendPreparMsg(){
		if(MainActivity.mRemoteUser.size() == 0)
			return;
		GameUserInfo remoteUser = MainActivity.mRemoteUser.get(0);
		PreparGameMessage message = new PreparGameMessage(MainActivity.mLocalUser.id, remoteUser.id);
		GameMessage msg = message.toGameMessage();
		MainActivity.mGameShare.sendMessage(msg);
	}
	public void setWindow(){
		WindowStyle style = new WindowStyle(font, Color.WHITE, new TextureRegionDrawable(AssetLoader.windowbg));
		windows = new Window("", style);
		windows.setPosition(400, 330);
		windows.setWidth(502);
		windows.setHeight(230);
		windows.setMovable(false);
		btn_ok.setPosition(0, 0);
		btn_no.setPosition(251, 0);
		windows.addActor(btn_ok);
		windows.addActor(btn_no);
	}
}

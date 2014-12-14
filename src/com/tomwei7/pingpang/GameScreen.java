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
import com.tomwei7.pingpang.GameMessages.GameDataMessage;
import com.tomwei7.pingpang.GameMessages.LoseGameMessage;
import com.tomwei7.pingpang.GameMessages.PauesGameMessage;

public class GameScreen implements Screen{
	private AirHockey mgame;
	
	private Stage mstage;
	
	private Image mbg;
	
	private Ball mball;
	private Bat mbat;
	
	private ImageButton pauseButton;
	private ImageButton mVioceButton;
	
	private VioceButton mVioceImg;
	
	private Timer timer;
	private Scorer scorer;
	
	public GameScreen(AirHockey game) {
		this.mgame = game;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		if(GameData.STATE == GameState.pause){
			mgame.setScreen(mgame.mPauseScreen);
			return;
		}
		checkwin();
		checkhit();
		mstage.act();
		mstage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		//游戏界面
		GameData.STATE = GameState.gameing;
		GameData.PREPAR_L = false;
		GameData.PREPAR_R = false;
		GameData.GETWIN = false;
		GameData.SCORE_L = 0;
		GameData.SCORE_R = 0;
		
		mstage = new Stage(1280,720,false);
		
		mbg = new Image(AssetLoader.gbg);
		mbg.setPosition(0, 0);
		
		pauseButton = new ImageButton(new TextureRegionDrawable(AssetLoader.button_stop));
		pauseButton.setPosition(1130, 570);
		pauseButton.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				GameData.STATE = GameState.pause;
				sendpause();
				System.out.println("游戏暂停");
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		
		mVioceImg = new VioceButton();
		mVioceButton = new ImageButton(new TextureRegionDrawable(AssetLoader.button_tem));
		mVioceButton.setPosition(1000, 570);
		mVioceButton.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				GameData.VIOCE = !GameData.VIOCE;
				System.out.println("改变声音");
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		
		mball = new Ball(600, 320 ,this);
		mbat = new Bat();
		
		//计时器
		
		timer = new Timer(80, 530, 60);
		//积分器
		scorer = new Scorer(80, 620);
		
		mstage.addActor(mbg);
		mstage.addActor(mball);
		mstage.addActor(mbat);
		if(GameData.TYPE == GameType.time)
			mstage.addActor(timer);
		mstage.addActor(scorer);
		mstage.addActor(mVioceImg);
		mstage.addActor(mVioceButton);
		mstage.addActor(pauseButton);
		
		System.out.println("游戏界面初始化完成！");
		//暂停界面
		Gdx.input.setInputProcessor(mstage);
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
	
	private void checkwin(){
		switch (GameData.TYPE) {
		case time:
			if(timer.isEnd()){
				if(GameData.SCORE_L>GameData.SCORE_R)
					GameData.WIN = true;
				else if(GameData.SCORE_L<GameData.SCORE_R)
					GameData.WIN = false;
				mgame.setScreen(mgame.mOverScreen);
			}
			break;

		default:
			break;
		}
	}
	
	public void send(int x, int vx, int vy){
		if(MainActivity.mRemoteUser.size() == 0)
			return;
		GameUserInfo remoteUser = MainActivity.mRemoteUser.get(0);
		GameDataMessage dmseeage = new GameDataMessage(MainActivity.mLocalUser.id, remoteUser.id, x, vx, vy);
		GameMessage msg = dmseeage.toGameMessage();
		MainActivity.mGameShare.sendMessage(msg);
	}

	

	public void setball(float x, float vx, float vy) {
		mball.receive(x, vx, vy);
	}

	public void gamepause() {
		GameData.STATE = GameState.pause;
	}
	
	private void checkhit() {
		if(mball.getRealX()>mbat.getRealX()-192&&mball.getRealX()<mbat.getRealX()+192){
			if(mball.getRealY()>mbat.getRealY()+15&&mball.getRealY()<mbat.getRealY()+65){
				if(mball.getVy()<0){
					mball.setVy(-mball.getVy());
					mball.setVx(mball.getVx()+mbat.getVx());
					mball.setY(mbat.getRealY()+25);
					if(GameData.VIOCE)
						AssetLoader.hit.play();
//					System.out.println("上");
				}
			}
			if(mball.getRealY()<mbat.getRealY()-15&&mball.getRealY()>mbat.getRealY()-65){
				if(mball.getVy()>0){
					mball.setVy(-mball.getVy());
					mball.setVx(mball.getVx()+mbat.getVx());
					mball.setY(mbat.getRealY()-25);
					if(GameData.VIOCE)
						AssetLoader.hit.play();
//					System.out.println("下");
				}
			}
		}
		else if(mball.getRealY()>mbat.getRealY()-45&&mball.getRealY()<mbat.getRealY()+45){
			if(mball.getRealX()>mbat.getRealX()-172&&mball.getRealX()<mbat.getRealX()-122){
				if(mball.getVx()>0){
					mball.setVx(-mball.getVx()+mbat.getVx());
					mball.setX(mbat.getRealX()-172);
					if(GameData.VIOCE)
						AssetLoader.hit.play();
//					System.out.println("左");
				}
				if(mball.getRealX()<mbat.getRealX()+172&&mball.getRealX()>mbat.getRealX()+122){
					if(mball.getVx()<0){
						mball.setVx(-mball.getVx()+mbat.getVx());
						mball.setX(mbat.getRealX()+172);
						if(GameData.VIOCE)
							AssetLoader.hit.play();
//						System.out.println("右");
					}
				}
			}
		}
	}

	public void lose() {
		GameData.SCORE_R++;
		sendlose();
		mball.setVy(-mball.getVy());
	}
	public void sendpause(){
		if(MainActivity.mRemoteUser.size() == 0)
			return;
		GameUserInfo remoteUser = MainActivity.mRemoteUser.get(0);
		PauesGameMessage dmseeage = new PauesGameMessage(MainActivity.mLocalUser.id, remoteUser.id);
		GameMessage msg = dmseeage.toGameMessage();
		MainActivity.mGameShare.sendMessage(msg);
	}
	
	public void sendlose(){
		if(MainActivity.mRemoteUser.size() == 0)
			return;
		GameUserInfo remoteUser = MainActivity.mRemoteUser.get(0);
		LoseGameMessage dmseeage = new LoseGameMessage(MainActivity.mLocalUser.id, remoteUser.id);
		GameMessage msg = dmseeage.toGameMessage();
		MainActivity.mGameShare.sendMessage(msg);
	}

	public void herlose() {
		GameData.SCORE_L++;
	}
}

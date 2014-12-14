package com.tomwei7.pingpang;

import com.badlogic.gdx.Game;
import com.lenovo.game.GameMessage;
import com.lenovo.game.GameMessageListener;
import com.tomwei7.pingpang.GameData.GameType;
import com.tomwei7.pingpang.GameMessages.AbstractGameMessage;
import com.tomwei7.pingpang.GameMessages.GameDataMessage;
import com.tomwei7.pingpang.GameMessages.GameTypeMessage;

import android.util.Log;

public class AirHockey extends Game {
	
	private static final String TAG = "GameActivity";
	
	public ReadySreen mReadyScreen;
	public HelpScreen mHelpScreen;
	public ChangeScreen mChangeScreen;
	public PreparScreen mPreparScreen;
	public GameScreen mGameScreen;
	public OverScreen mOverScreen;
	public PauseScreen mPauseScreen;
	
	@Override
	public void create() {
		MainActivity.mGameShare.addMessageListener(mMessageListener);
		
		AssetLoader.load();
		
		mReadyScreen = new ReadySreen(this);
		mHelpScreen = new HelpScreen(this);
		mChangeScreen = new ChangeScreen(this);
		mPreparScreen = new PreparScreen(this);
		mGameScreen = new GameScreen(this);
		mOverScreen = new OverScreen(this);
		mPauseScreen = new PauseScreen(this);
		setScreen(mReadyScreen);
	}
	@Override
	public void dispose() {
		MainActivity.mGameShare.removeMessageListener(mMessageListener);
		super.dispose();
		AssetLoader.dispose();
	}
	
	private GameMessageListener mMessageListener = new GameMessageListener() {
		
		@Override
		public void onMessage(GameMessage gameMessage) {
			Log.v(TAG, "onMessage, message : " + gameMessage.toString());
			try {
				AbstractGameMessage message = GameMessages.creatGameMessage(gameMessage.getType(), gameMessage.getMessage());
				if(message.getType() == GameMessages.MSG_TYPE_PREPARGAME){
					if(GameData.STATE == GameData.GameState.ready){
						mReadyScreen.prepar();
						Log.v(TAG,"gameprepar");
					}
					else if(GameData.STATE == GameData.GameState.prepar){
						System.out.println("prepar下获得开始信号");
						mPreparScreen.start();
						Log.v(TAG,"gamestart");
					}
					else if(GameData.STATE == GameData.GameState.overgame){
						System.out.println("over下获得开始信号");
						mOverScreen.prepar();
						Log.v(TAG,"gamestart");
					}
					else if(GameData.STATE == GameData.GameState.change){
						System.out.println("change下获得开始信号");
						mChangeScreen.prepar();
						Log.v(TAG,"gamestart");
					}
					
				}
				if(message.getType()== GameMessages.MSG_TYPE_GAMETYPE){
					GameTypeMessage msg = (GameTypeMessage)message;
					int type  = msg.getmType();
					switch (type) {
					case 1:
						GameData.TYPE = GameType.time;
						Log.v(TAG,"gamechange1");
						break;
					case 2:
						GameData.TYPE = GameType.forever;
						Log.v(TAG,"gamechange2");
						break;
					default:
						break;
					}
				}
				if(message.getType() == GameMessages.MSG_TYPE_STARTGAME){
					if(GameData.STATE == GameData.GameState.pause){
						mPauseScreen.start();
						Log.v(TAG,"gamerestart");
					}
				}
				if(message.getType() == GameMessages.MSG_TYPE_GAMEDATA){
					if(GameData.STATE == GameData.GameState.gameing){
						GameDataMessage msg = (GameDataMessage)message;
						mGameScreen.setball((float)msg.getmX(),(float)msg.getmVx(),(float)msg.getmVy());
						Log.v(TAG,"getgamedata");
					}
				}
				if(message.getType() == GameMessages.MSG_TYPE_PAUSEGAME){
					if(GameData.STATE == GameData.GameState.gameing){
						mGameScreen.gamepause();
						Log.v(TAG,"gamepause");
					}
				}
				if(message.getType() == GameMessages.MSG_TYPE_LOSEGAME){
					if(GameData.STATE == GameData.GameState.gameing){
						mGameScreen.herlose();
						Log.v(TAG,"gamelose");
					}
				}
			} catch (Exception e) {
				return;
			}
		}
	};
	
}

package com.tomwei7.pingpang;

import java.util.List;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.lenovo.game.GameUserInfo;
import com.lenovo.gamesdk.GameShare;
import com.lenovo.gamesdk.GameShare.Bindlistener;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AndroidApplication{
	
	private static final String TAG = "GameActivity";
	
	public static GameShare mGameShare;
	public static GameUserInfo mLocalUser;
	public static List<GameUserInfo> mRemoteUser;
	
	public static AirHockey mGame;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = true;
        
        //获得游戏状态，true = 邀请者 false = 被邀请者
        Intent intent = getIntent();
        if (intent.hasExtra("inviter")) {
            Bundle bundle = intent.getExtras();
            GameData.INVITER = bundle.getBoolean("inviter");
        }
        
        mGameShare = new GameShare(getApplicationContext());
        mGameShare.bind(mBindlistener);
        
        mGame = new AirHockey();
        
        initialize(mGame, cfg);
    }
	
	private Bindlistener mBindlistener = new Bindlistener() {
		
		@Override
		public void onBind(boolean success) {
			Log.v(TAG, "onBind, is bind success : " + success);
			mLocalUser = mGameShare.getLocalUser();
            mRemoteUser = mGameShare.getRemoteUsers();
		}
	};
	
	protected void onDestroy() {
		mGameShare.quitGame();
	};
}

package com.tomwei7.pingpang;

import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

	public static Texture ball, bat, button_begin, button_change, button_vioce, logo, prepar,pauseBg,pauseButton,
			bg, gbg ,youwin, youlose ,osbutton, helpscreen,title,window,rbg;
	public static TextureRegion button_stop, button_tem, button_change_time, button_change_forever, button_change_more;
	public static TextureRegion button_begin_start, button_begin_quit,windowbg,windowok,windowno,
			button_begin_help, button_vioce_start, button_vioce_stop,
			pausebutton, osquit, osrestart,pauseButton_go,pauseButton_quit;
	public static Sound hit;

	// public static Music background_music;

	public static void load() {
		rbg = new Texture(Gdx.files.internal("data/rbg.png"));
		//window
		window = new Texture(Gdx.files.internal("data/window.png"));
		windowbg =new TextureRegion(window, 0, 0, 502, 206);
		windowok =new TextureRegion(window, 0, 206, 251, 91);
		windowno =new TextureRegion(window, 251, 206, 251, 91);
		
		
		ball = new Texture(Gdx.files.internal("data/ball.png"));
		bat = new Texture(Gdx.files.internal("data/bat.png"));
		
		//开始按钮素材
		button_begin = new Texture(Gdx.files.internal("data/button_begin.png"));
		//游戏模式
		button_change = new Texture(Gdx.files.internal("data/button_change.png"));
		title = new Texture(Gdx.files.internal("data/title.png"));
		button_change_time = new TextureRegion(button_change, 0, 0, 243, 245);
		button_change_forever = new TextureRegion(button_change, 0, 245, 243, 245);
		button_change_more = new TextureRegion(button_change, 0, 490, 243, 245);
		
		//声音按钮
		button_vioce = new Texture(Gdx.files.internal("data/button_vioce.png"));
		
		//logo素材
		logo = new Texture(Gdx.files.internal("data/logo.png"));
		prepar = new Texture(Gdx.files.internal("data/prepar.png"));
		
		//主背景
		bg = new Texture(Gdx.files.internal("data/background.png"));
		gbg = new Texture(Gdx.files.internal("data/gbg.png"));
		pausebutton = new TextureRegion(new Texture(
				Gdx.files.internal("data/pausebutton.png")));
		youwin = new Texture(Gdx.files.internal("data/youwin.png"));
		youlose = new Texture(Gdx.files.internal("data/youlose.png"));
		osbutton = new Texture(Gdx.files.internal("data/overScreenButton.png"));
		helpscreen = new Texture(Gdx.files.internal("data/helpimg.png"));
		
		osrestart = new TextureRegion(osbutton, 0, 0, 372, 111);
		osquit = new TextureRegion(osbutton, 372, 0, 372, 111);
		
		button_stop = new TextureRegion(new Texture(
				Gdx.files.internal("data/button_stop.png")));
		button_tem = new TextureRegion(new Texture(
				Gdx.files.internal("data/tembutton.png")));
		//开始按钮
		button_begin_start = new TextureRegion(button_begin, 0, 0, 403, 93);
		button_begin_quit = new TextureRegion(button_begin, 0, 186, 403, 93);
		button_begin_help = new TextureRegion(button_begin, 0, 93, 403, 93);

		button_vioce_start = new TextureRegion(button_vioce, 0, 0, 119, 116);
		button_vioce_stop = new TextureRegion(button_vioce, 0, 116, 119, 116);
		//暂停界面素材
		pauseBg = new Texture(Gdx.files.internal("data/pausebg.png"));
		pauseButton = new Texture(Gdx.files.internal("data/pausebutton.png"));

		pauseButton_go = new TextureRegion(pauseButton, 0, 0, 266, 126);
		pauseButton_quit = new TextureRegion(pauseButton, 0, 126, 266, 126);
		//声音
		hit = Gdx.audio.newSound(Gdx.files.internal("sound/hit.ogg"));
	}

	public static void dispose() {
		ball.dispose();
		bat.dispose();
		button_begin.dispose();
		button_vioce.dispose();
		logo.dispose();
		prepar.dispose();
		hit.dispose();
	}
}

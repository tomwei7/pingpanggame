package com.tomwei7.pingpang;

public class GameData {
	public static float FKC = 0.98f;
	public static float FK = 1f; //阻力
	public static float TK = 0f; //传递系数
	public static float MV = 1500f; //最大速度
	public static boolean INVITER = false; //是否是房主
	public static boolean PREPAR_L = false;
	public static boolean PREPAR_R = false;
	public static boolean VIOCE = true;
	
	public static boolean WIN = false;
	public static boolean GETWIN = false;
	
	//游戏状态
	enum GameState{
		nothing,ready,change,prepar,gameing,pause,overgame;
	}
	public static GameState STATE = GameState.nothing;
	
	//游戏模式
	enum GameType{
		no,time,forever;
	}
	public static GameType TYPE = GameType.no;
	
	//游戏比分
	public static int SCORE_L = 0;
	public static int SCORE_R = 0;
}

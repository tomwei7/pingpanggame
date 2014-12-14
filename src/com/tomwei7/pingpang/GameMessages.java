package com.tomwei7.pingpang;

import org.json.JSONException;
import org.json.JSONObject;

import com.lenovo.game.GameMessage;

public class GameMessages {
	
	public static final String MSG_TYPE_GAMETYPE = "prepargame";
	public static final String MSG_TYPE_PREPARGAME = "gametype";
	public static final String MSG_TYPE_STARTGAME = "startgame";
	public static final String MSG_TYPE_PAUSEGAME = "pausegame";
	public static final String MSG_TYPE_STOPGAME = "stopgame";
	public static final String MSG_TYPE_LOSEGAME = "losegame";
	public static final String MSG_TYPE_GAMEDATA = "gamedata";
	
	public static abstract class AbstractGameMessage {
        AbstractGameMessage(String type) {
            mType = type;
        }

        public void setFrom(String from) {
            mFrom = from;
        }

        public void setTo(String to) {
            mTo = to;
        }

        public String getFrom() {
            return mFrom;
        }

        public String getTo() {
            return mTo;
        }

        public String getType() {
            return mType;
        }

        public GameMessage toGameMessage() {
            try {
                GameMessage gameMsg = new GameMessage(mType, mFrom, mTo);
                gameMsg.setMessage(toJSON().toString());
                return gameMsg;
            } catch (JSONException e) {}
            return null;
        }

        abstract public JSONObject toJSON() throws JSONException;

        abstract public void fromJSON(JSONObject json) throws JSONException;

        private String mType;
        private String mFrom;
        private String mTo;
    }
	
	public static class PreparGameMessage extends AbstractGameMessage{

		public PreparGameMessage() {
			super(MSG_TYPE_PREPARGAME);
		}
		
		public PreparGameMessage(String from, String to){
			super(MSG_TYPE_PREPARGAME);
			setFrom(from);
			setTo(to);
		}

		@Override
		public JSONObject toJSON() throws JSONException {
			JSONObject json = new JSONObject();
            json.put("type", getType());
            return json;
		}

		@Override
		public void fromJSON(JSONObject json) throws JSONException {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public static class StartGameMessage extends AbstractGameMessage{

		public StartGameMessage() {
			super(MSG_TYPE_STARTGAME);
		}
		
		public StartGameMessage(String from, String to){
			super(MSG_TYPE_STARTGAME);
			setFrom(from);
			setTo(to);
		}

		@Override
		public JSONObject toJSON() throws JSONException {
			JSONObject json = new JSONObject();
            json.put("type", getType());
            return json;
		}

		@Override
		public void fromJSON(JSONObject json) throws JSONException {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public static class PauesGameMessage extends AbstractGameMessage{

		public PauesGameMessage() {
			super(MSG_TYPE_PAUSEGAME);
		}
		
		public PauesGameMessage(String from, String to){
			super(MSG_TYPE_PAUSEGAME);
			setFrom(from);
			setTo(to);
		}

		@Override
		public JSONObject toJSON() throws JSONException {
			JSONObject json = new JSONObject();
            json.put("type", getType());
            return json;
		}

		@Override
		public void fromJSON(JSONObject json) throws JSONException {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public static class StopGameMessage extends AbstractGameMessage{

		public StopGameMessage() {
			super(MSG_TYPE_STOPGAME);
		}
		
		public StopGameMessage(String from, String to){
			super(MSG_TYPE_STOPGAME);
			setFrom(from);
			setTo(to);
		}

		@Override
		public JSONObject toJSON() throws JSONException {
			JSONObject json = new JSONObject();
            json.put("type", getType());
            return json;
		}

		@Override
		public void fromJSON(JSONObject json) throws JSONException {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public static class LoseGameMessage extends AbstractGameMessage{

		public LoseGameMessage() {
			super(MSG_TYPE_LOSEGAME);
		}
		
		public LoseGameMessage(String from, String to){
			super(MSG_TYPE_LOSEGAME);
			setFrom(from);
			setTo(to);
		}

		@Override
		public JSONObject toJSON() throws JSONException {
			JSONObject json = new JSONObject();
            json.put("type", getType());
            return json;
		}

		@Override
		public void fromJSON(JSONObject json) throws JSONException {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public static class GameTypeMessage extends AbstractGameMessage{
		
		int mType;
		
		public GameTypeMessage() {
			super(MSG_TYPE_GAMETYPE);
		}
		public GameTypeMessage(String from, String to, int type) {
			super(MSG_TYPE_GAMETYPE);
			setFrom(from);
			setTo(to);
			mType = type;
		}
		
		@Override
		public JSONObject toJSON() throws JSONException {
			JSONObject json = new JSONObject();
			json.put("type", getType());
			json.put("gametype", mType);
			return json;
		}
		@Override
		public void fromJSON(JSONObject json) throws JSONException {
			mType = json.getInt("gametype");
		}
		public int getmType() {
			return mType;
		}
	}
	
	public static class GameDataMessage extends AbstractGameMessage{
		int mX;
		int mVx;
		int mVy;
		
		public GameDataMessage(){
			super(MSG_TYPE_GAMEDATA);
		}
		
		public GameDataMessage(String from, String to, int x, int vx, int vy){
			super(MSG_TYPE_GAMEDATA);
			setFrom(from);
			setTo(to);
			mX = x;
			mVx = vx;
			mVy = vy;
		}
		
		@Override
		public JSONObject toJSON() throws JSONException {
			JSONObject json = new JSONObject();
            json.put("type", getType());
            json.put("x", mX);
            json.put("vx", mVx);
            json.put("vy", mVy);
            return json;
		}

		@Override
		public void fromJSON(JSONObject json) throws JSONException {
			mX = json.getInt("x");
			mVx = json.getInt("vx");
			mVy  = json.getInt("vy");
		}

		public int getmX() {
			return mX;
		}

		public int getmVx() {
			return mVx;
		}

		public int getmVy() {
			return mVy;
		}
	}
	
	public static AbstractGameMessage creatGameMessage (String type, String message) throws JSONException{
		AbstractGameMessage gameMessage = null;
        JSONObject json = new JSONObject(message);
        if(type.equalsIgnoreCase(MSG_TYPE_PREPARGAME))
        	gameMessage = new PreparGameMessage();
        if(type.equalsIgnoreCase(MSG_TYPE_STARTGAME))
        	gameMessage = new StartGameMessage();
        if(type.equalsIgnoreCase(MSG_TYPE_PAUSEGAME))
        	gameMessage = new PauesGameMessage();
        if(type.equalsIgnoreCase(MSG_TYPE_STOPGAME))
        	gameMessage = new StopGameMessage();
        if(type.equalsIgnoreCase(MSG_TYPE_LOSEGAME))
        	gameMessage = new LoseGameMessage();
        if(type.equalsIgnoreCase(MSG_TYPE_GAMEDATA))
        	gameMessage = new GameDataMessage();
        if(type.equalsIgnoreCase(MSG_TYPE_GAMETYPE))
        	gameMessage = new GameTypeMessage();
        
        gameMessage.fromJSON(json);
        return gameMessage;
	}
	
}

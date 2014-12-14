package com.tomwei7.pingpang;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class VioceButton extends Actor{
	TextureRegion vioceButtonon;
	TextureRegion vioceButtonoff;
	
	public VioceButton() {
		vioceButtonon = AssetLoader.button_vioce_start;
		vioceButtonoff = AssetLoader.button_vioce_stop;
	}
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		if(GameData.VIOCE)
			batch.draw(vioceButtonoff, 1000, 570);
		else
			batch.draw(vioceButtonon, 1000, 570);
		super.draw(batch, parentAlpha);
	}
}

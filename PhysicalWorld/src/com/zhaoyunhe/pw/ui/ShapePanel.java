package com.zhaoyunhe.pw.ui;

import info.u250.c2d.engine.Engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ShapePanel extends Group {

	private Image boxImage;
	private Image circleImage;
	private Image jointImage;

	private TextureAtlas atlas;

	public ShapePanel() {
		atlas = Engine.resource("atlas");

		boxImage = new Image(atlas.findRegion("button_box"));
		circleImage = new Image(atlas.findRegion("button_circle"));
		jointImage = new Image(atlas.findRegion("button_joint"));

		int offset = 60;
		jointImage.setPosition(0, 0);
		circleImage.setPosition(0, offset);
		boxImage.setPosition(0, offset * 2);

		setButtonListener();
		this.addActor(boxImage);
		this.addActor(circleImage);
		this.addActor(jointImage);
	}

	private void setButtonListener() {
		boxImage.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("debug", "boxImage clicked");
			}

		});

		circleImage.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("debug", "circleImage clicked");
			}

		});

		jointImage.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("debug", "jointImage clicked");
			}

		});
	}
}

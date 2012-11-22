package com.zhaoyunhe.pw.ui;

import info.u250.c2d.engine.Engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class JointPanel extends Table {

	private Button joint1;
	private Button joint2;
	private Button joint3;
	private TextureAtlas atlas;

	public JointPanel() {
		atlas = Engine.resource("atlas");

		this.setBackground(new TextureRegionDrawable(atlas.findRegion("joint-panel")));

		joint1 = new Button(new TextureRegionDrawable(atlas.findRegion("joint-1")));
		joint2 = new Button(new TextureRegionDrawable(atlas.findRegion("joint-1")));
		joint3 = new Button(new TextureRegionDrawable(atlas.findRegion("joint-1")));
		setButtonListener();
		this.add(joint1).spaceRight(20);
		this.add(joint2);
		this.row();
		this.add(joint3).spaceRight(20);
		this.add();
		this.pack();
	}
	
	private void setButtonListener() {
		joint1.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(joint1.isChecked()){
					Gdx.app.log("debug", "checked");
				}else{
					Gdx.app.log("debug", "not checked");
				}

			}
		});

		joint2.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				// TODO Auto-generated method stub

			}
		});

		joint3.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				// TODO Auto-generated method stub

			}
		});
	}
}

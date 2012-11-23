package com.zhaoyunhe.pw.ui;

import info.u250.c2d.engine.Engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.zhaoyunhe.pw.props.Box2dAdapter;

public class JointPanel extends Table {

	private Button mButtonDistanceJoint;
	private Button mButtonFrictionJoint;
	private Button mButtonPrismaticJoint;
	private Button mButtonPulleyJoint;
	private Button mButtonRevoluteJoint;
	private Button mButtonRopeJoint;
	private Button mButtonWeldJoint;
	private Button mButtonWheelJoint;

	private TextureAtlas atlas;
	private Box2dAdapter adapter;

	public JointPanel(Box2dAdapter adapter) {
		atlas = Engine.resource("atlas");
		this.adapter = adapter;

		this.setBackground(new TextureRegionDrawable(atlas.findRegion("joint-panel")));
		Drawable up = new TextureRegionDrawable(atlas.findRegion("joint-1"));
		Drawable down = new TextureRegionDrawable(atlas.findRegion("joint-1-down"));
		mButtonDistanceJoint = new Button(up, down, down);
		mButtonFrictionJoint = new Button(up, down, down);
		mButtonPrismaticJoint = new Button(up, down, down);
		mButtonPulleyJoint = new Button(up, down, down);
		mButtonRevoluteJoint = new Button(up, down, down);
		mButtonRopeJoint = new Button(up, down, down);
		mButtonWeldJoint = new Button(up, down, down);
		mButtonWheelJoint = new Button(up, down, down);

		setButtonListener();
		this.row().spaceBottom(20);
		this.add(mButtonDistanceJoint);
		this.row().spaceBottom(20);
		this.add(mButtonFrictionJoint);
		this.row().spaceBottom(20);
		this.add(mButtonPrismaticJoint);
		this.row().spaceBottom(20);
		this.add(mButtonPulleyJoint);
		this.row().spaceBottom(20);
		this.add(mButtonRevoluteJoint);
		this.row().spaceBottom(20);
		this.add(mButtonRopeJoint);
		this.row().spaceBottom(20);
		this.add(mButtonWeldJoint);
		this.row().spaceBottom(20);
		this.add(mButtonWheelJoint);
		this.pack();
	}

	private void setButtonListener() {
		mButtonDistanceJoint.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (mButtonDistanceJoint.isChecked()) {
					unCheckButton(mButtonDistanceJoint);
					adapter.activeDistanceJointHelper();
					Gdx.app.log("debug", "checked");
				} else {
					adapter.activeJointSelectHelper();
					Gdx.app.log("debug", "not checked");
				}

			}
		});

		mButtonFrictionJoint.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (mButtonFrictionJoint.isChecked()) {
					unCheckButton(mButtonFrictionJoint);
					adapter.activeFrictionJointHelper();
					Gdx.app.log("debug", "checked");
				} else {
					adapter.activeJointSelectHelper();
					Gdx.app.log("debug", "not checked");
				}
			}
		});

		mButtonPrismaticJoint.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (mButtonPrismaticJoint.isChecked()) {
					unCheckButton(mButtonPrismaticJoint);
					adapter.activePrismaticJointHelper();
					Gdx.app.log("debug", "checked");
				} else {
					adapter.activeJointSelectHelper();
					Gdx.app.log("debug", "not checked");
				}
			}
		});

		mButtonPulleyJoint.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (mButtonPulleyJoint.isChecked()) {
					unCheckButton(mButtonPulleyJoint);
					adapter.activePulleyJointHelper();
					Gdx.app.log("debug", "checked");
				} else {
					adapter.activeJointSelectHelper();
					Gdx.app.log("debug", "not checked");
				}

			}
		});

		mButtonRevoluteJoint.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (mButtonRevoluteJoint.isChecked()) {
					unCheckButton(mButtonRevoluteJoint);
					adapter.activeRevoluteJointHelper();
					Gdx.app.log("debug", "checked");
				} else {
					adapter.activeJointSelectHelper();
					Gdx.app.log("debug", "not checked");
				}

			}
		});

		mButtonRopeJoint.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (mButtonRopeJoint.isChecked()) {
					unCheckButton(mButtonRopeJoint);
					adapter.activeRopeJointHelper();
					Gdx.app.log("debug", "checked");
				} else {
					adapter.activeJointSelectHelper();
					Gdx.app.log("debug", "not checked");
				}

			}
		});

		mButtonWeldJoint.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (mButtonWeldJoint.isChecked()) {
					unCheckButton(mButtonWeldJoint);
					adapter.activeWeldJointHelper();
					Gdx.app.log("debug", "checked");
				} else {
					adapter.activeJointSelectHelper();
					Gdx.app.log("debug", "not checked");
				}

			}
		});

		mButtonWheelJoint.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (mButtonWheelJoint.isChecked()) {
					unCheckButton(mButtonWheelJoint);
					adapter.activeWheelJointHelper();
					Gdx.app.log("debug", "checked");
				} else {
					adapter.activeJointSelectHelper();
					Gdx.app.log("debug", "not checked");
				}

			}
		});

	}

	private void unCheckButton(Button currentButton) {
		for (Actor actor : this.getChildren()) {
			if (actor instanceof Button) {
				if (((Button) actor).isChecked()
						&& ((Button) actor) != currentButton) {
					((Button) actor).setChecked(false);
				}
			}
		}
	}
}

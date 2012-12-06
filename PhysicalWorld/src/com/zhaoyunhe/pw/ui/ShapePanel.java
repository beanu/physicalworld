package com.zhaoyunhe.pw.ui;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.input.PhysicalFingerInput;
import info.u250.c2d.physical.box2d.Cb2World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.zhaoyunhe.pw.Events;
import com.zhaoyunhe.pw.props.Box2dAdapter;

public class ShapePanel extends Group {

	private Button mButtonBox;
	private Button mButtonCircle;
	private Button mButtonPolygon;
	private Button mButtonJoint;

	private Image tools_run;// TODO delete

	private TextureAtlas atlas;
	private Box2dAdapter adapter;

	public ShapePanel(Box2dAdapter adapter) {
		atlas = Engine.resource("atlas");
		this.adapter = adapter;

		mButtonBox = new Button(new TextureRegionDrawable(
				atlas.findRegion("button_box")));
		mButtonCircle = new Button(new TextureRegionDrawable(
				atlas.findRegion("button_circle")));
		mButtonPolygon = new Button(new TextureRegionDrawable(
				atlas.findRegion("button_polygon")));
		mButtonJoint = new Button(new TextureRegionDrawable(
				atlas.findRegion("button_joint")));

		int offset = 60;
		mButtonJoint.setPosition(0, 0);
		mButtonCircle.setPosition(0, offset);
		mButtonPolygon.setPosition(0, offset * 2);
		mButtonBox.setPosition(0, offset * 3);

		setButtonListener();
		this.addActor(mButtonBox);
		this.addActor(mButtonCircle);
		this.addActor(mButtonPolygon);
		this.addActor(mButtonJoint);

		this.setHeight(offset * 4);
	}

	private void setButtonListener() {

		mButtonBox.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (mButtonBox.isChecked()) {
					unCheckButton(mButtonBox);
					mButtonBox.addAction(Actions.moveBy(20, 0, 0.2f));
					adapter.activeBoxHelper();
				} else {
					mButtonBox.addAction(Actions.moveBy(-20, 0, 0.2f));
					adapter.activeSelectHelper();
				}
			}
		});

		mButtonCircle.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (mButtonCircle.isChecked()) {
					unCheckButton(mButtonCircle);
					mButtonCircle.addAction(Actions.moveBy(20, 0, 0.2f));
					adapter.activeCircleHelper();
				} else {
					mButtonCircle.addAction(Actions.moveBy(-20, 0, 0.2f));
					adapter.activeSelectHelper();
				}

			}
		});

		mButtonPolygon.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (mButtonPolygon.isChecked()) {
					unCheckButton(mButtonPolygon);
					mButtonPolygon.addAction(Actions.moveBy(20, 0, 0.2f));
					adapter.activeCircleHelper();
				} else {
					mButtonPolygon.addAction(Actions.moveBy(-20, 0, 0.2f));
					adapter.activeSelectHelper();
				}

			}
		});

		mButtonJoint.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (mButtonJoint.isChecked()) {
					unCheckButton(mButtonJoint);
					mButtonJoint.addAction(Actions.moveBy(20, 0, 0.2f));
					Engine.getEventManager()
							.fire(Events.MOVE_JOINT_PANEL, true);
				} else {
					mButtonJoint.addAction(Actions.moveBy(-20, 0, 0.2f));
					Engine.getEventManager().fire(Events.MOVE_JOINT_PANEL,
							false);
				}

			}
		});

		// TODO delete
		final TextureRegionDrawable pauseRegion = new TextureRegionDrawable(
				atlas.findRegion("tools-pause"));
		final TextureRegionDrawable playRegion = new TextureRegionDrawable(
				atlas.findRegion("tools-run"));
		tools_run = new Image(atlas.findRegion("tools-pause"));
		tools_run.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (tools_run.getDrawable() == playRegion) {
					Engine.getEventManager().fire(Events.MOVE_PROPERTIES_PANEL,
							false);
					Cb2World.getInstance().dispose();
					Cb2World.getInstance().installDefaultWorld();

					tools_run.setDrawable(pauseRegion);
					adapter.play();

					InputMultiplexer mul = new InputMultiplexer();
					mul.addProcessor(Gdx.input.getInputProcessor());
					mul.addProcessor(new PhysicalFingerInput(Cb2World
							.getInstance().createScreenBox()));
					Gdx.input.setInputProcessor(mul);
				} else {
					Cb2World.getInstance().dispose();

					tools_run.setDrawable(playRegion);
					adapter.stop();

					// if(Gdx.input.getInputProcessor() instanceof
					// InputMultiplexer){
					// InputMultiplexer mul =
					// InputMultiplexer.class.cast(Gdx.input.getInputProcessor());
					// for(InputProcessor input:mul.getProcessors()){
					// if(input instanceof PhysicalFingerInput){
					// mul.removeProcessor(input);
					// }
					// }
					// }
				}
			}
		});

		tools_run.setDrawable(playRegion);
		// UiUtils.centerActor(tools_run);
		tools_run.setX(0);
		tools_run.setY(180);
		this.addActor(tools_run);
	}

	private void unCheckButton(Button btn) {
		for (Actor actor : this.getChildren()) {
			if (actor instanceof Button) {
				if (((Button) actor).isChecked() && ((Button) actor) != btn) {
					((Button) actor).setChecked(false);
				}
			}
		}
	}
}

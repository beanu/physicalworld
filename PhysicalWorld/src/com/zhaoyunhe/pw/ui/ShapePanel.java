package com.zhaoyunhe.pw.ui;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.physical.box2d.Cb2World;
import info.u250.c2d.utils.UiUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.zhaoyunhe.pw.IControl;

public class ShapePanel extends Group {

	private Image boxImage;
	private Image circleImage;
	private Image jointImage;
	
	private Image tools_run;//TODO delete
	private IControl control;
	
	private TextureAtlas atlas;

	public ShapePanel(IControl control) {
		atlas = Engine.resource("atlas");
		this.control=control;

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
		
		final TextureRegionDrawable pauseRegion = new TextureRegionDrawable(atlas.findRegion("tools-pause"));
		final TextureRegionDrawable playRegion = new TextureRegionDrawable(atlas.findRegion("tools-run"));
		tools_run = new Image(atlas.findRegion("tools-pause"));
		tools_run.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(tools_run.getDrawable() == playRegion){
					Cb2World.getInstance().dispose();
					Cb2World.getInstance().installDefaultWorld();
					
					tools_run.setDrawable(pauseRegion);
					control.play();
					
//					InputMultiplexer mul = new InputMultiplexer();
//					mul.addProcessor(Gdx.input.getInputProcessor());
//					mul.addProcessor(new PhysicalFingerInput(Cb2World.getInstance().createScreenBox()));
//					Gdx.input.setInputProcessor(mul);
				}else{
					Cb2World.getInstance().dispose();
					
					tools_run.setDrawable(playRegion);
					control.stop();
					
//					if(Gdx.input.getInputProcessor() instanceof InputMultiplexer){
//						InputMultiplexer mul = InputMultiplexer.class.cast(Gdx.input.getInputProcessor());
//						for(InputProcessor input:mul.getProcessors()){
//							if(input instanceof PhysicalFingerInput){
//								mul.removeProcessor(input);
//							}
//						}
//					}
				}
			}
		});
		
		
		tools_run.setDrawable(playRegion);
		UiUtils.centerActor(tools_run);
		tools_run.setY( Engine.getEngineConfig().height - tools_run.getHeight());
		this.addActor(tools_run);
	}
}

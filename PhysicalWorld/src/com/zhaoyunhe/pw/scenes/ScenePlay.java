package com.zhaoyunhe.pw.scenes;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.physical.box2d.Cb2World;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.zhaoyunhe.pw.IScene;
import com.zhaoyunhe.pw.props.Box2dAdapter;

public class ScenePlay implements IScene {

	Image bgImage;
	Box2dAdapter box2dAdapter;
	UIStage ui;
	InputMultiplexer input;

	public ScenePlay() {
		bgImage = new Image(Engine.resource("atlas", TextureAtlas.class)
				.findRegion("bg"));
		box2dAdapter = new Box2dAdapter();
		ui = new UIStage(this);
		input = new InputMultiplexer();
	}

	@Override
	public void update(float delta) {
		Cb2World.getInstance().update(delta);
		ui.act();
	}

	@Override
	public void render(float delta) {
		Engine.getSpriteBatch().begin();
		bgImage.draw(Engine.getSpriteBatch(), 1);
		Engine.getSpriteBatch().end();

		box2dAdapter.render(delta);
		ui.draw();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public InputProcessor getInputProcessor() {
		input.addProcessor(ui);
		input.addProcessor(box2dAdapter.getInputProcessor());
		return input;
	}

	@Override
	public void play() {
		box2dAdapter.play();
	}

	@Override
	public void stop() {
		box2dAdapter.stop();
	}

	public Box2dAdapter getBox2dAdapter() {
		return box2dAdapter;
	}
}

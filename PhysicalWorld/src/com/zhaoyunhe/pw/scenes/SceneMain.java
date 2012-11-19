package com.zhaoyunhe.pw.scenes;

import info.u250.c2d.engine.Scene;
import info.u250.c2d.physical.box2d.Cb2World;

import com.badlogic.gdx.InputProcessor;
import com.zhaoyunhe.pw.props.Box2dAdapter;

public class SceneMain implements Scene {

	Box2dAdapter box2d=new Box2dAdapter();
	
	@Override
	public void update(float delta) {
		Cb2World.getInstance().update(delta);
	}

	@Override
	public void render(float delta) {
		box2d.render(delta);
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
		return box2d.getInputProcessor();
	}

}

package com.zhaoyunhe.pw.props;

import com.badlogic.gdx.InputProcessor;

public interface Shape {

	public void render(float delta);

	public InputProcessor getInputProcessor();
}

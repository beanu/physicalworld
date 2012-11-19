package com.zhaoyunhe.pw.props;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Array;

public class ShapeGroup extends Array<Shape> implements Shape {

	final private InputMultiplexer input = new InputMultiplexer();

	@Override
	public void render(float delta) {
		for (Shape shape : this) {
			shape.render(delta);
		}
	}

	@Override
	public InputProcessor getInputProcessor() {
		if (this.size > 0) {
			input.clear();
			for (int i = this.size - 1; i >= 0; i--) {
				if (null != this.get(i).getInputProcessor()) {
					input.addProcessor(this.get(i).getInputProcessor());
				}
			}
			return input;
		}
		return null;
	}
}

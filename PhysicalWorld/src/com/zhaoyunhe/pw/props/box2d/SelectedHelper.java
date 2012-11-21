package com.zhaoyunhe.pw.props.box2d;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.physical.box2d.loader.cbt.data.BodyData;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.zhaoyunhe.pw.props.Box2dAdapter;
import com.zhaoyunhe.pw.props.Shape;

public class SelectedHelper implements Shape {
	final ShapeRenderer render;

	Box2dAdapter adapter;
	BodyData data;

	public SelectedHelper(Box2dAdapter adapter) {
		render = Engine.getShapeRenderer();
		this.adapter = adapter;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glEnable(GL10.GL_BLEND);

		Gdx.gl.glDisable(GL10.GL_BLEND);
	}

	Vector2 tmp = new Vector2();

	@Override
	public InputProcessor getInputProcessor() {
		return new InputAdapter() {
			@Override
			public boolean touchDown(int x, int y, int pointer, int button) {
				if (button == Buttons.LEFT) {
					boolean shapeOn = false;
					for (BodyData d : adapter.data.bodyDatas) {
						if (d.isFocus(Engine.screenToWorld(x, y))) {
							data = d;
//							Engine.getEventManager().fire(Events.UPDATE_BOXED_PANEL, data);
							shapeOn = true;
							break;
						}
					}
					if (shapeOn) {
						tmp.set(Engine.screenToWorld(x, y));
						return true;
					} else {
						data = null;
						return false;
					}
				}
				return false;
			}

			@Override
			public boolean touchDragged(int x, int y, int pointer) {
				if (null != data) {
					Vector2 offset = Engine.screenToWorld(x, y).sub(tmp);
					data.translate(offset.x, offset.y);
					tmp.set(Engine.screenToWorld(x, y));
//					Engine.getEventManager().fire(Events.UPDATE_BOXED_PANEL,data);
				}
				return false;
			}

			@Override
			public boolean touchUp(int x, int y, int pointer, int button) {
				data = null;
				return super.touchUp(x, y, pointer, button);
			}
		};

	}

}

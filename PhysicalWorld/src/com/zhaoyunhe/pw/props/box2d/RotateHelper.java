package com.zhaoyunhe.pw.props.box2d;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.physical.box2d.loader.cbt.data.BodyData;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.zhaoyunhe.pw.props.Box2dAdapter;
import com.zhaoyunhe.pw.props.Shape;

public class RotateHelper implements Shape {
	final ShapeRenderer render;
	Box2dAdapter adapter;
	BodyData data;
	private float width = 100;
	private Color colorInner = new Color(0.5f, 1, 0.8f, 0.3f);
	private Color colorOuter = new Color(0, 1, 0, 0.5f);
	private float triangleAngle = 0;

	public RotateHelper(Box2dAdapter adapter) {
		this.render = Engine.getShapeRenderer();
		this.adapter = adapter;
	}

	@Override
	public void render(float delta) {
		if (null != data) {
			Gdx.gl.glEnable(GL10.GL_BLEND);
			render.setColor(colorInner);
			render.begin(ShapeType.FilledTriangle);
			render.filledTriangle(
					data.center.x + width
							* MathUtils.cosDeg(triangleAngle - 30),
					data.center.y + width
							* MathUtils.sinDeg(triangleAngle - 30),
					data.center.x + width
							* MathUtils.cosDeg(triangleAngle + 90),
					data.center.y + width
							* MathUtils.sinDeg(triangleAngle + 90),
					data.center.x + width
							* MathUtils.cosDeg(triangleAngle + 210),
					data.center.y + width
							* MathUtils.sinDeg(triangleAngle + 210));
			render.end();
			render.setColor(colorOuter);
			render.begin(ShapeType.Circle);
			render.circle(data.center.x, data.center.y, width);
			render.end();
			Gdx.gl.glDisable(GL10.GL_BLEND);
		}
	}

	Vector2 tmp = new Vector2();

	@Override
	public InputProcessor getInputProcessor() {
		return new InputAdapter() {
			@Override
			public boolean touchDown(int x, int y, int pointer, int button) {
				if (button == Buttons.LEFT) {
					triangleAngle = 0;
					boolean shapeOn = false;
					for (BodyData d : adapter.data.bodyDatas) {
						if (d.isFocus(Engine.screenToWorld(x, y))) {
							data = d;
							// Engine.getEventManager().fire(Events.UPDATE_BOXED_PANEL,data);
							shapeOn = true;
							break;
						}
					}
					if (shapeOn) {
						tmp.set(Engine.screenToWorld(x, y));
					} else {
						data = null;
					}
				}
				return true;
			}

			@Override
			public boolean touchDragged(int x, int y, int pointer) {
				if (null != data) {
					Vector2 point = Engine.screenToWorld(x, y);
					float angle_append = point.sub(data.center).angle()
							- tmp.sub(data.center).angle();

					data.angle += angle_append;
					triangleAngle += angle_append;
					tmp.set(Engine.screenToWorld(x, y));
					// Engine.getEventManager().fire(Events.UPDATE_BOXED_PANEL,data);
				}
				return true;
			}

			@Override
			public boolean touchUp(int x, int y, int pointer, int button) {
				data = null;
				return super.touchUp(x, y, pointer, button);
			}
		};

	}

}

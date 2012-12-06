package com.zhaoyunhe.pw.props.box2d;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.physical.box2d.loader.cbt.data.CircleData;
import info.u250.c2d.physical.box2d.loader.cbt.data.PolygonData;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.zhaoyunhe.pw.props.Box2dAdapter;
import com.zhaoyunhe.pw.props.Shape;

public class PolygonHelper implements Shape {
	final ShapeRenderer render;
	PolygonData data;
	final Vector2 firstPoint = new Vector2();
	final Vector2 lastPoint = new Vector2();
	int currentPoint = -1;
	Box2dAdapter adapter;

	public PolygonHelper(Box2dAdapter adapter) {
		this.render = Engine.getShapeRenderer();
		this.adapter = adapter;
	}

	@Override
	public void render(float delta) {

		Gdx.gl.glEnable(GL10.GL_BLEND);
		if (currentPoint == 1) {
			float dst = firstPoint.dst(lastPoint);
			if (dst > 0) {
				render.setColor(new Color(1, 1, 1, 0.4f));
				
				for(int i=0;i<data.polygons.size-1;i++){
					Vector2[] point=data.polygons.get(i);
					Vector2[] nextPoint=data.polygons.get(i+1);
					
					render.begin(ShapeType.Line);
					render.line(point[x], y, x2, y2);
					render.end();
				}
				
			}
		}
		Gdx.gl.glDisable(GL10.GL_BLEND);

	}

	@Override
	public InputProcessor getInputProcessor() {

		return new InputAdapter() {
			@Override
			public boolean touchDown(int x, int y, int pointer, int button) {
				if (button == Buttons.LEFT) {
					if (-1 == currentPoint) {
						firstPoint.set(Engine.screenToWorld(x, y));
						secondPoint.set(Engine.screenToWorld(x, y));
						data = new CircleData();
//						Engine.getEventManager().fire(Events.UPDATE_BOXED_PANEL, data);
						currentPoint = 1;
						// do start
					}
				}
				return true;
			}

			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				if (1 == currentPoint) {
					// do move
					secondPoint.set(Engine.screenToWorld(screenX, screenY));
				}
				return super.touchDragged(screenX, screenY, pointer);
			}

			@Override
			public boolean touchUp(int x, int y, int pointer, int button) {
				if (1 == currentPoint) {
					secondPoint.set(Engine.screenToWorld(x, y));
					if (firstPoint.dst(secondPoint) > 0) {
						data.radius = firstPoint.dst(secondPoint);
						data.center.set(firstPoint);
						
						adapter.data.bodyDatas.add(data);
//						Engine.getEventManager().fire(Events.UPDATE_BOXED_PANEL, data);
						// do end
					}
					currentPoint = -1;
					data = null;
				}
				return super.touchUp(x, y, pointer, button);
			}
		};

	}

}

package com.zhaoyunhe.pw.props.box2d;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.physical.box2d.loader.cbt.data.PolygonData;
import info.u250.c2d.utils.Mathutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.zhaoyunhe.pw.props.Box2dAdapter;
import com.zhaoyunhe.pw.props.Shape;

public class PolygonHelper implements Shape {
	final ShapeRenderer render;
	PolygonData data;
	
	final Vector2 firstPoint = new Vector2();
	final Vector2 currentPoint = new Vector2();
	final Vector2 lastPoint = new Vector2();
	final Array<Vector2> pointList = new Array<Vector2>();

	int currentPointer = -1;
	Box2dAdapter adapter;

	public PolygonHelper(Box2dAdapter adapter) {
		this.render = Engine.getShapeRenderer();
		this.render.setColor(new Color(1, 1, 1, 0.4f));
		this.adapter = adapter;
	}

	@Override
	public void render(float delta) {

		Gdx.gl.glEnable(GL10.GL_BLEND);
		if (currentPointer == 1) {
//			for (Vector2[] polygon : pointList) {
				for (int i = 0; i < pointList.size - 1; i++) {
					render.begin(ShapeType.Line);
					render.line(pointList.get(i).x, pointList.get(i).y, pointList.get(i+1).x,pointList.get(i+1).y);
					render.end();
				}
//			}
		}
		Gdx.gl.glDisable(GL10.GL_BLEND);
	}

	@Override
	public InputProcessor getInputProcessor() {

		return new InputAdapter() {
			@Override
			public boolean touchDown(int x, int y, int pointer, int button) {
				if (button == Buttons.LEFT) {
					if (-1 == currentPointer) {
						firstPoint.set(Engine.screenToWorld(x, y));
						currentPoint.set(Engine.screenToWorld(x, y));

						data = new PolygonData();
						pointList.clear();
						pointList.add(firstPoint.cpy());

						// Engine.getEventManager().fire(Events.UPDATE_BOXED_PANEL,
						// data);
						currentPointer = 1;
					}
				}
				return true;
			}

			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				if (1 == currentPointer) {
					// do move
					if (Engine.screenToWorld(screenX, screenY)
							.dst(currentPoint) >= 10) {
						currentPoint
								.set(Engine.screenToWorld(screenX, screenY));
						Vector2 point = new Vector2();
						point.set(Engine.screenToWorld(screenX, screenY));
						pointList.add(point);
					}
				}
				return super.touchDragged(screenX, screenY, pointer);
			}

			@Override
			public boolean touchUp(int x, int y, int pointer, int button) {
				if (1 == currentPointer) {
					lastPoint.set(Engine.screenToWorld(x, y));
					pointList.add(lastPoint.cpy());

					int count=pointList.size/3;
					for(int i=0;i<count;i++){
						int temp=i*3;
						Vector2[] points = new Vector2[3];
						points[0]=pointList.get(temp);
						points[1]=pointList.get(temp+1);
						points[2]=pointList.get(temp+2);
						if (Mathutils.isClockwise(points[0], points[1], points[2])) {
							Vector2 vv = points[0];
							points[0] = points[2];
							points[2] = vv;
						}
						
						data.polygons.add(points);
					}

					data.center.set(firstPoint);//TODO error
					adapter.data.bodyDatas.add(data);
					// Engine.getEventManager().fire(Events.UPDATE_BOXED_PANEL,data);

					currentPointer = -1;
					data = null;
				}
				return super.touchUp(x, y, pointer, button);
			}
		};

	}

}

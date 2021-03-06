package com.zhaoyunhe.pw.props.box2d;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.physical.box2d.loader.cbt.data.BoxData;

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

public class BoxHelper implements Shape {
	final ShapeRenderer render;
	BoxData data;
	final Vector2 firstPoint = new Vector2();
	final Vector2 secondPoint = new Vector2();
	int currentPoint = -1;
	Box2dAdapter adapter;

	public BoxHelper(Box2dAdapter adapter) {
		this.adapter = adapter;
		this.render=Engine.getShapeRenderer();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glEnable(GL10.GL_BLEND);
		if (currentPoint == 1) {
			float width = secondPoint.x - firstPoint.x;
			float height = secondPoint.y - firstPoint.y;
			if (Math.abs(width) > 0 && Math.abs(height) > 0) {
				render.setColor(new Color(1, 1, 1, 0.4f));
				render.begin(ShapeType.FilledRectangle);
				render.filledRect(firstPoint.x, firstPoint.y, width, height);
				render.end();
			}
		}
		Gdx.gl.glDisable(GL10.GL_BLEND);

	}

	@Override
	public InputProcessor getInputProcessor() {

		return new InputAdapter() {
			@Override
			public boolean touchDown(int x, int y, int pointer, int button) {
				if(button == Buttons.LEFT){
					if (-1 == currentPoint) {
						firstPoint.set(Engine.screenToWorld(x, y));
						secondPoint.set(Engine.screenToWorld(x, y));
						data = new BoxData();
//						Engine.getEventManager().fire(Events.UPDATE_BOXED_PANEL,data);
						currentPoint = 1;
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

//			@Override
//			public boolean keyDown(int keycode) {
//				if(keycode == Keys.ESCAPE){
//					currentPoint = -1;
//					data = null;
//				}
//				return super.keyDown(keycode);
//			}
			@Override
			public boolean touchUp(int x, int y, int pointer, int button) {
				if(1==currentPoint){
					secondPoint.set(Engine.screenToWorld(x, y));
					float width =  secondPoint.x - firstPoint.x ;
					float height = secondPoint.y - firstPoint.y ;
					
					data.width = Math.abs(width);
					data.height = Math.abs(height);
					
					Vector2 start = new Vector2();
					
					if(width>0){
						start.x = firstPoint.x ;
					}else{
						start.x = secondPoint.x ;
					}
					
					if(height>0){
						start.y = firstPoint.y ;
					}else{
						start.y = secondPoint.y ;
					}
					
					data.center.set(start.cpy().add(data.width/2,data.height/2));
					adapter.data.bodyDatas.add(data);
//					Engine.getEventManager().fire(Events.UPDATE_BOXED_PANEL,data);
					data = null;
					currentPoint = -1;
					// do end
				}

				return super.touchUp(x, y, pointer, button);
			}
		};
	
	}

}

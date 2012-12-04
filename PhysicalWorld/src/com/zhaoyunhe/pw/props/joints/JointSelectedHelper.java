package com.zhaoyunhe.pw.props.joints;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.physical.box2d.loader.cbt.data.DistanceJointData;
import info.u250.c2d.physical.box2d.loader.cbt.data.FrictionJointData;
import info.u250.c2d.physical.box2d.loader.cbt.data.JointData;
import info.u250.c2d.physical.box2d.loader.cbt.data.PrismaticJointData;
import info.u250.c2d.physical.box2d.loader.cbt.data.PulleyJointData;
import info.u250.c2d.physical.box2d.loader.cbt.data.RevoluteJointData;
import info.u250.c2d.physical.box2d.loader.cbt.data.RopeJointData;
import info.u250.c2d.physical.box2d.loader.cbt.data.WeldJointData;
import info.u250.c2d.physical.box2d.loader.cbt.data.WheelJointData;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.zhaoyunhe.pw.Events;
import com.zhaoyunhe.pw.props.Box2dAdapter;
import com.zhaoyunhe.pw.props.Shape;

public class JointSelectedHelper implements Shape {
	final ShapeRenderer render;
	
	Box2dAdapter adapter;
	JointData data;
	Vector2 snapPoint,supportPoint;
	boolean showProperty;

	public JointSelectedHelper(Box2dAdapter adapter) {
		render = Engine.getShapeRenderer();
		this.adapter = adapter;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glEnable(GL10.GL_BLEND);
		if(null!=snapPoint){
			render.begin(ShapeType.FilledRectangle);
			render.setColor(Color.BLUE);
			render.filledRect(snapPoint.x-5, snapPoint.y-5, 2*5, 2*5);
			render.end();
		}
		Gdx.gl.glDisable(GL10.GL_BLEND);
	}

	Vector2 tmp = new Vector2();
	@Override
	public InputProcessor getInputProcessor() {
		return new InputAdapter() {
			@Override
			public boolean touchDown(int x, int y, int pointer, int button) {
				if(button == Buttons.LEFT){
					boolean shapeOn = false;
					for(JointData d:adapter.data.jointDatas){
						if(d.isFocus(Engine.screenToWorld(x, y))){
							data = d;
							if(data instanceof PulleyJointData){
								PulleyJointData tmpd = PulleyJointData.class.cast(data);
								if(tmpd.bodyA.center.cpy().add(tmpd.localAnchorA).dst(Engine.screenToWorld(x, y))<=5){
									supportPoint = tmpd.localAnchorA;
									snapPoint = tmpd.bodyA.center.cpy().add(tmpd.localAnchorA);
								}else{
									supportPoint = tmpd.localAnchorB;
									snapPoint = tmpd.bodyB.center.cpy().add(tmpd.localAnchorB);
								}
							}if(data instanceof DistanceJointData){
								DistanceJointData tmpd = DistanceJointData.class.cast(data);
								if(tmpd.bodyA.center.cpy().add(tmpd.localAnchorA).dst(Engine.screenToWorld(x, y))<=5){
									supportPoint = tmpd.localAnchorA;
									snapPoint = tmpd.bodyA.center.cpy().add(tmpd.localAnchorA);
								}else{
									supportPoint = tmpd.localAnchorB;
									snapPoint = tmpd.bodyB.center.cpy().add(tmpd.localAnchorB);
								}
							}else if(data instanceof RopeJointData){
								RopeJointData tmpd = RopeJointData.class.cast(data);
								if(tmpd.bodyA.center.cpy().add(tmpd.localAnchorA).dst(Engine.screenToWorld(x, y))<=5){
									supportPoint = tmpd.localAnchorA;
									snapPoint = tmpd.bodyA.center.cpy().add(tmpd.localAnchorA);
								}else{
									supportPoint = tmpd.localAnchorB;
									snapPoint = tmpd.bodyB.center.cpy().add(tmpd.localAnchorB);
								}
							}else if(data instanceof RevoluteJointData){
								RevoluteJointData tmpd = RevoluteJointData.class.cast(data);
								supportPoint = tmpd.localAnchorA;
								snapPoint = tmpd.bodyA.center.cpy().add(tmpd.localAnchorA);
							}else if(data instanceof PrismaticJointData){
								PrismaticJointData tmpd = PrismaticJointData.class.cast(data);
								supportPoint = tmpd.localAnchorA;
								snapPoint = tmpd.bodyA.center.cpy().add(tmpd.localAnchorA);
							}else if(data instanceof WeldJointData){
								WeldJointData tmpd = WeldJointData.class.cast(data);
								supportPoint = tmpd.localAnchorA;
								snapPoint = tmpd.bodyA.center.cpy().add(tmpd.localAnchorA);
							}else if(data instanceof FrictionJointData){
								FrictionJointData tmpd = FrictionJointData.class.cast(data);
								supportPoint = tmpd.localAnchorA;
								snapPoint = tmpd.bodyA.center.cpy().add(tmpd.localAnchorA);
							}else if(data instanceof WheelJointData){
								WheelJointData tmpd = WheelJointData.class.cast(data);
								supportPoint = tmpd.localAnchorA;
								snapPoint = tmpd.bodyA.center.cpy().add(tmpd.localAnchorA);
							}
							
							Engine.getEventManager().fire(Events.UPDATE_PROPERTY_PANEL,data);
							shapeOn = true;
							break;
						}
					}
					if(shapeOn){
						tmp.set(Engine.screenToWorld(x, y));
						showProperty = true;
						return true;
					}else{
						data = null;
						showProperty = false;
						return false;
					}
				}
				return false;
			}
			@Override
			public boolean touchDragged(int x, int y, int pointer) {
				if(null!=data && snapPoint!=null){
					Vector2 offset = Engine.screenToWorld(x, y).sub(tmp);
					snapPoint.add(offset.x,offset.y );
					supportPoint.add(offset.x,offset.y );
					tmp.set(Engine.screenToWorld(x, y));
//					Engine.getEventManager().fire(Events.UPDATE_BOXED_PANEL,data);
					if(offset.len2()!=0){
						showProperty = false;
					}
				}
				return false;
			}
			

			@Override
			public boolean touchUp(int x, int y, int pointer, int button) {
				data = null;
				snapPoint = null;
				supportPoint = null;
				if (showProperty) {
					Engine.getEventManager().fire(Events.MOVE_PROPERTIES_PANEL, true);
				} else {
					Engine.getEventManager().fire(Events.MOVE_PROPERTIES_PANEL, false);
				}
				return super.touchUp(x, y, pointer, button);
			}
		};
	
	}

}

package com.zhaoyunhe.pw.ui;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.events.Event;
import info.u250.c2d.engine.events.EventListener;
import info.u250.c2d.physical.box2d.loader.cbt.data.BoxData;
import info.u250.c2d.physical.box2d.loader.cbt.data.CircleData;
import info.u250.c2d.physical.box2d.loader.cbt.data.DistanceJointData;
import info.u250.c2d.physical.box2d.loader.cbt.data.FrictionJointData;
import info.u250.c2d.physical.box2d.loader.cbt.data.PrismaticJointData;
import info.u250.c2d.physical.box2d.loader.cbt.data.PulleyJointData;
import info.u250.c2d.physical.box2d.loader.cbt.data.RevoluteJointData;
import info.u250.c2d.physical.box2d.loader.cbt.data.RopeJointData;
import info.u250.c2d.physical.box2d.loader.cbt.data.WeldJointData;
import info.u250.c2d.physical.box2d.loader.cbt.data.WheelJointData;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.zhaoyunhe.pw.Events;
import com.zhaoyunhe.pw.props.properties.BoxBodyProperties;
import com.zhaoyunhe.pw.props.properties.CircleBodyProperties;
import com.zhaoyunhe.pw.props.properties.DistanceJointProperties;
import com.zhaoyunhe.pw.props.properties.FrictionJointProperties;
import com.zhaoyunhe.pw.props.properties.PrismaticJointProperties;
import com.zhaoyunhe.pw.props.properties.PulleyJointProperties;
import com.zhaoyunhe.pw.props.properties.RevoluteJointProperties;
import com.zhaoyunhe.pw.props.properties.RopeJointProperties;
import com.zhaoyunhe.pw.props.properties.WeldJointProperties;
import com.zhaoyunhe.pw.props.properties.WheelJointProperties;

public class PropertiesPanel extends Group {
	// private TextureAtlas atlas;
	final CircleBodyProperties circleBodyProperties;
	final BoxBodyProperties boxBodyProperties;
	final DistanceJointProperties distanceJointProperties;
	final RevoluteJointProperties revoluteJointProperties;
	final PrismaticJointProperties prismaticJointProperties;
	final RopeJointProperties ropeJointProperties;
	final WeldJointProperties weldJointProperties;
	final FrictionJointProperties frictionJointProperties;
	final WheelJointProperties wheelJointProperties;
	final PulleyJointProperties pulleyJointProperties;

	Object obejct_current = null;

	public PropertiesPanel() {
		// atlas = Engine.resource("atlas");
		boxBodyProperties = new BoxBodyProperties();
		circleBodyProperties = new CircleBodyProperties();
		distanceJointProperties = new DistanceJointProperties();
		revoluteJointProperties = new RevoluteJointProperties();
		prismaticJointProperties = new PrismaticJointProperties();
		ropeJointProperties = new RopeJointProperties();
		weldJointProperties = new WeldJointProperties();
		frictionJointProperties = new FrictionJointProperties();
		wheelJointProperties = new WheelJointProperties();
		pulleyJointProperties = new PulleyJointProperties();

		this.addActor(boxBodyProperties);
		this.setSize(boxBodyProperties.getWidth(), boxBodyProperties.getHeight());

		Engine.getEventManager().register(Events.UPDATE_PROPERTY_PANEL,
				new EventListener() {
					@Override
					public void onEvent(Event event) {
						// Box2dAdapter.class.cast(layer.adapter).save();

						Object data = event.getSource();
						obejct_current = data;
						if (data instanceof BoxData) {
							clear();
							addActor(boxBodyProperties);
							boxBodyProperties.update(data);
						} else if (data instanceof CircleData) {
							clear();
							addActor(circleBodyProperties);
							circleBodyProperties.update(data);
						} else if (data instanceof DistanceJointData) {
							clear();
							addActor(distanceJointProperties);
							distanceJointProperties.update(data);
						} else if (data instanceof RevoluteJointData) {
							clear();
							addActor(revoluteJointProperties);
							revoluteJointProperties.update(data);
						} else if (data instanceof PrismaticJointData) {
							clear();
							addActor(prismaticJointProperties);
							prismaticJointProperties.update(data);
						} else if (data instanceof RopeJointData) {
							clear();
							addActor(ropeJointProperties);
							ropeJointProperties.update(data);
						} else if (data instanceof WeldJointData) {
							clear();
							addActor(weldJointProperties);
							weldJointProperties.update(data);
						} else if (data instanceof FrictionJointData) {
							clear();
							addActor(frictionJointProperties);
							frictionJointProperties.update(data);
						} else if (data instanceof WheelJointData) {
							clear();
							addActor(wheelJointProperties);
							wheelJointProperties.update(data);
						} else if (data instanceof PulleyJointData) {
							clear();
							addActor(pulleyJointProperties);
							pulleyJointProperties.update(data);
						}
					}
				});
	}

	// public static class PropertyBoard extends Group {
	// private Vector2 vector=new Vector2();
	// private String nameX;
	// private String nameY;
	//
	// private Image bg;
	// private Image cursor;
	// private Image touchedImage;
	//
	// public PropertyBoard(TextureRegion bgRegion,TextureRegion
	// cursorRegion,String namex,String namey) {
	// this.bg = new Image(bgRegion);
	// this.cursor = new Image(cursorRegion);
	// this.nameX=namex;
	// this.nameY=namey;
	//
	// this.addListener(new InputListener(){
	//
	// @Override
	// public boolean touchDown(InputEvent event, float x, float y,int pointer,
	// int button) {
	// Actor actor=hit(x, y, true);
	// if(actor!=null && actor instanceof Image && actor==cursor){
	// touchedImage=(Image)actor;
	// }
	// return true;
	// }
	//
	// @Override
	// public void touchUp(InputEvent event, float x, float y,int pointer, int
	// button) {
	// touchedImage=null;
	// }
	//
	// @Override
	// public void touchDragged(InputEvent event, float x, float y,int pointer)
	// {
	// if(touchedImage!=null){
	// if(x>=0 && y>=0){
	// if(x<getWidth() && y<getHeight()){
	// Gdx.app.debug("debug", "x:"+x+" y:"+y);
	// touchedImage.setPosition(x-touchedImage.getWidth()/2,
	// y-touchedImage.getHeight()/2);
	// }
	// if(x>=getWidth() && y<getHeight()){
	// touchedImage.setPosition(getWidth()-touchedImage.getWidth()/2,
	// y-touchedImage.getHeight()/2);
	// }
	// if(x<getWidth() && y>=getHeight()){
	// touchedImage.setPosition(x-touchedImage.getWidth()/2,
	// getHeight()-touchedImage.getHeight()/2);
	// }
	// }
	// }
	// }
	//
	// });
	//
	//
	// this.addActor(bg);
	// this.addActor(cursor);
	// this.setSize(bg.getWidth(), bg.getHeight());
	// }
	//
	// public Vector2 getVector() {
	// return vector;
	// }
	//
	// public void setVector(Vector2 vector) {
	// this.vector = vector;
	// }
	//
	// }
}

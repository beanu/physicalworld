package com.zhaoyunhe.pw.props;

import java.util.Iterator;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.physical.box2d.Cb2ObjectGroup;
import info.u250.c2d.physical.box2d.loader.cbt.CbtWorldReader;
import info.u250.c2d.physical.box2d.loader.cbt.data.BodyData;
import info.u250.c2d.physical.box2d.loader.cbt.data.JointData;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.zhaoyunhe.pw.props.box2d.BoxHelper;

public class Box2dAdapter extends ShapeGroup {
	final BoxHelper box;
	public final CbtWorldReader data;
	final Cb2ObjectGroup group;
	final ShapeRenderer render;
	boolean runMode = false;

	public Box2dAdapter() {
		box = new BoxHelper(this);
		data = new CbtWorldReader();
		group = new Cb2ObjectGroup();
		render = Engine.getShapeRenderer();
		
		//add to group
		this.add(box);
	}

	@Override
	public void render(float delta) {

		if (runMode) {
//			Engine.getSpriteBatch().begin();
//			group.render(delta);
//			Engine.getSpriteBatch().end();
			// group.debug(editor.render);
			final Iterator<JointData> it2 = data.jointDatas.iterator();
			while (it2.hasNext()) {
				it2.next().debug(render);
			}
		} else {
			final Iterator<BodyData> it = data.bodyDatas.iterator();
			while (it.hasNext()) {
				it.next().debug(render);
			}
			final Iterator<JointData> it2 = data.jointDatas.iterator();
			while (it2.hasNext()) {
				it2.next().debug(render);
			}
		}
		super.render(delta);
		// PhysicalManager.PM().debug();

	}

	@Override
	public InputProcessor getInputProcessor() {
		return super.getInputProcessor();
	}

}

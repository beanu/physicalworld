package com.zhaoyunhe.pw.props;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.physical.box2d.Cb2ObjectGroup;
import info.u250.c2d.physical.box2d.loader.cbt.CbtWorldReader;
import info.u250.c2d.physical.box2d.loader.cbt.data.BodyData;
import info.u250.c2d.physical.box2d.loader.cbt.data.JointData;

import java.util.Iterator;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.zhaoyunhe.pw.props.box2d.BoxHelper;
import com.zhaoyunhe.pw.props.box2d.CircleHelper;
import com.zhaoyunhe.pw.props.box2d.RotateHelper;
import com.zhaoyunhe.pw.props.box2d.ScaleHelper;
import com.zhaoyunhe.pw.props.box2d.SelectedHelper;
import com.zhaoyunhe.pw.props.joints.DistanceJointHelper;
import com.zhaoyunhe.pw.props.joints.FrictionJointHelper;
import com.zhaoyunhe.pw.props.joints.PrismaticJointHelper;

public class Box2dAdapter extends ShapeGroup {
	final BoxHelper mBoxHelper;
	final CircleHelper mCircleHelper;
	final RotateHelper mRotateHelper;
	final ScaleHelper mScaleHelper;
	final SelectedHelper mSelectedHelper;
	
	final DistanceJointHelper mDistanceJointHelper;
	final FrictionJointHelper mFrictionJointHelper;
	final PrismaticJointHelper mPrismaticJointHelper; 
	
	public final CbtWorldReader data;
	final Cb2ObjectGroup group;
	final ShapeRenderer render;
	boolean runMode = false;

	public Box2dAdapter() {
		mBoxHelper = new BoxHelper(this);
		mCircleHelper=new CircleHelper(this);
		mRotateHelper=new RotateHelper(this);
		mScaleHelper=new ScaleHelper(this);
		mSelectedHelper=new SelectedHelper(this);
		
		mDistanceJointHelper=new DistanceJointHelper(this);
		mFrictionJointHelper=new FrictionJointHelper(this);
		mPrismaticJointHelper=new PrismaticJointHelper(this);
		
		data = new CbtWorldReader();
		group = new Cb2ObjectGroup();
		render = Engine.getShapeRenderer();
		
		//add to group
		this.add(mBoxHelper);
		this.add(mCircleHelper);
		this.add(mRotateHelper);
		this.add(mScaleHelper);
		this.add(mSelectedHelper);
		this.add(mDistanceJointHelper);
		this.add(mFrictionJointHelper);
		this.add(mPrismaticJointHelper);
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
		return mBoxHelper.getInputProcessor();
	}

}

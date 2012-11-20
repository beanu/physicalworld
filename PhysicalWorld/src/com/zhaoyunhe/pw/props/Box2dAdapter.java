package com.zhaoyunhe.pw.props;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.graphic.AdvanceSprite;
import info.u250.c2d.physical.box2d.Cb2Object;
import info.u250.c2d.physical.box2d.Cb2ObjectGroup;
import info.u250.c2d.physical.box2d.Cb2Object.Cb2ObjectSetupCallback;
import info.u250.c2d.physical.box2d.loader.cbt.CbtWorldReader;
import info.u250.c2d.physical.box2d.loader.cbt.data.BodyData;
import info.u250.c2d.physical.box2d.loader.cbt.data.BoxData;
import info.u250.c2d.physical.box2d.loader.cbt.data.CircleData;
import info.u250.c2d.physical.box2d.loader.cbt.data.JointData;

import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.zhaoyunhe.pw.IFileIO;
import com.zhaoyunhe.pw.props.box2d.BoxHelper;
import com.zhaoyunhe.pw.props.box2d.CircleHelper;
import com.zhaoyunhe.pw.props.box2d.RotateHelper;
import com.zhaoyunhe.pw.props.box2d.ScaleHelper;
import com.zhaoyunhe.pw.props.box2d.SelectedHelper;
import com.zhaoyunhe.pw.props.joints.DistanceJointHelper;
import com.zhaoyunhe.pw.props.joints.FrictionJointHelper;
import com.zhaoyunhe.pw.props.joints.PrismaticJointHelper;

public class Box2dAdapter extends ShapeGroup implements IFileIO {
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
	FileHandle file;
	final InputMultiplexer mulInput;

	public Box2dAdapter() {
		mBoxHelper = new BoxHelper(this);
		mCircleHelper = new CircleHelper(this);
		mRotateHelper = new RotateHelper(this);
		mScaleHelper = new ScaleHelper(this);
		mSelectedHelper = new SelectedHelper(this);

		mDistanceJointHelper = new DistanceJointHelper(this);
		mFrictionJointHelper = new FrictionJointHelper(this);
		mPrismaticJointHelper = new PrismaticJointHelper(this);

		data = new CbtWorldReader();
		group = new Cb2ObjectGroup();
		render = Engine.getShapeRenderer();

		mulInput = new InputMultiplexer();
		// add to group
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
			Engine.getSpriteBatch().begin();
			group.render(delta);
			Engine.getSpriteBatch().end();
			group.debug(Engine.getShapeRenderer());
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
		return mulInput;
	}

	@Override
	public void save() {
		try {
			if (file == null)
				file = Gdx.files.absolute("");// TODO
			data.write(file);
		} catch (Exception ex) {
			ex.printStackTrace();
			file = null;
		}
	}

	@Override
	public void read() {
		this.data.jointDatas.clear();
		this.data.bodyDatas.clear();
		this.data.read(this.file);
	}

	private void build() {
		for (BodyData body : this.data.bodyDatas) {
			body.build();
		}
		for (JointData joint : this.data.jointDatas) {
			joint.build();
		}
	}

	public void play() {
		this.build();
		this.mulInput.clear();

		group.clear();
		for (BodyData bd : this.data.bodyDatas) {
			if (bd.res == null
					|| bd.res.trim().equals("")
					|| Engine.resource("atlas", TextureAtlas.class).findRegion(
							bd.res) == null) {
				Random random = new Random();
				if (bd instanceof CircleData) {
					AdvanceSprite sp = new AdvanceSprite(Engine.resource(
							"CircleTexture", Texture.class));
					sp.setColor(random.nextFloat(), random.nextFloat(),
							random.nextFloat(), 1);
					group.add(new Cb2Object(bd, sp));
				} else if (bd instanceof BoxData) {
					AdvanceSprite sp = new AdvanceSprite(Engine.resource(
							"BoxTexture", Texture.class));
					sp.setColor(random.nextFloat(), random.nextFloat(),
							random.nextFloat(), 1);
					group.add(new Cb2Object(bd, sp));
				}
			} else {
				group.add(new Cb2Object(bd, new AdvanceSprite(Engine.resource(
						"atlas", TextureAtlas.class).findRegion(bd.res)),
						new Cb2ObjectSetupCallback() {

							@Override
							public void before(Cb2Object obj) {
							}

							@Override
							public void after(Cb2Object obj) {
								obj.data.body.setSleepingAllowed(true);
								;
								obj.data.body.setAwake(false);
							}
						}));
			}
		}
		runMode = true;
	}

	public void stop() {
		group.clear();
		data.spawn();
		this.mulInput.clear();
		// rbg.getCamera().position.set(Engine.getDefaultCamera().position);
		runMode = false;
	}

	// void activeJointSelectHelper(){
	// this.mulInput.clear();
	// this.mulInput.addProcessor(jointSelectHelper.getInputProcessor());
	// }
	public void activeFrictionJointHelper() {
		this.mulInput.clear();
		this.mulInput.addProcessor(mFrictionJointHelper.getInputProcessor());
	}

	// void activePulleyJointHelper(){
	// this.mulInput.clear();
	// this.mulInput.addProcessor(pulleyJointHelper.getInputProcessor());
	// }
	// void activeWheelJointHelper(){
	// this.mulInput.clear();
	// this.mulInput.addProcessor(wheelJointHelper.getInputProcessor());
	// }
	// void activeRopeJointHelper(){
	// this.mulInput.clear();
	// this.mulInput.addProcessor(ropeJointHelper.getInputProcessor());
	// }
	public void activePrismaticJointHelper() {
		this.mulInput.clear();
		this.mulInput.addProcessor(mPrismaticJointHelper.getInputProcessor());
	}

	// void activeWeldJointHelper(){
	// this.mulInput.clear();
	// this.mulInput.addProcessor(weldJointHelper.getInputProcessor());
	// }
	// void activeRevoluteJointHelper(){
	// this.mulInput.clear();
	// this.mulInput.addProcessor(revoluteJointHelper.getInputProcessor());
	// }
	public void activeSelectHelper() {
		this.mulInput.clear();
		this.mulInput.addProcessor(mSelectedHelper.getInputProcessor());
	}

	public void activeDistanceJointHelper() {
		this.mulInput.clear();
		this.mulInput.addProcessor(mDistanceJointHelper.getInputProcessor());
	}

	public void activeScaleHelper() {
		this.mulInput.clear();
		this.mulInput.addProcessor(mScaleHelper.getInputProcessor());
	}

	public void activeRotateHelper() {
		this.mulInput.clear();
		this.mulInput.addProcessor(mRotateHelper.getInputProcessor());
	}

	public void activeCircleHelper() {
		this.mulInput.clear();
		this.mulInput.addProcessor(mCircleHelper.getInputProcessor());
	}

	public void activeBoxHelper() {
		this.mulInput.clear();
		this.mulInput.addProcessor(mBoxHelper.getInputProcessor());
	}
}

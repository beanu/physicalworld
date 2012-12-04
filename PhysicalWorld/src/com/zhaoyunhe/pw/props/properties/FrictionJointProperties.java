package com.zhaoyunhe.pw.props.properties;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;


public class FrictionJointProperties extends JointProperties{
//	final TextField localAnchorX,localAnchorY,
	final Slider maxForce, maxTorque;
	
	public FrictionJointProperties(){
		super();
//		localAnchorX = new TextField("localAnchorX",skin);
//		localAnchorY = new TextField("localAnchorY",skin);
		maxForce = new Slider(0,100,1,false,sliderStyle);
		maxTorque = new Slider(0,100,1,false,sliderStyle);
//		
//		
//		this.add(new Label("localAnchorX", skin)).colspan(2);
//		this.add(this.localAnchorX).colspan(2).fillX();
//		this.row();
//		this.add(new Label("localAnchorY", skin)).colspan(2);
//		this.add(this.localAnchorY).colspan(2).fillX();
		this.row();
		this.add(new Label("maxForce", labelStyle));
		this.add(this.maxForce);
		this.row();
		this.add(new Label("maxTorque", labelStyle));
		this.add(this.maxTorque);
//		this.row();
		this.pack();
	}
	@Override
	public void update(Object data) {
//		bind(FrictionJointData.class.cast(data).localAnchorA, "x", localAnchorX);
//		bind(FrictionJointData.class.cast(data).localAnchorA, "y", localAnchorY);
		bind(data,"maxForce",maxForce);
		bind(data,"maxTorque",maxTorque);
		super.update(data);
	}
}

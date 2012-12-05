package com.zhaoyunhe.pw.props.properties;

import com.zhaoyunhe.pw.widget.HSlider;


public class FrictionJointProperties extends JointProperties{
//	final TextField localAnchorX,localAnchorY,
	final HSlider maxForce, maxTorque;
	
	public FrictionJointProperties(){
		super();
//		localAnchorX = new TextField("localAnchorX",skin);
//		localAnchorY = new TextField("localAnchorY",skin);
		maxForce = new HSlider("maxForce",0,100,1,sliderStyle,labelStyle);
		maxTorque = new HSlider("maxTorque",0,100,1,sliderStyle,labelStyle);

		this.add(this.maxForce);
		this.add(this.maxTorque);
		
		this.add(this.collideConnected);
		this.pack();
	}
	@Override
	public void update(Object data) {
//		bind(FrictionJointData.class.cast(data).localAnchorA, "x", localAnchorX);
//		bind(FrictionJointData.class.cast(data).localAnchorA, "y", localAnchorY);
		bind(data,"maxForce",maxForce.slider);
		bind(data,"maxTorque",maxTorque.slider);
		super.update(data);
	}
}

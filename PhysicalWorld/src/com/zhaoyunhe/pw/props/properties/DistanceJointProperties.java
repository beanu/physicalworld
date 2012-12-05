package com.zhaoyunhe.pw.props.properties;

import com.zhaoyunhe.pw.widget.HSlider;


public class DistanceJointProperties extends JointProperties{
//	final TextField localAnchorAx,localAnchorAy,localAnchorBx,localAnchorBy,
	final HSlider frequencyHz,dampingRatio;
	public DistanceJointProperties(){
		super();
//		localAnchorAx = new TextField("localAnchorAx",skin);
//		localAnchorAy = new TextField("localAnchorAy",skin);
//		localAnchorBx = new TextField("localAnchorBx",skin);
//		localAnchorBy = new TextField("localAnchorBy",skin);
		frequencyHz = new HSlider("frequencyHz",0,30,1,sliderStyle,labelStyle);
		dampingRatio = new HSlider("dampingRatio",0,1,0.01f,sliderStyle,labelStyle);

		this.row();
		this.add(this.frequencyHz);
		this.add(this.dampingRatio);
		
		this.add(this.collideConnected);
		this.pack();
	}
	@Override
	public void update(Object data) {
//		bind(DistanceJointData.class.cast(data).localAnchorA, "x", localAnchorAx);
//		bind(DistanceJointData.class.cast(data).localAnchorA, "y", localAnchorAy);
//		bind(DistanceJointData.class.cast(data).localAnchorB, "x", localAnchorBx);
//		bind(DistanceJointData.class.cast(data).localAnchorB, "y", localAnchorBy);
		bind(data,"frequencyHz",frequencyHz.slider);
		bind(data,"dampingRatio",dampingRatio.slider);
		super.update(data);
	}
}

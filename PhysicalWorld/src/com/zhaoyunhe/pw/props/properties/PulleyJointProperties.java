package com.zhaoyunhe.pw.props.properties;

import info.u250.c2d.physical.box2d.loader.cbt.data.PulleyJointData;

import com.zhaoyunhe.pw.widget.HSlider;

//TODO groundAnchorBx have no effect
public class PulleyJointProperties extends JointProperties{
//	final TextField localAnchorAx,localAnchorAy,localAnchorBx,localAnchorBy;
	final HSlider groundAnchorAx,groundAnchorAy,groundAnchorBx,groundAnchorBy;
	final HSlider ratio;
	public PulleyJointProperties(){
		super();
//		localAnchorAx = new TextField("",skin);
//		localAnchorAy = new TextField("",skin);
//		localAnchorBx = new TextField("",skin);
//		localAnchorBy = new TextField("",skin);
//		
		groundAnchorAx = new HSlider("groundAnchorAx",0,800,8,sliderStyle,labelStyle);
		groundAnchorAy = new HSlider("groundAnchorAy",0,400,4,sliderStyle,labelStyle);
		groundAnchorBx = new HSlider("groundAnchorBx",0,800,8,sliderStyle,labelStyle);
		groundAnchorBy = new HSlider("groundAnchorBy",0,400,4,sliderStyle,labelStyle);
		ratio = new HSlider("ratio",0, 1, 0.01f, sliderStyle,labelStyle);
		
		this.add(this.ratio);
		this.add(this.groundAnchorAx);
		this.add(this.groundAnchorAy);
		this.add(this.groundAnchorBx);
		this.add(this.groundAnchorBy);
		
		this.add(collideConnected);
		this.pack();
	}
	@Override
	public void update(Object data) {
//		bind(PulleyJointData.class.cast(data).localAnchorA, "x", localAnchorAx);
//		bind(PulleyJointData.class.cast(data).localAnchorA, "y", localAnchorAy);
//		bind(PulleyJointData.class.cast(data).localAnchorB, "x", localAnchorBx);
//		bind(PulleyJointData.class.cast(data).localAnchorB, "y", localAnchorBy);
		bind(data,"ratio",ratio);
		bind(PulleyJointData.class.cast(data).groundAnchorA, "x", groundAnchorAx.slider);
		bind(PulleyJointData.class.cast(data).groundAnchorA, "y", groundAnchorAy.slider);
		bind(PulleyJointData.class.cast(data).groundAnchorB, "x", groundAnchorBx.slider);
		bind(PulleyJointData.class.cast(data).groundAnchorB, "y", groundAnchorBy.slider);
		super.update(data);
	}
}

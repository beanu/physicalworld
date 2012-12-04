package com.zhaoyunhe.pw.props.properties;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;


public class DistanceJointProperties extends JointProperties{
//	final TextField localAnchorAx,localAnchorAy,localAnchorBx,localAnchorBy,
//					frequencyHz,dampingRatio;
	final Slider frequencyHz,dampingRatio;
	public DistanceJointProperties(){
		super();
//		localAnchorAx = new TextField("localAnchorAx",skin);
//		localAnchorAy = new TextField("localAnchorAy",skin);
//		localAnchorBx = new TextField("localAnchorBx",skin);
//		localAnchorBy = new TextField("localAnchorBy",skin);
		frequencyHz = new Slider(0,100,1,false,sliderStyle);
		dampingRatio = new Slider(0,1,0.01f,false,sliderStyle);
//		
//		this.add(new Label("anchorA_x", skin)).colspan(2);
//		this.add(this.localAnchorAx).colspan(2).fillX();
//		this.row();
//		this.add(new Label("anchorA_y", skin)).colspan(2);
//		this.add(this.localAnchorAy).colspan(2).fillX();
//		this.row();
//		this.add(new Label("anchorB_x", skin)).colspan(2);
//		this.add(this.localAnchorBx).colspan(2).fillX();
//		this.row();
//		this.add(new Label("anchorB_y", skin)).colspan(2);
//		this.add(this.localAnchorBy).colspan(2).fillX();
		this.row();
		this.add(new Label("frequencyHz", labelStyle));
		this.add(this.frequencyHz);
		this.row();
		this.add(new Label("dampingRatio", labelStyle));
		this.add(this.dampingRatio);
//		this.row();
		this.pack();
	}
	@Override
	public void update(Object data) {
//		bind(DistanceJointData.class.cast(data).localAnchorA, "x", localAnchorAx);
//		bind(DistanceJointData.class.cast(data).localAnchorA, "y", localAnchorAy);
//		bind(DistanceJointData.class.cast(data).localAnchorB, "x", localAnchorBx);
//		bind(DistanceJointData.class.cast(data).localAnchorB, "y", localAnchorBy);
		bind(data,"frequencyHz",frequencyHz);
		bind(data,"dampingRatio",dampingRatio);
		super.update(data);
	}
}

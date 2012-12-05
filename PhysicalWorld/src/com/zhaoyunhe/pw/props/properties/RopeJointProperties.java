package com.zhaoyunhe.pw.props.properties;

import com.zhaoyunhe.pw.widget.HSlider;

public class RopeJointProperties extends JointProperties {
	// final TextField localAnchorAx,localAnchorAy,localAnchorBx,localAnchorBy,
	final HSlider maxLength;

	public RopeJointProperties() {
		super();
		// localAnchorAx = new TextField("localAnchorAx",skin);
		// localAnchorAy = new TextField("localAnchorAy",skin);
		// localAnchorBx = new TextField("localAnchorBx",skin);
		// localAnchorBy = new TextField("localAnchorBy",skin);
		maxLength = new HSlider("maxLength", 0, 100, 1, sliderStyle, labelStyle);

		this.add(this.maxLength);
		this.add(collideConnected);
		this.pack();
	}

	@Override
	public void update(Object data) {
		// bind(RopeJointData.class.cast(data).localAnchorA, "x",
		// localAnchorAx);
		// bind(RopeJointData.class.cast(data).localAnchorA, "y",
		// localAnchorAy);
		// bind(RopeJointData.class.cast(data).localAnchorB, "x",
		// localAnchorBx);
		// bind(RopeJointData.class.cast(data).localAnchorB, "y",
		// localAnchorBy);
		bind(data, "maxLength", maxLength.slider);
		super.update(data);
	}
}

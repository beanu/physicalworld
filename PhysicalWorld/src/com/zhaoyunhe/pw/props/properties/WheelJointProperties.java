package com.zhaoyunhe.pw.props.properties;

import info.u250.c2d.physical.box2d.loader.cbt.data.WheelJointData;

import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.zhaoyunhe.pw.widget.HSlider;

public class WheelJointProperties extends JointProperties {
	// final TextField localAnchorX,localAnchorY,
	final HSlider localAxisAx, localAxisAy, dampingRatio, frequencyHz, motorSpeed, maxMotorTorque;

	final CheckBox enableMotor;

	public WheelJointProperties() {
		super();
		// localAnchorX = new TextField("localAnchorX",skin);
		// localAnchorY = new TextField("localAnchorY",skin);
		localAxisAx = new HSlider("localAxisAx",0, 800, 8, sliderStyle,labelStyle);
		localAxisAy = new HSlider("localAxisAy",0, 400, 4, sliderStyle,labelStyle);
		dampingRatio = new HSlider("dampingRatio",0, 1, 0.01f, sliderStyle,labelStyle);
		frequencyHz = new HSlider("frequencyHz",0, 100, 1, sliderStyle,labelStyle);
		motorSpeed = new HSlider("motorSpeed",0, 100, 1, sliderStyle,labelStyle);
		maxMotorTorque = new HSlider("maxMotorTorque",0, 100, 1, sliderStyle,labelStyle);

		enableMotor = new CheckBox("enableMotor", checkboxStyle);

		this.add(dampingRatio);
		this.add(frequencyHz);
		this.add(this.motorSpeed);
		this.add(this.maxMotorTorque);
		this.add(this.localAxisAx);
		this.add(this.localAxisAy);
		
		this.add(enableMotor);
		this.add(collideConnected);
		this.pack();
	}

	@Override
	public void update(Object data) {
		// bind(WheelJointData.class.cast(data).localAnchorA, "x",
		// localAnchorX);
		// bind(WheelJointData.class.cast(data).localAnchorA, "y",
		// localAnchorY);
		bind(WheelJointData.class.cast(data).localAxisA, "x", localAxisAx.slider);
		bind(WheelJointData.class.cast(data).localAxisA, "y", localAxisAy.slider);
		bind(data, "dampingRatio", dampingRatio.slider);
		bind(data, "frequencyHz", frequencyHz.slider);
		bind(data, "motorSpeed", motorSpeed.slider);
		bind(data, "maxMotorTorque", maxMotorTorque.slider);
		bind(data, "enableMotor", enableMotor);
		super.update(data);
	}
}

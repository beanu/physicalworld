package com.zhaoyunhe.pw.props.properties;

import info.u250.c2d.physical.box2d.loader.cbt.data.WheelJointData;

import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;

public class WheelJointProperties extends JointProperties {
	// final TextField localAnchorX,localAnchorY,
	final Slider localAxisAx, localAxisAy, dampingRatio, frequencyHz, motorSpeed, maxMotorTorque;

	final CheckBox enableMotor;

	public WheelJointProperties() {
		super();
		// localAnchorX = new TextField("localAnchorX",skin);
		// localAnchorY = new TextField("localAnchorY",skin);
		localAxisAx = new Slider(0, 800, 8, false, sliderStyle);
		localAxisAy = new Slider(0, 400, 4, false, sliderStyle);
		dampingRatio = new Slider(0, 1, 0.01f, false, sliderStyle);
		frequencyHz = new Slider(0, 100, 1, false, sliderStyle);
		motorSpeed = new Slider(0, 100, 1, false, sliderStyle);
		maxMotorTorque = new Slider(0, 100, 1, false, sliderStyle);

		enableMotor = new CheckBox("enableMotor", checkboxStyle);
		//
		//
		// this.add(new Label("localAnchorX", skin)).colspan(2);
		// this.add(this.localAnchorX).colspan(2).fillX();
		// this.row();
		// this.add(new Label("localAnchorY", skin)).colspan(2);
		// this.add(this.localAnchorY).colspan(2).fillX();
		this.row();

		this.add(new Label("dampingRatio", labelStyle));
		this.add(this.dampingRatio);
		this.row();
		this.add(new Label("frequencyHz", labelStyle));
		this.add(this.frequencyHz);
		this.row();

		this.add(new Label("enableMotor", labelStyle));
		this.add(enableMotor);
		this.row();

		this.add(new Label("motorSpeed", labelStyle));
		this.add(this.motorSpeed);
		this.row();
		this.add(new Label("maxMotorTorque", labelStyle));
		this.add(this.maxMotorTorque);
		this.row();

		this.add(new Label("localAxisA_x", labelStyle));
		this.add(this.localAxisAx);
		this.row();
		this.add(new Label("localAxisA_y", labelStyle));
		this.add(this.localAxisAy);
		// this.row();

		this.pack();
	}

	@Override
	public void update(Object data) {
		// bind(WheelJointData.class.cast(data).localAnchorA, "x",
		// localAnchorX);
		// bind(WheelJointData.class.cast(data).localAnchorA, "y",
		// localAnchorY);
		bind(WheelJointData.class.cast(data).localAxisA, "x", localAxisAx);
		bind(WheelJointData.class.cast(data).localAxisA, "y", localAxisAy);
		bind(data, "dampingRatio", dampingRatio);
		bind(data, "frequencyHz", frequencyHz);
		bind(data, "motorSpeed", motorSpeed);
		bind(data, "maxMotorTorque", maxMotorTorque);
		bind(data, "enableMotor", enableMotor);
		super.update(data);
	}
}

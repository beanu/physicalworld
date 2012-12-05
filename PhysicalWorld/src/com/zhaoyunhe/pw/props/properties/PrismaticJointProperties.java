package com.zhaoyunhe.pw.props.properties;

import info.u250.c2d.physical.box2d.loader.cbt.data.PrismaticJointData;

import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.zhaoyunhe.pw.widget.HSlider;

public class PrismaticJointProperties extends JointProperties {
	// final TextField localAnchorX,localAnchorY,
	final HSlider localAxisAx, localAxisAy, lowerTranslation, upperTranslation, motorSpeed, maxMotorForce;

	final CheckBox enableLimit, enableMotor;

	public PrismaticJointProperties() {
		super();
		// localAnchorX = new TextField("localAnchorX",skin);
		// localAnchorY = new TextField("localAnchorY",skin);
		localAxisAx = new HSlider("localAxisAx", 0, 800, 8, sliderStyle, labelStyle);
		localAxisAy = new HSlider("localAxisAy", 0, 400, 4, sliderStyle, labelStyle);
		lowerTranslation = new HSlider("lowerTranslation", 0, 100, 1, sliderStyle, labelStyle);
		upperTranslation = new HSlider("upperTranslation", 0, 100, 1, sliderStyle, labelStyle);
		motorSpeed = new HSlider("motorSpeed", 0, 100, 1, sliderStyle, labelStyle);
		maxMotorForce = new HSlider("maxMotorForce", 0, 100, 1, sliderStyle, labelStyle);
		
		enableLimit = new CheckBox("enableLimit", checkboxStyle);
		enableMotor = new CheckBox("enableMotor", checkboxStyle);

		this.add(lowerTranslation);
		this.add(upperTranslation);
		this.add(motorSpeed);
		this.add(maxMotorForce);
		this.add(localAxisAx);
		this.add(localAxisAy);
		
		this.add(enableLimit);
		this.add(enableMotor);
		this.add(collideConnected);
		this.pack();
	}

	@Override
	public void update(Object data) {
		// bind(PrismaticJointData.class.cast(data).localAnchorA, "x",
		// localAnchorX);
		// bind(PrismaticJointData.class.cast(data).localAnchorA, "y",
		// localAnchorY);
		bind(PrismaticJointData.class.cast(data).localAxisA, "x", localAxisAx.slider);
		bind(PrismaticJointData.class.cast(data).localAxisA, "y", localAxisAy.slider);
		bind(data, "lowerTranslation", lowerTranslation.slider);
		bind(data, "upperTranslation", upperTranslation.slider);
		bind(data, "motorSpeed", motorSpeed.slider);
		bind(data, "maxMotorForce", maxMotorForce.slider);
		bind(data, "enableLimit", enableLimit);
		bind(data, "enableMotor", enableMotor);
		super.update(data);
	}
}

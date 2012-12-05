package com.zhaoyunhe.pw.props.properties;

import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.zhaoyunhe.pw.widget.HSlider;

public class RevoluteJointProperties extends JointProperties {
	// final TextField localAnchorX,localAnchorY,
	final HSlider lowerAngle, upperAngle, motorSpeed, maxMotorTorque;

	final CheckBox enableLimit, enableMotor;

	public RevoluteJointProperties() {
		super();
		// localAnchorX = new TextField("localAnchorX",skin);
		// localAnchorY = new TextField("localAnchorY",skin);
		lowerAngle = new HSlider("lowerAngle",0, 360, 1, sliderStyle,labelStyle);
		upperAngle = new HSlider("upperAngle",0, 360, 1, sliderStyle,labelStyle);
		motorSpeed = new HSlider("motorSpeed",0, 100, 1, sliderStyle,labelStyle);
		maxMotorTorque = new HSlider("maxMotorTorque",0, 100, 1, sliderStyle,labelStyle);

		enableLimit = new CheckBox("enableLimit", checkboxStyle);
		enableMotor = new CheckBox("enableMotor", checkboxStyle);
		
		
		
		this.add(this.lowerAngle);
		this.add(this.upperAngle);
		this.add(this.motorSpeed);
		this.add(this.maxMotorTorque);
		
		this.add(enableLimit);
		this.add(enableMotor);
		this.add(collideConnected);
		this.pack();
	}

	@Override
	public void update(Object data) {
		// bind(RevoluteJointData.class.cast(data).localAnchorA, "x",
		// localAnchorX);
		// bind(RevoluteJointData.class.cast(data).localAnchorA, "y",
		// localAnchorY);
		 bind(data,"lowerAngle",lowerAngle.slider);
		 bind(data,"upperAngle",upperAngle.slider);
		 bind(data,"motorSpeed",motorSpeed.slider);
		 bind(data,"maxMotorTorque",maxMotorTorque.slider);
		 bind(data,"enableLimit",enableLimit);
		 bind(data,"enableMotor",enableMotor);
		super.update(data);
	}
}

package com.zhaoyunhe.pw.props.properties;

import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;

public class RevoluteJointProperties extends JointProperties {
	// final TextField localAnchorX,localAnchorY,
	final Slider lowerAngle, upperAngle, motorSpeed, maxMotorTorque;

	final CheckBox enableLimit, enableMotor;

	public RevoluteJointProperties() {
		super();
		// localAnchorX = new TextField("localAnchorX",skin);
		// localAnchorY = new TextField("localAnchorY",skin);
		lowerAngle = new Slider(0, 360, 1, false, sliderStyle);
		upperAngle = new Slider(0, 360, 1, false, sliderStyle);
		motorSpeed = new Slider(0, 100, 1, false, sliderStyle);
		maxMotorTorque = new Slider(0, 100, 1, false, sliderStyle);

		enableLimit = new CheckBox("enableLimit", checkboxStyle);
		enableMotor = new CheckBox("enableMotor", checkboxStyle);
		//
		//
		// this.add(new Label("localAnchorX", skin)).colspan(2);
		// this.add(this.localAnchorX).colspan(2).fillX();
		// this.row();
		// this.add(new Label("localAnchorY", skin)).colspan(2);
		// this.add(this.localAnchorY).colspan(2).fillX();
		this.row();

		this.add(new Label("enableLimit", labelStyle)).colspan(3);
		this.add(enableLimit).colspan(1).fillX();
		this.row();

		this.add(new Label("lowerAngle", labelStyle)).colspan(2);
		this.add(this.lowerAngle).colspan(2).fillX();
		this.row();
		this.add(new Label("upperAngle", labelStyle)).colspan(2);
		this.add(this.upperAngle).colspan(2).fillX();
		this.row();

		this.add(new Label("enableMotor", labelStyle)).colspan(3);
		this.add(enableMotor).colspan(1).fillX();
		this.row();

		this.add(new Label("motorSpeed", labelStyle)).colspan(2);
		this.add(this.motorSpeed).colspan(2).fillX();
		this.row();
		this.add(new Label("maxMotorTorque", labelStyle)).colspan(2);
		this.add(this.maxMotorTorque).colspan(2).fillX();
		// this.row();
		this.pack();
	}

	@Override
	public void update(Object data) {
		// bind(RevoluteJointData.class.cast(data).localAnchorA, "x",
		// localAnchorX);
		// bind(RevoluteJointData.class.cast(data).localAnchorA, "y",
		// localAnchorY);
		 bind(data,"lowerAngle",lowerAngle);
		 bind(data,"upperAngle",upperAngle);
		 bind(data,"motorSpeed",motorSpeed);
		 bind(data,"maxMotorTorque",maxMotorTorque);
		 bind(data,"enableLimit",enableLimit);
		 bind(data,"enableMotor",enableMotor);
		super.update(data);
	}
}

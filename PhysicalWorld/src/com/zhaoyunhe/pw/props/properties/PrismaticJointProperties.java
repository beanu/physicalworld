package com.zhaoyunhe.pw.props.properties;

import info.u250.c2d.physical.box2d.loader.cbt.data.PrismaticJointData;

import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;

public class PrismaticJointProperties extends JointProperties {
	// final TextField localAnchorX,localAnchorY,
	final Slider localAxisAx, localAxisAy, lowerTranslation, upperTranslation, motorSpeed, maxMotorForce;

	 final CheckBox enableLimit,enableMotor;
	public PrismaticJointProperties() {
		super();
		// localAnchorX = new TextField("localAnchorX",skin);
		// localAnchorY = new TextField("localAnchorY",skin);
		 localAxisAx = new Slider(0,800,8,false,sliderStyle);
		 localAxisAy = new Slider(0,400,4,false,sliderStyle);
		 lowerTranslation = new Slider(0,100,1,false,sliderStyle);
		 upperTranslation = new Slider(0,100,1,false,sliderStyle);
		 motorSpeed = new Slider(0,100,1,false,sliderStyle);
		 maxMotorForce = new Slider(0,100,1,false,sliderStyle);
		//
		 enableLimit = new CheckBox("enableLimit",checkboxStyle);
		 enableMotor = new CheckBox("enableMotor",checkboxStyle);
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
		
		 this.add(new Label("lowerTranslation", labelStyle)).colspan(2);
		 this.add(this.lowerTranslation).colspan(2).fillX();
		 this.row();
		 this.add(new Label("upperTranslation", labelStyle)).colspan(2);
		 this.add(this.upperTranslation).colspan(2).fillX();
		 this.row();
		
		
		 this.add(new Label("enableMotor", labelStyle)).colspan(3);
		 this.add(enableMotor).colspan(1).fillX();
		 this.row();
		
		 this.add(new Label("motorSpeed", labelStyle)).colspan(2);
		 this.add(this.motorSpeed).colspan(2).fillX();
		 this.row();
		 this.add(new Label("maxMotorForce", labelStyle)).colspan(2);
		 this.add(this.maxMotorForce).colspan(2).fillX();
		 this.row();
		
		 this.add(new Label("localAxisA_x", labelStyle)).colspan(2);
		 this.add(this.localAxisAx).colspan(2).fillX();
		 this.row();
		 this.add(new Label("localAxisA_y", labelStyle)).colspan(2);
		 this.add(this.localAxisAy).colspan(2).fillX();
		 this.row();
		this.pack();
	}

	@Override
	public void update(Object data) {
		// bind(PrismaticJointData.class.cast(data).localAnchorA, "x",
		// localAnchorX);
		// bind(PrismaticJointData.class.cast(data).localAnchorA, "y",
		// localAnchorY);
		bind(PrismaticJointData.class.cast(data).localAxisA, "x", localAxisAx);
		bind(PrismaticJointData.class.cast(data).localAxisA, "y", localAxisAy);
		bind(data, "lowerTranslation", lowerTranslation);
		bind(data, "upperTranslation", upperTranslation);
		bind(data, "motorSpeed", motorSpeed);
		bind(data, "maxMotorForce", maxMotorForce);
		bind(data, "enableLimit", enableLimit);
		bind(data, "enableMotor", enableMotor);
		super.update(data);
	}
}

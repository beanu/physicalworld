package com.zhaoyunhe.pw.props.properties;

import info.u250.c2d.engine.Engine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public abstract class JointProperties extends EditorProperties {
	final CheckBox collideConnected;
	
	final TextureAtlas atlas;
	final SliderStyle sliderStyle;
	final LabelStyle labelStyle;
	final CheckBoxStyle checkboxStyle;

	public JointProperties() {
		atlas = Engine.resource("atlas");

		sliderStyle = new SliderStyle();
		sliderStyle.background = new NinePatchDrawable(new NinePatch(atlas.findRegion("slider_bg")));
		sliderStyle.knob = new TextureRegionDrawable(atlas.findRegion("slider_knob"));

		labelStyle = new LabelStyle(Engine.getDefaultFont(), new Color(1, 1, 1, 1));

		checkboxStyle = new CheckBoxStyle();
		checkboxStyle.checkboxOff = new TextureRegionDrawable(atlas.findRegion("toggle_off"));
		checkboxStyle.checkboxOn = new TextureRegionDrawable(atlas.findRegion("toggle_on"));
		checkboxStyle.font=Engine.getDefaultFont();
		checkboxStyle.fontColor=new Color(1, 1, 1, 1);
		
		collideConnected = new CheckBox("collideConnected", checkboxStyle);


	}

	public void update(Object data) {
		bind(data, "collideConnected", collideConnected);
	}
}

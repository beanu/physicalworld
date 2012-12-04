package com.zhaoyunhe.pw.props.properties;

import info.u250.c2d.engine.Engine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public abstract class JointProperties extends EditorProperties {
	final CheckBox collideConnected;
	// final TextField name;
	// final TextField type;
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
		// name = new TextField("name", skin);
		// type = new TextField("", "", skin);

		// this.add(new Label("name:", skin));
		// this.add(this.name).colspan(3).fillX();
		this.row();
		this.add(new Label("collideConnected", labelStyle)).colspan(3);
		this.add(this.collideConnected).colspan(1).fillX();
		this.pack();
	}

	public void update(Object data) {
		bind(data, "collideConnected", collideConnected);
		// bind(data, "name", name);
	}
}

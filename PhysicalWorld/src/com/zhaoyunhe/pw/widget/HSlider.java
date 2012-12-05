package com.zhaoyunhe.pw.widget;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;

public class HSlider extends Group {

	public Slider slider;
	Label labelTitle;
	
	public HSlider(CharSequence title,float min, float max, float stepSize, SliderStyle sliderStyle,LabelStyle labelStyle) {

		slider=new Slider(min, max, stepSize, true, sliderStyle);
		labelTitle=new Label(title, labelStyle);
		
		this.setSize(80, 160);
		slider.setPosition((this.getWidth()-slider.getPrefWidth())/2, this.labelTitle.getPrefHeight()+5);
		labelTitle.setPosition((this.getWidth()-labelTitle.getPrefWidth())/2, 0);
		
		this.addActor(labelTitle);
		this.addActor(slider);
	}

}

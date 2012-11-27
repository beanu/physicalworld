package com.zhaoyunhe.pw.props.properties;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class CircleBodyProperties extends BodyProperties{
	final TextField radius;
	public CircleBodyProperties(){
		super();
		radius = new TextField("radius",skin);
		this.add(new Label("radius", skin));
		this.add(this.radius).colspan(3).fillX();
		this.row();
		this.pack();
	}
	@Override
	public void update(Object data) {
		bind(data, "radius", radius);
		super.update(data);
	}
}

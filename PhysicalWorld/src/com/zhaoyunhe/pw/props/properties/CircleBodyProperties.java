package com.zhaoyunhe.pw.props.properties;


public class CircleBodyProperties extends BodyProperties{
//	final TextField radius;
	public CircleBodyProperties(){
		super();
//		radius = new TextField("radius",skin);
//		this.add(new Label("radius", skin));
//		this.add(this.radius).colspan(3).fillX();
//		this.row();
		this.pack();
	}
	@Override
	public void update(Object data) {
//		bind(data, "radius", radius);
		super.update(data);
	}
}

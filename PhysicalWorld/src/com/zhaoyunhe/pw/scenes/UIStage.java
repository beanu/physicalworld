package com.zhaoyunhe.pw.scenes;

import info.u250.c2d.graphic.C2dStage;

import com.zhaoyunhe.pw.IScene;
import com.zhaoyunhe.pw.ui.ShapePanel;

public class UIStage extends C2dStage {

	private ShapePanel shapePanel;

	public UIStage(IScene scene) {
		shapePanel = new ShapePanel(scene);
		shapePanel.setPosition(0, 10);
		this.addActor(shapePanel);
	}

}

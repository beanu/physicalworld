package com.zhaoyunhe.pw.scenes;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.graphic.C2dStage;

import com.zhaoyunhe.pw.IScene;
import com.zhaoyunhe.pw.ui.JointPanel;
import com.zhaoyunhe.pw.ui.ShapePanel;

public class UIStage extends C2dStage {

	private ShapePanel shapePanel;
	private JointPanel jointPanel;

	public UIStage(IScene scene) {
		shapePanel = new ShapePanel(scene);
		shapePanel.setPosition(0, 10);
		
		jointPanel=new JointPanel();
		jointPanel.setPosition(Engine.getEngineConfig().width-jointPanel.getWidth(), 0);
		
		this.addActor(shapePanel);
		this.addActor(jointPanel);
	}

}

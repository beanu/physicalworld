package com.zhaoyunhe.pw.scenes;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.events.Event;
import info.u250.c2d.engine.events.EventListener;
import info.u250.c2d.graphic.C2dStage;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.zhaoyunhe.pw.Events;
import com.zhaoyunhe.pw.props.Box2dAdapter;
import com.zhaoyunhe.pw.ui.JointPanel;
import com.zhaoyunhe.pw.ui.ShapePanel;

public class UIStage extends C2dStage {

	private ShapePanel shapePanel;
	private JointPanel jointPanel;

	public UIStage(Box2dAdapter adapter) {
		shapePanel = new ShapePanel(adapter);
		shapePanel.setPosition(0, Engine.getEngineConfig().height-shapePanel.getHeight());
		
		jointPanel=new JointPanel(adapter);
		jointPanel.setPosition(Engine.getEngineConfig().width, 0);
		
		this.addActor(shapePanel);
		this.addActor(jointPanel);
		
		Engine.getEventManager().register(Events.MOVE_JOINT_PANEL, new EventListener() {
			
			@Override
			public void onEvent(Event event) {
				boolean show=(Boolean) event.getSource();
				if(show){
					jointPanel.addAction(Actions.moveBy(-100, 0, 0.2f));
				}else{
					jointPanel.addAction(Actions.moveBy(100, 0, 0.2f));
				}
			}
		});
	}

}

package com.zhaoyunhe.pw.scenes;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.events.Event;
import info.u250.c2d.engine.events.EventListener;
import info.u250.c2d.graphic.C2dStage;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.zhaoyunhe.pw.Events;
import com.zhaoyunhe.pw.props.Box2dAdapter;
import com.zhaoyunhe.pw.ui.JointPanel;
import com.zhaoyunhe.pw.ui.PropertiesPanel;
import com.zhaoyunhe.pw.ui.ShapePanel;

public class UIStage extends C2dStage {

	private ShapePanel shapePanel;
	private JointPanel jointPanel;
	private PropertiesPanel propertiesPanel;

	public UIStage(Box2dAdapter adapter) {
		shapePanel = new ShapePanel(adapter);
		shapePanel.setPosition(0, Engine.getHeight()-shapePanel.getHeight());
		
		jointPanel=new JointPanel(adapter);
		jointPanel.setPosition(Engine.getWidth(), 0);
		
		propertiesPanel=new PropertiesPanel();
		propertiesPanel.setPosition(0, -propertiesPanel.getHeight());
		
		this.addActor(shapePanel);
		this.addActor(jointPanel);
		this.addActor(propertiesPanel);
		
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
		
		Engine.getEventManager().register(Events.MOVE_PROPERTIES_PANEL, new EventListener() {
			
			@Override
			public void onEvent(Event event) {
				boolean show=(Boolean) event.getSource();
				if(show){
					if(!propertiesPanel.hasShowed()){
						propertiesPanel.addAction(Actions.moveBy(0, propertiesPanel.getHeight(), 0.2f));
						propertiesPanel.setShowed(true);
					}
				}else{
					if(propertiesPanel.hasShowed()){
						propertiesPanel.addAction(Actions.moveBy(0, -propertiesPanel.getHeight(), 0.2f));
						propertiesPanel.setShowed(false);
					}
				}
			}
		});
	}

}

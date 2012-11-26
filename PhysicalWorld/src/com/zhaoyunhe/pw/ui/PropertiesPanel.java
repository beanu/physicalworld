package com.zhaoyunhe.pw.ui;

import info.u250.c2d.engine.Engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class PropertiesPanel extends Group {
	private TextureAtlas atlas;
	private PropertyBoard densityAndRadius;
	private PropertyBoard frictionAndRestitution;
	
	public PropertiesPanel() {
		atlas = Engine.resource("atlas");
		TextureRegion bg=atlas.findRegion("board");
		TextureRegion cursor=atlas.findRegion("cursor");
		densityAndRadius=new PropertyBoard(bg, cursor);
		frictionAndRestitution=new PropertyBoard(bg, cursor);
		
		int space=20;
		densityAndRadius.setPosition(0, 0);
		frictionAndRestitution.setPosition(densityAndRadius.getX()+densityAndRadius.getWidth()+space, 0);
		this.addActor(densityAndRadius);
		this.addActor(frictionAndRestitution);
		
		this.setHeight(densityAndRadius.getHeight());
	}

	static class PropertyBoard extends Group {
		private Image bg;
		private Image cursor;
		
		private Vector2 vector=new Vector2();
		private Image touchedImage;
		
		public PropertyBoard(TextureRegion bgRegion,TextureRegion cursorRegion) {
			this.bg = new Image(bgRegion);
			this.cursor = new Image(cursorRegion);
			this.addListener(new InputListener(){

				@Override
				public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
					Actor actor=hit(x, y, true);
					if(actor!=null && actor instanceof Image && actor==cursor){
						touchedImage=(Image)actor;
					}
					return true;
				}

				@Override
				public void touchUp(InputEvent event, float x, float y,int pointer, int button) {
					touchedImage=null;
				}

				@Override
				public void touchDragged(InputEvent event, float x, float y,int pointer) {
					if(touchedImage!=null){
						if(x>=0 && y>=0){
							if(x<getWidth() && y<getHeight()){
								Gdx.app.debug("debug", "x:"+x+" y:"+y);
								touchedImage.setPosition(x-touchedImage.getWidth()/2, y-touchedImage.getHeight()/2);
							}
							if(x>=getWidth() && y<getHeight()){
								touchedImage.setPosition(getWidth()-touchedImage.getWidth()/2, y-touchedImage.getHeight()/2);
							}
							if(x<getWidth() && y>=getHeight()){
								touchedImage.setPosition(x-touchedImage.getWidth()/2, getHeight()-touchedImage.getHeight()/2);
							}
						}
					}
				}
				
			});
		

			this.addActor(bg);
			this.addActor(cursor);
			this.setSize(bg.getWidth(), bg.getHeight());
		}

		public Vector2 getVector() {
			return vector;
		}

		public void setVector(Vector2 vector) {
			this.vector = vector;
		}
		
	}
}

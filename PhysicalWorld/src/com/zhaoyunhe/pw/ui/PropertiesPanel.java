package com.zhaoyunhe.pw.ui;

import info.u250.c2d.engine.Engine;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class PropertiesPanel extends Group {
	TextureAtlas atlas;

	public PropertiesPanel() {
		atlas = Engine.resource("atlas");
	}

	class PropertyBoard extends Group {
		private Image bg;
		private Image cursor;
		private int x;
		private int y;

		public PropertyBoard() {
			this.bg = new Image(atlas.findRegion("board"));
			this.cursor = new Image(atlas.findRegion("cursor"));
			this.cursor.addListener(new InputListener() {

				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					// TODO Auto-generated method stub
					return super.touchDown(event, x, y, pointer, button);
				}

				@Override
				public void touchUp(InputEvent event, float x, float y,
						int pointer, int button) {
					
					super.touchUp(event, x, y, pointer, button);
				}

				@Override
				public void touchDragged(InputEvent event, float x, float y,
						int pointer) {
					// TODO Auto-generated method stub
					super.touchDragged(event, x, y, pointer);
				}

			});

			this.addActor(bg);
			this.addActor(cursor);
		}

	}
}

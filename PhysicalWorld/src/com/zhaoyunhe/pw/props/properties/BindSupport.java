package com.zhaoyunhe.pw.props.properties;

import java.lang.reflect.Field;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class BindSupport {
	Object obj;
	Actor inputField;
	Field field;
	String name;
	
	public void update(Object object){
		if(object == null) return ;
		this.obj = object;
		try {
			try{
				//first
				field = obj.getClass().getDeclaredField(name);
			}catch(Exception ex){
				try{
					//second
					field = obj.getClass().getSuperclass().getDeclaredField(name);
				}catch(Exception e){
					//third
					field = obj.getClass().getSuperclass().getSuperclass().getDeclaredField(name);
				}
			}
			if(inputField instanceof TextField){
				TextField.class.cast(inputField).setText(field.get(obj)+"");
			}else if(inputField instanceof CheckBox){
				CheckBox.class.cast(inputField).setChecked(Boolean.parseBoolean(field.get(obj)+""));
			}else if(inputField instanceof Slider){
				Slider.class.cast(inputField).setValue(Float.parseFloat(field.get(obj)+""));
			}
			
		}catch(Exception ex){ex.printStackTrace();}
	}
	public BindSupport(final Object object,final String name,final Actor widget){
		this.name = name;
		this.inputField = widget;
		if(inputField instanceof TextField){
			TextField.class.cast(inputField).setTextFieldListener(new TextFieldListener() {
				@Override
				public void keyTyped(TextField textField, char key) {
					try {
						if(field.getType() == float.class || field.getType() == Float.class)
							field.set(obj, Float.parseFloat(textField.getText()));
						else if(field.getType() == int.class||field.getType() == Integer.class)
							field.set(obj, Integer.parseInt(textField.getText()));
						else if(field.getType() == String.class)
							field.set(obj,textField.getText());
					} catch (Exception e) {
//						e.printStackTrace();
					}
				}
			});
		}else if(inputField instanceof CheckBox){
			CheckBox.class.cast(inputField).addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					try {
						field.set(obj, CheckBox.class.cast(inputField).isChecked());
					} catch (Exception e) {
//						e.printStackTrace();
					}
				}
			});
		}else if(inputField instanceof Slider){
			Slider.class.cast(inputField).addListener(new InputListener(){

				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;
				}

				@Override
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					try {
						field.set(obj, Slider.class.cast(inputField).getValue());
						Gdx.app.debug("value", field.getName()+":"+field.get(obj).toString());
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}

				}
				
			});
		}
		this.update(object);
	}
}

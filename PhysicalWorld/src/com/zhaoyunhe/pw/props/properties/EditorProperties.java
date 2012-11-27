package com.zhaoyunhe.pw.props.properties;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public abstract class EditorProperties extends Table {

	Map<String, BindSupport> bindMap = new HashMap<String, BindSupport>();

	/**
	 * 
	 * @param obj the BoxData
	 * @param name get the boxData field 
	 * @param board the board that hold the data
	 */
	void bind(final Object obj, final String name,final Actor board) {
		final BindSupport support = bindMap.get(name);
		if (null != support) {
			support.update(obj);
		} else {
			bindMap.put(name, new BindSupport(obj, name, board));
		}
	}

	public abstract void update(Object object);
}

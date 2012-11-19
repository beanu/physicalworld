package com.zhaoyunhe.pw;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.EngineDrive;
import info.u250.c2d.engine.resources.AliasResourceManager;

import com.zhaoyunhe.pw.scenes.SceneMain;

public class PWEngineDrive implements EngineDrive {

	@Override
	public EngineOptions onSetupEngine() {
		final EngineOptions opt = new EngineOptions(new String[] {}, 800, 480);
		return opt;
	}

	@Override
	public void onLoadedResourcesCompleted() {

		SceneMain main=new SceneMain();
		Engine.setMainScene(main);
	}

	@Override
	public void dispose() {

	}

	@Override
	public void onResourcesRegister(AliasResourceManager<String> reg) {

	}

}

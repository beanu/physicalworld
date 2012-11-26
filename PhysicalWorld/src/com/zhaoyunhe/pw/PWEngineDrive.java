package com.zhaoyunhe.pw;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.EngineDrive;
import info.u250.c2d.engine.resources.AliasResourceManager;

import com.zhaoyunhe.pw.scenes.ScenePlay;

public class PWEngineDrive implements EngineDrive {

	@Override
	public EngineOptions onSetupEngine() {
		final EngineOptions opt = new EngineOptions(new String[] {"data/"}, 800, 480);
		opt.autoResume = true;
		return opt;
	}

	@Override
	public void onLoadedResourcesCompleted() {

		ScenePlay main = new ScenePlay();
		Engine.setMainScene(main);
	}

	@Override
	public void dispose() {

	}

	@Override
	public void onResourcesRegister(AliasResourceManager<String> reg) {
		reg.textureAtlas("atlas", "data/t.atlas");
		reg.texture("CircleTexture", "data/circle.png");
		reg.texture("BoxTexture", "data/box.png");
		
//		reg.font("gameFont", "data/kingthings.fnt");
//		reg.sound("boom", "data/sound/missile-explosion.wav");
//		reg.music("countdown", "data/music/countdown.mp3");
	}

}

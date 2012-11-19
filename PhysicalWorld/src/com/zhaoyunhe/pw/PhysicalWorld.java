package com.zhaoyunhe.pw;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.EngineDrive;

public class PhysicalWorld extends Engine {

	@Override
	protected EngineDrive onSetupEngineDrive() {
		return new PWEngineDrive();
	}

}

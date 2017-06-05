package com.w4ll3.cade.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.w4ll3.cade.CADe;

public class DesktopLauncher {

	private static final int MINWIDTH = 640;
	private static final int MAXWIDTH = 1920;
	private static final int MINHEIGHT = 360;
	private static final int MAXHEIGHT = 1080;

	public static void main (String[] arg) {

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowSizeLimits(MINWIDTH, MINHEIGHT, MAXWIDTH, MAXHEIGHT);
		config.useVsync(true);
		config.setTitle("CADe");
		config.setMaximized(true);
		config.setResizable(true);
		new Lwjgl3Application(new CADe(), config);

	}
}

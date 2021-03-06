package main;

import javax.swing.SwingUtilities;

import com.doa.engine.DoaEngine;
import com.doa.engine.DoaWindow;
import com.doa.engine.graphics.DoaSprites;
import com.doa.engine.scene.DoaSceneHandler;

import gameplay.LevelLoader;
import util.Builders;
import util.Sprites;

public class Main {

	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;

	public static final int INNER_WIDTH = 1264;
	public static final int INNER_HEIGHT = 681;

	private static final DoaWindow w = DoaWindow.createWindow();
	private static final DoaEngine e = new DoaEngine();

	public static void main(final String[] args) {
		Sprites.initializeSprites();

		/* new Thread(() -> { for (int i = 255; i > 200; i--) { DoaLights.ambientLight(new Color(i - 55, i -
		 * 55, i)); DoaUtils.sleepFor(500); } }).start(); */

		DoaSceneHandler.loadScene(DoaSceneHandler.createScene("gameScene"));

		LevelLoader.loadLevel(DoaSprites.ORIGINAL_SPRITES.get("mapData"));

		Builders.SB.instantiate();
		Builders.HB.instantiate();

		SwingUtilities.invokeLater(Main::configureGUI);
	}

	private static void configureGUI() {
		w.setTitle("Java Zombie Survival!");
		w.setSize(WIDTH, HEIGHT);
		w.setResizable(false);
		w.setLocationByPlatform(true);
		w.setVisible(true);
		w.add(e);
	}
}

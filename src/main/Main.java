package main;

import java.awt.Color;

import javax.swing.SwingUtilities;

import com.doa.engine.DoaEngine;
import com.doa.engine.DoaWindow;
import com.doa.engine.graphics.DoaLights;
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
		DoaLights.ambientLight(new Color(100, 100, 200));

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

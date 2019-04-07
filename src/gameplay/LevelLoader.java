package gameplay;

import java.awt.Color;

import com.doa.engine.DoaCamera;
import com.doa.engine.DoaHandler;
import com.doa.engine.graphics.DoaSprite;

import ui.Level;

public class LevelLoader {

	public static final int BLOCK_WIDTH = 32;
	public static final int BLOCK_HEIGHT = 32;

	private LevelLoader() {}

	public static void loadLevel(final DoaSprite map) {
		DoaHandler.clear();
		final int w = map.getWidth();
		final int h = map.getHeight();
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				Color pixelColor = new Color(map.getRGB(x, y));
				final int red = pixelColor.getRed();
				final int green = pixelColor.getGreen();
				final int blue = pixelColor.getBlue();
				if (pixelColor.equals(Color.WHITE)) {
					DoaHandler.instantiateDoaObject(Wall.class, x * BLOCK_WIDTH, y * BLOCK_HEIGHT);
				} else if (green == 255 && blue == 255) {
					DoaHandler.instantiateDoaObject(Player.class, (float) x * BLOCK_WIDTH, (float) y * BLOCK_HEIGHT);
				} else if (red == 255 && blue == 255) {
					DoaHandler.instantiateDoaObject(EnemySpawner.class, (float) x * BLOCK_WIDTH, (float) y * BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);
				}
			}
		}
		DoaHandler.instantiateDoaObject(Level.class);
		DoaCamera.adjustCamera(Player.getInstance(), 0, 0, map.getWidth() * BLOCK_WIDTH + BLOCK_WIDTH, map.getHeight() * BLOCK_HEIGHT + BLOCK_HEIGHT);
	}
}

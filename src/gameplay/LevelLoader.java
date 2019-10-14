package gameplay;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.doa.engine.DoaCamera;
import com.doa.engine.scene.DoaObject;
import com.doa.engine.scene.DoaSceneHandler;

import util.Builders;

public class LevelLoader {

	public static final int BLOCK_WIDTH = 32;
	public static final int BLOCK_HEIGHT = 32;

	private LevelLoader() {}

	public static void loadLevel(final BufferedImage map) {
		DoaSceneHandler.getLoadedScene().clear();
		final int w = map.getWidth();
		final int h = map.getHeight();
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				Color pixelColor = new Color(map.getRGB(x, y));
				final int red = pixelColor.getRed();
				final int green = pixelColor.getGreen();
				final int blue = pixelColor.getBlue();
				DoaObject o = null;
				if (pixelColor.equals(Color.WHITE)) {
					o = Builders.WB.args((float) x * BLOCK_WIDTH, (float) y * BLOCK_HEIGHT).instantiate();
				} else if (green == 255 && blue == 255) {
					o = Builders.PB.args((float) x * BLOCK_WIDTH, (float) y * BLOCK_HEIGHT).instantiate();
				} else if (red == 255 && blue == 255) {
					o = Builders.ESB.args((float) x * BLOCK_WIDTH, (float) y * BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT).instantiate();
				}
				if (o != null) {
					Collision.add(o);
				}
			}
		}
		Builders.LB.instantiate();
		DoaCamera.adjustCamera(Player.getInstance(), 0, 0, map.getWidth() * BLOCK_WIDTH, map.getHeight() * BLOCK_HEIGHT);
		DoaCamera.setTweenAmountX(.01f);
		DoaCamera.setTweenAmountY(.01f);
	}
}

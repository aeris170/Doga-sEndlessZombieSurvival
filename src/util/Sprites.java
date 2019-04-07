package util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.io.IOException;

import com.doa.engine.graphics.DoaSprites;

import main.Main;

public final class Sprites {

	public static void initializeSprites() {
		try {
			DoaSprites.createSpriteFromSpriteSheet("PlayerSprite", "/KenneyAssets/Spritesheet/spritesheet_characters.png", new Rectangle(2, 132, 51, 43));
			DoaSprites.createSpriteFromSpriteSheet("EnemySprite", "/KenneyAssets/Spritesheet/spritesheet_characters.png", new Rectangle(426, 0, 33, 43));
			DoaSprites.createSprite("map", "/level-test2-bg.png");
			DoaSprites.createSprite("mapData", "/level-test2.png");
			Font customFont = Font.createFont(Font.TRUETYPE_FONT, Main.class.getResourceAsStream("/sojaAssets/soupofjustice.ttf")).deriveFont(12f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(customFont);
		} catch (FontFormatException | IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		} /* try { SpriteSheet = ImageIO.read(Sprites.class.getResourceAsStream(
		   * "/KenneyAssets/Spritesheet/spritesheet_characters.png")); PlayerSprite =
		   * SpriteSheet.getSubimage(2, 132, 51, 43); EnemySprite =
		   * SpriteSheet.getSubimage(426, 0, 33, 43); Level2Background =
		   * ImageIO.read(Sprites.class.getResourceAsStream("/level-test2-bg.png")); for
		   * (int xx = 0; xx < PlayerSprite.getWidth(); xx++) { for (int yy = 0; yy <
		   * PlayerSprite.getHeight(); yy++) { Color objColor = new
		   * Color(PlayerSprite.getRGB(xx, yy), true); int r = (objColor.getRed() * 100 /
		   * 255); int g = (objColor.getGreen() * 100 / 255); int b = (objColor.getBlue()
		   * * 200 / 255); int a = (objColor.getAlpha()); PlayerSprite.setRGB(xx, yy, new
		   * Color(r, g, b, a).getRGB()); } } for (int xx = 0; xx <
		   * EnemySprite.getWidth(); xx++) { for (int yy = 0; yy <
		   * EnemySprite.getHeight(); yy++) { Color objColor = new
		   * Color(EnemySprite.getRGB(xx, yy), true); int r = (objColor.getRed() * 100 /
		   * 255); int g = (objColor.getGreen() * 100 / 255); int b = (objColor.getBlue()
		   * * 200 / 255); int a = (objColor.getAlpha()); EnemySprite.setRGB(xx, yy, new
		   * Color(r, g, b, a).getRGB()); } } for (int xx = 0; xx <
		   * Level2Background.getWidth(); xx++) { for (int yy = 0; yy <
		   * Level2Background.getHeight(); yy++) { Color objColor = new
		   * Color(Level2Background.getRGB(xx, yy), true); int r = (objColor.getRed() *
		   * 100 / 255); int g = (objColor.getGreen() * 100 / 255); int b =
		   * (objColor.getBlue() * 200 / 255); int a = (objColor.getAlpha());
		   * Level2Background.setRGB(xx, yy, new Color(r, g, b, a).getRGB()); } } } catch
		   * (final IOException ex) { ex.printStackTrace(); System.exit(-1); } */
	}

	private Sprites() {}
}

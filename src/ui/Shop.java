package ui;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

import com.doa.engine.graphics.DoaGraphicsContext;
import com.doa.engine.input.DoaMouse;
import com.doa.engine.scene.DoaObject;

import gameplay.EnemySpawner;
import main.Main;

public class Shop extends DoaObject {

	private static final long serialVersionUID = -2410862007259184762L;

	private static final float X = 75;
	private static final float Y = 25;
	private static final int SHOP_WIDTH = Main.INNER_WIDTH - 150;
	private static final int SHOP_HEIGHT = Main.INNER_HEIGHT - 125;

	private static final Rectangle CLOSE_SHOP_BUTTON = new Rectangle((int) X + SHOP_WIDTH - 120, (int) Y + SHOP_HEIGHT - 60, 100, 40);

	private static boolean visible = false;

	public Shop() {
		super(X, Y, SHOP_WIDTH, SHOP_HEIGHT, 1);
		setFixed(true);
	}

	public static void show() {
		visible = true;
	}

	public static void hide() {
		visible = false;
	}

	public static boolean isVisible() {
		return visible;
	}

	@Override
	public void tick() {
		if (DoaMouse.MB1 && getBounds().intersects(new Rectangle((int) DoaMouse.X, (int) DoaMouse.Y, 1, 1))) {
			final Point mousePoint = new Point((int) DoaMouse.X, (int) DoaMouse.Y);
			if (CLOSE_SHOP_BUTTON.contains(mousePoint)) {
				hide();
				EnemySpawner.Difficulty++;
				EnemySpawner.EnemiesLeftToSpawn = (int) Math.exp(EnemySpawner.Difficulty);
				EnemySpawner.EnemiesLeftInWave = EnemySpawner.EnemiesLeftToSpawn;
			}
		}
	}

	@Override
	public void render(final DoaGraphicsContext g) {
		if (visible) {
			g.setColor(new Color(200, 200, 200, 100));
			g.fillRect(X, Y, SHOP_WIDTH, SHOP_HEIGHT);
			g.setColor(Color.YELLOW.darker());
			g.fill(CLOSE_SHOP_BUTTON);
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) X, (int) Y, SHOP_WIDTH, SHOP_HEIGHT);
	}
}

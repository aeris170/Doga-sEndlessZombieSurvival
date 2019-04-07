package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;

import com.doa.engine.DoaObject;
import com.doa.engine.graphics.DoaGraphicsContext;

import gameplay.EnemySpawner;
import gameplay.Player;
import main.Main;

public class HUD extends DoaObject {

	private static final long serialVersionUID = -2852967212992605846L;

	private static final Color TRANSLUCENT_GRAY = new Color(Color.GRAY.getRed(), Color.GRAY.getGreen(), Color.GRAY.getBlue(), 127);
	private static final Color TRANSLUCENT_GREEN = new Color(Color.GREEN.getRed(), Color.GREEN.getGreen(), Color.GREEN.getBlue(), 127);
	private static final Color TRANSLUCENT_DARK_GREEN = new Color(Color.GREEN.darker().darker().darker().getRed(), Color.GREEN.darker().darker().darker().getGreen(),
	        Color.GREEN.darker().darker().darker().getBlue(), 200);
	private static final Color GRAY = new Color(74, 74, 74);
	private static final Color ORANGE = new Color(232, 106, 23);

	private static final String COINS = "Coins: ";
	private static final String LEVEL = "Level: ";
	private static final String SCORE = "Score: ";

	public HUD() {
		super(0f, 0f, DoaObject.STATIC_FRONT);
	}

	@Override
	public void tick() {}

	@Override
	public void render(final DoaGraphicsContext g) {
		int windowWidth = Main.WIDTH;
		int windowHeight = Main.HEIGHT;
		final AffineTransform oldTransform = g.getTransform();
		g.rotate(Math.PI, Main.WIDTH / 2d, Main.HEIGHT / 2d);
		g.setColor(TRANSLUCENT_GRAY);
		g.fillRect(windowWidth - 60 - 3d, 50 - 3d, 50 + 6d, Player.getInstance().getHealthMAX() + 6d);
		g.setColor(TRANSLUCENT_GREEN);
		g.fillRect(windowWidth - 60d, 50, 50, Player.getInstance().getHealth());
		final Stroke oldStroke = g.getStroke();
		g.setStroke(new BasicStroke(4));
		g.setColor(TRANSLUCENT_DARK_GREEN);
		g.drawRect(windowWidth - 60d, 50, 50, Player.getInstance().getHealth());
		g.setStroke(oldStroke);
		g.setTransform(oldTransform);

		g.setFont(new Font("Soup of Justice", Font.BOLD, 32));

		g.setColor(GRAY);
		g.drawString(COINS + Player.getInstance().getCoins(), 69, windowHeight - 111d);
		g.drawString(COINS + Player.getInstance().getCoins(), 69, windowHeight - 109d);
		g.drawString(COINS + Player.getInstance().getCoins(), 71, windowHeight - 111d);
		g.drawString(COINS + Player.getInstance().getCoins(), 71, windowHeight - 109d);
		g.drawString(LEVEL + EnemySpawner.getDifficulty(), 69, windowHeight - 81d);
		g.drawString(LEVEL + EnemySpawner.getDifficulty(), 69, windowHeight - 79d);
		g.drawString(LEVEL + EnemySpawner.getDifficulty(), 71, windowHeight - 81d);
		g.drawString(LEVEL + EnemySpawner.getDifficulty(), 71, windowHeight - 79d);
		g.drawString(SCORE + Player.getInstance().getScore(), 69, windowHeight - 51d);
		g.drawString(SCORE + Player.getInstance().getScore(), 69, windowHeight - 49d);
		g.drawString(SCORE + Player.getInstance().getScore(), 71, windowHeight - 51d);
		g.drawString(SCORE + Player.getInstance().getScore(), 71, windowHeight - 49d);

		g.setColor(ORANGE);
		g.drawString(COINS + Player.getInstance().getCoins(), 70, windowHeight - 110d);
		g.drawString(LEVEL + EnemySpawner.getDifficulty(), 70, windowHeight - 80d);
		g.drawString(SCORE + Player.getInstance().getScore(), 70, windowHeight - 50d);
	}

	@Override
	public Shape getBounds() {
		return null;
	}
}

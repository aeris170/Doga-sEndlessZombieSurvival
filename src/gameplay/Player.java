package gameplay;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import com.doa.engine.DoaCamera;
import com.doa.engine.graphics.DoaGraphicsContext;
import com.doa.engine.graphics.DoaSprites;
import com.doa.engine.input.DoaKeyboard;
import com.doa.engine.input.DoaMouse;
import com.doa.engine.task.DoaTaskGuard;
import com.doa.engine.task.DoaTasker;
import com.doa.maths.DoaVectorF;

import util.Builders;

public class Player extends TypedGameObject {

	private static final long serialVersionUID = -5588139160019846890L;

	private static final double SIZE_FACTOR = 0.9;

	private static Player INSTANCE = null;

	private transient BulletSpecs bs = new BulletSpecs();
	private DoaTaskGuard cooldown = new DoaTaskGuard(true);
	private double health = 100;
	private double healthMAX = 100;
	private int coins = 0;
	private int score = 0;
	private double angleRad = 0;

	public Player(final float x, final float y) {
		super(x, y);
		super.type = ObjectType.PLAYER;
		INSTANCE = this;
		width = (int) (DoaSprites.get("PlayerSprite").getWidth() * SIZE_FACTOR);
		height = (int) (DoaSprites.get("PlayerSprite").getHeight() * SIZE_FACTOR);
		bs.setWidth(16);
		bs.setHeight(16);
		bs.setColor(Color.RED);
		bs.setCooldown(1000);
		bs.setVelocity(2f);
		bs.setDamage(200);
		// bs.setPiercing(true);
	}

	@Override
	public void tick() {
		final float oldX = position.x;
		final float oldY = position.y;
		if (DoaKeyboard.A) {
			velocity.x = -1;
		} else if (DoaKeyboard.D) {
			velocity.x = 1;
		} else {
			velocity.x = 0;
		}
		position.x += velocity.x;
		if (Collision.checkCollision(this, ObjectType.OBSTACLE)) {
			position.x = oldX;
		}
		if (DoaKeyboard.W) {
			velocity.y = -1;
		} else if (DoaKeyboard.S) {
			velocity.y = 1;
		} else {
			velocity.y = 0;
		}
		position.y += velocity.y;
		if (Collision.checkCollision(this, ObjectType.OBSTACLE)) {
			position.y = oldY;
		}
		if (DoaMouse.MB1_HOLD && cooldown.get()) {
			DoaTasker.guard(cooldown, bs.getCooldown());
			final float mx = (float) (DoaMouse.X + DoaCamera.getX());
			final float my = (float) (DoaMouse.Y + DoaCamera.getY());
			DoaVectorF bulletStartingPosition = new DoaVectorF(position.x + width / 2f + bs.getWidth() / 2f, position.y + height / 2f + bs.getHeight() / 2f)
			        .translate(-(position.x + width / 2f), -(position.y + height / 2f)).rotateRadians(angleRad).translate(position.x + width / 2f, position.y + height / 2f);
			Builders.BB.args(bulletStartingPosition.x - bs.getWidth() / 2f, bulletStartingPosition.y - bs.getHeight() / 2f, mx, my, bs).instantiate();
		}

		final TypedGameObject[] touchingEnemies = Collision.getCollidingObjects(this, ObjectType.ENEMY);
		health -= touchingEnemies.length * 0.2;

		angleRad = Math.atan2(DoaMouse.Y + DoaCamera.getY() - position.y, DoaMouse.X + DoaCamera.getX() - position.x);
	}

	@Override
	public void render(final DoaGraphicsContext g) {
		final AffineTransform oldTransform = g.getTransform();
		g.rotate(angleRad, position.x + width / 2d, position.y + height / 2d);
		g.drawImage(DoaSprites.get("PlayerSprite"), position.x, position.y, width, height, null);
		g.setTransform(oldTransform);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) position.x - 1, (int) position.y - 1, width - 2, height - 2);
	}

	public static Player getInstance() {
		return INSTANCE;
	}

	public BulletSpecs getBulletSpecs() {
		return bs;
	}

	public int getHealth() {
		return (int) health;
	}

	public int getHealthMAX() {
		return (int) healthMAX;
	}

	public void setHealthMAX(int newMax) {
		double percentage = health / healthMAX;
		healthMAX = newMax;
		health = healthMAX * percentage;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(final int coins) {
		this.coins = coins;
	}

	public int getScore() {
		return score;
	}

	public void setScore(final int score) {
		this.score = score;
	}
}

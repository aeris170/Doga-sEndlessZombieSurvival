package gameplay;

import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.doa.engine.DoaHandler;
import com.doa.engine.graphics.DoaGraphicsContext;
import com.doa.engine.graphics.DoaSprites;
import com.doa.maths.DoaVectorF;

public class Enemy extends TypedGameObject {

	private static final long serialVersionUID = -3839835030750716763L;

	private int health;
	private List<Bullet> touchedBullets = new CopyOnWriteArrayList<>();
	private double angleRad = 0;

	public Enemy(final Float x, final Float y, final Integer width, final Integer height, final Integer health) {
		super(x, y, width, height);
		super.type = ObjectType.ENEMY;
		this.health = health;
	}

	@Override
	public void tick() {
		final float oldX = position.x;
		final float oldY = position.y;
		velocity = Player.getInstance().getPosition().clone().sub(position.clone()).normalise();
		DoaVectorF velCopy = velocity.clone();
		velocity.x *= Math.random() * 2;
		velocity.y *= Math.random() * 2;
		position.x += velocity.x;
		if (Collision.checkCollision(this, ObjectType.OBSTACLE) || Collision.checkCollision(this, ObjectType.ENEMY)) {
			position.x = oldX;
		}
		position.y += velocity.y;
		if (Collision.checkCollision(this, ObjectType.OBSTACLE) || Collision.checkCollision(this, ObjectType.ENEMY)) {
			position.y = oldY;
		}
		angleRad = Math.atan2(velCopy.y, velCopy.x);
	}

	@Override
	public void render(final DoaGraphicsContext g) {
		final AffineTransform oldTransform = g.getTransform();
		g.rotate(angleRad, position.x + width / 2d, position.y + height / 2d);
		g.drawImage(DoaSprites.get("EnemySprite"), position.x, position.y, width, height, null);
		g.setTransform(oldTransform);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) position.x, (int) position.y, width, height);
	}

	public void getHit(final Bullet b) {
		if (!touchedBullets.contains(b)) {
			health -= Player.getInstance().getBulletSpecs().getDamage();
			if (health < 0) {
				DoaHandler.remove(this);
				EnemySpawner.enemyDied();
				Player.getInstance().setCoins(Player.getInstance().getCoins() + (int) Math.max(1, Math.ceil(EnemySpawner.getDifficulty() / Math.PI)));
				Player.getInstance().setScore((int) (Player.getInstance().getScore() + EnemySpawner.getDifficulty() * Math.PI));
			} else {
				touchedBullets.add(b);
			}
		}
	}
}

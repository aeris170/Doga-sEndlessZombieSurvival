package gameplay;

import java.awt.Rectangle;
import java.util.Arrays;

import com.doa.engine.graphics.DoaGraphicsContext;
import com.doa.engine.scene.DoaSceneHandler;
import com.doa.engine.task.DoaTasker;

import util.Builders;

public class Bullet extends TypedGameObject {

	private static final long serialVersionUID = -4315362367824405514L;

	private transient BulletSpecs bs;

	public Bullet(final Float x, final Float y, final Float mx, final Float my, final BulletSpecs bs) {
		super(x, y);
		super.type = ObjectType.PROJECTILE;
		velocity.x = mx - x;
		velocity.y = my - y;
		this.bs = bs;
		velocity = velocity.normalise().mul(bs.getVelocity());
		DoaTasker.executeLater(() -> deleteBullet(), 1000);
	}

	@Override
	public synchronized void tick() {
		position.add(velocity);
		if (!bs.isBouncing() && Collision.checkCollision(this, ObjectType.OBSTACLE)) {
			deleteBullet();
		}
		final TypedGameObject[] possibleHitEnemies = Collision.getCollidingObjects(this, ObjectType.ENEMY);
		if (!bs.isPiercing()) {
			if (possibleHitEnemies.length > 0) {
				((Enemy) possibleHitEnemies[0]).getHit(this);
				deleteBullet();
			}
		} else {
			Arrays.stream(possibleHitEnemies).forEach(enemy -> ((Enemy) enemy).getHit(this));
		}
	}

	@Override
	public synchronized void render(final DoaGraphicsContext g) {
		g.setColor(bs.getColor());
		g.fillOval(position.x, position.y, bs.getWidth(), bs.getHeight());
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) position.x, (int) position.y, bs.getWidth(), bs.getHeight());
	}

	private void deleteBullet() {
		DoaSceneHandler.getLoadedScene().remove(this);
		Collision.remove(this);
	}

	public static void createBullet(BulletSpecs bs, final float x, final float y, final float mx, final float my) {
		Collision.add(Builders.BB.args(x, y, mx, my, bs).instantiate());
	}
}

package gameplay;

import java.awt.Rectangle;
import java.util.Arrays;

import com.doa.engine.DoaHandler;
import com.doa.engine.graphics.DoaGraphicsContext;
import com.doa.engine.task.DoaTasker;

public class Bullet extends TypedGameObject {

	private static final long serialVersionUID = -4315362367824405514L;

	private transient BulletSpecs bs = ((Player) DoaHandler.getGameObjects().stream().parallel().filter(object -> object instanceof Player).findFirst().get())
	        .getBulletSpecs();

	public Bullet(final Float x, final Float y, final Float mx, final Float my) {
		super(x, y);
		super.type = ObjectType.PROJECTILE;
		velocity.x = mx - x;
		velocity.y = my - y;
		velocity = velocity.normalise().mul(bs.getVelocity());
		DoaTasker.executeLater(() -> DoaHandler.remove(this), 1000);
	}

	@Override
	public synchronized void tick() {
		position.add(velocity);
		if (!bs.isBouncing() && Collision.checkCollision(this, ObjectType.OBSTACLE)) {
			DoaHandler.remove(this);
		}
		final TypedGameObject[] possibleHitEnemies = Collision.getCollidingObjects(this, ObjectType.ENEMY);
		if (!bs.isPiercing()) {
			if (possibleHitEnemies.length > 0) {
				((Enemy) possibleHitEnemies[0]).getHit(this);
				DoaHandler.remove(this);
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
}

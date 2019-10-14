package gameplay;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.concurrent.ThreadLocalRandom;

import com.doa.engine.graphics.DoaGraphicsContext;
import com.doa.engine.task.DoaTaskGuard;
import com.doa.engine.task.DoaTasker;
import com.doa.utils.DoaUtils;

import ui.Shop;
import util.Builders;

public class EnemySpawner extends TypedGameObject {

	private static final long serialVersionUID = -5257482521631706854L;

	private static final int MAX_ALLOWED_ENEMY_AT_ONCE = 50;
	private static final int BASE_COOLDOWN_TIME = 1000;
	private static final double ENEMY_SIZE_FACTOR = 0.95;

	public static int EnemiesPresent = 0;
	public static int EnemiesLeftToSpawn = (int) Math.ceil(Math.E);
	public static int EnemiesLeftInWave = EnemiesLeftToSpawn;
	public static int Difficulty = 1;

	private DoaTaskGuard cooldownGuard = new DoaTaskGuard(false);

	public EnemySpawner(final Float x, final Float y, final Integer width, final Integer height) {
		super(x, y);
		super.height = width;
		super.width = height;
		super.type = ObjectType.MUTATOR;
		DoaTasker.executeLater(() -> cooldownGuard.set(true), 1000 + ThreadLocalRandom.current().nextLong(5000));
	}

	@Override
	public void tick() {
		if (cooldownGuard.get() && EnemiesPresent < MAX_ALLOWED_ENEMY_AT_ONCE && EnemiesLeftToSpawn > 0) {
			DoaTasker.guard(cooldownGuard, BASE_COOLDOWN_TIME + (ThreadLocalRandom.current().nextLong(4000)));
			Collision.add(Builders.EB.args(position.x, position.y, (int) (width * ENEMY_SIZE_FACTOR), (int) (height * ENEMY_SIZE_FACTOR), 50).instantiate());
			EnemiesPresent++;
			EnemiesLeftToSpawn--;
		} else if (EnemiesLeftInWave == 0 && cooldownGuard.get()) {
			cooldownGuard.set(false);
			Shop.show();
			DoaTasker.executeNow(() -> {
				while (Shop.isVisible()) {
					DoaUtils.sleepFor(100);
				}
				DoaTasker.executeLater(() -> cooldownGuard.set(true), 1000 + ThreadLocalRandom.current().nextLong(5000));
			});
		}
	}

	@Override
	public void render(final DoaGraphicsContext g) {
		g.setColor(Color.MAGENTA);
		g.drawOval(position.x, position.y, width, height);
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

	public static void enemyDied() {
		EnemiesPresent--;
		EnemiesLeftInWave--;
	}

	public static int getDifficulty() {
		return Difficulty;
	}
}

package util;

import com.doa.engine.scene.DoaObjectBuilder;

import gameplay.Bullet;
import gameplay.Enemy;
import gameplay.EnemySpawner;
import gameplay.Player;
import gameplay.Wall;
import ui.HUD;
import ui.Level;
import ui.Shop;

public final class Builders {

	public static final DoaObjectBuilder<Bullet> BB = new DoaObjectBuilder<>(Bullet.class);
	public static final DoaObjectBuilder<Enemy> EB = new DoaObjectBuilder<>(Enemy.class);
	public static final DoaObjectBuilder<EnemySpawner> ESB = new DoaObjectBuilder<>(EnemySpawner.class);
	public static final DoaObjectBuilder<Wall> WB = new DoaObjectBuilder<>(Wall.class);
	public static final DoaObjectBuilder<Player> PB = new DoaObjectBuilder<>(Player.class);

	public static final DoaObjectBuilder<HUD> HB = new DoaObjectBuilder<>(HUD.class);
	public static final DoaObjectBuilder<Level> LB = new DoaObjectBuilder<>(Level.class);
	public static final DoaObjectBuilder<Shop> SB = new DoaObjectBuilder<>(Shop.class);

	private Builders() {}
}

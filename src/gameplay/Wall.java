package gameplay;

import java.awt.Rectangle;

import com.doa.engine.graphics.DoaGraphicsContext;

public class Wall extends TypedGameObject {

	private static final long serialVersionUID = -1823772727435109215L;

	public Wall(final Integer x, final Integer y) {
		super(x, y);
		super.type = ObjectType.OBSTACLE;
	}

	@Override
	public void tick() {}

	@Override
	public void render(final DoaGraphicsContext g) {}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) position.x, (int) position.y, LevelLoader.BLOCK_WIDTH, LevelLoader.BLOCK_HEIGHT);
	}
}

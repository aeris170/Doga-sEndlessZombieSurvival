package ui;

import java.awt.Shape;

import com.doa.engine.graphics.DoaGraphicsContext;
import com.doa.engine.graphics.DoaSprites;
import com.doa.engine.scene.DoaObject;

public class Level extends DoaObject {

	private static final long serialVersionUID = -3444176980562935927L;

	public Level() {
		super(0f, 0f, -1);
	}

	@Override
	public void tick() {}

	@Override
	public void render(final DoaGraphicsContext g) {
		g.drawImage(DoaSprites.get("map"), position.x, position.y, null);
	}

	@Override
	public Shape getBounds() {
		return null;
	}
}

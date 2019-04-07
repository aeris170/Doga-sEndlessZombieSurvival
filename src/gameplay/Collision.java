package gameplay;

import java.awt.geom.Rectangle2D;
import java.util.List;

import com.doa.engine.DoaHandler;
import com.doa.engine.DoaObject;

public class Collision {

	private Collision() {}

	public static boolean checkCollision(final TypedGameObject object, final ObjectType type) {
		return checkCollision(object, type, DoaHandler.getGameObjects());
	}

	public static TypedGameObject[] getCollidingObjects(final TypedGameObject object, final ObjectType type) {
		return getCollidingObjects(object, type, DoaHandler.getGameObjects());
	}

	public static boolean checkCollision(final TypedGameObject object, final ObjectType type, final List<DoaObject> others) {
		return others.stream().anyMatch(otherObject -> ((TypedGameObject) otherObject).getType() == type && object != otherObject
		        && object.getBounds().intersects((Rectangle2D) otherObject.getBounds()));
	}

	public static TypedGameObject[] getCollidingObjects(final TypedGameObject object, final ObjectType type, final List<DoaObject> others) {
		return others.stream().filter(otherObject -> ((TypedGameObject) otherObject).getType() == type && object != otherObject
		        && object.getBounds().intersects((Rectangle2D) otherObject.getBounds())).toArray(TypedGameObject[]::new);
	}
}

package gameplay;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import com.doa.engine.scene.DoaObject;

public class Collision {

	private static final List<DoaObject> OBJECTS = new ArrayList<>();

	private Collision() {}

	public static boolean checkCollision(final TypedGameObject object, final ObjectType type) {
		return checkCollision(object, type, OBJECTS);
	}

	public static TypedGameObject[] getCollidingObjects(final TypedGameObject object, final ObjectType type) {
		return getCollidingObjects(object, type, OBJECTS);
	}

	public static boolean checkCollision(final TypedGameObject object, final ObjectType type, final List<DoaObject> others) {
		return others.stream().anyMatch(
		        otherObject -> ((TypedGameObject) otherObject).getType() == type && object != otherObject && object.getBounds().intersects((Rectangle2D) otherObject.getBounds()));
	}

	public static TypedGameObject[] getCollidingObjects(final TypedGameObject object, final ObjectType type, final List<DoaObject> others) {
		return others.stream().filter(
		        otherObject -> ((TypedGameObject) otherObject).getType() == type && object != otherObject && object.getBounds().intersects((Rectangle2D) otherObject.getBounds()))
		        .toArray(TypedGameObject[]::new);
	}

	public static void add(DoaObject o) {
		OBJECTS.add(o);
	}

	public static void remove(DoaObject o) {
		OBJECTS.remove(o);
	}
}

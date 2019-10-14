package gameplay;

import com.doa.engine.scene.DoaObject;

public abstract class TypedGameObject extends DoaObject {

	private static final long serialVersionUID = -6747630116426199740L;

	protected ObjectType type;

	public TypedGameObject(final float x, final float y) {
		super(x, y);
	}

	public TypedGameObject(final float x, final float y, final int width, final int height) {
		super(x, y, width, height);
	}

	public ObjectType getType() {
		return type;
	}

}

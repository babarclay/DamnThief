package collidables;
import java.util.HashSet;

public class CollidableSet<T extends Collidable> extends HashSet<T>{

	private static final long serialVersionUID = 5302672596981180727L;

	public boolean collidesWith(Collidable collidable) {
		boolean intersects = false;
		for (Collidable c : this) {
			if (c.intersects(collidable)) {
				intersects = true;
			}
		}
		return intersects;
	}
}

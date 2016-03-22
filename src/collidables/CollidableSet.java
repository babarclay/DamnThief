package collidables;

import java.util.HashSet;

public class CollidableSet extends HashSet<Collidable> {

	private static final long serialVersionUID = -3674335840924743885L;

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

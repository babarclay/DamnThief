package collidables;

import org.newdawn.slick.geom.Shape;

public interface Collidable {
	Shape getBoundingBox();
	boolean intersects(Collidable other);
	boolean intersects(Shape shape);
}

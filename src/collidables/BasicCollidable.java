package collidables;

import org.newdawn.slick.geom.Shape;

public class BasicCollidable implements Collidable{

	private Shape shape;
	public BasicCollidable(Shape s){
		this.shape = s;
	}
	
	@Override
	public Shape getBoundingBox() {
		return shape;
	}

	@Override
	public boolean intersects(Collidable other) {
		return shape.intersects(other.getBoundingBox());
	}


}

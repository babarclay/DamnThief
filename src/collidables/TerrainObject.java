package collidables;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class TerrainObject implements Collidable{

	private static final Point OBJECT_SPAWN_POINT = new Point(200, 300);
	private static final String OBJECT_IMAGE_LOCATION = "resources/terrainObject.png";

	private Image objectImage;
	private Point objectLocation;

	public TerrainObject() {
		try {
			objectImage = new Image(OBJECT_IMAGE_LOCATION);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		objectLocation = OBJECT_SPAWN_POINT;
	}

	public Image getPlayerImage() {
		return objectImage;
	}

	public float getX() {
		return objectLocation.getX();
	}

	public float getY() {
		return objectLocation.getY();
	}

	public void setX(float x) {
		objectLocation.setX(x);
	}

	public void setY(float y) {
		objectLocation.setY(y);
	}

	public void draw() {
		objectImage.draw(objectLocation.getX(), objectLocation.getY());
	}

	public Shape getBoundingBox() {
		return new Rectangle(getX(), getY(), objectImage.getWidth(),
				objectImage.getHeight());
	}

	public boolean intersects(Collidable other) {
		return getBoundingBox().intersects(other.getBoundingBox());
	}
}

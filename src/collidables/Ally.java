package collidables;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Ally implements Collidable {

	private static final String ALLY_IMAGE_LOCATION = "resources/ally.png";

	private Image image;
	private Point location;
	private Point waypoint;
	private boolean selected;

	public Ally(Point spawnPoint) {
		try {
			image = new Image(ALLY_IMAGE_LOCATION);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		location = spawnPoint;
		waypoint = location;
		selected = false;
	}

	public void update(Input input) {
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			Point mousePoint = new Point(input.getMouseX(), input.getMouseY());
			this.isSelected(mousePoint);
		}
		if (input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON)) {
			Point mousePoint = new Point(input.getMouseX(), input.getMouseY());
			this.setAllyWaypoint(mousePoint);
		}
		this.moveAlly();
	}

	public Image getAllyImage() {
		return image;
	}

	public float getX() {
		return location.getX();
	}

	public float getY() {
		return location.getY();
	}

	public void setX(float x) {
		location.setX(x);
	}

	private void setY(float y) {
		location.setY(y);
	}

	public void setAllyWaypoint(Point waypoint) {
		this.waypoint = waypoint;
	}

	public void moveAlly() {
		if (selected) {
			if (location.getX() < waypoint.getX()) {
				setX(location.getX() + 1);
			}
			if (location.getX() > waypoint.getX()) {
				setX(location.getX() - 1);
			}
			if (location.getY() < waypoint.getY()) {
				setY(location.getY() + 1);
			}
			if (location.getY() > waypoint.getY()) {
				setY(location.getY() - 1);
			}
		}
	}

	public void draw() {
		image.draw(location.getX(), location.getY());
	}

	public boolean isSelected(Point mousePoint) {
		Rectangle selection = new Rectangle(mousePoint.getX() - 10,
				mousePoint.getY() - 10, 20, 20);
		if (selection.intersects(getBoundingBox())) {
			selected = true;
		} else {
			selected = false;
		}
		return selected;
	}

	@Override
	public Shape getBoundingBox() {
		return new Rectangle(getX(), getY(), image.getWidth(),
				image.getHeight());
	}

	@Override
	public boolean intersects(Collidable other) {
		return getBoundingBox().intersects(other.getBoundingBox());
	}

}

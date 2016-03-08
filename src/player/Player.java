package player;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Player {

	private static final Point PLAYER_SPAWN_POINT = new Point(100, 100);
	private static final String PLAYER_IMAGE_LOCATION = "resources/player.png";

	private Image playerImage;
	private Point playerLocation;

	public Player() {
		try {
			playerImage = new Image(PLAYER_IMAGE_LOCATION);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		playerLocation = PLAYER_SPAWN_POINT;
	}

	public Image getPlayerImage() {
		return playerImage;
	}

	public float getX() {
		return playerLocation.getX();
	}

	public float getY() {
		return playerLocation.getY();
	}

	public void setX(float x) {
		playerLocation.setX(x);
	}

	public void setY(float y) {
		playerLocation.setY(y);
	}

	public void draw() {
		playerImage.draw(playerLocation.getX(), playerLocation.getY());
	}

	public Shape getBoundingBox() {
		return new Rectangle(getX(), getY(), playerImage.getWidth(),
				playerImage.getHeight());
	}
}

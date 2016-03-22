package collidables;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Ally implements Collidable {

	private static final Point ALLY_SPAWN_POINT = new Point(200, 100);
	private static final String ALLY_IMAGE_LOCATION = "resources/ally.png";
	
	private Image allyImage;
	private Point allyLocation;
	private Point allyWaypoint;
	
	public Ally(){
		try {
			allyImage = new Image(ALLY_IMAGE_LOCATION);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		allyLocation = ALLY_SPAWN_POINT;
		allyWaypoint = allyLocation;
	
	}
	
	public Image getAllyImage(){
		return allyImage;
	}
	
	public float getX() {
		return allyLocation.getX();
	}

	public float getY() {
		return allyLocation.getY();
	}

	public void setX(float x) {
		allyLocation.setX(x);
	}

	private void setY(float y) {
		allyLocation.setY(y);
	}
	
	public void setAllyWaypoint(Point waypoint){
		this.allyWaypoint = waypoint;
	}
	
	public void moveAlly(){
		if(allyLocation.getX() < allyWaypoint.getX()){
			setX(allyLocation.getX() + 1);
		}
		if(allyLocation.getX() > allyWaypoint.getX()){
			setX(allyLocation.getX() - 1);
		}
		if(allyLocation.getY() < allyWaypoint.getY()){
			setY(allyLocation.getY() + 1);
		}
		if(allyLocation.getY() > allyWaypoint.getY()){
			setY(allyLocation.getY() - 1);
		}
	}
	
	public void draw() {
		allyImage.draw(allyLocation.getX(), allyLocation.getY());
	}
	
	@Override
	public Shape getBoundingBox() {
		return new Rectangle(getX(), getY(), allyImage.getWidth(),
				allyImage.getHeight());
	}

	@Override
	public boolean intersects(Collidable other) {
		return getBoundingBox().intersects(other.getBoundingBox());
	}

}

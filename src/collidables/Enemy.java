package collidables;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Enemy implements Collidable {

	private static final String ENEMY_IMAGE_LOCATION = "resources/enemy.png";
	
	private Image enemyImage;
	private Point enemyLocation;
	private Point enemyWayPoint;
	
	public static enum EnemySpawn{
		TOP(new Point(250,10)), LEFT(new Point(20,230)), RIGHT(new Point(450,230)), BOTTOM(new Point(250,450));
		
		private final Point location;
		private EnemySpawn(Point p){
			this.location = p;
		}
		
		private static EnemySpawn getRandom(){
			int index = (int)(Math.random()*(EnemySpawn.values().length));
			return EnemySpawn.values()[index];
		}
	
	}
	
	public Enemy(){
		try {
			EnemySpawn spawn = EnemySpawn.getRandom();
			enemyLocation = new Point(spawn.location.getX(), spawn.location.getY());
			enemyImage=  new Image(ENEMY_IMAGE_LOCATION);
			enemyWayPoint = new Point(40,100);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	
	public Enemy(EnemySpawn spawn){
		try {
			enemyLocation = new Point(spawn.location.getX(), spawn.location.getY());
			enemyImage=  new Image(ENEMY_IMAGE_LOCATION);
			enemyWayPoint = new Point(40,100);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public float getX(){
		return enemyLocation.getX();
	}
	
	public float getY(){
		return enemyLocation.getY();
	}
	
	public void draw(){
		enemyImage.draw(enemyLocation.getX(), enemyLocation.getY());
	}
	
	public void setX(float x){
		this.enemyLocation.setX(x);
	}
	
	public void setY(float y){
		this.enemyLocation.setY(y);
	}
	
	public void setWayPoint(Point enemyWayPoint){
		this.enemyWayPoint = enemyWayPoint;
	}
	
	public void moveEnemy(){
		if(enemyLocation.getX() < enemyWayPoint.getX()){
			setX(enemyLocation.getX() + 1);
		}
		if(enemyLocation.getX() > enemyWayPoint.getX()){
			setX(enemyLocation.getX() - 1);
		}
		if(enemyLocation.getY() < enemyWayPoint.getY()){
			setY(enemyLocation.getY() + 1);
		}
		if(enemyLocation.getY() > enemyWayPoint.getY()){
			setY(enemyLocation.getY() - 1);
		}
	}
	
	@Override
	public Shape getBoundingBox() {
		return new Rectangle(getX(), getY(), enemyImage.getWidth(), enemyImage.getHeight());
	}

	@Override
	public boolean intersects(Collidable other) {
		return this.getBoundingBox().intersects(other.getBoundingBox());
	}

}

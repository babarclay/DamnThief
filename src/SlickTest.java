import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import collidables.Ally;
import collidables.BasicCollidable;
import collidables.Collidable;
import collidables.CollidableSet;
import collidables.Enemy;
import collidables.Player;
import collidables.TerrainObject;
import map.Room;

public class SlickTest extends BasicGame {
	Player player;
	Ally ally;
	Ally ally2;
	Point mousePoint;
	Shape mouseSelect;

	CollidableSet<Ally> allies;
	TerrainObject object;
	TerrainObject object2;
	Room room;
	CollidableSet<Collidable> terrainCollidables;
	CollidableSet<Enemy> enemies;

	public SlickTest() {
		super("SimpleTest");
	}

	@Override
	public void init(GameContainer container) throws SlickException {

		// this code is supposed to help the game run smoothly at any framerate
		container.setVSync(true);
		container.setTargetFrameRate(60);
		// initialize objects
		room = new Room();
		player = new Player();
		allies = new CollidableSet<Ally>();
		ally = new Ally(new Point(200, 100));
		ally2 = new Ally(new Point(250, 100));
		allies = new CollidableSet<Ally>();
		allies.add(new Ally(new Point(100, 150)));
		allies.add(ally);
		allies.add(ally2);

		object = new TerrainObject();
		object2 = new TerrainObject();
		enemies = new CollidableSet<Enemy>();
		enemies.add(new Enemy());

		terrainCollidables = new CollidableSet<Collidable>();
		terrainCollidables.add(object);
		terrainCollidables.add(object2);
		object2.setX(300);

		mousePoint = new Point(0, 0);
		mouseSelect = new Rectangle(mousePoint.getX(), mousePoint.getY(), 20,
				20);
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		playerUpdate(container.getInput(), delta);
		allyUpdate(container.getInput());
		enemyUpdate();
		handleEnemyCollisionsWithAlly();
		handleEnemyCollisionsWithPlayer();
		mouseUpdate(container.getInput());

	}

	private void mouseUpdate(Input input) {
		if (input.isMouseButtonDown(input.MOUSE_LEFT_BUTTON)) {
			mouseSelect = new Rectangle(input.getMouseX() - 10,
					input.getMouseY() - 10, 20, 20);
		}
		if (input.isMouseButtonDown(input.MOUSE_RIGHT_BUTTON)) {
			mousePoint = new Point(input.getMouseX(), input.getMouseY());
		}
	}

	private void allyUpdate(Input input) {
		for (Ally ally : allies) {
			ally.update(input);
		}
	}

	private void enemyUpdate() {
		for (Enemy enemy : enemies) {
			enemy.setWayPoint(new Point(player.getX(), player.getY()));
			enemy.moveEnemy();
		}
	}

	private void playerUpdate(Input input, int delta) {
		int speed = 100;
		float distance = speed * ((float) delta / 1000);

		// these values will be used if there is a collision
		float futurePlayerX = player.getX();
		float futurePlayerY = player.getY();

		if (input.isKeyDown(Input.KEY_LEFT)) {
			futurePlayerX -= distance;
		}
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			futurePlayerX += distance;
		}
		if (input.isKeyDown(Input.KEY_UP)) {
			futurePlayerY -= distance;
		}
		if (input.isKeyDown(Input.KEY_DOWN)) {
			futurePlayerY += distance;
		}

		BasicCollidable futurePlayerShape = new BasicCollidable(
				player.getFutureShape(futurePlayerX, futurePlayerY));
		BasicCollidable futurePlayerXShape = new BasicCollidable(
				player.getFutureXShape(futurePlayerX));
		BasicCollidable futurePlayerYShape = new BasicCollidable(
				player.getFutureYShape(futurePlayerY));
		if (collidesWithRoomOrTerrain(futurePlayerShape)) {
		}
		if (collidesWithRoomOrTerrain(futurePlayerXShape)) {
			if (!collidesWithRoomOrTerrain(futurePlayerYShape)) {
				player.setY(futurePlayerY);
			}
		} else if (collidesWithRoomOrTerrain(futurePlayerYShape)) {
			if (!collidesWithRoomOrTerrain(futurePlayerXShape)) {
				player.setX(futurePlayerX);
			}
		} else {
			player.setX(futurePlayerX);
			player.setY(futurePlayerY);
		}
	}

	private boolean collidesWithRoomOrTerrain(Collidable c) {
		return room.collidesWith(c) || terrainCollidables.collidesWith(c);
	}

	private void handleEnemyCollisionsWithAlly() {
		for (Enemy enemy : enemies) {
			if (allies.collidesWith(enemy)) {
				enemy.setX(0);
				enemy.setY(0);
			}
		}
	}

	private void handleEnemyCollisionsWithPlayer() {
		for (Enemy enemy : enemies) {
			if (enemy.intersects(player)) {
				enemy.setX(0);
				enemy.setY(0);
			}
		}
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		room.draw();
		object.draw();
		object2.draw();
		player.draw();
		for (Enemy enemy : enemies) {
			enemy.draw();
		}
		for (Ally ally : allies) {
			ally.draw();
		}
		for (Ally ally : allies) {
			ally.draw();
		}
		g.draw(mouseSelect);
	}

	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new SlickTest());
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
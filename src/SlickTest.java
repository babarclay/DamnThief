import map.Room;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

import collidables.BasicCollidable;
import collidables.Collidable;
import collidables.CollidableSet;
import collidables.Player;
import collidables.TerrainObject;

public class SlickTest extends BasicGame {
	Player player;
	TerrainObject object;
	TerrainObject object2;
	Room room;
	CollidableSet terrainCollidables;

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
		object = new TerrainObject();
		object2 = new TerrainObject();
		terrainCollidables = new CollidableSet();
		terrainCollidables.add(object);
		terrainCollidables.add(object2);
		object2.setX(300);
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		playerUpdate(container, delta);
	}

	private void playerUpdate(GameContainer container, int delta) {
		Input input = container.getInput();
		int speed = 200;
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
		if (collidesWithRoomOrTerrain(futurePlayerShape)){}
			if (collidesWithRoomOrTerrain(futurePlayerXShape)) {
				if (!collidesWithRoomOrTerrain(futurePlayerYShape)) {
					player.setY(futurePlayerY);
				}
			} else if (collidesWithRoomOrTerrain(futurePlayerYShape)) {
				if (!collidesWithRoomOrTerrain(futurePlayerXShape)) {
					player.setX(futurePlayerX);
				}
			} 
			else {
				player.setX(futurePlayerX);
				player.setY(futurePlayerY);
			}
	}

	private boolean collidesWithRoomOrTerrain(Collidable c) {
		return room.collidesWith(c) || terrainCollidables.collidesWith(c);
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		g.drawString("Hello, Slick world!", 0, 100);
		room.draw();
		object.draw();
		object2.draw();
		player.draw();
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
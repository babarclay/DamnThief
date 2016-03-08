import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

import collidables.Collidable;
import collidables.Player;
import collidables.TerrainObject;

public class SlickTest extends BasicGame {
	SpriteSheet dungeonTiles;
	TiledMap prototypeRoom;
	Player player;
	TerrainObject object;
	ArrayList<Collidable> collidables;
	public SlickTest() {
		super("SimpleTest");
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		
		//this code is supposed to help the game run smoothly at any framerate
		container.setVSync(true);
		container.setTargetFrameRate(60);
		
		dungeonTiles = new SpriteSheet("resources/sheet.png", 304, 208);
		prototypeRoom = new TiledMap("resources/prototypeRoom.tmx");
		player = new Player();
		object = new TerrainObject();
		collidables = new ArrayList<Collidable>();
		collidables.add(player);
		collidables.add(object);
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		Input input = container.getInput();
		int speed = 200;
		float distance = speed * ((float) delta / 1000);
		
		//these values will be used if there is a collision
		float futurePlayerX = player.getX();
		float futurePlayerY = player.getY();
		
		if (input.isKeyDown(Input.KEY_LEFT)) {
			futurePlayerX-= distance;
		}
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			futurePlayerX+=distance;
		}
		if (input.isKeyDown(Input.KEY_UP)) {
			futurePlayerY-= distance;
		}
		if (input.isKeyDown(Input.KEY_DOWN)) {
			futurePlayerY+=distance;
		}
		if(player.getFutureShape(futurePlayerX, futurePlayerY).intersects(object.getBoundingBox())){
			if(player.getFutureXShape(futurePlayerX).intersects(object.getBoundingBox())){
				if(!object.intersects(player.getFutureYShape(futurePlayerY))){
					player.setY(futurePlayerY);
				}
			}
			else if(object.intersects(player.getFutureYShape(futurePlayerY))){
				if(!object.intersects(player.getFutureXShape(futurePlayerX))){
					player.setX(futurePlayerX);
				}
			}
		}
		else{
			player.setX(futurePlayerX);
			player.setY(futurePlayerY);
		}
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		g.drawString("Hello, Slick world!", 0, 100);
		dungeonTiles.draw(0, 0, 2);
		prototypeRoom.render(0, 0);
		object.draw();
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
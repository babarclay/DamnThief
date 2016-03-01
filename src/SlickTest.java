
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class SlickTest extends BasicGame {
	SpriteSheet dungeonTiles;
	
	public SlickTest() {
		super("SimpleTest");
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		dungeonTiles = new SpriteSheet("resources/dungeonTiles.png",304,208);
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		g.drawString("Hello, Slick world!", 0, 100);
		dungeonTiles.draw(0, 0,2);
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
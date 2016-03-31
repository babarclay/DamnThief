package map;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

import collidables.Collidable;
import collidables.CollidableSet;

public class Room {

	private final int tileScale = 2;
	private final int tileWidth = 16*tileScale;
	private final int tileHeight = 16*tileScale;
	private SpriteSheet dungeonTiles;
	private TiledMap prototypeRoom;
	private CollidableSet wallTiles;
	
	public Room() {
		try {
			dungeonTiles = new SpriteSheet("resources/sheet.png", 304, 208);
			prototypeRoom = new TiledMap("resources/prototypeRoom.tmx");
			wallTiles = refreshCollidables();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	private class CollidableTile implements Collidable{

		private int x;
		private int y;
		
		CollidableTile(int x, int y){
			this.x = x*tileWidth;
			this.y = y*tileHeight;
		}
		
		@Override
		public Shape getBoundingBox() {
			return new Rectangle(x,y,tileWidth,tileHeight);
		}

		@Override
		public boolean intersects(Collidable other) {
			return this.getBoundingBox().intersects(other.getBoundingBox());
		}
	}
	
	private CollidableSet refreshCollidables(){
		 CollidableSet set = new CollidableSet();
		 int width = prototypeRoom.getWidth();
			int height = prototypeRoom.getHeight();
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					int tileID = prototypeRoom.getTileId(x, y, 0);
					String blocked = prototypeRoom.getTileProperty(tileID,
							"Blocked", "false");
					if(blocked.equals("True")){
						set.add(new CollidableTile(x,y));
					}
				}
			}
		 return set;
	}
	
	public boolean collidesWith(Collidable c){
		return wallTiles.collidesWith(c);
	}
	
	public void draw() {
		prototypeRoom.render(0, 0);
	}
	
}

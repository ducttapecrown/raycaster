import java.awt.Color;

/**
 * A single tile on a Map.
 *
 * @author Sam Lindbloom-Airey, Tim Stoddard
 * @version program07
 */

public class Tile {

   public static final int SPACE = 0, WALL = 1, FINISH = 2, SHOW_PATH = 3;
   private int type;

   /**
    * Create a tile.
    * 
    * @param type the type to create the tile with.
    */
   public Tile(int type) {
      this.type = type;
   }

   /**
    * Get the type of the tile.
    * 
    * @return the type of the tile.
    */
   public int getType() {
      return type;
   }

   /**
    * Get the Color of the tile.
    * 
    * @param cheatMode true if the game is in cheatmode,
    * false otherwise.
    * @return the color to display the tile with on the 
    * minimap.
    */
   public Color getColor(boolean cheatMode) {
      switch (type) {
         case SPACE:
            return Color.white;
         case WALL:
            return Color.black;
         case FINISH:
            return Color.red;
         case SHOW_PATH:
            return cheatMode ? Color.yellow : Color.white;
      }
      return Color.green;
   }
}

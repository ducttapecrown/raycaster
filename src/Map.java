/**
 * The map of the engine class.
 * 
 * @author Sam Lindbloom-Airey, Tim Stoddard
 * @version program07
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Map {

   private int level;
   private Tile[][] map;

   /**
    * Creates a new map of level 1.
    */
   public Map() {
      this(1);
   }

   /**
    * Creates a new map with the specified level.
    * 
    * @param level
    */
   public Map(int level) {
      this.level = level;
      read(level);
   }

   /**
    * Returns the width of the map.
    * 
    * @return - the width of the map
    */
   public int getWidth() {
      return map[0].length;
   }

   /**
    * Returns the height of the map.
    * 
    * @return - the height of the map
    */
   public int getHeight() {
      return map.length;
   }

   /**
    * Checks if x, y is in bounds of map.
    * 
    * @return true if in bounds, false otherwise
    */
   public boolean inBounds(double x, double y) {
      return ((x > 0 && x < this.getWidth()) && (y > 0 && y < getHeight()));
   }

   /**
    * Returns the tile at the requested row and column on the map.
    * 
    * @return - the tile at the requested row and column on the map
    */
   public Tile getTile(int row, int col) {
      return map[col][row];
   }

   /**
    * Returns the int number of the current level.
    * 
    * @return the int number of the current level
    */
   public int getLevel() {
      return level;
   }

   /** 
    * Convenience method for reading in the level that the map
    * is currently set to.
    */
   public void read() {
      read(level);
   }

   /**
    * Reads levels in from their text files.
    *
    * @param level the number of the level to read in
    */
   public void read(int level) {
      BufferedReader in;
      try {
         in = new BufferedReader(new FileReader(new File("").getAbsolutePath()
               + "/resources/level" + level + ".txt"));
         String[] dimensions = in.readLine().split("x");
         map = new Tile[Integer.parseInt(dimensions[0])][Integer
               .parseInt(dimensions[1])];
         for (int i = 0; i < map.length; i++) {
            String[] data = in.readLine().split("");
            for (int j = 0; j < map[i].length; j++) {
               map[i][j] = new Tile(Integer.parseInt(data[j]));
            }
         }
         /*
          * for (Tile[] sammy : map) { for (Tile sam : sammy) {
          * System.out.print( sam.getType() == 0 ? "  " : // empty space
          * (sam.getType() == 1 ?"##" : // wall (sam.getType() == 2 ? "!!" : //
          * endpoint "^^"))); // solution } System.out.println(); }//
          */
      } catch (FileNotFoundException e) {
         System.out.println("File not found.");
      } catch (IOException e) {
         System.out.println("IO Error occurred.");
      }
   }

}

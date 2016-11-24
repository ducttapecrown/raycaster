<<<<<<< HEAD
=======
/**
 * Reads, writes, and stores all the scores
 * 
 * @author Tim Stoddard
 * @version program007
 */

>>>>>>> b24443d84dc3914a1262b556cb69db2a3bad61b7
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Reads, writes, and stores all the scores
 * 
 * @author Tim Stoddard
 * @version program007
 */

public class Scores {

   private ArrayList<Score> scores;

   /**
    * Constructs an object in which to hold all the scores.
    */
   public Scores() {
      scores = new ArrayList<Score>();
      read();
   }

   /**
    * Gets the specified number of high scores, or all high scores if there are
    * less than the specified number. Returns in String format.
    * 
    * @return the specified number of top high scores
    */
   public String getHighScores(int howMany) {
      if (howMany < 0) {
         throw new ArrayIndexOutOfBoundsException(
               "negative index: " + howMany);
      }
      String text = "";
      int num = Math.min(howMany, scores.size());
      for (int i = 0; i < num; i++) {
         text += scores.get(i).score + " (" + scores.get(i).name + ")\n";
      }
      return text;
   }

   /**
    * Calculates a score based on the time parameter.
    * 
    * @param ms
    *           - The time to complete the level, in milliseconds
    * @return The score of the level
    */
   public int calcScore(long ms) {
      return Math.max(
            (int) (42069420 / (ms / 1000.0 + (1.0 / Integer.MAX_VALUE))), 0);
   }

   /**
    * Adds a new high score to the list. The score must be between
    * 
    * @param newScore
    *           - score that was earned.
    * @param name
    *           - name associated with the score
    */
   public void addNewHighScore(int newScore, String name) {
      scores.add(new Score(newScore, name));

      // selection sort
      for (int i = 0; i < scores.size(); i++) {
         int maxPos = i;
         for (int j = i + 1; j < scores.size(); j++) {
            if (scores.get(j).compareTo(scores.get(maxPos)) < 0) {
               maxPos = j;
            }
         }
         Score temp = scores.get(i);
         scores.set(i, scores.get(maxPos));
         scores.set(maxPos, temp);
      }

      // write to file
      BufferedWriter out;
      try {
         out = new BufferedWriter(new FileWriter(
               new File("").getAbsolutePath() + "/src/high_scores.txt"));
         for (int i = 0; i < scores.size(); i++) {
            out.write(scores.get(i).score + "," + scores.get(i).name + "\n");
         }
         out.close();
      } catch (FileNotFoundException e) {
         System.out.println("File not found.");
      } catch (IOException e) {
         System.out.println("IO Error occurred.");
      }
   }

   /**
    * Reads the score data from the text file.
    */
   private void read() {
      BufferedReader in;
      try {
         in = new BufferedReader(new FileReader(
               new File("").getAbsolutePath() + "/src/high_scores.txt"));
         ArrayList<String> data = new ArrayList<String>();
         String text;
         while ((text = in.readLine()) != null) {
            data.add(text);
         }
         in.close();
         for (int i = 0; i < data.size(); i++) {
            String[] tempData = data.get(i).split(",");
            scores.add(new Score(Integer.parseInt(tempData[0]), tempData[1]));
         }
      } catch (FileNotFoundException e) {
         System.out.println("File not found.");
      } catch (IOException e) {
         System.out.println("IO Error occurred.");
      } catch (java.lang.NumberFormatException e) {
         System.out.println("Score was too large.");
      }
   }

   /**
    * Represents a score object. Has a score and a name associated with that
    * score.
    * 
    * @author timstoddard
    *
    */
   private class Score implements Comparable<Score> {

      int score;
      String name;

      /**
       * Creates a new Score.
       * 
       * @param score
       * @param name
       */
      public Score(int score, String name) {
         this.score = score;
         this.name = name;
      }

      /**
       * Compares this score to another. If the scores are different, the larger
       * one comes first; if they are the same, the one whose name
       * lexicographically precedes the other comes first.
       */
      @Override
      public int compareTo(Score other) {
         if (other == null) {
            return Integer.MAX_VALUE;
         }
         if (other.getClass() != this.getClass()) {
            return Integer.MAX_VALUE;
         }
         if (this.score > other.score) {
            return -1;
         } else if (this.score < other.score) {
            return 1;
         }
         return this.name.compareTo(other.name);
      }
   }
}

/**
 * Frame to display the engine.
 * 
 * @author Tim Stoddard, Sam Lindbloom-Airey
 * @version program007
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Image;
import java.text.DecimalFormat;

import java.util.Arrays;

/**
 * Frame to display the engine.
 * 
 * @author Tim Stoddard, Sam Lindbloom-Airey
 * @version program007
 */

public class EngineFrame extends JFrame {

    // double buffering
    private Image dbImage;
    private Graphics dbg;

    // stuff
    public Map map;
    public Player player;
    public Keyboard keyboard;
    public Mouse mouse;
    private int currLevel;
    private long startTime;
    private double[] distances;

    /**
     * Creates a new EngineFrame to display and render the game.
     */
    public EngineFrame() {
        super("CPE Quest");

        add(new JPanel() {

            public void paint(Graphics window) {

                /* don't edit this */
                dbImage = createImage(getWidth(), getHeight());
                dbg = dbImage.getGraphics();
                paintComponent(dbg);
                window.drawImage(dbImage, 0, 0, this);
                /* end don't edit this */
            }

            public void paintComponent(Graphics g) {

                Graphics2D g2 = (Graphics2D) g;
                if (distances != null) {
                    draw3D(g2);
                }
                drawMiniMap(g2, 520, 0);
            }
        });

        init();

        setSize(865, 480);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void draw3D(Graphics2D g2) {
        int height = 480;
        for (int x = 0; x < distances.length; x++) {
            int lineHeight = (int) (height / distances[x]);
            int drawStart = - lineHeight / 2 + height / 2;
            drawStart = drawStart < 0 ? 0 : drawStart;
            int drawEnd = lineHeight / 2 + height / 2;
            drawEnd = drawEnd >= height ? height - 1 : drawEnd;
            g2.drawLine(x, drawStart, x, drawEnd);
        }
    }

    private void drawMiniMap(Graphics2D g2, int dx, int dy) {
        // draw stats
        double scale = 1;
        dy += scale * map.getHeight();
        g2.setColor(Color.black);
        DecimalFormat dec = new DecimalFormat("##.00");
        g2.setFont(new Font("Helvetica", Font.PLAIN, 30));
        g2.drawString("Position: " + dec.format(player.position[0]) + ", "
                + dec.format(player.position[1]), 10 + dx, 50 + dy);
        g2.drawString("Direction: " + dec.format(player.direction[0]) + ", " 
                + dec.format(player.direction[1]), 10 + dx, 150 + dy);
        g2.drawString(
                "Time: " + new DecimalFormat("##.00")
                .format((System.currentTimeMillis() - startTime) / 1000.0),
                145 + dx, 250 + dy);
    }

    /**
     * Starts the game at level1.
     */
    public void init() {
        currLevel = 1;
        updateMap(currLevel);
        startTime = System.currentTimeMillis();
    }

    /**
     * Setter method for the field of vision, set by Engine.
     *
     * @param fieldOfVision a double array of rectangle heights that
     * has length equal to number of rays cast by the engine.
     */
    public void sendDistances(double[] distances) {
        this.distances = Arrays.copyOf(distances, distances.length);
    }

    private String stripNonAlpha(String text) {
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (!Character.isAlphabetic(chars[i]) && !Character.isDigit(chars[i])
                    && chars[i] != 32) {
                chars[i] = '.';
                    }
        }
        String temp = new String(chars).replace(".", "");
        return temp.length() > 20 ? temp.substring(0, 20) : temp;
    }

    /**
     * Returns the current map.
     * 
     * @return The current map
     */
    public Map getMap() {
        return map;
    }

    /**
     * Returns the player.
     * 
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    private void updateMap(int level) {
        map = new Map(level);
        player = new Player(map);
        keyboard = new Keyboard(player);
        mouse = new Mouse(player);
        addKeyListener(keyboard);
        addMouseMotionListener(mouse);
    }
}

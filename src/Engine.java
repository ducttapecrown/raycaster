/**
 * A simple java game engine that uses raycasting.
 * 
 * @author Sam Lindbloom-Airey, Tim Stoddard
 * @version program007
 */

public class Engine {

    private EngineFrame frame;
    private static final int PIXELS = 500;
    private static final int COLUMNS = 500; // number of vertical columns
    public static final int WIDTH = PIXELS / COLUMNS; // column in pixels
    private static final int VIEW_DISTANCE = 20;
    private static final int WALL_HEIGHT = 100; // height of walls in pixels

    /**
     * Creates a new Engine to run the game.
     */
    public Engine() {
        frame = new EngineFrame();
    }

    private void render() {
        int width = COLUMNS;
        double[] distances = new double[width];
        
        // send out the rays
        for (int x = 0; x < width; x++) {
            double[] position = frame.player.position, 
                direction = frame.player.direction,
                camera = frame.player.camera;
            double cameraScale = 2 * ((double) x) / width - 1;

            // ray position and direction
            double rpx = position[0]; // ray position x
            double rpy = position[1]; // ray position y
            double rdx = direction[0] + camera[0] * cameraScale; // ray direction x
            double rdy = direction[1] + camera[1] * cameraScale; // ray direction y

            // which box of map we are in
            int mapx = (int) rpx;
            int mapy = (int) rpy;

            // length of ray from current position to next grid line
            double firstx, firsty;

            // length of ray from first grid line to next grid line
            double dx = Math.sqrt(1 + (rdy * rdx) / (rdx * rdx));
            double dy = Math.sqrt(1 + (rdx * rdy) / (rdy * rdy));

            // direction of steps
            int stepx;
            int stepy;

            boolean hit = false; // wall hit yet?
            int side = 0;  // was the wall NS or EW?

            stepx = rdx < 0 ? -1 : 1;
            firstx = rdx < 0 ? ((rpx - mapx) * dx) : ((mapx + 1 - rpx) * dx);

            stepy = rdy < 0 ? -1 : 1;
            firsty = rdy < 0 ? ((rpy - mapy) * dy) : ((mapy + 1 - rpy) * dy);

            // perform Digital Differential Analysis
            while (!hit) {
                if (firstx < firsty) {
                    firstx += dx;
                    mapx += stepx;
                    side = 0;
                }
                else {
                    firsty += dy;
                    mapy += stepy;
                    side = 1;
                }
                if(frame.map.getTile(mapx, mapy).getType() != Tile.SPACE) {
                    hit = true;
                }
            }

            distances[x] = side == 0 ? (mapx - rpx + (1 + stepx) / 2) / rdx : 
                                       (mapy - rpy + (1 + stepy) / 2) / rdy ;
        }
        frame.sendDistances(distances);
    }

    private void update() {
        frame.player.newMove();
        frame.repaint();
    }

    /**
     * Starts the game.
     */
    public void start() { // start the game loop
        while (true) {
            update();
            render();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                // do nothing #yolo
            }
        }
    }
}

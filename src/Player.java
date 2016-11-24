/**
 * The player of the engine class.
 * 
 * @author Sam Lindbloom-Airey, Tim Stoddard
 * @version program007
 */

public class Player {

    public boolean madeItToFinish, movingFast, canMoveFast, canRotate,
           cheatMode;
    private static final double moveFastVel = 3.5;
    private static final double moveSpeed = 0.05, rotateSpeed = 0.09;
    public int rotating, speed, sideSpeed;
    public double mouseTurn;
    public double[] position, direction, camera;
    private Map map;


    /**
     * Creates a new player with default position and direction, with the
     * specified map.
     */
    public Player(Map map) {
        this(new double[] {1.5, 1.5}, new double[] {0, 1}, map);
    }

    /**
     * Creates a new player with specified position and direction, with the
     * specified map.
     * 
     * @param position
     *           - the player's position
     * @param direction
     *           - the player's direction
     */
    public Player(double[] position, double[] direction, Map map) {
        madeItToFinish = movingFast = false;
        canMoveFast = canRotate = true;
        mouseTurn = 0;
        this.position = position;
        this.direction = direction;
        this.camera = new double[2];
        this.camera[0] = -1 * direction[1];
        this.camera[1] = direction[0];
        this.map = map;
    }

    /**
     * Allows the player to move at a faster speed for 1/2 a second. Takes 3
     * seconds to recharge.
     */
    public void moveFast() {
        if (canMoveFast) {
            movingFast = true;
            canMoveFast = false;
            canRotate = false;
        }
    }

    /**
     * Getter method for if the player can move fast.
     * 
     * @return true if the player isn't in cooldown or using
     * the speed boost.
     */
    public boolean canMoveFast() {
        return canMoveFast;
    }

    /**
     * Returns true if the player has made it to the finish point of the current
     * level.
     * 
     * @return - whether or not the player has finished the current level
     */
    public boolean madeItToFinish() {
        return madeItToFinish;
    }

    /**
     * Moves the player in their current direction, and handles collision
     * detection. Also handles when the player has made it to the end of the
     * level.
     */
    public void newMove() {

        // player wins!
        if (madeItToFinish) {
            return;
        }

        // do any necessary rotation
        double angle = rotating * rotateSpeed;
        if (rotating != 0) {
            double x = direction[0], y = direction[1];
            direction[0] = (Math.cos(angle) * x - Math.sin(angle) * y);
            direction[1] = (Math.sin(angle) * x + Math.cos(angle) * y);
            x = camera[0]; y = camera[1];
            camera[0] = (Math.cos(angle) * x - Math.sin(angle) * y);
            camera[1] = (Math.sin(angle) * x + Math.cos(angle) * y);
        }

        // find out regular movement
        double dx = speed * moveSpeed * direction[0];
        double dy = speed * moveSpeed * direction[1];

        // add strafing movement
        // the swapped sin and cos result from
        // a pi/2 rotation plugged into the generic | cosX -sinX |   | x | 
        // 2d rotation matrix                       | sinX  cosX | x | y |
        dx += sideSpeed * moveSpeed * direction[0] * -1;
        dy += sideSpeed * moveSpeed * direction[1];

        // speed limit movement in case
        // we have both strafing movement and regular
        // movement
        double magnitude = Math.sqrt(dx * dx + dy * dy);
        dx = dx / magnitude * moveSpeed;
        dy = dy / magnitude * moveSpeed;

        // handles moving fast
        // needs to be after speed limit because
        // otherwise we limit the moving fast back
        // down to the speed limit!
        if (movingFast) {
            dx *= moveFastVel;
            dy *= moveFastVel;
        }

        // separately collision checking x and y
        // makes movement when you hit wall nice
        // so we do it :D
        // bound check x
        if (map.inBounds(position[0] + dx, position[1])
                && map.getTile((int) (position[0] + dx), (int) position[1])
                .getType() != Tile.WALL) {
            // do the x movement
            position[0] += dx;
                }

        // bound check y
        if (map.inBounds(position[0], position[1] + dy)
                && map.getTile((int) (position[0]), (int) (position[1] + dy))
                .getType() != Tile.WALL) {
            // do the y movement
            position[1] += dy;
                }
    }

    /**
     * Returns deep copy of the player's position.
     * 
     * @return - the player's position as a length 2 double array
     */
    public double[] getPosition() {
        return new double[] {position[0], position[1]};
    }

    /**
     * Sets the position of the player.
     * 
     * @param newPos
     *           - the player's new position
     */
    public void setPosition(double x, double y) {
        position[0] = x;
        position[1] = y;
    }

    /**
     * Returns deep copy of player's direction vector.
     * 
     * @return - a size 2 double array 
     */
    public double[] getDirection() {
        return new double[] {direction[0], direction[1]};
    }
    /**
     * Returns deep copy of player's camera vector.
     * 
     * @return - a size 2 double array 
     */
    public double[] getCamera() {
        return new double[] {camera[0], camera[1]};
    }
    /**
     * Sets the player's direction.
     * 
     * @param direction
     *           - the player's new direction
     */
    public void setDirection(double x, double y) {
        direction[0] = x;
        direction[1] = y;
    }

    /**
     * Toggles cheat mode on the minimap.
     */
    public void toggleCheatMode() {
        cheatMode = !cheatMode;
    }

    /**
     * Returns true if cheat mode is currently activated, or false otherwise.
     */
    public boolean getCheatMode() {
        return cheatMode;
    }
}

import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.Robot;
import java.awt.AWTException;
import javax.swing.JFrame;

/**
 * Listens to mouse input and locks mouse in place
 * for engine.
 * 
 * @author Sam Lindbloom-Airey
 * @version program07
 */

public class Mouse implements MouseMotionListener {

    public static final double SENSITIVITY = Math.PI * 70;
    private Player player;
    private Robot robot;

    /**
     * Instantiates a mouse.
     * 
     * @param player the player to change the direction of.
     */
    public Mouse(Player player) {
        this.player = player;
        try {
            this.robot = new Robot();
        } catch (AWTException e) {
            System.out.println("awt exception");
        }
    }

    /**
     * Method from mouseMotionListener.
     *
     * @param e The MouseEvent triggered by the user.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        // do nothing
    }

    /**
     * Method from mouseMotionListener.
     * Calculates the distance the mouse has traveled from
     * the center of the screen, then changes the players direction
     * by that amount. 
     *
     * @param e The MouseEvent triggered by the user.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        /*
        int halfRaycastingWid = 260;
        double turnAmount = e.getX() - halfRaycastingWid;
        if (player.canRotate) {
            turnAmount /= SENSITIVITY;
            player.setDirection(player.getDirection() + turnAmount);
        }
        robot.mouseMove(halfRaycastingWid, 200);
        */
    }

}

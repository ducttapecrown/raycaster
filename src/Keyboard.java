import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * A class to handle key input for the engine.
 * 
 * @author Sam Lindbloom-Airey, Tim Stoddard
 * @version program07
 */

public class Keyboard implements KeyListener {

    private Player player;

    public Keyboard(Player player) {
        System.out.println("Player: " + player.toString());
        this.player = player;
    }

    /**
     * Called when a key is typed.
     *
     * @param e the KeyEvent triggered by the user.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // nothing here!
    }

    /**
     * Called when a key is pressed.
     * Sets instance variables in player according to what keys
     * are pressed.
     * 
     * @param e The KeyEvent triggered by the user.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                player.speed = 1;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                player.speed = -1;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                player.sideSpeed = 1;
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                player.sideSpeed = -1;
                break;
            case KeyEvent.VK_C:
                player.toggleCheatMode();
                break;
            case KeyEvent.VK_J:
            case KeyEvent.VK_Q:
                player.rotating = -1;
                break;
            case KeyEvent.VK_K:
            case KeyEvent.VK_E:
                player.rotating = 1;
                break;
            case KeyEvent.VK_SPACE:
                player.moveFast();
                break;
        }
    }

    /**
     * Called when the user releases a key.
     * Sets instance variables to zero in player if the value
     * of that variable is what the key sets it to. This way, for example,
     * releasing s while holding w doesn't stop the player.
     * 
     * @param e The KeyEvent generated by the user's key press.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                player.speed = player.speed == 1 ? 0 : player.speed;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                player.speed = player.speed == -1 ? 0 : player.speed;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                player.sideSpeed = player.sideSpeed == 1 ? 0 : player.sideSpeed;
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                player.sideSpeed = player.sideSpeed == -1 ? 0 : player.sideSpeed;
                break;
            case KeyEvent.VK_J:
            case KeyEvent.VK_Q:
                player.rotating = player.rotating == -1 ? 0 : player.rotating;
                break;
            case KeyEvent.VK_K:
            case KeyEvent.VK_E:
                player.rotating = player.rotating == 1 ? 0 : player.rotating;
                break;
        }
    }
}

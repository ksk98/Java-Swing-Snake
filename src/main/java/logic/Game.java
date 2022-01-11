package logic;

import concurrents.TimerThread;
import entities.board.complex.Player;
import entities.board.simple.Coordinate;
import entities.board.simple.Drawable;
import enums.Direction;
import managers.SettingsGetter;
import views.components.BoardPanel;
import views.components.BoardView;
import views.views.ViewGame;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Game {
    private JFrame frame;
    private BoardView window;
    private BoardPanel panel;
    private SettingsGetter settingsGetter;

    private int score = 0;

    //Coin
    private Coordinate coin;
    private boolean coinCollected = false;

    private Random rand = new Random();

    private Player player;

    private boolean dirChanged;
    private Direction newDirection = Direction.left;

    public boolean isRunning = true;
    private TimerThread timer;

    public Game(ViewGame frame, BoardView boardView, BoardPanel boardPanel,
                SettingsGetter settings) throws InterruptedException {
        this.frame = frame;
        window = boardView;
        panel = boardPanel;
        settingsGetter = settings;
        timer = new TimerThread(settingsGetter.getDifficulty().getFrameDelay());

        //Add key listeners for the controls
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {}

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                Direction newDir = null;
                boolean changeDir = true;

                if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
                    newDir = Direction.up;
                } else if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
                    newDir = Direction.right;
                } else if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
                    newDir = Direction.down;
                } else if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
                    newDir = Direction.left;
                } else {
                    changeDir = false;
                }

                //If the direction was changed and player is not trying to go backwards then change the players direction
                if (changeDir && isValidDirection(newDir)) {
                    newDirection = newDir;
                    dirChanged = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {}
        });
    }

    public void start() throws InterruptedException {
        player = new Player(new Coordinate(
                settingsGetter.getBoardSize().getX() /2,
                settingsGetter.getBoardSize().getY() /2),
                settingsGetter.getBodySet());

        drawNewCoin();

        while (isRunning) {
            try {
                if (collisionDetected()) {
                    isRunning = false;
                    break;
                }

                timer.start();

                Drawable tailPrev = player.getTailPrevious();
                window.erase(tailPrev.getCoordinate().x, tailPrev.getCoordinate().y);

                drawPlayer();
            } catch (ArrayIndexOutOfBoundsException e) {
                isRunning = false;
                break;
            }

            dirChanged = false;
            coinCollected = false;

            if (player.getHead().getCoordinate().equals(coin))
                collectCoin();

            movePlayer();

            while (!timer.timePassed() && !dirChanged) {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(10);
                    if (dirChanged)
                        break;
                }
            }
        }
    }

    /**
     * Draws a new coin in a random, unoccupied position. \n
     */
    private void drawNewCoin() {
        //Generate a new place for the coin. If the coordinates are occupied, roll again
        int x, y;
        do {
            x = rand.nextInt(settingsGetter.getBoardSize().getX());
            y = rand.nextInt(settingsGetter.getBoardSize().getY());
        } while (window.isOccupied(x,  y));

        //Draw coin on screen. occupy = false tells us that the coin will not cause collision thus end the game
        window.draw(x, y, settingsGetter.getTileSet().getTreasure(), false);

        //Update the coins coordinates
        coin = new Coordinate(x, y);
    }

    /**
     * Handles everything that happens when the player touches the coin, including drawing a new coin.
     */
    private void collectCoin() {
        coinCollected = true;
        player.addSegment();
        drawNewCoin();
        score += 10;
        panel.setScore(score);
    }

    private boolean collisionDetected() {
        return window.isOccupied(player.getHead().getCoordinate().x, player.getHead().getCoordinate().y);
    }

    private void movePlayer() {
        Drawable head = player.getHead();
        int newHeadX = head.getCoordinate().x, newHeadY = head.getCoordinate().y;
        //Move head
        switch (newDirection) {
            case up:
                newHeadY -= 1;
                break;
            case right:
                newHeadX += 1;
                break;
            case down:
                newHeadY += 1;
                break;
            case left:
                newHeadX -= 1;
                break;
        }

        player.updateHeadPrev();
        head.setPosition(new Coordinate(newHeadX, newHeadY), newDirection);

        if (!coinCollected)
            player.updateBody();
    }

    private void drawPlayer() {
        // In the old place of the head draw a body segment (if exists)
        Direction bodyDir;
        ImageIcon currentBodyIcon = settingsGetter.getBodySet().getBody();

        if (player.getLength() > 0) {
            if (player.getHead().getCoordinate().y == player.getSecond().getCoordinate().y) {
                // If the head and the refSegment are on the same Y coordinate, draw straight horizontal body
                bodyDir = Direction.left;
            } else {
                // If the head and the refSegment are on the same X coordinate, draw straight vertical body
                if (player.getHead().getCoordinate().x == player.getSecond().getCoordinate().x) {
                    bodyDir = Direction.up;
                } else {
                    // Draw curved body. The orientation depends on the position of head and second body fragment closest
                    // to the head (which can be the tail). It also depends on the orientation of the curved segment itself.
                    currentBodyIcon = settingsGetter.getBodySet().getCurvedBody();
                    if (player.getHead().getCoordinate().y < player.getSecond().getCoordinate().y) {
                        if (player.getHead().getCoordinate().x < player.getSecond().getCoordinate().x) {
                            if(player.getFirst().getDirection() == Direction.left)
                                bodyDir = Direction.down;
                            else
                                bodyDir = Direction.up;
                        } else {
                            if(player.getFirst().getDirection() == Direction.right)
                                bodyDir = Direction.right;
                            else
                                bodyDir = Direction.left;
                        }
                    } else {
                        if (player.getHead().getCoordinate().x < player.getSecond().getCoordinate().x) {
                            if(player.getFirst().getDirection() == Direction.left)
                                bodyDir = Direction.left;
                            else
                                bodyDir = Direction.right;
                        } else {
                            if(player.getFirst().getDirection() == Direction.right)
                                bodyDir = Direction.up;
                            else
                                bodyDir = Direction.down;
                        }
                    }
                }
            }

            // Draw the segment closest to the head
            window.draw(player.getFirst().getCoordinate().x, player.getFirst().getCoordinate().y, currentBodyIcon, bodyDir, true);
        }
        // Draw tail
        window.draw(player.getTail().getCoordinate().x, player.getTail().getCoordinate().y,
                settingsGetter.getBodySet().getTail(), player.getTail().getDirection(), true);

        // Draw head
        window.draw(player.getHead().getCoordinate().x, player.getHead().getCoordinate().y,
                settingsGetter.getBodySet().getHead(), player.getHead().getDirection(), true);
    }

    private boolean isValidDirection(Direction dir) {
        Direction playerDir = player.getHead().getDirection();
        switch (playerDir) {
            case up:
                if (dir == Direction.down) return false;
                break;
            case right:
                if (dir == Direction.left) return false;
                break;
            case down:
                if (dir == Direction.up) return false;
                break;
            case left:
                if (dir == Direction.right) return false;
                break;
            default:
                return false;
        }

        return true;
    }
}
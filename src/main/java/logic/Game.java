package logic;

import concurrents.TimerThread;
import entities.board.complex.Player;
import entities.board.simple.Coordinate;
import entities.board.simple.Drawable;
import enums.Direction;
import managers.SettingsGetter;
import views.components.BoardPanel;
import views.components.BoardView;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Game {
    private static BoardView window;
    private static BoardPanel panel;
    private static SettingsGetter settingsGetter;

    private int score = 0, timeInSeconds = 0;

    //Coin
    private Coordinate coin;
    private boolean coinCollected = false;

    private Random rand = new Random();

    private Player player;

    private boolean dirChanged;
    private Direction newDirection = Direction.left;

    private boolean game = true;
    private TimerThread timer;

    public Game(BoardView boardView, BoardPanel boardPanel,
                SettingsGetter settings) throws InterruptedException {
        window = boardView;
        panel = boardPanel;
        settingsGetter = settings;
        timer = new TimerThread(settingsGetter.getDifficulty().getFrameDelay());

        //Add key listeners for the controls
        window.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {}

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                Direction newDir = null;
                boolean changeDir = true;

                if(keyEvent.getKeyCode() == KeyEvent.VK_UP){
                    newDir = Direction.up;
                } else if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT){
                    newDir = Direction.right;
                } else if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN){
                    newDir = Direction.down;
                } else if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
                    newDir = Direction.left;
                } else {
                    changeDir = false;
                }

                //If the direction was changed and player is not trying to go backwards then change the players direction
                if(changeDir && isValidDirection(newDir)){
                    newDirection = newDir;
                    dirChanged = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {}
        });

        player = new Player(new Coordinate(
                settingsGetter.getBoardSize().getX() /2,
                settings.getBoardSize().getY() /2),
                settings.getBodySet());

        drawNewCoin();

        while(game){
            //Draw player
            try{
                if(collisionDetected()) {
                    game = false;
                    break;
                }

                timer.start();

                //Erase the players old tail
                Drawable tailPrev = player.getTailPrevious();
                window.erase(tailPrev.getCoordinate().x, tailPrev.getCoordinate().y);

                //Draw the player
                drawPlayer();
            } catch (ArrayIndexOutOfBoundsException e){
                //If the player is out of bounds then the game is over
                game = false;
                break;
            }

            //Reset the booleans
            dirChanged = false;
            coinCollected = false;

            //Check if player got the coin
            if(player.getHead().getCoordinate().equals(coin))
                collectCoin();

            //Move the player
            movePlayer();

            //Delay between the next frame. Wait for the timer OR for player to press an arrow.
            //If the player holds the arrow then the game will speed up
            while(!timer.timePassed() && !dirChanged){
                Thread.sleep(100);
            }
        }
    }

    /**
     * Draws a new coin in a random, unoccupied position. \n
     */
    private void drawNewCoin(){
        //Generate a new place for the coin. If the coordinates are occupied, roll again
        int x, y;
        do{
            x = rand.nextInt(settingsGetter.getBoardSize().getX());
            y = rand.nextInt(settingsGetter.getBoardSize().getY());
        } while(window.isOccupied(x,  y));

        //Draw coin on screen. occupy = false tells us that the coin will not cause collision thus end the game
        window.draw(x, y, settingsGetter.getTileSet().getTreasure(), false);

        //Update the coins coordinates
        coin = new Coordinate(x, y);
    }

    /**
     * Handles everything that happens when the player touches the coin, including drawing a new coin.
     */
    private void collectCoin(){
        coinCollected = true;

        //Make player longer
        player.addSegment();

        //Call the coin drawing method
        drawNewCoin();

        //Update score
        score += 10;
        panel.setScore(score);
        return;
    }

    /**
     * Tells if position of the players head is already occupied with another body segment.\n
     * @return true if the player hit himself.
     */
    private boolean collisionDetected(){
        if(window.isOccupied(player.getHead().getCoordinate().x, player.getHead().getCoordinate().y))
            return true;
        return false;
    }

    /**
     * Move player in the direction specified by newDirection variable. \n
     * The method changes the position of the head, updates its old position and calls for body update
     * if a coin was not collected.
     */
    private void movePlayer(){
        Drawable head = player.getHead();
        int newHeadX = head.getCoordinate().x, newHeadY = head.getCoordinate().y;
        //Move head
        switch(newDirection){
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

        if(!coinCollected)
            player.updateBody();
    }

    /**
     * Draws player on screen.
     * This is done by drawing only the head, tail and the body segment closest to the head, if it exists.
     */
    private void drawPlayer(){
        //In the old place of the head draw a body segment (if exists)
        Direction bodyDir = Direction.up;
        ImageIcon currentBodyIcon = settingsGetter.getBodySet().getBody();

        if(player.getLength() > 0) {
            if (player.getHead().getCoordinate().y == player.getSecond().getCoordinate().y) {
                //If the head and the refSegment are on the same Y coord, draw straight horizontal body
                bodyDir = Direction.left;
            } else {
                //If the head and the refSegment are on the same X coord, draw straight vertical body
                if (player.getHead().getCoordinate().x == player.getSecond().getCoordinate().x) {
                    bodyDir = Direction.up;
                } else {
                    //Draw curved body. The orientation depends on the position of head and second body fragment closest
                    //to the head (which can be the tail). It also depends on the orientation of the curved segment itself.
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

            //Draw the segment closest to the head
            window.draw(player.getFirst().getCoordinate().x, player.getFirst().getCoordinate().y, currentBodyIcon, bodyDir, true);
        }
        //Draw tail
        window.draw(player.getTail().getCoordinate().x, player.getTail().getCoordinate().y,
                settingsGetter.getBodySet().getTail(), player.getTail().getDirection(), true);

        //Draw head
        window.draw(player.getHead().getCoordinate().x, player.getHead().getCoordinate().y,
                settingsGetter.getBodySet().getHead(), player.getHead().getDirection(), true);
    }

    /**
     * Tells if the player can move in the given direction at the moment.
     * @param dir the direction thats being checked.
     * @return false if player tries to go backwards, inside own body. Otherwise returns true.
     */
    private boolean isValidDirection(Direction dir){
        Direction playerDir = player.getHead().getDirection();
        switch(playerDir){
            case up:
                if(dir == Direction.down) return false;
                break;
            case right:
                if(dir == Direction.left) return false;
                break;
            case down:
                if(dir == Direction.up) return false;
                break;
            case left:
                if(dir == Direction.right) return false;
                break;
            default:
                return false;
        }

        return true;
    }
}
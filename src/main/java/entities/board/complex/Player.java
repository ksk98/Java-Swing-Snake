package entities.board.complex;

import entities.board.simple.BaseDrawable;
import entities.board.simple.Coordinate;
import entities.board.simple.Drawable;
import enums.Direction;
import managers.Body.BodySet;

import java.util.ArrayDeque;
import java.util.Deque;

public class Player {
    private final Drawable head, tail;
    private Drawable headPrevious, tailPrevious;
    private final Deque<Drawable> body;
    private Drawable first, second, last;
    private int length;

    public Player(Coordinate headCoordinate, BodySet bodySet) {
        this(headCoordinate, bodySet, Direction.left);
    }

    public Player(Coordinate headCoordinate, BodySet bodySet, Direction startDirection) {
        head = new BaseDrawable(bodySet.getHead(), headCoordinate);

        Coordinate tailCoordinate = new Coordinate(headCoordinate);
        Coordinate tailPreviousCoordinate = new Coordinate(headCoordinate);
        switch (startDirection) {
            case up:
                tailCoordinate.y += 1;
                tailPreviousCoordinate.y += 2;
                break;
            case down:
                tailCoordinate.y -= 1;
                tailPreviousCoordinate.y -= 2;
                break;
            case left:
                tailCoordinate.x -=1;
                tailPreviousCoordinate.x -= 2;
                break;
            case right:
                tailCoordinate.x += 1;
                tailPreviousCoordinate.x += 2;
                break;
        }
        tail = new BaseDrawable(bodySet.getTail(), tailCoordinate);
        headPrevious = new BaseDrawable(tail);
        tailPrevious = new BaseDrawable(bodySet.getTail(), tailPreviousCoordinate, startDirection);
        body = new ArrayDeque<>();

        length = 0;
        first = new BaseDrawable(tail);
        second = new BaseDrawable(tail);
        last = new BaseDrawable(head);
    }

    public void updateBody(){
        //Update the tails previous position
        tailPrevious.setCoordinate(tail);

        //Player consists only of the head and the tail.
        if(length == 0){
            tail.setPosition(headPrevious.getCoordinate(), head.getDirection());

            first.setPosition(tail);
            second.setPosition(tail);
            last.setPosition(head);

            return;
        }

        //Player consists of head, tail and one segment
        if(length == 1){
            tail.setPosition(first.getCoordinate(), headPrevious.getDirection());

            first.setPosition(headPrevious);
            second.setPosition(tail);
            last.setPosition(first);

            return;
        }

        //Player consists of head, tail and two segments
        if(length == 2){
            tail.setPosition(last.getCoordinate(), first.getDirection());

            second.setPosition(first);
            first.setPosition(headPrevious);
            last.setPosition(second);

            return;
        }

        //Player is fully developed and doesn't contain any extra segments
        if(length == 3){
            tail.setPosition(last.getCoordinate(), second.getDirection());

            last.setPosition(second);
            second.setPosition(first);
            first.setPosition(headPrevious);

            return;
        }

        //Player is fully developed and the body queue is now used
        Drawable fromQueue = body.remove();

        tail.setCoordinate(last.getCoordinate());

        last.setPosition(fromQueue);
        body.add(new BaseDrawable(second));
        second.setPosition(first);
        first.setPosition(headPrevious);

        tail.setDirection(last.getDirection());
    }

    /**
     * Updates the player heads old position and orientation.
     */
    public void updateHeadPrev(){
        headPrevious.setPosition(head);
    }

    /**
     * Makes the player longer by adding an extra segment.
     */
    public void addSegment(){
        Drawable newSegment = new BaseDrawable(head);
        length++;

        //If the player consists of more than 3 (first, second, last is occupied) segments, add the excesive segments to the queue
        if(length >= 4){
            body.add(new BaseDrawable(second));
        }

        second.setPosition(first);
        first.setPosition(newSegment);
    }

    public int getLength() {
        return length;
    }

    public Drawable getHead() {
        return head;
    }

    public Drawable getTail() {
        return tail;
    }

    public Drawable getHeadPrevious() {
        return headPrevious;
    }

    public Drawable getTailPrevious() {
        return tailPrevious;
    }

    public Drawable getFirst() {
        return first;
    }

    public Drawable getSecond() {
        return second;
    }

    public Drawable getLast() {
        return last;
    }
}

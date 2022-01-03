package managers.Body;

import javax.swing.*;

public abstract class BodySetBase implements BodySet {
    protected ImageIcon head, headLeft, headRight, body, bodyCurved, tail;

    public BodySetBase() {
        head = headLeft = headRight = body = bodyCurved = tail = null;
    }

    @Override
    public ImageIcon getHead() {
        return head;
    }

    @Override
    public ImageIcon getHeadLeft() {
        return headLeft;
    }

    @Override
    public ImageIcon getHeadRight() {
        return headRight;
    }

    @Override
    public ImageIcon getBody() {
        return body;
    }

    @Override
    public ImageIcon getCurvedBody() {
        return bodyCurved;
    }

    @Override
    public ImageIcon getTail() {
        return tail;
    }
}

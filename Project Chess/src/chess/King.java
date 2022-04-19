package chess;

import view.GamePanel;

import java.awt.*;

public class King extends Chess {
    public King(int player, Point p) {
        super("King", player, p);
    }

    public King(int player, int px) {
        this(player, new Point(px, 1));
    }


    @Override
    public boolean isAbleStep(Point tp, GamePanel gamePanel) {
        return true;
    }

    @Override
    public boolean isAbleEat(Point tp, GamePanel gamePanel) {
        return true;
    }

}

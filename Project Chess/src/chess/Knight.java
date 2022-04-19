package chess;

import view.GamePanel;

import java.awt.*;

public class Knight extends Chess {
    public Knight(int player, Point p) {
        super("Knight", player, p);
    }

    public Knight(int player, int px) {
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

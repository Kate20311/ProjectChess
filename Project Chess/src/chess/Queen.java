package chess;

import view.GamePanel;

import java.awt.*;

public class Queen extends Chess {
    public Queen(int player, Point p) {
        super("Queen", player, p);
    }

    public Queen(int player, int px) {
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

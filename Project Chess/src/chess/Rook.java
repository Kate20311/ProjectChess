package chess;

import view.GamePanel;

import java.awt.*;

public class Rook extends Chess {
    public Rook(int player, Point p) {
        super("Rook", player, p);
    }

    public Rook(int player, int px) {
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

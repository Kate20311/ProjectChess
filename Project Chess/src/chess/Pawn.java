package chess;

import view.GamePanel;

import java.awt.*;

public class Pawn extends Chess{
    public Pawn(int player, Point p) {
        super("Pawn", player, p);
    }

    public Pawn(int player, int px) {
        this(player, new Point(px, 2));
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

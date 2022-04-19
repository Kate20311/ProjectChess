package chess;

import view.GamePanel;


import java.awt.*;

public class Bishop extends Chess{
    public Bishop(int player, Point p) {
        super("Bishop", player, p);
    }

    public Bishop(int player, int px) {
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

package online;

import java.util.List;


public class Rook_ol extends Chess_ol {

    public Rook_ol(int id, int chessBoard_x, int chessBoard_y, String imgFilePath, Team team) {
        super(id, chessBoard_x, chessBoard_y, imgFilePath, team);
    }

    @Override
    protected boolean step(int chessBoard_x, int chessBoard_y, List<Chess_ol> redChessList, List<Chess_ol> blackChessList) {
        return true;
    }

    @Override
    protected boolean attack(int chessBoard_x, int chessBoard_y, List<Chess_ol> redChessList, List<Chess_ol> blackChessList) {
        return false;
    }


}

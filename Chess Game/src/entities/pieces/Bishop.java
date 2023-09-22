package entities.pieces;

import entities.board.Piece;
import enums.PieceSide;

public class Bishop extends Piece {
    public Bishop(PieceSide pieceSide) {
        super(pieceSide);
    }

    @Override
    public String getSymbol() {
        return getPieceSide().equals(PieceSide.WHITE) ? " ♗ " : " ♝ ";
    }
}

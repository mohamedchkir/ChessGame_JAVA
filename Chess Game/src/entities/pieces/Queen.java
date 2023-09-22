package entities.pieces;

import entities.board.Piece;
import enums.PieceSide;

public class Queen extends Piece {
    public Queen(PieceSide pieceSide) {
        super(pieceSide);
    }

    @Override
    public String getSymbol() {
        return getPieceSide().equals(PieceSide.WHITE) ? " ♕ " : " ♛ ";
    }
}

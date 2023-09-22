package entities.pieces;

import entities.board.Piece;
import enums.PieceSide;

public class Pawn extends Piece {
    public Pawn(PieceSide pieceSide) {
        super(pieceSide);
    }

    @Override
    public String getSymbol() {
        return getPieceSide().equals(PieceSide.WHITE) ? " ♙ " : " ♟ ";
    }
}

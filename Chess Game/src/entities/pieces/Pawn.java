package entities.pieces;

import enums.PieceSide;

public class Pawn extends Piece {
    public Pawn(PieceSide pieceSide) {
        super(pieceSide);
    }

    @Override
    public String getSymbol() {
        return getPieceSide().equals(PieceSide.WHITE) ? " ♔ " : " ♚ ";
    }
}

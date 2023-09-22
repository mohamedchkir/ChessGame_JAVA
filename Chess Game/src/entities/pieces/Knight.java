package entities.pieces;

import entities.board.Piece;
import enums.PieceSide;

public class Knight extends Piece {
    public Knight(PieceSide pieceSide) {
        super(pieceSide);
    }

    @Override
    public String getSymbol() {
        return getPieceSide().equals(PieceSide.WHITE) ? " ♘ " : " ♞ ";
    }
}
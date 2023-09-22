package entities.pieces;

import entities.board.Piece;
import enums.PieceSide;

public class Rook extends Piece {

    public Rook(PieceSide pieceSide) {
        super(pieceSide);
    }

    @Override
    public String getSymbol() {
        return getPieceSide().equals(PieceSide.WHITE) ? " ♖ " : " ♜ ";
    }
}

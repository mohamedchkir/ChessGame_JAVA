package entities.pieces;


import entities.board.Piece;
import enums.PieceSide;

public class King extends Piece {
    public King(PieceSide pieceSide) {
        super(pieceSide);
    }

    @Override
    public String getSymbol() {
        return getPieceSide().equals(PieceSide.WHITE) ? " ♔ " : " ♚ ";
    }
}

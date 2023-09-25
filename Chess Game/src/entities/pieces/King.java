package entities.pieces;


import entities.board.Piece;
import entities.board.Square;
import enums.PieceSide;

import java.util.List;

public class King extends Piece {
    public King(PieceSide pieceSide) {
        super(pieceSide);
    }

    @Override
    public String getSymbol() {
        return getPieceSide().equals(PieceSide.WHITE) ? " k " : " K ";
    }

    @Override
    public List<Square> abilityMoves(Square[][] board) {
        return null;
    }
}

package entities.pieces;

import entities.board.Piece;
import entities.board.Square;
import enums.PieceSide;

import java.util.List;

public class Bishop extends Piece {
    public Bishop(PieceSide pieceSide) {
        super(pieceSide);
    }

    @Override
    public String getSymbol() {
        return getPieceSide().equals(PieceSide.WHITE) ? " b " : " B ";
    }

    @Override
    public List<Square> abilityMoves(Square[][] board) {
        return null;
    }
}

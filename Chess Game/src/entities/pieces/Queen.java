package entities.pieces;

import entities.board.Piece;
import entities.board.Square;
import enums.PieceSide;

import java.util.List;

public class Queen extends Piece {
    public Queen(PieceSide pieceSide) {
        super(pieceSide);
    }

    @Override
    public String getSymbol() {
        return getPieceSide().equals(PieceSide.WHITE) ? " q " : " Q ";
    }

    @Override
    public List<Square> abilityMoves(Square[][] board) {
        return null;
    }
}

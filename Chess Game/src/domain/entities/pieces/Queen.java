package domain.entities.pieces;

import domain.entities.board.Piece;
import domain.entities.board.Square;
import domain.enums.PieceSide;

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

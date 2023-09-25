package entities.pieces;

import entities.board.Piece;
import entities.board.Square;
import enums.PieceSide;

import java.util.List;

public class Rook extends Piece {

    public Rook(PieceSide pieceSide) {
        super(pieceSide);
    }

    @Override
    public String getSymbol() {
        return getPieceSide().equals(PieceSide.WHITE) ? " r " : " R ";
    }

    @Override
    public List<Square> abilityMoves(Square[][] board) {
        return null;
    }
}

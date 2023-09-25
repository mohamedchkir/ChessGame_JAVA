package entities.pieces;

import entities.board.Piece;
import entities.board.Square;
import enums.PieceSide;

import java.util.List;

public class Knight extends Piece {
    public Knight(PieceSide pieceSide) {
        super(pieceSide);
    }

    @Override
    public String getSymbol() {
        return getPieceSide().equals(PieceSide.WHITE) ? " n " : " N ";
    }

    @Override
    public List<Square> abilityMoves(Square[][] board) {
        return null;
    }
}
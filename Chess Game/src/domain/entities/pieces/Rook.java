package domain.entities.pieces;

import domain.entities.board.Piece;
import domain.entities.board.Square;
import domain.entities.pieces.movements.RookMovementLogic;
import domain.enums.PieceSide;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece implements RookMovementLogic {

    public Rook(PieceSide pieceSide) {
        super(pieceSide);
    }

    @Override
    public String getSymbol() {
        return getPieceSide().equals(PieceSide.WHITE) ? " r " : " R ";
    }

    @Override



    public List<Square> abilityMoves(Square[][] board) {
        return RookMovementLogic.super.getValidMoves(board);
    }


}

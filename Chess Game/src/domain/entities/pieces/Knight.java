package domain.entities.pieces;

import domain.entities.board.Piece;
import domain.entities.board.Square;
import domain.entities.pieces.movements.KingMovementsLogic;
import domain.entities.pieces.movements.KnightMovementsLogic;
import domain.enums.PieceSide;

import java.util.List;

public class Knight extends Piece implements KnightMovementsLogic {
    public Knight(PieceSide pieceSide) {
        super(pieceSide);
    }

    @Override
    public String getSymbol() {
        return getPieceSide().equals(PieceSide.WHITE) ? " n " : " N ";
    }

    @Override
    public List<Square> abilityMoves(Square[][] board) {
        return KnightMovementsLogic.super.getValidMoves(board);
    }
}
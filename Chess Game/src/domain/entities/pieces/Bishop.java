package domain.entities.pieces;

import domain.entities.board.Piece;
import domain.entities.board.Square;
import domain.entities.pieces.movements.BishopMovementsLogic;
import domain.enums.PieceSide;

import java.util.List;

public class Bishop extends Piece implements BishopMovementsLogic {
    public Bishop(PieceSide pieceSide) {
        super(pieceSide);
    }

    @Override
    public String getSymbol() {
        return getPieceSide().equals(PieceSide.WHITE) ? " b " : " B ";
    }

    @Override
    public List<Square> abilityMoves(Square[][] board) {
        return BishopMovementsLogic.super.getValidMoves(board);
    }
}

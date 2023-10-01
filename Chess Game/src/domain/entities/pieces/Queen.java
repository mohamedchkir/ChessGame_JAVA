package domain.entities.pieces;

import domain.entities.board.Piece;
import domain.entities.board.Square;
import domain.entities.pieces.movements.BishopMovementsLogic;
import domain.entities.pieces.movements.RookMovementsLogic;
import domain.enums.PieceSide;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece implements BishopMovementsLogic , RookMovementsLogic {
    public Queen(PieceSide pieceSide) {
        super(pieceSide);
    }

    @Override
    public String getSymbol() {
        return getPieceSide().equals(PieceSide.WHITE) ? " q " : " Q ";
    }

    @Override
    public List<Square> abilityMoves(Square[][] board) {
        return getValidMoves(board);
    }

    @Override
    public List<Square> getValidMoves(Square[][] board) {
        List<Square> squareList = new ArrayList<>();
        squareList.addAll(BishopMovementsLogic.super.getValidMoves(board));
        squareList.addAll(RookMovementsLogic.super.getValidMoves(board));

        return squareList;
    }
}

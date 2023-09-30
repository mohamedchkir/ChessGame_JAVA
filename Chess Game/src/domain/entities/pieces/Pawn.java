package domain.entities.pieces;

import domain.entities.pieces.movements.PawnMovementsLogic;

import domain.entities.board.Piece;
import domain.entities.board.Square;
import domain.enums.PieceSide;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece implements  PawnMovementsLogic {
    public Pawn(PieceSide pieceSide) {
        super(pieceSide);
    }

    @Override
    public String getSymbol() {
        return getPieceSide().equals(PieceSide.WHITE) ? " p " : " P ";
    }

    @Override
    public List<Square> abilityMoves(Square[][] board) {
        return PawnMovementsLogic.super.getValidMoves(board);
    }

    @Override
    public String toString() {
        return "Pawn{" +
                "x=" + this.getSquare().getX() +
                ", y=" + this.getSquare().getY() +
                ", moved=" + this.isMoved() +
                ", side=" + this.getPieceSide() +
                "}";
    }
}

package domain.entities.pieces.movements;

import domain.entities.board.Piece;
import domain.entities.board.Square;
import domain.enums.PieceSide;
import services.InputService;

import java.util.ArrayList;
import java.util.List;

public interface PawnMovementsLogic {

    default List<Square> getValidMoves(Square[][] board) {
        Piece pawn = (Piece) this;
        List<Square> squareList = new ArrayList<>();
        Square actualSquare = pawn.getSquare();

        int operation = pawn.getPieceSide().equals(PieceSide.WHITE) ? 1 : -1;

        int newY = actualSquare.getY() - operation;

        if (newY >= 0 && newY < 8) {
            Square up = board[newY][actualSquare.getX()];
            if (up.getPiece() == null) {
                squareList.add(up);

                if (!pawn.isMoved()) {
                    int doubleMoveY = actualSquare.getY() - 2 * operation;
                    if (doubleMoveY >= 0) {
                        Square forward = board[doubleMoveY][actualSquare.getX()];
                        if (forward.getPiece() == null) {
                            squareList.add(forward);
                        }
                    }
                }
            }
        }

        if (actualSquare.getX() > 0) {
            int upLeftY = actualSquare.getY() - operation;
            int upLeftX = actualSquare.getX() - 1;
            if (upLeftY >= 0 && upLeftX >= 0) {
                Square upLeft = board[upLeftY][upLeftX];
                if (upLeft.getPiece() != null) {
                    squareList.add(upLeft);
                }
            }
        }

        if (actualSquare.getX() < 7) {
            int upRightY = actualSquare.getY() - operation;
            int upRightX = actualSquare.getX() + 1;
            if (upRightY >= 0 && upRightX < 8) {
                Square upRight = board[upRightY][upRightX];
                if (upRight.getPiece() != null) {
                    squareList.add(upRight);
                }
            }
        }

        return squareList;
    }


    default void promotePawn() {
        Piece pawn = (Piece) this;
        Piece chosenPiece = pawn.getSquare().getPiece();
        Piece newPiece = InputService.getPromotePawn(chosenPiece.getPieceSide());

        pawn.getSquare().setPiece(newPiece);
    }
}

package domain.entities.pieces.movements;

import domain.entities.board.Piece;
import domain.entities.board.Square;
import domain.enums.PieceSide;

import java.util.List;

public interface CheckLogic {
    default boolean onCheck(Square[][] board, PieceSide pieceSide) {
        Square square = (Square) this;
        PieceSide opponentSide = pieceSide.equals(PieceSide.WHITE) ? PieceSide.BLACK : PieceSide.WHITE;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j].getPiece();

                if (piece != null && piece.getPieceSide() == opponentSide) {
                    List<Square> squareList = piece.abilityMoves(board);

                    if (squareList.contains(square)) {

                        return true;
                    }
                }
            }
        }

        return false;
    }


}
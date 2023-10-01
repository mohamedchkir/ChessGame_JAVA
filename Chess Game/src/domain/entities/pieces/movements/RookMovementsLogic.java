package domain.entities.pieces.movements;

import java.util.ArrayList;
import java.util.List;

import domain.entities.board.Piece;
import domain.entities.board.Square;

public interface RookMovementsLogic {

    default List<Square> getValidMoves(Square[][] board){
        Piece rook = (Piece) this;
        List<Square> squareList = new ArrayList<>();
        Square actualSquare = rook.getSquare();

        int x = actualSquare.getX();
        int y = actualSquare.getY();

        // Movements vers le haut
        for (int i = y - 1; i >= 0; i--) {
            Square square = board[i][x];
            if (square.getPiece() == null) {
                squareList.add(square);
            } else {
                break;
            }
        }

        for (int i = y + 1; i < 8; i++) {
            Square square = board[i][x];
            if (square.getPiece() == null) {
                squareList.add(square);
            } else {
                break;
            }
        }

        for (int i = x - 1; i >= 0; i--) {
            Square square = board[y][i];
            if (square.getPiece() == null) {
                squareList.add(square);
            } else {
                break;
            }
        }

        for (int i = x + 1; i < 8; i++) {
            Square square = board[y][i];
            if (square.getPiece() == null) {
                squareList.add(square);
            } else {
                break;
            }
        }

        return squareList;

    }
}

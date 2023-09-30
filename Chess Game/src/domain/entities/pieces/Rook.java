package domain.entities.pieces;

import domain.entities.board.Piece;
import domain.entities.board.Square;
import domain.enums.PieceSide;

import java.util.ArrayList;
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
        List<Square> squareList = new ArrayList<>();
        Square actualSquare = this.getSquare();
        int x = actualSquare.getX();
        int y = actualSquare.getY();

        // Mouvements vers le haut
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

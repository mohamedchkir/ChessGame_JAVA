package domain.entities.pieces.movements;

import domain.entities.board.Piece;
import domain.entities.board.Square;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface KingMovementsLogic {
    default List<Square> getValidMoves(Square[][] board){

        Piece king =(Piece) this;
        List<Square> squareList = new ArrayList<>();
        Square actualSquare = king.getSquare();

            int x = actualSquare.getX();
            int y = actualSquare.getY();

            int[][] possibleMoves = {
                    {x + 1, y},
                    {x - 1, y},
                    {x, y + 1},
                    {x, y - 1},
                    {x + 1, y + 1},
                    {x + 1, y - 1},
                    {x - 1, y + 1},
                    {x - 1, y - 1}
            };

            for (int[] move : possibleMoves) {
                int newX = move[0];
                int newY = move[1];

                if (newX >= 0 && newX < 8 && newY >= 0 && newY < 8) {
                    Square square = board[newY][newX];
                    if ((square.getPiece() == null || square.getPiece().getPieceSide() != king.getPieceSide() && !square.onCheck(board, king.getPieceSide()))) {

                        squareList.add(square);
                    }
                }
            }

            return squareList;

    }

    default boolean possibleToCastle(Square[][] squares ,Square square){
        Piece king = (Piece) this;
        Piece rook = square.getPiece();

        if (king.isMoved() || rook.isMoved() || king.getPieceSide() != rook.getPieceSide()){
            return false;
        }

        int starX = Math.min(king.getSquare().getX(), rook.getSquare().getX()) + 1;
        int endX = Math.min(king.getSquare().getX(), rook.getSquare().getX());

        int kingY = king.getSquare().getY();

        for (int i =starX ; i<endX ; i++){
            if(squares[kingY][i].getPiece() != null){
                return false;
            }
        }

        return true;
    }
}

package entities.board;

import enums.PieceSide;
import java.util.List;

public abstract class Piece {

    private final PieceSide pieceSide;
     private  Square square;

    private boolean moved =false;

    public Piece(PieceSide pieceSide) {
        this.pieceSide = pieceSide;
    }

    public abstract String getSymbol();

    public PieceSide getPieceSide() {
        return pieceSide;
    }

    public Square getSquare() {
        return square;
    }

    public void setSquare(Square square){
        this.square = square;
    }

    public boolean isMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public abstract List<Square> abilityMoves(Square[][] board);
}

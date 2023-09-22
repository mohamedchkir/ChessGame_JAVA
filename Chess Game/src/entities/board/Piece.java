package entities.board;


import enums.PieceSide;

public abstract class Piece {
    private final PieceSide pieceSide;

    public Piece(PieceSide pieceSide) {
        this.pieceSide = pieceSide;
    }

    public abstract String getSymbol();

    public PieceSide getPieceSide() {
        return pieceSide;
    }
}

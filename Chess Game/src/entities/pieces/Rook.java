package entities.pieces;

import enums.PieceSide;

public class Rook extends Piece {

    public Rook(PieceSide pieceSide){
        super(pieceSide);
    }

    @Override
    public String getSymbol() {
        return getPieceSide().equals(PieceSide.WHITE) ? "" :"";
}

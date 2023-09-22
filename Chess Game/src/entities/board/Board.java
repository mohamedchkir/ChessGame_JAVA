package entities.board;


import entities.pieces.*;
import enums.PieceSide;

public class Board {

    private final Square[][] board;

    public Board() {
        this.board = new Square[8][8];
    }

    public Square[][] getBoard() {
        return board;
    }
}
package domain.entities.board;


public class Board {

    private final Square[][] board;

    public Board() {
        this.board = new Square[8][8];
    }

    public Square[][] getBoard() {
        return board;
    }
    public Square getSquare(int row, int col) {
        return board[row][col];
    }
}
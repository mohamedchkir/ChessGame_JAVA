package entities.board;


import entities.pieces.*;
import enums.PieceSide;

public class BoardService {
    private Piece[][] board = new Piece[8][8];

    public BoardService(){
        initialBoard();
    }
    public void initialBoard() {
        board[0][0] = new Rook(PieceSide.WHITE);
        board[0][1] = new Knight(PieceSide.WHITE);
        board[0][2] = new Bishop(PieceSide.WHITE);
        board[0][3] = new Queen(PieceSide.WHITE);
        board[0][4] = new King(PieceSide.WHITE);
        board[0][5] = new Bishop(PieceSide.WHITE);
        board[0][6] = new Knight(PieceSide.WHITE);
        board[0][7] = new Rook(PieceSide.WHITE);
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(PieceSide.WHITE);
        }

        board[7][0] = new Rook(PieceSide.BLACK);
        board[7][1] = new Knight(PieceSide.BLACK);
        board[7][2] = new Bishop(PieceSide.BLACK);
        board[7][3] = new Queen(PieceSide.BLACK);
        board[7][4] = new King(PieceSide.BLACK);
        board[7][5] = new Bishop(PieceSide.BLACK);
        board[7][6] = new Knight(PieceSide.BLACK);
        board[7][7] = new Rook(PieceSide.BLACK);
        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(PieceSide.BLACK);
        }
    }

    public void printChessboard() {
        String[] rows = {"8", "7", "6", "5", "4", "3", "2", "1"};
        String columns = "   a   b   c   d   e   f   g   h";

        System.out.println("\n\t  => Chess Game <=\n");
        System.out.println(columns);

        for (int row = 0; row < 8; row++) {
            System.out.print(rows[row] + " ");
            for (int column = 0; column < 8; column++) {
                Piece piece = board[row][column];
                String pieceSymbol = (piece == null) ? "   " : piece.getSymbol();

                // Color the squares
                String bgColor = (row + column) % 2 == 0 ? "\u001B[48;5;240m" : "\u001B[48;5;235m";
                String resetColor = "\u001B[0m";

                System.out.print(bgColor + "[" + pieceSymbol + "]" + resetColor);
            }

            System.out.println(" " + rows[row]);
        }

        System.out.println(columns);
    }
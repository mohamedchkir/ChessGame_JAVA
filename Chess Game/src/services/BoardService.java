package services;

import domain.entities.board.Board;
import domain.entities.board.Move;
import domain.entities.board.Piece;
import domain.entities.board.Square;
import domain.entities.pieces.*;
import domain.enums.PieceSide;

import java.util.List;
import java.util.Scanner;

public class BoardService {

    private final Board boardEntity = new Board();
    private final Scanner scanner = new Scanner(System.in);
    private Square[][] board = boardEntity.getBoard();

    private Piece enPassantVulnerable = null;

    public BoardService(Board boardEntity) {
        this.board =boardEntity.getBoard();
        initialBoard();
    }

    public void initialBoard() {

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col] = new Square(col, row);
            }
        }

        board[0][0].setPiece(new Rook(PieceSide.BLACK));
        board[0][1].setPiece(new Knight(PieceSide.BLACK));
        board[0][2].setPiece(new Bishop(PieceSide.BLACK));
        board[0][3].setPiece(new Queen(PieceSide.BLACK));
        board[0][4].setPiece(new King(PieceSide.BLACK));
        board[0][5].setPiece(new Bishop(PieceSide.BLACK));
        board[0][6].setPiece(new Knight(PieceSide.BLACK));
        board[0][7].setPiece(new Rook(PieceSide.BLACK));

        for (int i = 0; i < 8; i++) {
            board[1][i].setPiece(new Pawn(PieceSide.BLACK));
        }

        board[7][0].setPiece(new Rook(PieceSide.WHITE));
        board[7][1].setPiece(new Knight(PieceSide.WHITE));
        board[7][2].setPiece(new Bishop(PieceSide.WHITE));
        board[7][3].setPiece(new Queen(PieceSide.WHITE));
        board[7][4].setPiece(new King(PieceSide.WHITE));
        board[7][5].setPiece(new Bishop(PieceSide.WHITE));
        board[7][6].setPiece(new Knight(PieceSide.WHITE));
        board[7][7].setPiece(new Rook(PieceSide.WHITE));
        for (int i = 0; i < 8; i++) {
            board[6][i].setPiece(new Pawn(PieceSide.WHITE));
        }

    }


    public void printChessboard () {
            String[] rows = {"8", "7", "6", "5", "4", "3", "2", "1"};
            String columns = "   a   b   c   d   e   f   g   h";

            System.out.println("\n\t  => Chess Game <=\n");

            for (int row = 0; row < 8; row++) {
                System.out.print(rows[row] + " ");
                for (int column = 0; column < 8; column++) {
                    Square square = board[row][column];
                    String pieceSymbol = (square.getPiece() == null) ? "   " : square.getPiece().getSymbol();

                    // Color the squares
                    String bgColor = (row + column) % 2 == 0 ? "\u001B[48;5;240m" : "\u001B[48;5;235m";
                    String resetColor = "\u001B[0m";

                    System.out.print(bgColor +  pieceSymbol  +" "+ resetColor);
                }

                System.out.println(" ");
            }

            System.out.println(columns);
    }



    public void movePawn() {
        while (true) {
            System.out.print("Enter the piece (e.g., 'e2'): ");
            String piecePosition = scanner.nextLine().toLowerCase();

            if (!piecePosition.matches("[a-h][1-8]")) {
                System.out.println("Invalid input. Please enter a valid piece position (e.g., 'e2').");
                continue; // Ask for input again
            }

            System.out.print("Enter the target position (e.g., 'e4'): ");
            String targetPosition = scanner.nextLine().toLowerCase();

            if (!targetPosition.matches("[a-h][1-8]")) {
                System.out.println("Invalid input. Please enter a valid target position (e.g., 'e4').");
                continue; // Ask for input again
            }

            int pieceColumn = piecePosition.charAt(0) - 'a';
            int pieceRow = 7 - (piecePosition.charAt(1) - '1');

            int targetColumn = targetPosition.charAt(0) - 'a';
            int targetRow = 7 - (targetPosition.charAt(1) - '1');

            Square chosenSquare = board[pieceRow][pieceColumn];
            Piece chosenPiece = chosenSquare.getPiece();

            if (chosenPiece == null) {
                System.out.println("Invalid move. Please select a square with a piece.");
                continue; // Ask for input again
            }

            List<Square> validMoves = chosenPiece.abilityMoves(board);

            boolean validMoveFound = false;
            for (Square square : validMoves) {
                if (square.getX() == targetColumn && square.getY() == targetRow) {
                    validMoveFound = true;
                    break;
                }
            }

            if (!validMoveFound) {
                System.out.println("Invalid move. The selected piece cannot move to the target position.");
                continue; // Ask for input again
            }

            Square targetSquare = board[targetRow][targetColumn];
            targetSquare.setPiece(chosenPiece);
            chosenSquare.setPiece(null);

            // To change the piece
            if (targetRow == 0 || targetRow == 7) {
                promotePawn(targetSquare);
            }

            // The move was successful; exit the loop
            break;
        }
    }


    private void promotePawn(Square square) {
        System.out.println("Pawn promotion! Enter the piece to promote to (Q, R, N, or B): ");
        String promotionChoice = scanner.nextLine().toUpperCase();

        Piece chosenPiece = square.getPiece();
        Piece newPiece;

        switch (promotionChoice) {
            case "Q":
                newPiece = new Queen(chosenPiece.getPieceSide());
                break;
            case "R":
                newPiece = new Rook(chosenPiece.getPieceSide());
                break;
            case "N":
                newPiece = new Knight(chosenPiece.getPieceSide());
                break;
            case "B":
                newPiece = new Bishop(chosenPiece.getPieceSide());
                break;
            default:
                System.out.println("Invalid promotion choice. Promoting to Queen by default.");
                newPiece = new Queen(chosenPiece.getPieceSide());
        }

        square.setPiece(newPiece);
    }


    public boolean applyMove(Move move) {
        Square sourceSquare = move.getStartPosition();
        Square targetSquare = move.getEndPosition();
        Piece pieceToMove = move.getPiece();
        Piece capturedPiece = move.getCapturedPiece();

        if (!isValidMove(sourceSquare, targetSquare)) {

        // check for en passant
        boolean isTargetInRangeForEnPassant = Math.abs(sourceSquare.getX() - targetSquare.getX()) == 1 && Math.abs(sourceSquare.getY() - targetSquare.getY()) == 1;
        if (pieceToMove instanceof Pawn && capturedPiece == null && isTargetInRangeForEnPassant) {
            int operation = pieceToMove.getPieceSide().equals(PieceSide.WHITE) ? 1 : -1;
            Piece opponentPawn = board[targetSquare.getY() + operation][targetSquare.getX()].getPiece();

            if (opponentPawn == enPassantVulnerable) {
                sourceSquare.setPiece(null);
                pieceToMove.setMoved(true);
                targetSquare.setPiece(pieceToMove);

                opponentPawn.getSquare().setPiece(null);

                return true;
            }
        }

        System.out.println("Invalid move. This piece cannot move to the target square.");
        return false;
    }

    // check for promoted the pawn
        if (pieceToMove instanceof Pawn) {
        if (!pieceToMove.isMoved() && Math.abs(sourceSquare.getY() - targetSquare.getY()) == 2) {
            enPassantVulnerable = pieceToMove;
        } else {
            enPassantVulnerable = null;
        }

        if (targetSquare.getY() == 0 || targetSquare.getY() == 7) {
            ((Pawn) pieceToMove).promotePawn();
        }
    }

        sourceSquare.setPiece(null);
        pieceToMove.setMoved(true);
        targetSquare.setPiece(pieceToMove);

        return true;
}

    public boolean isValidMove(Square sourceSquare, Square targetSquare) {
        Piece pieceToMove = sourceSquare.getPiece();
        List<Square> validMoves = pieceToMove.abilityMoves(board);

        return validMoves.contains(targetSquare);
    }
}




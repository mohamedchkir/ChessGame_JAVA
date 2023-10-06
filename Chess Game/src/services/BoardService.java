package services;

import domain.entities.board.Board;
import domain.entities.board.Move;
import domain.entities.board.Piece;
import domain.entities.board.Square;
import domain.entities.pieces.*;
import domain.enums.PieceSide;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static domain.enums.PieceSide.*;

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

        board[7][0].setPiece(new Rook(WHITE));
        board[7][1].setPiece(new Knight(WHITE));
        board[7][2].setPiece(new Bishop(WHITE));
        board[7][3].setPiece(new Queen(WHITE));
        board[7][4].setPiece(new King(WHITE));
        board[7][5].setPiece(new Bishop(WHITE));
        board[7][6].setPiece(new Knight(WHITE));
        board[7][7].setPiece(new Rook(WHITE));
        for (int i = 0; i < 8; i++) {
            board[6][i].setPiece(new Pawn(WHITE));
        }

    }


    public void printChessboard() {
        String[] rows = {"8", "7", "6", "5", "4", "3", "2", "1"};
        String columns = "   a  b  c  d  e  f  g  h";

        System.out.println("\n\t\tChess Game \n");

        for (int row = 0; row < 8; row++) {
            System.out.print(rows[row] + " ");
            for (int column = 0; column < 8; column++) {
                Square square = board[row][column];
                String pieceSymbol = (square.getPiece() == null) ? "   " : square.getPiece().getSymbol();
                String pieceColor = (square.getPiece() != null && square.getPiece().getPieceSide() == WHITE) ? "\u001B[97m" : "\u001B[30m"; // White text for white pieces, black text for black pieces

                // Color the squares with light and dark colors
                String bgColor = (row + column) % 2 == 0 ? "\u001B[48;5;94m" : "\u001B[48;5;208m";  // Brown and light orange colors
                String resetColor = "\u001B[0m";

                System.out.print(bgColor + pieceColor + pieceSymbol + resetColor );
            }

            System.out.println(" ");
        }

        System.out.println(columns);
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
            int operation = pieceToMove.getPieceSide().equals(WHITE) ? 1 : -1;
            Piece opponentPawn = board[targetSquare.getY() + operation][targetSquare.getX()].getPiece();

            if (opponentPawn == enPassantVulnerable) {
                sourceSquare.setPiece(null);
                pieceToMove.setMoved(true);
                targetSquare.setPiece(pieceToMove);

                opponentPawn.getSquare().setPiece(null);

                return true;
            }
        }

       // Check for castling
            if (pieceToMove instanceof King && capturedPiece instanceof Rook) {
                King king = (King) pieceToMove;

                if (king.possibleToCastle(board, targetSquare)) {
                    int isCastleQueenSide = targetSquare.getX() == 0 ? -1 : 1;
                    board[sourceSquare.getY()][sourceSquare.getX() + 2 * isCastleQueenSide].setPiece(king);
                    sourceSquare.setPiece(null);
                    king.setMoved(true);

                    board[sourceSquare.getY()][sourceSquare.getX() + isCastleQueenSide].setPiece(capturedPiece);
                    targetSquare.setPiece(null);
                    capturedPiece.setMoved(true);

                    return true;
                }
            }

        System.out.println("Invalid move. This piece cannot move to the target square.");
        return false;
    }

    // check for promoted the pawn
        if (pieceToMove instanceof Pawn && !pieceToMove.isMoved() && Math.abs(sourceSquare.getY() - targetSquare.getY()) == 2) {
            enPassantVulnerable = pieceToMove;
        } else {
            enPassantVulnerable = null;
        }

        if (pieceToMove instanceof Pawn && (targetSquare.getY() == 0 || targetSquare.getY() == 7)) {

            ((Pawn) pieceToMove).promotePawn();
    }

        sourceSquare.setPiece(null);
        pieceToMove.setMoved(true);
        targetSquare.setPiece(pieceToMove);

        // check the square after moving the piece
        if (findMyKing(board,pieceToMove.getPieceSide()).onCheck(board,pieceToMove.getPieceSide())){

            System.out.println("You can not move the piece ,you are checked");
            sourceSquare.setPiece(pieceToMove);
            pieceToMove.setMoved(false);
            targetSquare.setPiece(capturedPiece);

            return false ;
        }

        return true;
}

    public boolean isValidMove(Square sourceSquare, Square targetSquare) {
        Piece pieceToMove = sourceSquare.getPiece();
        List<Square> validMoves = pieceToMove.abilityMoves(board);

        return validMoves.contains(targetSquare);

    }


    public Square findMyKing(Square[][] board , PieceSide side){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j].getPiece();

                if (piece instanceof King && side.equals(piece.getPieceSide()) ){
                    return piece.getSquare();
                }

            }
        }
        return null;
    }
}




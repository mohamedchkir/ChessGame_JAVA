package services;

import domain.entities.Game;
import domain.entities.Player;
import domain.entities.board.Board;
import domain.entities.board.Move;
import domain.entities.board.Piece;
import domain.entities.board.Square;
import domain.enums.GameResult;
import domain.enums.PieceSide;

import java.util.List;
import java.util.Scanner;


public class GameService {

    private Game game = new Game();
    private final Board boardEntity = new Board();
    private final BoardService boardService = new BoardService(boardEntity);

    private PieceSide currentPlayer = PieceSide.WHITE;

    public void startGame() {

        // firstly, get names of player's
        String whitePlayer = InputService.getPlayerName("White");
        String blackPlayer = InputService.getPlayerName("Black");

        game.setWhitePlayer(new Player(whitePlayer));
        game.setBlackPlayer(new Player(blackPlayer));
        game.setBoard(boardEntity);

        inGame();

    }

    public void inGame() {


        while (true) {
            boardService.printChessboard();
            System.out.println(getCurrentPlayer().getName()+ " turn's - " + currentPlayer + "\n");

            // Get input from the current player (e.g., source and target squares)
            Square sourceSquare = InputService.getSquareInput(boardEntity);

            // validate that the source square has a piece, and it belongs to the current player
            // restart the loop if the source square is not valid
            if (!checkSquare(sourceSquare)) continue;

            Square targetSquare = InputService.getSquareInput(boardEntity);

            Move move = new Move(sourceSquare, targetSquare, sourceSquare.getPiece(), targetSquare.getPiece());
            if(!boardService.applyMove(move)) continue;

            // Check for game end conditions (e.g., checkmate, stalemate)
            if (gameEnd()) {
                break;
                }

            // Switch to the next player's turn
            switchPlayer();
            }

        }




    private boolean gameEnd() {

        PieceSide opponentSide = (currentPlayer == PieceSide.WHITE) ? PieceSide.BLACK : PieceSide.WHITE;
        Square[][] board = boardEntity.getBoard();

        if (isCheckmate(board, opponentSide)) {
            System.out.println("Checkmate! " + getCurrentPlayer().getName() + " wins the game!");
            game.setResult(currentPlayer.equals(PieceSide.WHITE) ? GameResult.WHITE_WIN : GameResult.BLACK_WIN);
            return true;
        } else if (isStalemate(board, opponentSide)) {
            System.out.println("Stalemate! The game ends in a draw.");
            game.setResult(GameResult.DRAW);
            return true;
        }

        return false;
    }

    public Player getCurrentPlayer(){
        return currentPlayer.equals(PieceSide.WHITE) ? game.getWhitePlayer() : game.getBlackPlayer();
    }

    public void switchPlayer(){
        currentPlayer = currentPlayer.equals(PieceSide.WHITE) ? PieceSide.BLACK : PieceSide.WHITE;
    }

    public boolean checkSquare(Square square){
        if (square.getPiece() == null){

            System.out.println("No piece to move, select another square");

            return false;
        }
        if (square.getPiece().getPieceSide() != currentPlayer){

            System.out.println("please select a piece from your side");

            return false;
        }
        return true;
    }

    // Check if the opponent is in checkmate
    public boolean isCheckmate(Square[][] board, PieceSide side) {
        Square opponentKingSquare = boardService.findMyKing(board,side);

        if (!opponentKingSquare.onCheck(board, side)) {
            return false;
        }

        // Check if any move by the opponent can get out of check
        return isCurrentPlayerHasLegalMoves(board, side);
    }

    // Check if the game is in stalemate
    private boolean isStalemate(Square[][] board, PieceSide side) {
        Square opponentKingSquare = boardService.findMyKing(board,side);

        if (opponentKingSquare.onCheck(board, side)) {
            return false;
        }

        // Check for stalemate
        return isCurrentPlayerHasLegalMoves(board, side);
    }

    // Check if the current player has legal moves to make
    private boolean isCurrentPlayerHasLegalMoves(Square[][] board, PieceSide side) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Square sourceSquare = boardEntity.getSquare(row, col);
                Piece piece = sourceSquare.getPiece();

                if (piece != null && piece.getPieceSide() == side) {
                    List<Square> validMoves = piece.abilityMoves(board);

                    for (Square targetSquare : validMoves) {
                        // Make the move
                        Piece capturedPiece = targetSquare.getPiece();
                        targetSquare.setPiece(piece);
                        sourceSquare.setPiece(null);

                        boolean inCheck = boardService.findMyKing(board,side).onCheck(board, side);

                        // Undo the move
                        sourceSquare.setPiece(piece);
                        targetSquare.setPiece(capturedPiece);

                        if (!inCheck) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}

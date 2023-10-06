package services;

import domain.entities.Game;
import domain.entities.Player;
import domain.entities.board.Board;
import domain.entities.board.Move;
import domain.entities.board.Square;
import domain.enums.PieceSide;

import java.util.Scanner;


public class GameService {

    private Game game = new Game();
    private final Board boardEntity = new Board();
    private final BoardService boardService = new BoardService(boardEntity);

    private PieceSide currentPlayer = PieceSide.WHITE;

    private String whitePlayer ;
    private String blackPlayer;
    public void startGame() {

        // firstly, get names of player's
        whitePlayer = InputService.getPlayerName("White");
        blackPlayer = InputService.getPlayerName("Black");

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
            if (isGameEnd()) {
                break;
                }

            // Switch to the next player's turn
            switchPlayer();
            }

        }




    private boolean isGameEnd() {

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
}

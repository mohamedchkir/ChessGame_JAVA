package services;

import domain.entities.Game;
import domain.entities.board.Board;
import domain.enums.PieceSide;


public class GameService {

    private Game game = new Game();
    private final Board boardEntity = new Board();
    private final BoardService boardService = new BoardService(boardEntity);
    private PieceSide currentPlayer = PieceSide.WHITE;
    public void startGame() {

    }
}

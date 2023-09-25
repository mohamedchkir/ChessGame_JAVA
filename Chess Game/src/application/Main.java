package application;

import services.BoardService;

public class Main {



        public static void main(String[] args) {
            // Create a new instance of the BoardService class
            BoardService boardService = new BoardService();

            // Call methods
            boardService.initialBoard();
            boardService.printChessboard();
            boardService.movePawn();
            boardService.printChessboard();

        }


}
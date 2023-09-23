package application;

import services.BoardService;

public class Main {



        public static void main(String[] args) {
            // Create a new instance of the Board class
            BoardService boardService = new BoardService();

            
            boardService.initialBoard();
            boardService.printChessboard();
            boardService.movePawn();
        }


}
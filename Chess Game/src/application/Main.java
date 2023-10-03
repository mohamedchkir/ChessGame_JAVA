package application;

import services.GameService;

public class Main {

        public static void main(String[] args) {

        GameService service = new GameService();

        service.startGame();
        }


}
package Tests;

import TexasHoldem.Game;

/**
 * Created by Andrew on 02/10/2017.
 */
public class TestGame {
    public static void main(String[] args){
        Game game = new Game();

        game.runGame();
        System.out.println("game.getWinners() = " + game.getWinners());
        System.out.println("game.getResults() = \n" + game.getResults());
    }
}

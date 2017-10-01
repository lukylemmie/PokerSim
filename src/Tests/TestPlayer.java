package Tests;

import HoldemTexas.Card;
import HoldemTexas.Player;

import java.util.ArrayList;

/**
 * Created by Andrew on 01/10/2017.
 */
public class TestPlayer {
    public static void main(String[] args){

    }

    public Boolean testScoring(ArrayList<Card> cards, Integer expectedScore, ArrayList<Card> expectedBestHand){
        Boolean passed = false;
        Player player = new Player();

        player.calculateScoreAndBestHand(cards);
        passed = player.getScore() == expectedScore;


        return passed;
    }
}

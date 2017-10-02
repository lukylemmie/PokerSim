package Tests;

import TexasHoldem.Card;
import TexasHoldem.Player;

import java.util.ArrayList;

/**
 * Created by Andrew on 01/10/2017.
 */
public class TestPlayer {
    public static void main(String[] args){
        TestPlayer testPlayer = new TestPlayer();

        System.out.println("testPlayer.test01() = " + testPlayer.test01());
    }

    public Boolean testScoring(ArrayList<Card> cards, Integer expectedScore, ArrayList<Card> expectedBestHand){
        Boolean passed;
        Player player = new Player();

        player.calculateScoreAndBestHand(cards);
        passed = (player.getScore() == expectedScore);
        passed &= player.getBestHand().toString().equals(expectedBestHand.toString());

        return passed;
    }

    public Boolean test01(){
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Card> expectedBestHand = new ArrayList<>();

        Integer expectedScore = Player.SCORE_HIGH_NUMBER;

        return testScoring(cards, expectedScore, expectedBestHand);
    }
}

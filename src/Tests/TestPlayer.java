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
        System.out.println("testPlayer.test02() = " + testPlayer.test02());
        System.out.println("testPlayer.test03() = " + testPlayer.test03());
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

    public Boolean test02(){
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Card> expectedBestHand = new ArrayList<>();

        cards.add(new Card(0, 2));
        cards.add(new Card(1, 4));
        cards.add(new Card(2, 6));
        cards.add(new Card(3, 8));
        cards.add(new Card(1, 10));
        cards.add(new Card(2, 12));
        cards.add(new Card(3, 3));

        expectedBestHand.add(new Card(2, 12));
        expectedBestHand.add(new Card(1, 10));
        expectedBestHand.add(new Card(3, 8));
        expectedBestHand.add(new Card(2, 6));
        expectedBestHand.add(new Card(1, 4));

        Integer expectedScore = Player.SCORE_HIGH_NUMBER;

        return testScoring(cards, expectedScore, expectedBestHand);
    }

    public Boolean test03(){
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Card> expectedBestHand = new ArrayList<>();

        cards.add(new Card(0, 2));
        cards.add(new Card(1, 4));
        cards.add(new Card(2, 6));
        cards.add(new Card(3, 8));
        cards.add(new Card(1, 10));
        cards.add(new Card(2, 12));
        cards.add(new Card(3, 0));

        expectedBestHand.add(new Card(3, 0));
        expectedBestHand.add(new Card(2, 12));
        expectedBestHand.add(new Card(1, 10));
        expectedBestHand.add(new Card(3, 8));
        expectedBestHand.add(new Card(2, 6));

        Integer expectedScore = Player.SCORE_HIGH_NUMBER;

        return testScoring(cards, expectedScore, expectedBestHand);
    }
}

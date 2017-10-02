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
        System.out.println("testPlayer.test04() = " + testPlayer.test04());
        System.out.println("testPlayer.test05() = " + testPlayer.test05());
        System.out.println("testPlayer.test06() = " + testPlayer.test06());
    }

    public Boolean testScoring(ArrayList<Card> cards, Integer expectedScore, ArrayList<Card> expectedBestHand){
        Boolean passed;
        Player player = new Player();

        player.calculateScoreAndBestHand(cards);
        passed = (player.getScore() == expectedScore);
        passed &= player.getBestHand().toString().equals(expectedBestHand.toString());

        if (!passed){
            System.out.println(player.getBestHand().toString());
            System.out.println(expectedBestHand.toString());
        }

        return passed;
    }

    // testing empty
    public Boolean test01(){
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Card> expectedBestHand = new ArrayList<>();

        Integer expectedScore = Player.SCORE_HIGH_NUMBER;

        return testScoring(cards, expectedScore, expectedBestHand);
    }

    // testing high number score
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

    // testing ace interaction
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

    // testing pair
    public Boolean test04(){
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Card> expectedBestHand = new ArrayList<>();

        cards.add(new Card(0, 2));
        cards.add(new Card(1, 4));
        cards.add(new Card(2, 2));
        cards.add(new Card(3, 8));
        cards.add(new Card(1, 10));
        cards.add(new Card(2, 12));
        cards.add(new Card(3, 0));

        expectedBestHand.add(new Card(0, 2));
        expectedBestHand.add(new Card(2, 2));
        expectedBestHand.add(new Card(3, 0));
        expectedBestHand.add(new Card(2, 12));
        expectedBestHand.add(new Card(1, 10));

        Integer expectedScore = Player.SCORE_PAIR;

        return testScoring(cards, expectedScore, expectedBestHand);
    }

    // testing two pair
    public Boolean test05(){
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Card> expectedBestHand = new ArrayList<>();

        cards.add(new Card(0, 2));
        cards.add(new Card(1, 4));
        cards.add(new Card(2, 2));
        cards.add(new Card(3, 8));
        cards.add(new Card(1, 10));
        cards.add(new Card(2, 4));
        cards.add(new Card(3, 0));

        expectedBestHand.add(new Card(1, 4));
        expectedBestHand.add(new Card(2, 4));
        expectedBestHand.add(new Card(0, 2));
        expectedBestHand.add(new Card(2, 2));
        expectedBestHand.add(new Card(3, 0));

        Integer expectedScore = Player.SCORE_TWO_PAIR;

        return testScoring(cards, expectedScore, expectedBestHand);
    }

    // testing multiple two pair
    public Boolean test06(){
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Card> expectedBestHand = new ArrayList<>();

        cards.add(new Card(0, 2));
        cards.add(new Card(1, 4));
        cards.add(new Card(2, 2));
        cards.add(new Card(3, 6));
        cards.add(new Card(1, 6));
        cards.add(new Card(2, 4));
        cards.add(new Card(3, 0));

        expectedBestHand.add(new Card(1, 6));
        expectedBestHand.add(new Card(3, 6));
        expectedBestHand.add(new Card(1, 4));
        expectedBestHand.add(new Card(2, 4));
        expectedBestHand.add(new Card(3, 0));

        Integer expectedScore = Player.SCORE_TWO_PAIR;

        return testScoring(cards, expectedScore, expectedBestHand);
    }
}

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
        System.out.println("testPlayer.test07() = " + testPlayer.test07());
        System.out.println("testPlayer.test08() = " + testPlayer.test08());
        System.out.println("testPlayer.test09() = " + testPlayer.test09());
        System.out.println("testPlayer.test10() = " + testPlayer.test10());
        System.out.println("testPlayer.test11() = " + testPlayer.test11());
        System.out.println("testPlayer.test12() = " + testPlayer.test12());
        System.out.println("testPlayer.test13() = " + testPlayer.test13());
        System.out.println("testPlayer.test14() = " + testPlayer.test14());
        System.out.println("testPlayer.test15() = " + testPlayer.test15());
        System.out.println("testPlayer.test16() = " + testPlayer.test16());
        System.out.println("testPlayer.test17() = " + testPlayer.test17());
        System.out.println("testPlayer.test18() = " + testPlayer.test18());
        System.out.println("testPlayer.test19() = " + testPlayer.test19());
        System.out.println("testPlayer.test20() = " + testPlayer.test20());
        System.out.println("testPlayer.test21() = " + testPlayer.test21());
        System.out.println("testPlayer.test22() = " + testPlayer.test22());
        System.out.println("testPlayer.test23() = " + testPlayer.test23());
    }

    public Boolean testScoring(ArrayList<Card> cards, Integer expectedScore, ArrayList<Card> expectedBestHand){
        Boolean passed;
        Player player = new Player();

        player.calculateScoreAndBestHand(cards);
        passed = (player.getScore() == expectedScore);
        passed &= player.getBestHand().toString().equals(expectedBestHand.toString());

        if (!passed){
            System.out.println("player.getScore() = " + player.getScore());
            System.out.println(player.getBestHand().toString());
            System.out.println("expectedScore = " + expectedScore);
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

    // testing 3 of a kind
    public Boolean test07(){
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Card> expectedBestHand = new ArrayList<>();

        cards.add(new Card(0, 2));
        cards.add(new Card(1, 4));
        cards.add(new Card(2, 6));
        cards.add(new Card(3, 8));
        cards.add(new Card(1, 2));
        cards.add(new Card(2, 2));
        cards.add(new Card(3, 0));

        expectedBestHand.add(new Card(0, 2));
        expectedBestHand.add(new Card(1, 2));
        expectedBestHand.add(new Card(2, 2));
        expectedBestHand.add(new Card(3, 0));
        expectedBestHand.add(new Card(3, 8));

        Integer expectedScore = Player.SCORE_3_OF_A_KIND;

        return testScoring(cards, expectedScore, expectedBestHand);
    }

    // testing full house
    public Boolean test08(){
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Card> expectedBestHand = new ArrayList<>();

        cards.add(new Card(0, 2));
        cards.add(new Card(1, 4));
        cards.add(new Card(2, 6));
        cards.add(new Card(3, 4));
        cards.add(new Card(1, 2));
        cards.add(new Card(2, 2));
        cards.add(new Card(3, 0));

        expectedBestHand.add(new Card(0, 2));
        expectedBestHand.add(new Card(1, 2));
        expectedBestHand.add(new Card(2, 2));
        expectedBestHand.add(new Card(1, 4));
        expectedBestHand.add(new Card(3, 4));

        Integer expectedScore = Player.SCORE_FULL_HOUSE;

        return testScoring(cards, expectedScore, expectedBestHand);
    }

    // testing full house, triple and two pairs
    public Boolean test09(){
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Card> expectedBestHand = new ArrayList<>();

        cards.add(new Card(0, 2));
        cards.add(new Card(1, 4));
        cards.add(new Card(2, 6));
        cards.add(new Card(3, 4));
        cards.add(new Card(1, 2));
        cards.add(new Card(2, 2));
        cards.add(new Card(3, 6));

        expectedBestHand.add(new Card(0, 2));
        expectedBestHand.add(new Card(1, 2));
        expectedBestHand.add(new Card(2, 2));
        expectedBestHand.add(new Card(2, 6));
        expectedBestHand.add(new Card(3, 6));

        Integer expectedScore = Player.SCORE_FULL_HOUSE;

        return testScoring(cards, expectedScore, expectedBestHand);
    }

    // testing full house, 2 triples
    public Boolean test10(){
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Card> expectedBestHand = new ArrayList<>();

        cards.add(new Card(0, 2));
        cards.add(new Card(1, 4));
        cards.add(new Card(2, 6));
        cards.add(new Card(3, 4));
        cards.add(new Card(1, 2));
        cards.add(new Card(2, 2));
        cards.add(new Card(2, 4));

        expectedBestHand.add(new Card(1, 4));
        expectedBestHand.add(new Card(2, 4));
        expectedBestHand.add(new Card(3, 4));
        expectedBestHand.add(new Card(0, 2));
        expectedBestHand.add(new Card(1, 2));

        Integer expectedScore = Player.SCORE_FULL_HOUSE;

        return testScoring(cards, expectedScore, expectedBestHand);
    }

    // testing straight
    public Boolean test11(){
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Card> expectedBestHand = new ArrayList<>();

        cards.add(new Card(0, 2));
        cards.add(new Card(1, 5));
        cards.add(new Card(2, 3));
        cards.add(new Card(3, 4));
        cards.add(new Card(1, 2));
        cards.add(new Card(2, 2));
        cards.add(new Card(2, 6));

        expectedBestHand.add(new Card(2, 6));
        expectedBestHand.add(new Card(1, 5));
        expectedBestHand.add(new Card(3, 4));
        expectedBestHand.add(new Card(2, 3));
        expectedBestHand.add(new Card(0, 2));

        Integer expectedScore = Player.SCORE_STRAIGHT;

        return testScoring(cards, expectedScore, expectedBestHand);
    }

    // testing straight2
    public Boolean test12(){
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Card> expectedBestHand = new ArrayList<>();

        cards.add(new Card(0, 3));
        cards.add(new Card(1, 5));
        cards.add(new Card(2, 3));
        cards.add(new Card(3, 4));
        cards.add(new Card(1, 2));
        cards.add(new Card(2, 2));
        cards.add(new Card(2, 6));

        expectedBestHand.add(new Card(2, 6));
        expectedBestHand.add(new Card(1, 5));
        expectedBestHand.add(new Card(3, 4));
        expectedBestHand.add(new Card(0, 3));
        expectedBestHand.add(new Card(1, 2));

        Integer expectedScore = Player.SCORE_STRAIGHT;

        return testScoring(cards, expectedScore, expectedBestHand);
    }

    // testing flush
    public Boolean test13(){
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Card> expectedBestHand = new ArrayList<>();

        cards.add(new Card(0, 2));
        cards.add(new Card(0, 4));
        cards.add(new Card(0, 6));
        cards.add(new Card(0, 8));
        cards.add(new Card(0, 10));
        cards.add(new Card(0, 12));
        cards.add(new Card(0, 0));

        expectedBestHand.add(new Card(0, 0));
        expectedBestHand.add(new Card(0, 12));
        expectedBestHand.add(new Card(0, 10));
        expectedBestHand.add(new Card(0, 8));
        expectedBestHand.add(new Card(0, 6));

        Integer expectedScore = Player.SCORE_FLUSH;

        return testScoring(cards, expectedScore, expectedBestHand);
    }

    // testing flush2
    public Boolean test14(){
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Card> expectedBestHand = new ArrayList<>();

        cards.add(new Card(0, 2));
        cards.add(new Card(0, 4));
        cards.add(new Card(0, 6));
        cards.add(new Card(0, 8));
        cards.add(new Card(0, 10));
        cards.add(new Card(1, 12));
        cards.add(new Card(1, 0));

        expectedBestHand.add(new Card(0, 10));
        expectedBestHand.add(new Card(0, 8));
        expectedBestHand.add(new Card(0, 6));
        expectedBestHand.add(new Card(0, 4));
        expectedBestHand.add(new Card(0, 2));

        Integer expectedScore = Player.SCORE_FLUSH;

        return testScoring(cards, expectedScore, expectedBestHand);
    }

    // testing flush3
    public Boolean test15(){
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Card> expectedBestHand = new ArrayList<>();

        cards.add(new Card(0, 2));
        cards.add(new Card(2, 0));
        cards.add(new Card(0, 6));
        cards.add(new Card(1, 0));
        cards.add(new Card(0, 10));
        cards.add(new Card(0, 12));
        cards.add(new Card(0, 0));

        expectedBestHand.add(new Card(0, 0));
        expectedBestHand.add(new Card(0, 12));
        expectedBestHand.add(new Card(0, 10));
        expectedBestHand.add(new Card(0, 6));
        expectedBestHand.add(new Card(0, 2));

        Integer expectedScore = Player.SCORE_FLUSH;

        return testScoring(cards, expectedScore, expectedBestHand);
    }

    // testing full house, 2 triples and triple ace
    public Boolean test16(){
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Card> expectedBestHand = new ArrayList<>();

        cards.add(new Card(0, 2));
        cards.add(new Card(1, 0));
        cards.add(new Card(2, 6));
        cards.add(new Card(3, 0));
        cards.add(new Card(1, 2));
        cards.add(new Card(2, 2));
        cards.add(new Card(2, 0));

        expectedBestHand.add(new Card(1, 0));
        expectedBestHand.add(new Card(2, 0));
        expectedBestHand.add(new Card(3, 0));
        expectedBestHand.add(new Card(0, 2));
        expectedBestHand.add(new Card(1, 2));

        Integer expectedScore = Player.SCORE_FULL_HOUSE;

        return testScoring(cards, expectedScore, expectedBestHand);
    }

    // testing 4 of a kind
    public Boolean test17(){
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Card> expectedBestHand = new ArrayList<>();

        cards.add(new Card(0, 2));
        cards.add(new Card(1, 3));
        cards.add(new Card(2, 6));
        cards.add(new Card(3, 2));
        cards.add(new Card(1, 2));
        cards.add(new Card(2, 0));
        cards.add(new Card(2, 2));

        expectedBestHand.add(new Card(0, 2));
        expectedBestHand.add(new Card(1, 2));
        expectedBestHand.add(new Card(2, 2));
        expectedBestHand.add(new Card(3, 2));
        expectedBestHand.add(new Card(2, 0));

        Integer expectedScore = Player.SCORE_4_OF_A_KIND;

        return testScoring(cards, expectedScore, expectedBestHand);
    }

    // testing straight ace 5 high
    public Boolean test18(){
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Card> expectedBestHand = new ArrayList<>();

        cards.add(new Card(0, 3));
        cards.add(new Card(1, 4));
        cards.add(new Card(2, 3));
        cards.add(new Card(3, 4));
        cards.add(new Card(1, 2));
        cards.add(new Card(2, 0));
        cards.add(new Card(3, 1));

        expectedBestHand.add(new Card(1, 4));
        expectedBestHand.add(new Card(0, 3));
        expectedBestHand.add(new Card(1, 2));
        expectedBestHand.add(new Card(3, 1));
        expectedBestHand.add(new Card(2, 0));

        Integer expectedScore = Player.SCORE_STRAIGHT;

        return testScoring(cards, expectedScore, expectedBestHand);
    }

    // testing straight 10 ace high
    public Boolean test19(){
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Card> expectedBestHand = new ArrayList<>();

        cards.add(new Card(0, 10));
        cards.add(new Card(1, 9));
        cards.add(new Card(2, 3));
        cards.add(new Card(3, 4));
        cards.add(new Card(1, 12));
        cards.add(new Card(2, 0));
        cards.add(new Card(3, 11));

        expectedBestHand.add(new Card(2, 0));
        expectedBestHand.add(new Card(1, 12));
        expectedBestHand.add(new Card(3, 11));
        expectedBestHand.add(new Card(0, 10));
        expectedBestHand.add(new Card(1, 9));

        Integer expectedScore = Player.SCORE_STRAIGHT;

        return testScoring(cards, expectedScore, expectedBestHand);
    }

    // testing 4 of a kind2
    public Boolean test20(){
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Card> expectedBestHand = new ArrayList<>();

        cards.add(new Card(0, 2));
        cards.add(new Card(1, 0));
        cards.add(new Card(3, 0));
        cards.add(new Card(3, 2));
        cards.add(new Card(1, 2));
        cards.add(new Card(2, 0));
        cards.add(new Card(2, 2));

        expectedBestHand.add(new Card(0, 2));
        expectedBestHand.add(new Card(1, 2));
        expectedBestHand.add(new Card(2, 2));
        expectedBestHand.add(new Card(3, 2));
        expectedBestHand.add(new Card(1, 0));

        Integer expectedScore = Player.SCORE_4_OF_A_KIND;

        return testScoring(cards, expectedScore, expectedBestHand);
    }

    // testing straight ace 5 high flush
    public Boolean test21(){
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Card> expectedBestHand = new ArrayList<>();

        cards.add(new Card(0, 3));
        cards.add(new Card(1, 4));
        cards.add(new Card(3, 3));
        cards.add(new Card(3, 4));
        cards.add(new Card(3, 2));
        cards.add(new Card(3, 0));
        cards.add(new Card(3, 1));

        expectedBestHand.add(new Card(3, 4));
        expectedBestHand.add(new Card(3, 3));
        expectedBestHand.add(new Card(3, 2));
        expectedBestHand.add(new Card(3, 1));
        expectedBestHand.add(new Card(3, 0));

        Integer expectedScore = Player.SCORE_STRAIGHT_FLUSH;

        return testScoring(cards, expectedScore, expectedBestHand);
    }

    // testing straight 10 ace high flush
    public Boolean test22(){
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Card> expectedBestHand = new ArrayList<>();

        cards.add(new Card(3, 10));
        cards.add(new Card(3, 9));
        cards.add(new Card(2, 3));
        cards.add(new Card(3, 4));
        cards.add(new Card(3, 12));
        cards.add(new Card(3, 0));
        cards.add(new Card(3, 11));

        expectedBestHand.add(new Card(3, 0));
        expectedBestHand.add(new Card(3, 12));
        expectedBestHand.add(new Card(3, 11));
        expectedBestHand.add(new Card(3, 10));
        expectedBestHand.add(new Card(3, 9));

        Integer expectedScore = Player.SCORE_STRAIGHT_FLUSH;

        return testScoring(cards, expectedScore, expectedBestHand);
    }

    // testing straight ace 7 high flush
    public Boolean test23(){
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Card> expectedBestHand = new ArrayList<>();

        cards.add(new Card(3, 6));
        cards.add(new Card(3, 5));
        cards.add(new Card(3, 3));
        cards.add(new Card(3, 4));
        cards.add(new Card(3, 2));
        cards.add(new Card(3, 0));
        cards.add(new Card(3, 1));

        expectedBestHand.add(new Card(3, 6));
        expectedBestHand.add(new Card(3, 5));
        expectedBestHand.add(new Card(3, 4));
        expectedBestHand.add(new Card(3, 3));
        expectedBestHand.add(new Card(3, 2));

        Integer expectedScore = Player.SCORE_STRAIGHT_FLUSH;

        return testScoring(cards, expectedScore, expectedBestHand);
    }
}

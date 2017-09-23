package HoldemTexas;

import java.util.ArrayList;

/**
 * Created by Andrew on 23/09/2017.
 */
public class Player {
    public static final int HAND_SIZE = 5;
    public static final int QUADRUPLE = 4;
    public static final int TRIPLE = 3;
    public static final int PAIR = 2;


    private ArrayList<Card> hand;
    private int[] suiteStats = new int[Card.MAX_SUITE];
    private int[] numberStats = new int[Card.MAX_NUMBER];
    private boolean straight = false;
    private boolean flush = false;
    private boolean quadruple = false;
    private boolean triple = false;
    private boolean pair = false;
    private boolean twoPair = false;
    private int highestNumberOfHand = -1;

    private int score = -1;
    /*
        scoring
        -1 = undefined
         0 = high number
         1 = pair
         2 = two pair
         3 = 3 of a kind
         4 = straight
         5 = flush
         6 = full house
         7 = 4 of a kind
         8 = straight flush
    */
    public static final int SCORE_UNDEFINED = -1;
    public static final int SCORE_HIGH_NUMBER = 0;
    public static final int SCORE_PAIR = 1;
    public static final int SCORE_TWO_PAIR = 2;
    public static final int SCORE_3_OF_A_KIND = 3;
    public static final int SCORE_STRAIGHT = 4;
    public static final int SCORE_FLUSH = 5;
    public static final int SCORE_FULL_HOUSE = 6;
    public static final int SCORE_4_OF_A_KIND = 7;
    public static final int SCORE_STRAIGHT_FLUSH = 8;


    public Player(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public int calculateScore(ArrayList<Card> table){
        hand.addAll(table);

        for(Card card : hand){
            suiteStats[card.getSuite()]++;
            numberStats[card.getNumber()]++;
        }

        return score;
    }

    public int getHighestNumberOfHand() {
        return highestNumberOfHand;
    }

    public int getScore() {
        return score;
    }
}

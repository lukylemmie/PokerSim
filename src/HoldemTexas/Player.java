package HoldemTexas;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Andrew on 23/09/2017.
 */
public class Player {
    public static final int HAND_SIZE = 5;
    public static final int QUADRUPLE = 4;
    public static final int TRIPLE = 3;
    public static final int PAIR = 2;

    private ArrayList<Card> hand = new ArrayList<>();
    private ArrayList<Card> bestHand = new ArrayList<>();
    private Card highestNumberOfHand;

    private ArrayList<Card>[] suiteStats = new ArrayList[Card.MAX_SUITE];
    private ArrayList<Card>[] numberStats = new ArrayList[Card.MAX_NUMBER + 1];
    private Boolean multiStraight = false;
    private ArrayList<Card> bestStraight = new ArrayList<>();
    private Boolean multiFlush = false;
    private ArrayList<Card> bestFlush = new ArrayList<>();
    private Card quadruple = null;
    private Card triple = null;
    private Card pair = null;
    private Card twoPair = null;

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

    public Player(){

    }

    public void addCard(Card card){
        card.setInHand(true);
        hand.add(card);
    }

    public void addCards(ArrayList<Card> cards){
        for(Card card : cards){
            card.setInHand(true);
        }
        hand.addAll(cards);
    }

    public int calculateScore(ArrayList<Card> table){
        hand.addAll(table);
        Collections.sort(hand, new Card.CardComparatorDescending());

        for(Card card : hand){
            suiteStats[card.getSuite()].add(card);
            numberStats[card.getNumber()].add(card);
            if (card.getNumber() == 0){
                numberStats[Card.MAX_NUMBER].add(card);
            }
        }

        // check for flush
        playerHasFlush();
        if (!bestFlush.isEmpty()){
            if (score < SCORE_FLUSH){
                score = SCORE_FLUSH;
                bestHand = bestFlush;
            }
        }

        // check for straight
        playerHasStraight();
        if (!bestStraight.isEmpty()){
            if (score < SCORE_STRAIGHT){
                score = SCORE_STRAIGHT;
                bestHand = bestStraight;
            }
        }

        // check for straight flush
        if (!bestStraight.isEmpty() && !bestFlush.isEmpty()){
            ArrayList<Card> cards = hasStraight(suiteStats[bestFlush.get(0).getSuite()]);
            if (!cards.isEmpty() && score < SCORE_STRAIGHT_FLUSH){
                score = SCORE_STRAIGHT_FLUSH;
                bestHand = cards;
            }
        }

        // check for 4 of a kind
        for (int i = 0; i < Card.MAX_NUMBER + 1; i++) {
            if (numberStats[i].size() == 4){
                quadruple = numberStats[i].get(0);
                if (score < SCORE_4_OF_A_KIND){
                    score = SCORE_4_OF_A_KIND;
                    bestHand.clear();
                    bestHand.addAll(numberStats[i]);
                    Boolean kickerAdded = false;
                    int j = Card.MAX_NUMBER;
                    while (!kickerAdded){
                        if (j != i && !numberStats[j].isEmpty()){
                            kickerAdded = true;
                            bestHand.add(numberStats[j].get(0));
                        }
                    }
                }
            }
        }

        return score;
    }

    public int getHighestNumberOfHand() {
        return highestNumberOfHand.getNumber();
    }

    public int getScore() {
        return score;
    }


    // null if no straight, straight hand if straight exists
    private ArrayList<Card> hasStraight(ArrayList<Card> cards){
        ArrayList<Card> straightHand = new ArrayList<>();
        ArrayList<Card>[] numbers = new ArrayList[Card.MAX_NUMBER + 1];

        for(Card card : cards){
            numbers[card.getNumber()].add(card);
            if (card.getNumber() == 0){
                numbers[Card.MAX_NUMBER].add(card);
            }
        }

        for (int i = 0; i <= Card.MAX_NUMBER - HAND_SIZE; i++) {
            Boolean straight = true;
            for (int j = 0; straight && j < HAND_SIZE; j++) {
                straight &= !numbers[i].isEmpty();
            }
            if (straight){
                if (!straightHand.isEmpty()){
                    straightHand.clear();
                }
                for (int j = 0; j < HAND_SIZE; j++) {
                    straightHand.add(numbers[i + j].get(0));
                }
            }
        }

        return straightHand;
    }

    private void playerHasFlush(){
        for (ArrayList<Card> cards : suiteStats) {
            if (cards.size() >= HAND_SIZE) {
                if (cards.size() > HAND_SIZE) {
                    multiFlush = true;
                }
                for (int i = 0; i < HAND_SIZE; i++) {
                    bestFlush.add(cards.get(i));
                }
            }
        }
    }

    private void playerHasStraight(){
        for (int i = 0; i < Card.MAX_NUMBER + 1 - HAND_SIZE; i++) {
            Boolean straight = true;
            for (int j = 0; straight && j < HAND_SIZE; j++) {
                straight &= !numberStats[i].isEmpty();
            }
            if (straight){
                if (!bestStraight.isEmpty()){
                    multiStraight = true;
                    bestStraight.clear();
                }
                for (int j = 0; j < HAND_SIZE; j++) {
                    bestStraight.add(numberStats[i + j].get(0));
                }
            }
        }
    }
}

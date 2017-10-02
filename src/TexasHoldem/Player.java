package TexasHoldem;

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

    private ArrayList<Card>[] suiteStats = new ArrayList[Card.MAX_SUITE];
    private ArrayList<Card>[] numberStats = new ArrayList[Card.MAX_NUMBER + 1];
    private Boolean multiStraight = false;
    private ArrayList<Card> bestStraight = new ArrayList<>();
    private Boolean multiFlush = false;
    private ArrayList<Card> bestFlush = new ArrayList<>();
    private Card quadruple = null;
    private Card triple = null;
    private Card twoTriple = null;
    private Card pair = null;
    private Card twoPair = null;

    private int score = SCORE_UNDEFINED;
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
        for (int i = 0; i < Card.MAX_SUITE; i++) {
            suiteStats[i] = new ArrayList<>();
        }
        for (int i = 0; i < Card.MAX_NUMBER + 1; i++) {
            numberStats[i] = new ArrayList<>();
        }
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

    public void calculateScoreAndBestHand(ArrayList<Card> table){
        ArrayList<Card> cardPool = new ArrayList<>();
        cardPool.addAll(table);
        cardPool.addAll(hand);
        Collections.sort(cardPool, new Card.CardComparatorDescending());

        for(Card card : cardPool){
            suiteStats[card.getSuite()].add(card);
            numberStats[card.getNumber()].add(card);
            if (card.getNumber() == 0){
                numberStats[Card.MAX_NUMBER].add(card);
            }
        }


        // check for straight flush
        if (score < SCORE_STRAIGHT_FLUSH) {

            // check for flush
            playerHasFlush();
            if (!bestFlush.isEmpty()) {
                if (score < SCORE_FLUSH) {
                    score = SCORE_FLUSH;
                    bestHand = bestFlush;
                }
            }

            // check for straight
            playerHasStraight();
            if (!bestStraight.isEmpty()) {
                if (score < SCORE_STRAIGHT) {
                    score = SCORE_STRAIGHT;
                    bestHand = bestStraight;
                }
            }

            if (!bestStraight.isEmpty() && !bestFlush.isEmpty()) {
                ArrayList<Card> cards = hasStraight(suiteStats[bestFlush.get(0).getSuite()]);
                if (!cards.isEmpty()) {
                    score = SCORE_STRAIGHT_FLUSH;
                    bestHand = cards;
                }
            }

        }


        for (int i = 0; i < Card.MAX_NUMBER + 1; i++) {
            // check for 4 of a kind
            if (score < SCORE_4_OF_A_KIND){
                if (numberStats[i].size() == 4){
                    quadruple = numberStats[i].get(0);
                    score = SCORE_4_OF_A_KIND;
                    bestHand.clear();
                    bestHand.addAll(numberStats[quadruple.getNumber()]);
                    Boolean kickerAdded = false;
                    int j = Card.MAX_NUMBER;
                    while (j > -1 && !kickerAdded){
                        if (j != i && !numberStats[j].isEmpty()){
                            kickerAdded = true;
                            bestHand.add(numberStats[j].get(0));
                        }
                        j--;
                    }
                }
            }

            if (score < SCORE_FULL_HOUSE) {
                // check for 3 of a kind
                if (numberStats[i].size() == 3){
                    if (triple != null){
                        twoTriple = triple;
                    }
                    triple = numberStats[i].get(0);
                    if (score < SCORE_3_OF_A_KIND){
                        score = SCORE_3_OF_A_KIND;
                        int kickerCount = 0;
                        bestHand.clear();
                        bestHand.addAll(numberStats[i]);
                        int j = Card.MAX_NUMBER;
                        while(j > -1 && kickerCount < HAND_SIZE - 3){
                            if (j != i && !numberStats[j].isEmpty()){
                                kickerCount++;
                                bestHand.add(numberStats[j].get(0));
                            }
                            j--;
                        }
                    }
                }

                // check for pair and two pair
                if (numberStats[i].size() == 2){
                    if (pair != null){
                        twoPair = pair;
                        if (score <= SCORE_TWO_PAIR){
                            score = SCORE_TWO_PAIR;
                            Boolean kickerAdded = false;
                            bestHand.clear();
                            bestHand.addAll(numberStats[i]);
                            bestHand.addAll(numberStats[twoPair.getNumber()]);
                            int j = Card.MAX_NUMBER;
                            while(j > -1 && !kickerAdded){
                                if (j != i && j != twoPair.getNumber() && !numberStats[j].isEmpty()){
                                    kickerAdded = true;
                                    bestHand.add(numberStats[j].get(0));
                                }
                                j--;
                            }
                        }
                    }
                    pair = numberStats[i].get(0);
                    if (score < SCORE_PAIR){
                        score = SCORE_PAIR;
                        int kickerCount = 0;
                        bestHand.clear();
                        bestHand.addAll(numberStats[i]);
                        int j = Card.MAX_NUMBER;
                        while(j > -1 && kickerCount < (HAND_SIZE - 2)){
                            if (j != i && !numberStats[j].isEmpty()){
                                kickerCount++;
                                bestHand.add(numberStats[j].get(0));
                            }
                            j--;
                        }
                    }
                }
            }
        }

        // check for full house
        if (score < SCORE_FULL_HOUSE) {
            if (twoTriple != null && pair != null){
                ArrayList<Card> cards = new ArrayList<>();
                score = SCORE_FULL_HOUSE;
                cards.addAll(numberStats[twoTriple.getNumber()]);
                cards.addAll(numberStats[triple.getNumber()]);
                cards.addAll(numberStats[pair.getNumber()]);
                Collections.sort(cards, new Card.CardComparatorDescending());
                bestHand.clear();
                for (int i = 0; i < HAND_SIZE; i++) {
                    bestHand.add(cards.get(i));
                }
            } else if (twoTriple != null){
                ArrayList<Card> cards = new ArrayList<>();
                score = SCORE_FULL_HOUSE;
                cards.addAll(numberStats[twoTriple.getNumber()]);
                cards.addAll(numberStats[triple.getNumber()]);
                Collections.sort(cards, new Card.CardComparatorDescending());
                bestHand.clear();
                for (int i = 0; i < HAND_SIZE; i++) {
                    bestHand.add(cards.get(i));
                }
            } else if (triple != null && pair != null){
                score = SCORE_FULL_HOUSE;
                bestHand.clear();
                bestHand.addAll(numberStats[triple.getNumber()]);
                bestHand.addAll(numberStats[pair.getNumber()]);
            }
        }

        // no combinations
        if (score < SCORE_HIGH_NUMBER){
            score = SCORE_HIGH_NUMBER;
            bestHand.clear();
            for (int i = 0; i < HAND_SIZE; i++) {
                if (cardPool.size() >= HAND_SIZE) {
                    bestHand.add(cardPool.get(i));
                }
            }
        }
    }

    public int getScore() {
        return score;
    }

    public ArrayList<Card> getBestHand() {
        return bestHand;
    }

    public ArrayList<Card> getHand() {
        return hand;
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
            for (int j = i; j < i + HAND_SIZE; j++) {
                straight &= !numberStats[j].isEmpty();
            }
            if (straight){
                if (!bestStraight.isEmpty()){
                    multiStraight = true;
                    bestStraight.clear();
                }
                for (int j = HAND_SIZE - 1; j >= 0; j--) {
                    bestStraight.add(numberStats[i + j].get(0));
                }
            }
        }
    }
}

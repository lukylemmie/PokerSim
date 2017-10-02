package TexasHoldem;

import java.util.Comparator;

/**
 * Created by Andrew on 23/09/2017.
 */
public class Card {
    public static final Character[] SUITES = {'s', 'h', 'c', 'd'};
    public static final String[] NUMBERS = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    public static final int MAX_SUITE = 4;
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 13;

    private final Integer suite;  // Spades (0), Hearts (1), Clubs (2), Diamonds (3)
    private final Integer number;  // 0 - 12
    private boolean inHand = false;


    public Card(int suite, int number) {
        this.suite = suite;
        this.number = number;
    }

    public int getSuite() {
        return suite;
    }

    public int getNumber() {
        return number;
    }

    public String toString(){
        return SUITES[suite].toString() + NUMBERS[number];
    }

    public boolean isInHand() {
        return inHand;
    }

    public void setInHand(boolean inHand) {
        this.inHand = inHand;
    }

    // negative if card1 < card2
    // 0 if same
    // positive if card1 > card2
    // does not compare suite
    public static int compare(Card card1, Card card2){
        Integer isLarger = card1.getNumber() - card2.getNumber();
        if(isLarger != 0 && card2.getNumber() == 0){
            isLarger = -1;
        } else if(isLarger != 0 && card1.getNumber() == 0){
            isLarger = 1;
        }

        if (isLarger == 0 && card2.isInHand()){
            isLarger = -1;
        } else if (isLarger == 0 && card1.isInHand()){
            isLarger = 1;
        }

        return isLarger;
    }

    // negative if card1 > card2
    // 0 if same
    // positive if card1 < card2
    // does not compare suite
    public static int compareDesc(Card card1, Card card2){
        Integer isLarger = card2.getNumber() - card1.getNumber();
        if(isLarger != 0 && card2.getNumber() == 0){
            isLarger = 1;
        } else if(isLarger != 0 && card1.getNumber() == 0){
            isLarger = -1;
        }

        if (isLarger == 0 && card2.isInHand()){
            isLarger = 1;
        } else if (isLarger == 0 && card1.isInHand()){
            isLarger = -1;
        }

        return isLarger;
    }

    /*
    returns negative if card1 < card2
    returns 0 if card1 == card2
    returns positive if card1 > card2
    compares suites
     */
    public static class CardComparatorAscending implements Comparator<Card>{
        public int compare(Card card1, Card card2){
            int isLarger = card1.getNumber() - card2.getNumber();

            if (isLarger != 0) {
                if (card2.getNumber() == 0) {
                    isLarger = -1;
                } else if (card1.getNumber() == 0) {
                    isLarger = 1;
                }
            } else {
                if (card2.isInHand()) {
                    isLarger = -1;
                } else if (card1.isInHand()) {
                    isLarger = 1;
                } else {
                    isLarger = card1.getSuite() - card2.getSuite();
                }
            }

            return isLarger;
        }
    }

    /*
    returns negative if card1 > card2
    returns 0 if card1 == card2
    returns positive if card1 < card2
    compares suites
     */
    public static class CardComparatorDescending implements Comparator<Card>{
        public int compare(Card card1, Card card2){
            int isLarger = card2.getNumber() - card1.getNumber();

            if (isLarger != 0) {
                if (card2.getNumber() == 0) {
                    isLarger = 1;
                } else if (card1.getNumber() == 0) {
                    isLarger = -1;
                }
            } else {
                if (card2.isInHand()) {
                    isLarger = 1;
                } else if (card1.isInHand()) {
                    isLarger = -1;
                } else {
                    isLarger = card1.getSuite() - card2.getSuite();
                }
            }

            return isLarger;
        }
    }
}

package HoldemTexas;

import java.util.Comparator;

/**
 * Created by Andrew on 23/09/2017.
 */
public class Card {
    public static final Character[] SUITES = {'s', 'h', 'c', 'd'};
    public static final int MAX_SUITE = 4;
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 13;

    private final Integer suite;  // Spades (0), Hearts (1), Clubs (2), Diamonds (3)
    private final Integer number;  // 0 - 12
    private boolean onTable = false;
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
        return SUITES[suite].toString() + (number + 1);
    }

    public boolean isOnTable() {
        return onTable;
    }

    public void setOnTable(boolean onTable) {
        this.onTable = onTable;
    }

    public boolean isInHand() {
        return inHand;
    }

    public void setInHand(boolean inHand) {
        this.inHand = inHand;
    }

    /*
    returns negative if this < card
    returns 0 if this == card
    returns positive if this > card
     */
    static class CardComparatorAscending implements Comparator<Card>{
        public int compare(Card card1, Card card2){
            int isLarger = card1.getNumber() - card2.getNumber();
            if(isLarger != 0 && card1.getNumber() == 0){
                isLarger = 1;
            } else if(isLarger != 0 && card2.getNumber() == 0){
                isLarger = -1;
            }

            return isLarger;
        }
    }

    /*
    returns negative if this > card
    returns 0 if this == card
    returns positive if this < card
     */
    static class CardComparatorDescending implements Comparator<Card>{
        public int compare(Card card1, Card card2){
            int isLarger = card2.getNumber() - card1.getNumber();
            if(isLarger != 0 && card2.getNumber() == 0){
                isLarger = 1;
            } else if(isLarger != 0 && card1.getNumber() == 0){
                isLarger = -1;
            }

            return isLarger;
        }
    }
}

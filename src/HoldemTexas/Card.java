package HoldemTexas;

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
}

package HoldemTexas;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Andrew on 23/09/2017.
 */
public class Deck {
    ArrayList<Card> deck = new ArrayList<>();

    public Deck(){
        for (int suite = 0; suite < Card.MAX_SUITE; suite++) {
            for (int number = 0; number < Card.MAX_NUMBER; number++) {
                deck.add(new Card(suite, number));
            }
        }
    }

    public void shuffle(){
        Collections.shuffle(deck);
    }

    public Card drawCard(){
        return deck.remove(0);
    }

    public String toString(){
        return deck.toString();
    }
}

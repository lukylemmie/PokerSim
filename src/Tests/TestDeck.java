package Tests;

import HoldemTexas.Deck;

/**
 * Created by Andrew on 23/09/2017.
 */
public class TestDeck {

    public static void main(String argv[]){
        Deck deck = new Deck();

        System.out.println(deck);

        deck.shuffle();

        System.out.println(deck);
    }
}

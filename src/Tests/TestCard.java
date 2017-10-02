package Tests;

import TexasHoldem.Card;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Andrew on 01/10/2017.
 */
public class TestCard {
    public static void main(String[] args){
        TestCard testCard = new TestCard();

        System.out.println("testCard.test01() = " + testCard.test01());
        System.out.println("testCard.test02() = " + testCard.test02());
        System.out.println("testCard.test03() = " + testCard.test03());
        System.out.println("testCard.test04() = " + testCard.test04());
        System.out.println("testCard.test05() = " + testCard.test05());
        System.out.println("testCard.test06() = " + testCard.test06());
    }

    public Boolean testSortDesc(ArrayList<Card> cards, ArrayList<Card> expectedCards){
        Collections.sort(cards, new Card.CardComparatorDescending());
        return cards.toString().equals(expectedCards.toString());
    }

    // testing empty
    public Boolean test01(){
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Card> sortedCards = new ArrayList<>();
        return testSortDesc(cards, sortedCards);
    }

    // testing cards of same suite
    public Boolean test02(){
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Card> sortedCards = new ArrayList<>();

        cards.add(new Card(0, 4));
        cards.add(new Card(0, 8));
        cards.add(new Card(0, 3));
        cards.add(new Card(0, 5));
        cards.add(new Card(0, 2));

        sortedCards.add(new Card(0, 8));
        sortedCards.add(new Card(0, 5));
        sortedCards.add(new Card(0, 4));
        sortedCards.add(new Card(0, 3));
        sortedCards.add(new Card(0, 2));

        return testSortDesc(cards, sortedCards);
    }

    // testing same suite and ace interaction
    public Boolean test03(){
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Card> sortedCards = new ArrayList<>();

        cards.add(new Card(0, 4));
        cards.add(new Card(0, 8));
        cards.add(new Card(0, 0));
        cards.add(new Card(0, 5));
        cards.add(new Card(0, 2));

        sortedCards.add(new Card(0, 0));
        sortedCards.add(new Card(0, 8));
        sortedCards.add(new Card(0, 5));
        sortedCards.add(new Card(0, 4));
        sortedCards.add(new Card(0, 2));

        return testSortDesc(cards, sortedCards);
    }

    // testing different suites and same number
    public Boolean test04(){
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Card> sortedCards = new ArrayList<>();

        cards.add(new Card(0, 4));
        cards.add(new Card(3, 4));
        cards.add(new Card(1, 4));
        cards.add(new Card(2, 4));

        sortedCards.add(new Card(0, 4));
        sortedCards.add(new Card(1, 4));
        sortedCards.add(new Card(2, 4));
        sortedCards.add(new Card(3, 4));

        return testSortDesc(cards, sortedCards);
    }

    // testing different suites and same number with card ownership
    public Boolean test05(){
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Card> sortedCards = new ArrayList<>();

        cards.add(new Card(0, 4));
        cards.add(new Card(3, 4));
        Card card = new Card(1, 4);
        card.setInHand(true);
        cards.add(card);
        cards.add(new Card(2, 4));

        sortedCards.add(new Card(1, 4));
        sortedCards.add(new Card(0, 4));
        sortedCards.add(new Card(2, 4));
        sortedCards.add(new Card(3, 4));

        return testSortDesc(cards, sortedCards);
    }

    // testing same suite and ace interaction with card ownership
    public Boolean test06(){
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Card> sortedCards = new ArrayList<>();

        Card card = new Card(0, 4);
        card.setInHand(true);
        cards.add(card);
        cards.add(new Card(0, 8));
        cards.add(new Card(0, 0));
        cards.add(new Card(0, 5));
        cards.add(new Card(0, 2));

        sortedCards.add(new Card(0, 0));
        sortedCards.add(new Card(0, 8));
        sortedCards.add(new Card(0, 5));
        sortedCards.add(new Card(0, 4));
        sortedCards.add(new Card(0, 2));

        return testSortDesc(cards, sortedCards);
    }
}

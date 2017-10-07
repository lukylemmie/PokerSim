package TexasHoldem;

import java.util.ArrayList;

/**
 * Created by Andrew on 07/10/2017.
 */
public class PlayableGame extends Game {

    public void playGame() {
        deck = new Deck();
        players = new ArrayList<>();
        table = new ArrayList<>();
        rankedPlayers = new ArrayList<>();
        winners = new ArrayList<>();

        deck.shuffle();
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new Player(i));
        }
        for (int i = 0; i < Player.HAND_SIZE; i++) {
            for (int j = 0; j < numberOfPlayers; j++) {
                players.get(j).addCard(deck.drawCard());
            }
        }
        for (int i = 0; i < TABLE_SIZE; i++) {
            table.add(deck.drawCard());
        }
    }
}

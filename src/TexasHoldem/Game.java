package TexasHoldem;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Andrew on 02/10/2017.
 */
public class Game implements SimControls {
    protected final static Integer TABLE_SIZE = 5;
    protected Integer numberOfPlayers = DEFAULT_PLAYER_COUNT;
    protected Deck deck;
    protected ArrayList<Player> players;
    protected ArrayList<Card> table;
    protected ArrayList<Player> rankedPlayers;
    protected ArrayList<Integer> winners;

    public Game(){

    }

    public Game(Deck testDeck){
        deck = testDeck;
    }

    @Override
    public void setNumberOfPlayers(Integer count) {
        numberOfPlayers = count;
    }

    @Override
    public void runGame() {
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
        for (int i = 0; i < numberOfPlayers; i++) {
            players.get(i).calculateScoreAndBestHand(table);
        }
        rankedPlayers.addAll(players);
        Collections.sort(rankedPlayers, new Player.PlayerComparatorDescending());
        int i = 0;
        while (i < numberOfPlayers && !rankedPlayers.get(i).getInferiorHand()){
            winners.add(rankedPlayers.get(i).getPlayerNumber());
            i++;
        }
    }

    @Override
    public ArrayList<Integer> getWinners() {
        return winners;
    }

    @Override
    public ArrayList<String> getPlayerHand(int player) {
        ArrayList<String> output = new ArrayList<>();

        for (Card card : players.get(player).getHand()){
            output.add(card.toString());
        }

        return output;
    }

    /*
        returns the results based on the following format
        1:(int)PLACE_1_POSITION, Hand:[(string)PLACE_1_CARD_1, (string)PLACE_1_CARD_2], Score:(int)score, BestHand:[list of cards]
        2:(int)PLACE_2_POSITION, Hand:[(string)PLACE_1_CARD_1, (string)PLACE_1_CARD_2], Score:(int)score, BestHand:[list of cards]
        3:(int)PLACE_3_POSITION, Hand:[(string)PLACE_1_CARD_1, (string)PLACE_1_CARD_2], Score:(int)score, BestHand:[list of cards]
        4:(int)PLACE_4_POSITION, Hand:[(string)PLACE_1_CARD_1, (string)PLACE_1_CARD_2], Score:(int)score, BestHand:[list of cards]
        5:(int)PLACE_5_POSITION, Hand:[(string)PLACE_1_CARD_1, (string)PLACE_1_CARD_2], Score:(int)score, BestHand:[list of cards]
     */
    @Override
    public String getResults() {
        String output = "table:" + table.toString() + "\n";
        Integer place = 1;
        for (int i = 0; i < numberOfPlayers; i++) {
            Player player = rankedPlayers.get(i);
            if (player.getInferiorHand()){
                place++;
            }
            output += place + ":" + player.getPlayerNumber() +
                    ", Hand:" + player.getHand().toString() +
                    ", Score:" + player.getScore().toString() +
                    ", BestHand:" + player.getBestHand().toString() + "\n";
        }
        return output;
    }
}

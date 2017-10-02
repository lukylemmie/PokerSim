package TexasHoldem;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Andrew on 02/10/2017.
 */
public class Game implements SimControls {
    private final static Integer TABLE_SIZE = 5;
    private Deck deck;
    private Integer numberOfPlayers = DEFAULT_PLAYER_COUNT;
    private ArrayList<Player> players;
    private ArrayList<Card> table;
    private ArrayList<Player> rankedPlayers;
    private ArrayList<Integer> winners;

    public Game(){
        deck = new Deck();
        deck.shuffle();
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
        players = new ArrayList<>();
        table = new ArrayList<>();
        rankedPlayers = new ArrayList<>();
        winners = new ArrayList<>();

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
    public String getWinners() {
        return winners.toString();
    }

    @Override
    public String getWinnerHands() {
        ArrayList<String> winningHands = new ArrayList<>();
        for (Integer winner : winners){
            winningHands.add(players.get(winner).getHand().toString());
        }
        return winningHands.toString();
    }

    /*
        returns the results based on the following format
        1:(int)PLACE_1_POSITION, Hand:[(string)PLACE_1_CARD_1, (string)PLACE_1_CARD_2], BestHand:[list of cards]
        2:(int)PLACE_2_POSITION, Hand:[(string)PLACE_1_CARD_1, (string)PLACE_1_CARD_2], BestHand:[list of cards]
        3:(int)PLACE_3_POSITION, Hand:[(string)PLACE_1_CARD_1, (string)PLACE_1_CARD_2], BestHand:[list of cards]
        4:(int)PLACE_4_POSITION, Hand:[(string)PLACE_1_CARD_1, (string)PLACE_1_CARD_2], BestHand:[list of cards]
        5:(int)PLACE_5_POSITION, Hand:[(string)PLACE_1_CARD_1, (string)PLACE_1_CARD_2], BestHand:[list of cards]
     */
    @Override
    public String getResults() {
        String output = "";
        Integer place = 1;
        for (int i = 0; i < numberOfPlayers; i++) {
            Player player = rankedPlayers.get(i);
            output += place + ":" + player.getPlayerNumber() +
                    ", Hand:" + player.getHand().toString() +
                    ", BestHand:" + player.getBestHand().toString() + "\n";
        }
        return output;
    }
}

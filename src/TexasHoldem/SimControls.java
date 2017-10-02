package TexasHoldem;

import java.util.ArrayList;

/**
 * Created by Andrew on 30/09/2017.
 */
public interface SimControls {
    // game limits and default values
    Integer MAX_PLAYERS = 6;
    Integer MIN_PLAYERS = 2;
    Integer DEFAULT_PLAYER_COUNT = 5;

    // sets the number of players
    void setNumberOfPlayers(Integer count);

    // simulates 1 game of Texas Hold'em
    void runGame();

    /*
        returns the winner's position (default: 0-4)
     */
    ArrayList<Integer> getWinners();

    /*
        returns a string of the following format
        [(string)CARD1, (string)CARD2]
     */
    String getPlayerHand(int player);

    /*
        returns the results based on the following format
        table:[list of cards]
        1:(int)PLACE_1_POSITION, Hand:[(string)PLACE_1_CARD_1, (string)PLACE_1_CARD_2], Score:(int)score, BestHand:[list of cards]
        2:(int)PLACE_2_POSITION, Hand:[(string)PLACE_1_CARD_1, (string)PLACE_1_CARD_2], Score:(int)score, BestHand:[list of cards]
        3:(int)PLACE_3_POSITION, Hand:[(string)PLACE_1_CARD_1, (string)PLACE_1_CARD_2], Score:(int)score, BestHand:[list of cards]
        4:(int)PLACE_4_POSITION, Hand:[(string)PLACE_1_CARD_1, (string)PLACE_1_CARD_2], Score:(int)score, BestHand:[list of cards]
        5:(int)PLACE_5_POSITION, Hand:[(string)PLACE_1_CARD_1, (string)PLACE_1_CARD_2], Score:(int)score, BestHand:[list of cards]
     */
    String getResults();
}

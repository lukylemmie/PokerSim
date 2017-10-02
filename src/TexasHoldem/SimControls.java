package TexasHoldem;

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
        returns the results based on the following format
        (int)PLACE_1_POSITION, (string)PLACE_1_CARD_1, (string)PLACE_1_CARD_2
        (int)PLACE_2_POSITION, (string)PLACE_2_CARD_1, (string)PLACE_2_CARD_2
        (int)PLACE_3_POSITION, (string)PLACE_3_CARD_1, (string)PLACE_3_CARD_2
        (int)PLACE_4_POSITION, (string)PLACE_4_CARD_1, (string)PLACE_4_CARD_2
        (int)PLACE_5_POSITION, (string)PLACE_5_CARD_1, (string)PLACE_5_CARD_2
     */
    String getResults();

    // returns the winner's position (default: 0-4)
    Integer getWinner();

    /*
        returns a string of the following format
        (string)CARD1, (string)CARD2
     */
    String getWinnerHand();
}

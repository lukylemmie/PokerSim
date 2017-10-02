package Demo;

import TexasHoldem.Game;
import TexasHoldem.SimControls;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Andrew on 02/10/2017.
 */
public class SimDemo {
    public static final Integer SIMULATE_COUNT = 10000;
    public static void main(String[] args){
        SimControls sim = new Game();
        HashMap<String, Integer> gameStats = new HashMap<>();

        for (int i = 0; i < SIMULATE_COUNT; i++) {
            sim.runGame();
            ArrayList<Integer> winners = sim.getWinners();
            for (Integer winner : winners){
                String hand = sim.getPlayerHand(winner);
                Integer count = 0;
                if (gameStats.containsKey(hand)){
                    count = gameStats.get(hand);
                }
                gameStats.put(hand, count + 1);
            }
        }

        System.out.println("gameStats = " + gameStats);
    }
}

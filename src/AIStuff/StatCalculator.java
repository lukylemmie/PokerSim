package AIStuff;

import TexasHoldem.Game;
import TexasHoldem.SimControls;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Andrew on 14/10/2017.
 */
public class StatCalculator {
    public static final Integer SIMULATE_COUNT = 1000000;
    public static void main(String[] args){
        SimControls sim = new Game();
        HashMap<String, Integer> wins = new HashMap<>();
        HashMap<String, Integer> handCount = new HashMap<>();

        for (int i = 0; i < SIMULATE_COUNT; i++) {
            sim.runGame();

            ArrayList<Integer> winners = sim.getWinners();
            for (Integer j = 0; j < Game.DEFAULT_PLAYER_COUNT; j++) {
                ArrayList<String> hand = sim.getPlayerHand(j);
                Collections.sort(hand);
                Integer count = 0;
                if (wins.containsKey(hand.toString())){
                    count = handCount.get(hand.toString());
                }
                handCount.put(hand.toString(), count + 1);
                if (winners.contains(j)){
                    count = 0;
                    if (wins.containsKey(hand.toString())){
                        count = wins.get(hand.toString());
                    }
                    wins.put(hand.toString(), count + 1);
                }
            }
        }

//        System.out.println("gameStats = " + wins);
//        System.out.println("handCount = " + handCount);

        ArrayList<String> cardPairs = new ArrayList<>();
        cardPairs.addAll(wins.keySet());
        ArrayList<PairStat> pairStats = new ArrayList<>();

        for (String cardPair : cardPairs){
            Double winPercent = wins.get(cardPair).doubleValue() / handCount.get(cardPair).doubleValue();
            pairStats.add(new PairStat(cardPair, wins.get(cardPair), handCount.get(cardPair), winPercent));
        }

        Collections.sort(pairStats, new PairStat.WinPercentDescending());
//        System.out.println("pairStats = " + pairStats);
        try {
            PrintWriter out = new PrintWriter("PokerStats.txt");
            for (PairStat pairStat : pairStats){
                out.println(pairStat.toString());
            }
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("pairStats.size() = " + pairStats.size());
    }
}

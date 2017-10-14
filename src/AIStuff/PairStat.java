package AIStuff;

import java.util.Comparator;

/**
 * Created by Andrew on 14/10/2017.
 */
public class PairStat {
    private final String pair;
    private final Integer wins;
    private final Integer count;
    private final Double winPercent;

    public PairStat(String pair, Integer wins, Integer count, Double winPercent) {
        this.pair = pair;
        this.wins = wins;
        this.count = count;
        this.winPercent = winPercent * 100;
    }

    public String getPair() {
        return pair;
    }

    public Double getWinPercent() {
        return winPercent;
    }

    public Integer getWins() {
        return wins;
    }

    public Integer getCount() {
        return count;
    }

    public String toString(){
        return pair + ":" /*+ wins + "/" + count + ":"*/ + winPercent + "%";
    }

    /*
    returns negative if card1 > card2
    returns 0 if card1 == card2
    returns positive if card1 < card2
     */
    public static class WinPercentDescending implements Comparator<PairStat> {
        public int compare(PairStat pair1, PairStat pair2){
            return pair2.getWinPercent().intValue() - pair1.getWinPercent().intValue();
        }
    }
}

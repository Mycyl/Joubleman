public class Stats {
    private int wins;
    private int totalGames;
    private int loss;
 
    public Stats() {
        wins = 0;
        totalGames = 0;
        loss = 0;
    }
 
    public int getWins() {
        return wins;
    }

    public int getLoss() {
        return loss;
    }
 
 
    public int getTotalGames() {
        return totalGames;
    }
 
    public void win() {
        wins++;
        totalGames++;
    }
 
    public void loss() {
        loss++;
        totalGames++;
    }
 
 
    public double averageWin() {
        return round(wins/ (double) totalGames);
    }
 
    private double round(double num) {
        return ((int) (Math.round(num * 100)));
    }
 }
 
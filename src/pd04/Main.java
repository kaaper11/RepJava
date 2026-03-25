package pd04;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Player {
    private String name;
    private double[] scores = new double[3];

    public void setName(String name) {
        this.name = name;
    }

    public double[] getScores() {
        return scores;
    }

    @Override
    public String toString() {
        return "imię = \'" + name + '\'' + ", scores = " + Arrays.toString(scores);
    }
}

class PlayerStatistic implements Comparable<PlayerStatistic> {
    private Player player;
    private double sumScore;
    private double meanScore;
    private double minScore;
    private double maxScore;

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setSumScore(double sumScore) {
        this.sumScore = sumScore;
    }

    public void setMeanScore(double meanScore) {
        this.meanScore = meanScore;
    }

    public void setMinScore(double minScore) {
        this.minScore = minScore;
    }

    public void setMaxScore(double maxScore) {
        this.maxScore = maxScore;
    }

    public double getSumScore() {
        return sumScore;
    }

    public double getMaxScore() {
        return maxScore;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        return player +
                ", suma wyników = " + sumScore +
                ", średnia wyników = " + meanScore +
                ", minimalny wynik = " + minScore +
                ", maksymalny wynik = " + maxScore;
    }

    @Override
    public int compareTo(PlayerStatistic o) {
        return Double.compare(o.getSumScore(), this.sumScore);
    }
}

public class Main {
    static Scanner sc = new Scanner(System.in);

    static Player[] loadPlayersData() {
        System.out.println("Wczytaj liczbe graczy min 2, max 10");
        int playersNumber;

        while ((playersNumber = sc.nextInt()) < 2 || playersNumber > 10) {
            System.out.println("Miała być liczba z przedziału 2-10!");
        }

        Player[] players = new Player[playersNumber];

        for (int i = 0; i < playersNumber; i++) {
            System.out.println("Podaj imię gracza");
            players[i] = new Player();
            players[i].setName(sc.next());
            System.out.println("Podaj wyniki gracza");
            for (int j = 0; j < 3; j++) {
                players[i].getScores()[j] = sc.nextDouble();
            }
        }
        return players;
    }

    static PlayerStatistic[] calculatePlayerStatistics(Player[] players) {
        PlayerStatistic[] statistics = new PlayerStatistic[players.length];
        double maxValue = Double.MIN_VALUE;
        double minValue = Double.MAX_VALUE;
        double sumValue = 0;

        for (int i = 0; i < players.length; i++) {
            statistics[i] = new PlayerStatistic();
            statistics[i].setPlayer(players[i]);
            for (int j = 0; j < 3; j++) {
                if (players[i].getScores()[j] > maxValue) {
                    maxValue = players[i].getScores()[j];
                }
                if (players[i].getScores()[j] < minValue) {
                    minValue = players[i].getScores()[j];
                }
                sumValue += players[i].getScores()[j];
            }
            statistics[i].setSumScore(sumValue);
            System.out.println(statistics[i].getSumScore());
            statistics[i].setMeanScore(sumValue / 3);
            statistics[i].setMinScore(minValue);
            statistics[i].setMaxScore(maxValue);
            maxValue = Double.MIN_VALUE;
            minValue = Double.MAX_VALUE;
            sumValue = 0;
        }
        return statistics;
    }

    static void printLeaderboard(PlayerStatistic[] statistics) {
        Arrays.sort(statistics);

        int bestScoreIndex = 0;
        double bestScore = -10;
        for (int i = 0; i < statistics.length; i++) {
            if (statistics[i].getMaxScore() > bestScore) {
                bestScoreIndex = i;
                bestScore = statistics[i].getMaxScore();
            }
        }

        for (int i = 0; i < statistics.length; i++) {
            if (i == bestScoreIndex) {
                System.out.println("(*)" + statistics[i].toString());
            } else {
                System.out.println(statistics[i].toString());
            }
        }


        System.out.println("\n \"Podium\": ");
        for (int i = 0; i < statistics.length; i++) {
            System.out.println(i + 1 + " miejsce " + statistics[i].getPlayer().toString());
        }
    }


    public static void main(String[] args) {
        try {
            printLeaderboard(calculatePlayerStatistics(loadPlayersData()));
        } catch (RuntimeException e) {
            System.out.println("To nie jest poprawny format!" + e);
        }
    }
}

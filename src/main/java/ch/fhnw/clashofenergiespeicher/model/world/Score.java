package ch.fhnw.clashofenergiespeicher.model.world;

import ch.fhnw.clashofenergiespeicher.settings.Setting;
public class Score {
    private int totalScore;

    public Score() {
        this.totalScore = 0;
    }

    /**
     * calculates the Score of a Player
     * 
     * @param optimalEnergyToStore the optimal energy to be stored
     * @param currentEnergyToStore the current energy to be stored
     */
    public void calculateScore(double optimalEnergyToStore, double currentEnergyToStore) {
        double points = 0;
        double basepoints = Setting.BASE_POINTS_PER_HOUR;
        if (currentEnergyToStore > optimalEnergyToStore) {
            // Linear Function, for each 1000kw difference there is 200 points less.
            double x = currentEnergyToStore - optimalEnergyToStore;
            double functionPoints = (1.0 / 5.0) * x;
            points = basepoints - functionPoints;
        } else {
            // exponential function (basepoints 2777)
            // Goal: 1000kw = 2177, 2000kw = 1300, 3000kw = 300 (1x6, 2x7, 3x8 etc.)

            double x = (optimalEnergyToStore - currentEnergyToStore); // 3000
            double z = x / 1000; // 3

            double calc = (z * (z + 5)); // 3*8 = 24
            points = basepoints - (calc * 100); // 2400
        }

        System.out.println("Score --> +Points: " + points);

        if (points > 0) {
            totalScore += points;
        }
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public void resetScore() {
        this.totalScore = 0;
    }
}

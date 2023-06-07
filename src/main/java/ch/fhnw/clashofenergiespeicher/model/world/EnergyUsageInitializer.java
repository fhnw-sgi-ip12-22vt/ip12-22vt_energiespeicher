package ch.fhnw.clashofenergiespeicher.model.world;

import ch.fhnw.clashofenergiespeicher.settings.Setting;


/*
 * The EnergyUsageInitializer class is used for loading the energy usage needed for every hour
 */
public class EnergyUsageInitializer {

    // Array filled with pre defined values for each hour how much energy is used in
    // a city
    protected static final double[] ENERGY_USAGE_PER_HOUR_SCALA = { 1.5, 0.8, 0.2, 0.2, 0.8,
        2.9, 5.2, 6.6, 7.1, 7.1, 7.3, 7.9, 8.3, 7.3, 6,
        5.4, 5.5, 6.8, 8.5, 9.3, 9.1, 8.4, 6.5, 3.4 };

    private EnergyUsageInitializer() {}

    public static double[] initEnergyUsageArray() {
        /**
         * energy Usage every hour as a fixed constant which is defined in
         * Confluence @Programming Notes
         * see
         * https://www.cs.technik.fhnw.ch/confluence20/display/VT122203/Programming+Notes?preview=/86116112/86116289/Bildschirmfoto%202022-12-01%20um%2011.34.33.png
         */

        // Array for the energy usage for every game hour
        // ex: a day lasts 10seconds. this means 10 game hours.
        double[] eUsagePerGameHour = new double[Setting.DAY_TIME_INTERVAL];

        // unrounded size of a section. A section means how many hours will get merged
        // together
        double sectionSize = 24 / (double) Setting.DAY_TIME_INTERVAL;

        int counterSection = 1;
        int counterHour = 1;
        double counterAvg = 0;
        double avg = 0;

        // for every hour i: 0-23
        for (int i = 0; i < ENERGY_USAGE_PER_HOUR_SCALA.length; i++) {
            // If current section <= rounded next max section -> increase avg
            if (counterHour <= (Math.round(sectionSize * counterSection))) {
                avg += ENERGY_USAGE_PER_HOUR_SCALA[i];
                counterAvg++;
            } else {
                // set the section with the combined calculated avg
                if (counterAvg != 0) {
                    eUsagePerGameHour[counterSection - 1] = avg / counterAvg;
                }

                // increase section and reset avg
                counterSection++;
                avg = ENERGY_USAGE_PER_HOUR_SCALA[i];
                counterAvg = 1;
            }

            // special case for the last hour
            if (counterHour == ENERGY_USAGE_PER_HOUR_SCALA.length && counterAvg != 0) {
                eUsagePerGameHour[counterSection - 1] = avg / counterAvg;
            }

            counterHour++;
        }

        // (Energy usage of the city / n hours a day) / our scala which has 11 blocks
        // * 2. This gives us the max output for an hour.
        double maxEnergyOutputPerHour = (Setting.CITY_DEFAULT_ENERGY_USAGE / (double)eUsagePerGameHour.length)
                / 11.0 * 2.0;

        double[] energyUsagePerHour = new double[Setting.DAY_TIME_INTERVAL];

        // loop through every hour and calculate the needed energyUsage of the city.
        for (int i = 0; i < energyUsagePerHour.length; i++) {
            energyUsagePerHour[i] = eUsagePerGameHour[i] * maxEnergyOutputPerHour;
        }

        return energyUsagePerHour;
    }
}

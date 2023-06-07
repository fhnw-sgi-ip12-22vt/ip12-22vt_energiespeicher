package ch.fhnw.clashofenergiespeicher.settings;

/**
 * This class stores general setting
 */
public final class Setting {

    public static final Integer GAME_LENGTH = 4;
    public static final int MAX_WARNINGS = 3;
    public static final int STARTING_HOUR = 20;

    private Setting() {
        throw new IllegalStateException("Utility class");
    }

    // Interval in seconds how long a day will last in real time
    // MAX: 24s -> 1s per hour as there is no function built for longer than a day
    public static final int DAY_TIME_INTERVAL = 12;

    // Default energy settings
    public static final int ENERGY_PRODUCTION_PER_DAY = 94000;
    public static final double CITY_DEFAULT_ENERGY_USAGE = ENERGY_PRODUCTION_PER_DAY - 10000.0;

    /*
     * Max capacity of energy storages in kw.
     * Big storages (Compressed air- & pumped storage)
     * small storages (crane & flywheel)
     */

    public static final int MAX_ENERGY_BIG_STORAGE = 60000;
    public static final int MAX_ENERGY_SMALL_STORAGE = 20000;

    // Points
    public static final double BASE_POINTS_PER_HOUR = 2777;

    public static final int POINTS_COST_PER_EVENT = 5000;

    // Events
    public static final int EVENT_DURATION = DAY_TIME_INTERVAL;

    public static final int ENERGY_PRODUCTION_PER_DAY_EVENT = ENERGY_PRODUCTION_PER_DAY - 30000;

}

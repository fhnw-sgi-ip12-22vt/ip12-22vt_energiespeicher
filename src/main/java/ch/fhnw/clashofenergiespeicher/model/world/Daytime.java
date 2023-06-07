package ch.fhnw.clashofenergiespeicher.model.world;

import ch.fhnw.clashofenergiespeicher.settings.Setting;
import java.time.LocalTime;
/**
 * This class defines our daily cycle
 */
public class Daytime {
    private LocalTime currentTime;

    /**
     * Constructor that sets the initial time to 00:00
     */
    public Daytime() {
        currentTime = LocalTime.of(Setting.STARTING_HOUR, 0);
    }
    /**
     * Adds the specified number of hours to the current time
     *
     * @param hours the number of hours to add
     */
    public boolean addHoursCheckNewDay(int hours) {
        currentTime = currentTime.plusHours(hours);

        // Check if it's midnight
        return currentTime.getHour() == 0;
    }

    public LocalTime getCurrentTime() {
        return currentTime;
    }
}

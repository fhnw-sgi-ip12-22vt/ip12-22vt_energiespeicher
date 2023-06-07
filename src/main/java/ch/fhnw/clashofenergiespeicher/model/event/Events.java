package ch.fhnw.clashofenergiespeicher.model.event;

import ch.fhnw.clashofenergiespeicher.settings.Setting;

public enum Events {
    GODZILLA(new Event(Setting.EVENT_DURATION, "Godzilla greift deine Energieproduktion an!")),
    KINGKONG(new Event(Setting.EVENT_DURATION, "King Kong greift deine Energieproduktion an!"));
    private Event event;

    private Events(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }

    public static Events getRandomEvent() {
        return values()[(int) (Math.random() * values().length)];
    }
}
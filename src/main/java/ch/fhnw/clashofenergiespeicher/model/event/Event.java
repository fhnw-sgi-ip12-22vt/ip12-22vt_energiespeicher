package ch.fhnw.clashofenergiespeicher.model.event;

public class Event {
    private int duration;
    private String notification;

    public Event(int duration, String notification) {
        this.duration = duration;
        this.notification = notification;
    }

    public int getDuration() {
        return duration;
    }

    public String getNotification() {
        return notification;
    }
}
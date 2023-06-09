package ch.fhnw.clashofenergiespeicher.catalog.components.helpers;

public class ContinuousMeasuringException extends RuntimeException {

    public ContinuousMeasuringException(String message) {
        super(message);
    }

    public ContinuousMeasuringException(String message, Throwable cause) {
        super(message, cause);
    }
}

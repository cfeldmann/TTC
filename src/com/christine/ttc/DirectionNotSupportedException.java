package com.christine.ttc;

/**
 * Thrown when a particular station does not support the requested direction.
 */
public class DirectionNotSupportedException extends Exception {

    DirectionNotSupportedException(String message) {
        super(message);
    }
}

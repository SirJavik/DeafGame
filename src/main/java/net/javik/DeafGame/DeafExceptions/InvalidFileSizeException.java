package net.javik.DeafGame.DeafExceptions;

import java.io.IOException;

public class InvalidFileSizeException extends IOException {
    public InvalidFileSizeException() { super(); }

    public InvalidFileSizeException(String message) { super(message); }
}

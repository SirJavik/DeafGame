package net.javik.DeafGame.DeafBasics;

import java.io.IOException;

public class DeafConsole  {
    public static void writeLine(String message) {
        System.out.println("[DeafGame] " + message);
    }

    public static void writeLine(double message) { writeLine(Double.toString(message)); }

    public static void writeLine(int message) { writeLine(Integer.toString(message)); }

    public static void write(String message) {
        System.out.print("[DeafGame] " + message);
    }

    public static class Error {
        public static void writeLine(String message) {
            System.err.println("[DeafGame] " + message);
        }

        public static void writeLine(double message) { writeLine(Double.toString(message)); }

        public static void writeLine(int message) { writeLine(Integer.toString(message)); }
    }
}


package me.tiezhu.quiz.utils;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Logging {

    private static final AtomicBoolean logging = new AtomicBoolean();

    public static void enableLog() {
        logging.set(true);
    }

    public static void disableLog() {
        logging.set(false);
    }

    public static void printlnAlways(String msg, Object... args) {
        if (logging.get()) {
            msg = "  " + msg;
        }
        System.out.println(String.format(msg, args));
    }

    public static void println(String msg, Object... args) {
        if (logging.get()) {
            System.out.println(String.format(msg, args));
        }
    }
}

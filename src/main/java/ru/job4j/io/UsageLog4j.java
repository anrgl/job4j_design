package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class);

    public static void main(String[] args) {
        byte b = 42;
        short s = 21;
        int i = 10;
        long l = 5;
        float f = 3.14f;
        double d = 2.5;
        char c = '\u20AC';
        boolean flag = false;

        LOG.trace("trace message with numbers: byte - {} and short - {}", b, s);
        LOG.debug("debug message with numbers: int - {} and long - {}", i, l);
        LOG.info("info message with float numbers: float - {} and double - {}", f, d);
        LOG.warn("warn message with character: char - {}", c);
        LOG.error("error message with boolean: boolean - {}", flag);
    }
}

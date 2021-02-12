package ru.job4j.template;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class GeneratorTest {
    @Test
    public void whenOneArg() {
        Generator generator = new StringGenerator();
        String template = "Hello ${user}";
        String expect = "Hello Java Developer";
        String out = generator.produce(template, Map.of("user", "Java Developer"));
        assertEquals(expect, out);
    }

    @Test
    public void whenTwoArgs() {
        Generator generator = new StringGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        String expect = "I am a Petr Arsentev, Who are you?";
        String out = generator.produce(template, Map.of(
                "name", "Petr Arsentev",
                "subject", "you"));
        assertEquals(expect, out);
    }

    @Test(expected = Exception.class)
    public void whenWaitTwoArgsThenGiveLess() {
        Generator generator = new StringGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        String expect = "I am a Petr Arsentev, Who are you?";
        String out = generator.produce(template, Map.of(
                "name", "Petr Arsentev"));
    }

    @Test(expected = Exception.class)
    public void whenWaitTwoArgsThenGiveMore() {
        Generator generator = new StringGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        String expect = "I am a Petr Arsentev, Who are you?";
        String out = generator.produce(template, Map.of(
                "name", "Petr Arsentev",
                "subject", "you",
                "favoriteColor", "purple"));
    }
}
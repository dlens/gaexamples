package com.decisionlens.gaexamples.helloworld;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HelloWorldGeneratorTest {

    @Test
    public void helloworld() {
        HelloWorldGenerator generator = new HelloWorldGenerator();
        String result = generator.generate();
        assertEquals("HELLO WORLD", result);
    }
}

package com.decisionlens.gaexamples.helloworldv2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HelloWorldGeneratorV2Test {

    @Test
    public void helloworld() {
        HelloWorldGeneratorV2 generator =
                new HelloWorldGeneratorV2("XYZEJHSKSHS", "HELLO WORLD",
                        0.5, 0.5);
        String result = generator.generate();
        assertEquals("HELLO WORLD", result);
    }
}

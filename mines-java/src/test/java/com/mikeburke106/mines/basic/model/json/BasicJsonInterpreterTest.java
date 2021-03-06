package com.mikeburke106.mines.basic.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

/**
 * Created by Mike Burke on 4/8/17.
 */
public class BasicJsonInterpreterTest {
    private static final int TEST_INTEGER = 1248;
    private static final String TEST_INTEGER_JSON = "{\"integer\":1248}";

    static class TestJson {
        @JsonProperty
        int integer;

        public TestJson() {
            /*
             * Do nothing.  Needed for Jackson deserialization.
             */
        }

        TestJson(int integer) {
            this.integer = integer;
        }
    }

    private BasicJsonInterpreter<TestJson> basicJsonInterpreter;

    @Before
    public void setup() {
        basicJsonInterpreter = new BasicJsonInterpreter<>(TestJson.class);
    }

    @Test
    public void testSerialization() throws JsonProcessingException {
        String jsonString = basicJsonInterpreter.toJson(new TestJson(TEST_INTEGER));
        assertEquals(TEST_INTEGER_JSON, jsonString);
    }

    @Test
    public void testDeserialization() throws IOException {
        TestJson testJsonObject = basicJsonInterpreter.fromJson(TEST_INTEGER_JSON);
        assertEquals(TEST_INTEGER, testJsonObject.integer);
    }
}

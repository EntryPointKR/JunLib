package com.github.entrypointkr.junlib;

import com.github.entrypointkr.junlib.bukkit.configuration.ReflectiveYaml;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.Map;

/**
 * Created by JunHyeong on 2018-11-03
 */
public class ConfigurationTestObject {
    private final ReflectiveYaml config = new ReflectiveYaml();
    private final Map<String, TestObject> testMap = ImmutableMap.<String, TestObject>builder()
            .put("a", new TestObject("a1", "a2"))
            .put("b", new TestObject("b1", "b2"))
            .build();

    @Test
    public void test() {
//        StringWriter writer = new StringWriter();
//        config.write(testMap, writer);
//        Map<String, TestObject> newMap = config.<Map<String, TestObject>>read(new StringReader(writer.toString())).orElseThrow(() -> new AssertionError("a"));
//        Assert.assertEquals(testMap, newMap);
    }

    static class TestObject {
        private final String a;
        private final String b;
        private transient int c;

        public TestObject(String a, String b) {
            this.a = a;
            this.b = b;
        }
    }
}

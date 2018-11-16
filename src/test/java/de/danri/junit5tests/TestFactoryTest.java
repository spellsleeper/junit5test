package de.danri.junit5tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.List;

class TestFactoryTest {

    @TestFactory
    @DisplayName("test factory execution")
    List<DynamicTest> dynamicTestFactory(){
        return Arrays.asList(
                DynamicTest.dynamicTest("first dynamic test",()-> Assertions.assertSame(this,this)),
                DynamicTest.dynamicTest("second dynamic test",()-> Assertions.assertFalse(2>2))
        );
    }
}

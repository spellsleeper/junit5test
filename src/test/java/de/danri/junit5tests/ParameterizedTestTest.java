package de.danri.junit5tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;


class ParameterizedTestTest {

    private static final Pattern TEST_DIG_PATTERN = Pattern.compile("test(?<digit>\\d+)");

    @ParameterizedTest
    @ValueSource(strings = {"test1", "test2", "test3"})
    @DisplayName("testing string values as params")
    void testWithStringParams(String string) {
        Assertions.assertTrue(string.matches(TEST_DIG_PATTERN.pattern()));
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 18, 21})
    @DisplayName("testing int values as params")
    void testWithIntParams(int integer) {
        Assertions.assertSame(integer % 3, 0);
    }

    @ParameterizedTest
    @CsvSource({"test1,1", "test2,2", "test3,3"})
    @DisplayName("testing CSV values as params")
    void testWithCSVParams(String string, int integer) {
        Matcher matcher = TEST_DIG_PATTERN.matcher(string);
        Assertions.assertTrue(matcher.matches());
        Assertions.assertSame(integer, Integer.parseInt(matcher.group("digit")));
    }

    @ParameterizedTest
    @EnumSource(value = TimeUnit.class,names = {"NANOSECONDS","MICROSECONDS","MILLISECONDS"})
    @DisplayName("testing enumeration values as params")
    void testWithEnumParams(TimeUnit timeUnit) {
        Assertions.assertSame(timeUnit.toSeconds(1),0L);
    }

    @ParameterizedTest
    @MethodSource("createParamStream")
    @DisplayName("testing values from method as params")
    void testWithMethodParams(String string) {
        Matcher matcher = TEST_DIG_PATTERN.matcher(string);
        Assertions.assertTrue(matcher.matches());
        Assertions.assertSame(0,Integer.parseInt(matcher.group("digit"))%10);
    }

    private static Stream<String> createParamStream(){
        return Stream.of("test10","test100","test1000");
    }
}

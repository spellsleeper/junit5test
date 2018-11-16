package de.danri.junit5tests;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AssertionTest {

    @Test
    @DisplayName("running basic assertions")
    void testBasicAssertions() {
        assertTrue(2 > 1);
        assertTrue(() -> "test".matches("\\w{4}"));
        assertNotEquals("a", "b");
        assertEquals("a", "a");
        assertArrayEquals(ArrayUtils.toArray("a", "b"), ArrayUtils.toArray("a", "b"));
        assertThrows(IndexOutOfBoundsException.class, () -> Arrays.copyOfRange(ArrayUtils.toArray(0, 1), 3, 3));
        assertNotSame(new ArrayList<>(), new ArrayList<>());
        List list = new ArrayList();
        assertSame(list, list);
        assertDoesNotThrow(() -> Arrays.copyOfRange(ArrayUtils.toArray(0, 1), 2, 2));
        assertTimeout(Duration.of(1L, ChronoUnit.SECONDS), () -> {
            for (long l = 0L; l < 1000; l++) {
                // if this loop runs longer than one second you need a new pc :-)
            }
        });
        assertIterableEquals(new ArrayList<>(), new ArrayList<>());
    }

    @Test
    @DisplayName("running assertAll assertion")
    void testAssertAllAssertion() {
        List<String> list = Stream.of("a", "b").collect(Collectors.toList());
        assertAll(
                () -> assertTrue(() -> list.stream().anyMatch(s -> s.equals("a"))),
                () -> assertFalse(() -> list.stream().allMatch(s -> s.equals("b"))),
                () -> assertArrayEquals(list.toArray(), ArrayUtils.toArray("a", "b"))
        );
    }

}

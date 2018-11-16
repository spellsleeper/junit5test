package de.danri.junit5tests;

import org.apache.commons.lang3.LocaleUtils;
import org.apache.commons.lang3.SystemUtils;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.platform.commons.util.StringUtils;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class OtherComponentsTest {

    @Test
    @DisplayName("using fail")
    void testUsingFail() {
        if (StringUtils.containsIsoControlCharacter("a string")) {
            fail("string contains iso control char");
        } else if (LocaleUtils.countriesByLanguage("de").size() <= 2) {
            fail("there are more than two countries with German language");
        }

        try {
            Path path = Paths.get(".");
            path.toUri().toURL();
        } catch (MalformedURLException ex) {
            fail("unable to get url from path", ex);
        }
    }

    @Test
    @DisplayName("using assumptions")
    @EnabledIf("2*3==6") //requires nashorn engine
    void testUsingAssumptions(){
        Assumptions.assumeFalse(SystemUtils.IS_OS_WINDOWS_95||SystemUtils.IS_OS_WINDOWS_98,
                "this os seems to be to old for this test. the test will be ignored");
        assertTrue(true);
    }
}

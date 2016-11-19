package de.xn__ho_hia.interesting;

import static de.xn__ho_hia.interesting.converter.StandardConverters.stringFormat;
import static de.xn__ho_hia.interesting.handler.StandardInvocationHandlers.FORMAT_TEMPLATE;
import static de.xn__ho_hia.interesting.sink.StandardSinks.systemOut;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import de.xn__ho_hia.interesting.converter.XStreamConverters;

/**
 *
 *
 */
@RunWith(JUnitPlatform.class)
public class InterestedTest {

    @Test
    @SuppressWarnings({ "nls", "null", "static-method" })
    void shouldCreateNonNullProxyForInterface() {
        // given
        final TestInterface instance = Interested.in(TestInterface.class)
                .buildHandler()
                .converter(XStreamConverters.xml())
                .sinks(systemOut().andThen(systemOut()))
                .buildHandler()
                .converter(stringFormat(FORMAT_TEMPLATE))
                .sinks(systemOut().andThen(systemOut()))
                .createLogger();

        // when
        instance.someMethod("test"); //$NON-NLS-1$

        final Pair pair = new Pair();
        pair.left = "one";
        pair.right = "two";
        instance.otherMethod(pair);

        instance.thirdMethod("test", pair);
        instance.thirdMethod("test", pair, 5);
        instance.thirdMethod("test", pair, 18, false);

        final Map<String, Integer> defaults = new HashMap<>();
        defaults.put("ains", Integer.valueOf(123));
        defaults.put("zwai", Integer.valueOf(759));
        defaults.put("draih", Integer.valueOf(634));
        instance.otherMethod(pair, defaults);

        // then
        Assertions.assertNotNull(instance);
    }

    static interface TestInterface {

        void someMethod(String someParam);

        void otherMethod(Pair pair);

        void otherMethod(Pair pair, Map<String, Integer> defaults);

        void thirdMethod(String someParam, Pair pair);

        void thirdMethod(String someParam, Pair pair, int num);

        void thirdMethod(String someParam, Pair pair, int num, boolean whatever);

    }

    @SuppressWarnings("null")
    static class Pair {

        public String left;
        public String right;

    }

}

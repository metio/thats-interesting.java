package de.xn__ho_hia.interesting;

import static de.xn__ho_hia.interesting.converter.StandardConverters.stringFormat;
import static de.xn__ho_hia.interesting.handler.StandardInvocationHandlers.FORMAT_TEMPLATE;
import static de.xn__ho_hia.interesting.sink.StandardSinks.systemOut;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import de.xn__ho_hia.interesting.converter.JsonConverter;

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
                .converter(new JsonConverter(new ObjectMapper()))
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

        // then
        Assertions.assertNotNull(instance);
    }

    static interface TestInterface {

        void someMethod(String someParam);

        void otherMethod(Pair pair);

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

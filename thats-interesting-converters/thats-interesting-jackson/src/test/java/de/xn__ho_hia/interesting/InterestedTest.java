package de.xn__ho_hia.interesting;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import de.xn__ho_hia.interesting.converter.JsonConverter;
import de.xn__ho_hia.interesting.handler.GenericInvocationHandler;

/**
 *
 *
 */
@RunWith(JUnitPlatform.class)
@SuppressWarnings("static-method")
public class InterestedTest {

    @SuppressWarnings("nls")
    @Test
    void shouldCreateNonNullProxyForInterface() {
        // given
        final TestInterface instance = new LoggerBuilder<>(TestInterface.class)
                .invocationHandler(new GenericInvocationHandler<>(
                        new JsonConverter(new ObjectMapper()),
                        System.out::println))
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

    static class Pair {

        public String left;
        public String right;

    }

}

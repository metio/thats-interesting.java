package de.xn__ho_hia.interesting;

import static de.xn__ho_hia.interesting.converter.StandardConverters.stringFormat;
import static de.xn__ho_hia.interesting.handler.StandardInvocationHandlers.FORMAT_TEMPLATE;
import static de.xn__ho_hia.interesting.sink.StandardSinks.systemOut;

import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import de.xn__ho_hia.interesting.handler.StandardInvocationHandlers;
import de.xn__ho_hia.interesting.sink.StandardSinks;

/**
 *
 *
 */
@RunWith(JUnitPlatform.class)
public class InterestedTest {

    @Test
    @SuppressWarnings({ "nls", "static-method", "null" })
    void shouldCreateNonNullProxyForInterface() {
        // given
        final TestInterface instance = Interested.in(TestInterface.class)
                .buildHandler()
                .converter(stringFormat(FORMAT_TEMPLATE))
                .sinks(systemOut())
                .buildHandler()
                .converter(stringFormat("%s#%s: %s"))
                .sinks(StandardSinks.fileAppender(Paths.get("target", "out.log")))
                .invocationHandler(StandardInvocationHandlers.systemOut())
                .createLogger();

        // when
        instance.someMethod("hello");
        instance.someMethod("world", new TestModel("one", "two"));

        // then
        Assertions.assertNotNull(instance);
    }

    static interface TestInterface {

        void someMethod(String someParam);

        void someMethod(String someParam, TestModel model);

    }

    static class TestModel {

        private final String left;
        private final String right;

        public TestModel(final String left, final String right) {
            this.left = left;
            this.right = right;
        }

        @Override
        @SuppressWarnings("null")
        public String toString() {
            return String.format("{left: %s, right: %s}", left, right); //$NON-NLS-1$
        }

    }

}

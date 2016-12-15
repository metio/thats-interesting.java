package de.xn__ho_hia.interesting;

import static de.xn__ho_hia.interesting.converter.StandardConverters.stringFormat;
import static de.xn__ho_hia.interesting.filter.StandardInvocationFilters.methods;
import static de.xn__ho_hia.interesting.sink.StandardSinks.systemOut;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

/**
 *
 *
 */
@RunWith(JUnitPlatform.class)
public class InterestedTest {

    @Test
    @SuppressWarnings({ "nls", "static-method" })
    void shouldCreateNonNullProxyForInterface() {
        // given
        final TestInterface instance = Interested.in(TestInterface.class)
                .withStaticExtra("global-extra-key", "global-extra-value")
                .buildHandler()
                .filters(methods("someFilteredMethod"))
                .converter(stringFormat())
                .withStaticExtra("extra-key", "extra-value")
                .sinks(systemOut())
                .buildHandler()
                .filters(methods("someMethod"))
                .converter(stringFormat())
                .withStaticExtra("someMethod-extra-key", "someMethod-extra-value")
                .sinks(systemOut())
                .createLogger();

        // when
        instance.someMethod("hello");
        instance.someFilteredMethod("hello");
        instance.someMethod("world", new TestModel("one", "two"));

        // then
        Assertions.assertNotNull(instance);
    }

    static interface TestInterface {

        void someMethod(String someParam);

        void someFilteredMethod(String someParam);

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

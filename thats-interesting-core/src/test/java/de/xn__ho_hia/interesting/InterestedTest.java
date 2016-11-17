package de.xn__ho_hia.interesting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

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
        final TestInterface instance = Interested.in(TestInterface.class);

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

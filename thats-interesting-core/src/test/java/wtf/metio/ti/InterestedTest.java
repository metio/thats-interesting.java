/*
 * This file is part of TI. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of TI,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.ti;

import static wtf.metio.ti.converter.StandardConverters.stringFormat;
import static wtf.metio.ti.filter.StandardInvocationFilters.methods;
import static wtf.metio.ti.sink.StandardSinks.systemOut;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import wtf.metio.ti.Interested;

class InterestedTest {

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

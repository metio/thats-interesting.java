/*
 * This file is part of TI. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of TI,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.ti.converter.gson;

import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import wtf.metio.ti.Interested;

import static wtf.metio.ti.sink.StandardSinks.systemOut;

class InterestedTest {

    @Test
    void shouldCreateNonNullProxyForInterface() {
        // given
        final var logger = Interested.in(TestInterface.class)
                .buildHandler()
                .converter(new GsonConverter(new Gson()))
                .withStaticExtra("extra-key", "extra-value")
                .sinks(systemOut())
                .createLogger();

        // when
        logger.someMethod("test"); //$NON-NLS-1$

        final var pair = new Pair();
        pair.left = "one";
        pair.right = "two";
        logger.otherMethod(pair);

        logger.thirdMethod("test", pair);
        logger.thirdMethod("test", pair, 5);
        logger.thirdMethod("test", pair, 18, false);

        // then
        Assertions.assertNotNull(logger);
    }

    interface TestInterface {

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

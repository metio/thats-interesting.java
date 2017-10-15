package wtf.metio.ti;

import static wtf.metio.ti.sink.StandardSinks.systemOut;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import wtf.metio.ti.Interested;
import wtf.metio.ti.converter.JacksonConverter;

class InterestedTest {

    @Test
    @SuppressWarnings({ "nls", "static-method" })
    void shouldCreateNonNullProxyForInterface() {
        // given
        final TestInterface instance = Interested.in(TestInterface.class)
                .buildHandler()
                .converter(new JacksonConverter(new ObjectMapper()))
                .withStaticExtra("extra-key", "extra-value")
                .sinks(systemOut())
                .createLogger();

        // when
        instance.someMethod("test");

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

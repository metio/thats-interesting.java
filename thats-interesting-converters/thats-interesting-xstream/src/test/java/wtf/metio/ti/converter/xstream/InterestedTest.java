package wtf.metio.ti.converter.xstream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import wtf.metio.ti.Interested;

import java.util.HashMap;
import java.util.Map;

import static wtf.metio.ti.sink.StandardSinks.systemOut;

class InterestedTest {

    @Test
    void shouldCreateNonNullProxyForInterface() {
        // given
        final var instance = Interested.in(TestInterface.class)
                .buildHandler()
                .converter(XStreamConverters.xml())
                .withStaticExtra("extra-key", "extra-value")
                .sinks(systemOut())
                .createLogger();

        // when
        instance.someMethod("test"); //$NON-NLS-1$

        final var pair = new Pair();
        pair.left = "one";
        pair.right = "two";
        instance.otherMethod(pair);

        instance.thirdMethod("test", pair);
        instance.thirdMethod("test", pair, 5);
        instance.thirdMethod("test", pair, 18, false);

        final Map<String, Integer> defaults = new HashMap<>();
        defaults.put("ains", 123);
        defaults.put("zwai", 759);
        defaults.put("draih", 634);
        instance.otherMethod(pair, defaults);

        // then
        Assertions.assertNotNull(instance);
    }

    interface TestInterface {

        void someMethod(String someParam);

        void otherMethod(Pair pair);

        void otherMethod(Pair pair, Map<String, Integer> defaults);

        void thirdMethod(String someParam, Pair pair);

        void thirdMethod(String someParam, Pair pair, int num);

        void thirdMethod(String someParam, Pair pair, int num, boolean whatever);

    }

    static class Pair {

        public String left;
        public String right;

    }

}

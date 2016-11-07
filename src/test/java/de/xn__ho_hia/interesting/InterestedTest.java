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

    @Test
    void shouldCreateNonNullProxyForInterface() {
        // given
        TestInterface instance;

        // when
        instance = Interested.in(TestInterface.class).createLogger();
        instance.someMethod("test"); //$NON-NLS-1$

        // then
        Assertions.assertNotNull(instance);
    }

    static interface TestInterface {

        void someMethod(String someParam);

    }

}

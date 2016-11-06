package de.xn__ho_hia.interesting;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.github.sebhoss.utils.strings.S;

/**
 *
 *
 */
public class SystemOutInvocationHandler implements InvocationHandler {

    @Override
    @SuppressWarnings("nls")
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        System.out.println("Called: " + method.getName() + " with arguments: " + S.arrayToString(args));
        return null;
    }

}

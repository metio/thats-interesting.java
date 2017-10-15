package wtf.metio.ti.filter;

import java.lang.reflect.Method;

/**
 * A filter for method invocations.
 */
public interface InvocationFilter {

    /**
     * @param proxy
     * @param method
     * @param args
     * @return <code>true</code> if the input is allowed to pass through the filter, <code>false</code> otherwise.
     */
    boolean accept(Object proxy, Method method, Object[] args);

}

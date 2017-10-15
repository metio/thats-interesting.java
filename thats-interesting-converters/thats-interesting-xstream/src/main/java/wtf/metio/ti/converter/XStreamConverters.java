package wtf.metio.ti.converter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import wtf.metio.ti.converter.InvocationConverter;
import wtf.metio.ti.converter.internal.MethodInvocationModel;
import wtf.metio.ti.converter.internal.MethodInvocationModelConverter;

/**
 * Factory for XStream based converters.
 */
public final class XStreamConverters {

    /**
     * @return The configured method invocation converter.
     */
    public static final InvocationConverter<String> xml() {
        final XStream xstream = new XStream(new StaxDriver());
        xstream.registerConverter(new MethodInvocationModelConverter());
        xstream.alias("interesting", MethodInvocationModel.class); //$NON-NLS-1$
        return xml(xstream);
    }

    /**
     * @param xstream
     *            The xstream instance to use.
     * @return The configured method invocation converter.
     */
    public static final InvocationConverter<String> xml(final XStream xstream) {
        return new XStreamConverter(xstream);
    }

}

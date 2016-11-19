package de.xn__ho_hia.interesting.converter.internal;

import java.util.Map;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 *
 *
 */
public final class MethodInvocationModelConverter implements Converter {

    @Override
    @SuppressWarnings("rawtypes")
    public boolean canConvert(final Class type) {
        return MethodInvocationModel.class.isAssignableFrom(type);
    }

    @Override
    public void marshal(final Object source, final HierarchicalStreamWriter writer, final MarshallingContext context) {
        // cast is fine here because XStream checks with '#canConvert' before
        final MethodInvocationModel model = (MethodInvocationModel) source;
        for (final Map.Entry<String, Object> entry : model.getParameterNamesAndValues().entrySet()) {
            writer.startNode(entry.getKey());
            context.convertAnother(entry.getValue());
            writer.endNode();
        }
    }

    @Override
    public Object unmarshal(final HierarchicalStreamReader reader, final UnmarshallingContext context) {
        throw new UnsupportedOperationException("This converter only supports one-way converting into XML."); //$NON-NLS-1$
    }

}

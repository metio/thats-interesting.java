package wtf.metio.ti.converter.internal;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import wtf.metio.ti.converter.StandardConverters;

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
    @SuppressWarnings("null")
    public void marshal(final Object source, final HierarchicalStreamWriter writer, final MarshallingContext context) {
        // cast is fine here because XStream checks with '#canConvert' before
        final MethodInvocationModel model = (MethodInvocationModel) source;

        writer.startNode(StandardConverters.CLASS);
        writer.setValue(StandardConverters.getPOIName(model.getMethod()));
        writer.endNode();

        writer.startNode(StandardConverters.METHOD);
        writer.setValue(StandardConverters.getPOIMethodName(model.getMethod()));
        writer.endNode();

        writer.startNode(StandardConverters.ARGUMENTS);
        for (final Map.Entry<String, Object> entry : model.getParameterNamesAndValues().entrySet()) {
            writer.startNode(entry.getKey());
            context.convertAnother(entry.getValue());
            writer.endNode();
        }
        writer.endNode();

        for (final Entry<String, Supplier<Object>> entry : model.getExtras().entrySet()) {
            writer.startNode(entry.getKey());
            context.convertAnother(entry.getValue().get());
            writer.endNode();
        }
    }

    @Override
    public Object unmarshal(final HierarchicalStreamReader reader, final UnmarshallingContext context) {
        throw new UnsupportedOperationException("This converter only supports one-way converting into XML."); //$NON-NLS-1$
    }

}

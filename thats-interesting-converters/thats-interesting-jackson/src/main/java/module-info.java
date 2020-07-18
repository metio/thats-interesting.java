module wtf.metio.ti.converter.jackson {

    exports wtf.metio.ti.converter.jackson;

    requires wtf.metio.ti.core;
    requires com.fasterxml.jackson.databind;
    
    opens wtf.metio.ti.converter.jackson to com.fasterxml.jackson.databind;

}

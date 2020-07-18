module wtf.metio.ti.converter.gson {

    exports wtf.metio.ti.converter.gson;

    requires wtf.metio.ti.core;
    requires com.google.gson;
    
    opens wtf.metio.ti.converter.gson to com.google.gson;

}

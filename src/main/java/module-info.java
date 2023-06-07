module ch.fhnw {
    // Module Exports
    exports ch.fhnw.clashofenergiespeicher.catalog;
    exports ch.fhnw.clashofenergiespeicher.catalog.components;
    exports ch.fhnw.clashofenergiespeicher.catalog.components.helpers;

    // Pi4J Modules
    requires com.pi4j;
    requires com.pi4j.library.pigpio;
    requires com.pi4j.library.linuxfs;
    requires com.pi4j.plugin.pigpio;
    requires com.pi4j.plugin.raspberrypi;
    requires com.pi4j.plugin.mock;
    requires com.pi4j.plugin.linuxfs;

    uses com.pi4j.extension.Extension;
    uses com.pi4j.provider.Provider;

    // for logging
    requires java.logging;

    // JavaFX
    requires javafx.base;
    requires javafx.controls;

    requires info.picocli;
    requires javafx.fxml;
    requires org.apache.logging.log4j;

    // allow access to classes in the following namespaces for Pi4J annotation
    // processing
    opens ch.fhnw.clashofenergiespeicher.catalog to com.pi4j, info.picocli;
    opens ch.fhnw.clashofenergiespeicher.catalog.components to com.pi4j, info.picocli;
    opens ch.fhnw.clashofenergiespeicher.catalog.components.helpers to com.pi4j, info.picocli;
    opens ch.fhnw.clashofenergiespeicher.controller to javafx.fxml;

    exports ch.fhnw.clashofenergiespeicher.catalog.applications;
    exports ch.fhnw.clashofenergiespeicher.controller;


    opens ch.fhnw.clashofenergiespeicher.catalog.applications to com.pi4j, info.picocli;
  exports ch.fhnw.clashofenergiespeicher;
  opens ch.fhnw.clashofenergiespeicher to javafx.fxml;

}

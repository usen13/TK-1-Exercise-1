module com.tk1.exercise1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.rmi;

    opens com.tk1.exercise1 to javafx.fxml;
    exports com.tk1.exercise1;
}
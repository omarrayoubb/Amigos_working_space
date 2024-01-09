module com.example.co_working_space1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.co_working_space1 to javafx.fxml;
    exports com.example.co_working_space1;
}
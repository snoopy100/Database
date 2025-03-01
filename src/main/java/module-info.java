module com.database {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires com.h2database;

    opens com.database to javafx.fxml;
    exports com.database;
}
module Francisco.MinkNotas.MinkNotas {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires mysql.connector.java;

    opens Francisco.MinkNotas.MinkNotas to javafx.fxml;
    exports Francisco.MinkNotas.MinkNotas;
}

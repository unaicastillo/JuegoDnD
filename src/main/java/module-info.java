module com.unaidario {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.unaidario;
    exports com.unaidario.controlador to javafx.fxml;

    opens com.unaidario.controlador to javafx.fxml;
    opens com.unaidario.views to javafx.fxml;
}

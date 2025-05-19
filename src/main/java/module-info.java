module com.unaidarioefra {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.unaidarioefra;
    exports com.unaidarioefra.controlador to javafx.fxml;

    opens com.unaidarioefra.controlador to javafx.fxml;
    opens com.unaidarioefra.views to javafx.fxml;
}

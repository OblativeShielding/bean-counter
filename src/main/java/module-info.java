module com.oblativeshielding {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires javafx.base;

    opens com.oblativeshielding.beancounter to javafx.fxml;
    exports com.oblativeshielding.beancounter;
}

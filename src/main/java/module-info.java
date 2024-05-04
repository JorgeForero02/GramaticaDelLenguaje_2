module com.ufps.gramaticalenguaje_2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.ufps.gramaticalenguaje_2 to javafx.fxml;
    exports com.ufps.gramaticalenguaje_2;
}

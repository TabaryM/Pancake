// https://edencoding.com/javafx-maven/
module Pancake {
    requires javafx.controls;
    requires javafx.fxml;
    opens fr.ul.ia to javafx.fxml;
    exports fr.ul.ia;
    exports fr.ul.ia.modele;
    exports fr.ul.ia.exception;
    exports fr.ul.ia.engine;
}
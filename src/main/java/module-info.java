// https://edencoding.com/javafx-maven/
module Pancake {
    requires javafx.controls;
    requires javafx.fxml;
    opens fr.ul.ia to javafx.fxml;
    exports fr.ul.ia;
}
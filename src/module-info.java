
module CocktailBar {
    requires java.datatransfer;
    requires java.desktop;
    requires javafx.graphics;
    requires javafx.controls;
    opens util to javafx.graphics;
}
package Ingredient;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.CheckBoxMaker;
import util.WindowMaker;

import java.util.stream.Collectors;

public class IngredientToogleView {

    private Stage toogleWindow = new WindowMaker().maker("Ingredient Toogle");
    CheckBoxMaker spiritField = new CheckBoxMaker(Ingredients.getIngredients().stream()
            .filter(x -> x.getType().toLowerCase().equals("spirit")).collect(Collectors.toList()));
    CheckBoxMaker liqueurField = new CheckBoxMaker(Ingredients.getIngredients().stream()
            .filter(x -> x.getType().toLowerCase().equals("liqueur")).collect(Collectors.toList()));
    CheckBoxMaker wineField = new CheckBoxMaker(Ingredients.getIngredients().stream()
            .filter(x -> x.getType().toLowerCase().equals("wine")).collect(Collectors.toList()));
    CheckBoxMaker juiceField = new CheckBoxMaker(Ingredients.getIngredients().stream()
            .filter(x -> x.getType().toLowerCase().equals("juice")).collect(Collectors.toList()));
    CheckBoxMaker syrupField = new CheckBoxMaker(Ingredients.getIngredients().stream()
            .filter(x -> x.getType().toLowerCase().equals("syrup")).collect(Collectors.toList()));
    CheckBoxMaker bitterField = new CheckBoxMaker(Ingredients.getIngredients().stream()
            .filter(x -> x.getType().toLowerCase().equals("bitter")).collect(Collectors.toList()));
    CheckBoxMaker softDrinkField = new CheckBoxMaker(Ingredients.getIngredients().stream()
            .filter(x -> x.getType().toLowerCase().equals("soft drink")).collect(Collectors.toList()));
    CheckBoxMaker dairyField = new CheckBoxMaker(Ingredients.getIngredients().stream()
            .filter(x -> x.getType().toLowerCase().equals("dairy")).collect(Collectors.toList()));
    private String[] types = new String[]{"Spirit", "Liqueur", "Wine", "Mixer", "Syrup", "Misc"};

    public IngredientToogleView() {
        show();
    }

    public void show() {

        GridPane egp = new GridPane();
        egp.setVgap(5);
        egp.setHgap(5);

        for (int i = 0; i < types.length; i++) {
            Button typeButton = new Button(types[i]);
            egp.add(typeButton, i, 0);
            typeButton.setOnAction((event) -> {
                System.out.println(typeButton.getText());
            });
        }

        Button endButton = new Button("End");

       VBox toggleVB = new VBox();

        VBox vb = new VBox();
        vb.setSpacing(15.0);
        vb.getChildren().addAll(egp, toggleVB);

        endButton.setOnAction((event) -> {
            toogleWindow.close();
        });

        Scene szene = new Scene(vb);
        toogleWindow.setScene(szene);
        toogleWindow.show();
    }

    public CheckBoxMaker getPane(String type){
        Pane temp;

        return spiritField;
    }

}

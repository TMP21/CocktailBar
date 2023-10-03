package Ingredient;
import java.util.stream.Collectors;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.CheckBoxMaker;
import util.WindowMaker;

public class IngredientToogleView {

	private Stage toogleWindow = new WindowMaker().maker("Ingredient Toogle");

	public IngredientToogleView() {
		show();
	}

	public void show() {

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

		GridPane egp = new GridPane();
		egp.setVgap(5);
		egp.setHgap(5);
		egp.add(spiritField.toogleGP(), 0, 0);
		egp.add(liqueurField.toogleGP(), 1, 0);
		egp.add(wineField.toogleGP(), 2, 0);
		egp.add(juiceField.toogleGP(), 3, 0);
		egp.add(syrupField.toogleGP(), 4, 0);
		egp.add(bitterField.toogleGP(), 5, 0);
		egp.add(softDrinkField.toogleGP(), 6, 0);
		egp.add(dairyField.toogleGP(), 7, 0);

		Button endButton = new Button("End");

		HBox hb = new HBox();
		hb.setSpacing(10.0);
		hb.getChildren().addAll(endButton);

		VBox vb = new VBox();
		vb.setSpacing(15.0);
		vb.getChildren().addAll(egp, hb);

		endButton.setOnAction((event) -> {
			toogleWindow.close();

		});

		Scene szene = new Scene(vb);
		toogleWindow.setScene(szene);
		toogleWindow.show();
	}

}

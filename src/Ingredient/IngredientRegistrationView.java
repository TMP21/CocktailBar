package Ingredient;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import util.WindowMaker;

public class IngredientRegistrationView {

	Stage erfassungWindow = new WindowMaker().maker("Erfassungs Screen");

	public IngredientRegistrationView() {
		show();
	}

	public void show() {

		Label nameLabel = new Label("Ingredient Name: ");
		Label typeLabel = new Label("Type: ");
		Label secTypeLabel = new Label("Secondary Type: ");
		Label abvLabel = new Label("Abv: ");
		TextField nameTextField = new TextField();
		TextField typeTextField = new TextField();
		TextField secTypeTextField = new TextField();
		TextField abvTextField = new TextField();
		Button newButton = new Button("New");
		Button cancelButton = new Button("Cancel");

		HBox hb = new HBox();
		hb.setSpacing(5.0);
		hb.getChildren().addAll(newButton, cancelButton);

		GridPane gp = new GridPane();
		gp.setVgap(20);
		gp.setHgap(10);
		gp.add(nameLabel, 0, 1);
		gp.add(typeLabel, 0, 2);
		gp.add(secTypeLabel, 0, 3);
		gp.add(abvLabel, 0, 4);
		gp.add(nameTextField, 1, 1);
		gp.add(typeTextField, 1, 2);
		gp.add(secTypeTextField, 1, 3);
		gp.add(abvTextField, 1, 4);
		gp.add(hb, 1, 5);

		newButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Ingredient i = new Ingredient(nameTextField.getText(), typeTextField.getText(), secTypeTextField.getText(), Double.parseDouble(abvTextField.getText().replace(",", ".")), true);
				Ingredients.addIngredient(i);
				erfassungWindow.close();
			}
		});

		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				erfassungWindow.close();
			}

		});

		Scene szene = new Scene(gp);
		erfassungWindow.setScene(szene);
		erfassungWindow.show();
	}
}

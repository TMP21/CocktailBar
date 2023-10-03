package Ingredient;
import javax.swing.JOptionPane;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import util.WindowMaker;

public class IngredientRemovalView {

	String name = "Removal Screen";
	Stage removalWindow = new WindowMaker().maker(name);

	public IngredientRemovalView() {
		show();
	}

	public void show() {

		Label nameLabel = new Label("Name Of The Ingredient: ");
		TextField nameTextField = new TextField();
		Button nextButton = new Button("Next");
		Button cancelButton = new Button("Cancel");

		HBox hb = new HBox();
		hb.setSpacing(5.0);
		hb.getChildren().addAll(nextButton, cancelButton);

		GridPane gp = new GridPane();
		gp.setVgap(20);
		gp.setHgap(10);
		gp.add(nameLabel, 0, 1);
		gp.add(nameTextField, 1, 1);
		gp.add(hb, 1, 3);

		nextButton.setOnAction((event) -> {
			if (!nameTextField.getText().isEmpty()) {
				if (Ingredients.removeIngredient(nameTextField.getText())) {
					JOptionPane.showMessageDialog(null, nameTextField.getText() + " Has Been Removed", name,
							JOptionPane.INFORMATION_MESSAGE);
					nameTextField.clear();
				} else {
					JOptionPane.showMessageDialog(null, "There Was No Ingredient With This Name", name,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Please Enter A Name", name, JOptionPane.INFORMATION_MESSAGE);
			}
		});

		cancelButton.setOnAction((event) -> {
			removalWindow.close();
		});

		Scene szene = new Scene(gp);
		removalWindow.setScene(szene);
		removalWindow.show();
	}

}

package Cocktail;
import javax.swing.JOptionPane;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import util.WindowMaker;

public class CocktailRemovalView {

	Stage removalWindow = new WindowMaker().maker("Removal Screen");

	public CocktailRemovalView() {
		show();
	}

	public void show() {

		Label nameLabel = new Label("Name des Cocktails: ");
		TextField nameTextField = new TextField();
		Button nextButton = new Button("Next");
		Button cancelButton = new Button("Abbrechen");

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
				if (Cocktails.removeCocktail(nameTextField.getText())) {
					JOptionPane.showMessageDialog(null, nameTextField.getText() + " Has Been Removed",
							"Cocktail Removal", JOptionPane.INFORMATION_MESSAGE);
					nameTextField.clear();
				} else {
					JOptionPane.showMessageDialog(null, "There Was No Cocktail With This Name", "Cocktail Removal",
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Please Enter A Name", "Cocktail Removal",
						JOptionPane.INFORMATION_MESSAGE);
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

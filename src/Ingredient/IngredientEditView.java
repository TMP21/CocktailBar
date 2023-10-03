package Ingredient;
import javax.swing.JOptionPane;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import util.WindowMaker;

public class IngredientEditView {

	Stage editWindow = new WindowMaker().maker("Edit Screen");

	private static Ingredient edit;

	public IngredientEditView() {
		show();
	}

	public void show() {

		TextField nameTextField = new TextField();
		Button nextButton = new Button("Next");
		Button cancelButton = new Button("Cancel");

		HBox hb = new HBox();
		hb.setSpacing(5.0);
		hb.getChildren().addAll(nextButton, cancelButton);

		GridPane gp = new GridPane();
		gp.setVgap(20);
		gp.setHgap(10);
		gp.add(new Label("Name Of The Ingredient: "), 0, 1);
		gp.add(nameTextField, 1, 1);
		gp.add(hb, 1, 3);

		nextButton.setOnAction((event) -> {
			if (!nameTextField.getText().isEmpty()) {
				try {
					edit = Ingredients.getIngredient(nameTextField.getText());
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (edit == null) {
					JOptionPane.showMessageDialog(null, "There Is No Ingredient With This Name In The List",
							"Ingredients", JOptionPane.INFORMATION_MESSAGE);
				} else {
					dataView();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Please Insert A Ingredient Name And/Or Type", "Ingredients",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		cancelButton.setOnAction((event) -> {
			editWindow.close();
		});

		Scene szene = new Scene(gp);
		editWindow.setScene(szene);
		editWindow.show();
	}

	public void dataView() {

		TextField nameTextField = new TextField();
		TextField typeTextField = new TextField();
		TextField secTypeTextField = new TextField();
		TextField abvTextField = new TextField();
		CheckBox availableCheckBox = new CheckBox();
		availableCheckBox.setSelected(edit.getAvailable());
		CheckBox alcCheckBox = new CheckBox();
		alcCheckBox.setSelected(edit.getAlcoholic());
		Button finishButton = new Button("Finish");
		Button cancelButton = new Button("Cancel");

		HBox hb = new HBox();
		hb.setSpacing(5.0);
		hb.getChildren().addAll(finishButton, cancelButton);

		GridPane gp = new GridPane();
		gp.setVgap(20);
		gp.setHgap(10);
		gp.add(new Label(edit.getName()), 0, 1);
		gp.add(nameTextField, 1, 1);
		gp.add(new Label(edit.getType()), 0, 2);
		gp.add(typeTextField, 1, 2);
		gp.add(new Label(edit.getSecType()), 0, 3);
		gp.add(secTypeTextField, 1, 3);
		gp.add(new Label(edit.getAbvString()), 0, 4);
		gp.add(abvTextField, 1, 4);
		gp.add(new Label("Available"), 0, 5);
		gp.add(availableCheckBox, 1, 5);
		gp.add(new Label("Alcohol"), 0, 6);
		gp.add(alcCheckBox, 1, 6);
		gp.add(hb, 1, 7);

		finishButton.setOnAction((event) -> {
			if (!nameTextField.getText().isEmpty()) {
				edit.setName(nameTextField.getText());
			}
			if (!typeTextField.getText().isEmpty()) {
				edit.setType(typeTextField.getText());
			}
			if (!secTypeTextField.getText().isEmpty()) {
				edit.setSecType(secTypeTextField.getText());
			}
			if (!abvTextField.getText().isEmpty()) {
				edit.setAbv(Double.parseDouble(abvTextField.getText().replace(",", ".")));
			}
			edit.setAlcoholic(alcCheckBox.isSelected());
			edit.setAvailable(availableCheckBox.isSelected());
			editWindow.close();
		});

		cancelButton.setOnAction((event) -> {
			editWindow.close();
		});

		Scene szene = new Scene(gp);
		editWindow.setScene(szene);
		editWindow.show();
	}

}

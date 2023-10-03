package Cocktail;

import Ingredient.IngredientNotFoundException;
import Ingredient.Ingredients;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.Content;
import util.MenuButtonMaker;
import util.WindowMaker;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class CocktailEditView {

	private String name = "Cocktail Edit";
	private Stage editWindow = new WindowMaker().maker(name);
	private static Cocktail edit;

	public CocktailEditView() {
		show();
	}

	public void show() {

		Label nameLabel = new Label("Name of the Cocktails: ");
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
				edit = Cocktails.getCocktail(nameTextField.getText());
				if (edit == null) {
					JOptionPane.showMessageDialog(null, "There Is No Cocktail With This Name In The List", "Cocktails",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					dataEdit();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Please Insert A Cocktail Name", "Cocktails",
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

	public void dataEdit() {

		TextField nameMenuButton = new TextField();
		TextField ratingMenuButton = new TextField();
		MenuButton glassMenuButton = new MenuButtonMaker().glassMaker();
		MenuButton methodMenuButton = new MenuButtonMaker().methodMaker();

		Button nextButton = new Button("Next");
		Button cancelButton = new Button("Cancel");

		HBox hb = new HBox();
		hb.setSpacing(5.0);
		hb.getChildren().addAll(nextButton, cancelButton);

		GridPane gp = new GridPane();
		gp.setVgap(20);
		gp.setHgap(10);
		gp.add(new Label(edit.getName()), 0, 1);
		gp.add(nameMenuButton, 1, 1);
		gp.add(new Label(edit.getRating().toString()), 0, 2);
		gp.add(ratingMenuButton, 1, 2);
		gp.add(new Label(edit.getGlassWare()), 0, 3);
		gp.add(glassMenuButton, 1, 3);
		gp.add(new Label(edit.getMethod()), 0, 4);
		gp.add(methodMenuButton, 1, 4);
		gp.add(hb, 1, 5);

		nextButton.setOnAction(e -> {
			if (!nameMenuButton.getText().isEmpty()) {
				edit.setName(nameMenuButton.getText());
			}
			if (!ratingMenuButton.getText().isEmpty()) {
				edit.setRating(Double.parseDouble(ratingMenuButton.getText().replace(",", ".")));
			}
			if (!glassMenuButton.getText().contentEquals("----")) {
				edit.setGlassWare(glassMenuButton.getText());
			}
			if (!methodMenuButton.getText().contentEquals("----")) {
				edit.setMethod(methodMenuButton.getText());
			}
			ingrEdit();
		});

		cancelButton.setOnAction(e -> {
			editWindow.close();
		});

		Scene szene = new Scene(gp);
		editWindow.setScene(szene);
		editWindow.show();
	}

	public void ingrEdit() {

		GridPane egp = new GridPane();
		egp.setVgap(20);
		egp.setHgap(10);

		List<String> ingredientsName = new LinkedList<String>();
		List<TextField> ingredientsField = new LinkedList<TextField>();
		List<String> amountName = new LinkedList<String>();
		List<TextField> amountField = new LinkedList<TextField>();
		List<MenuButton> unitField = new LinkedList<MenuButton>();

		for (Content c : edit.getContent()) {
			TextField txtFld1 = new TextField();
			txtFld1.setPromptText("Enter New Ingredient");
			TextField txtFld2 = new TextField();
			txtFld2.setPromptText("Enter New Amount");
			ingredientsName.add(Ingredients.getIngredientFromId(c.getIngrId()).getName());
			amountName.add(c.getAmnt().toString() + " " + c.getUnit());
			ingredientsField.add(txtFld1);
			amountField.add(txtFld2);
			unitField.add(new MenuButtonMaker().unitMaker());
		}

		for (int i = 0; i < (ingredientsField.size()); i++) {
			egp.add(new Label(ingredientsName.get(i)), 0, i);
			egp.add(new Label(amountName.get(i)), 1, i);
			egp.add(ingredientsField.get(i), 2, i);
			egp.add(amountField.get(i), 3, i);
			egp.add(unitField.get(i), 4, i);
		}

		Button ingrButton = new Button("New Ingredient");
		Button recipeButton = new Button("Recipe");
		Button endButton = new Button("End");
		Button cancelButton = new Button("Cancel");

		HBox hb = new HBox();
		hb.setSpacing(5.0);
		hb.getChildren().addAll(ingrButton, recipeButton, endButton, cancelButton);

		VBox vb = new VBox();
		vb.setSpacing(5);
		vb.getChildren().addAll(egp, hb);

		ingrButton.setOnAction(e -> {
			TextField txtFld1 = new TextField();
			txtFld1.setPromptText("Enter New Ingredient");
			TextField txtFld2 = new TextField();
			txtFld2.setPromptText("Enter New Amount");
			ingredientsField.add(txtFld1);
			amountField.add(txtFld2);
			unitField.add(new MenuButtonMaker().unitMaker());
			egp.add(ingredientsField.get(amountField.size() - 1), 2, ingredientsField.size() - 1);
			egp.add(amountField.get(amountField.size() - 1), 3, amountField.size() - 1);
			egp.add(unitField.get(amountField.size() - 1), 4, amountField.size() - 1);
		});

		recipeButton.setOnAction(e -> {
			updateIngr(ingredientsField, amountField, unitField, edit);
			recipeEdit();
		});

		endButton.setOnAction(e -> {
			updateIngr(ingredientsField, amountField, unitField, edit);
			editWindow.close();
		});

		cancelButton.setOnAction(e -> {
			editWindow.close();
		});

		Scene szene = new Scene(vb);
		editWindow.setScene(szene);
		editWindow.show();
	}

	public void recipeEdit() {

		GridPane egp = new GridPane();
		egp.setVgap(20);
		egp.setHgap(10);
		List<String> recipeList = new LinkedList<String>();
		List<TextField> recipeTextField = new LinkedList<TextField>();
		for (String s : edit.getRecipe()) {
			recipeList.add(s);
		}
		int counter = 0;

		for (String s : recipeList) {
			recipeTextField.add(new TextField());
			egp.add(new Label(s), 0, counter);
			egp.add(recipeTextField.get(counter), 1, counter);
			counter++;
		}

		Button stepButton = new Button("New Step");
		Button endButton = new Button("End");
		Button cancelButton = new Button("Cancel");

		HBox hb = new HBox();
		hb.setSpacing(5.0);
		hb.getChildren().addAll(stepButton, endButton, cancelButton);

		egp.add(hb, 0, counter + 1);

		stepButton.setOnAction(e -> {
			TextField tf = new TextField();
			int temp = GridPane.getRowIndex(hb);
			recipeTextField.add(tf);
			egp.add(tf, 1, temp);
			egp.getChildren().removeAll(hb);
			egp.add(hb, 1, temp + 1);
		});

		endButton.setOnAction(e -> {
			updateRecipe(recipeList, recipeTextField, edit);
			editWindow.close();
		});

		cancelButton.setOnAction(e -> {
			editWindow.close();
		});

		Scene szene = new Scene(egp);
		editWindow.setScene(szene);
		editWindow.show();
	}

	public int editOutside(Cocktail c) {
		edit = c;
		dataEdit();
		return 1;
	}

	public void updateRecipe(List<String> recipeList, List<TextField> recipeTextField, Cocktail edit) {
		int c = 0;
		for (TextField tf : recipeTextField) {
			if (!tf.getText().isEmpty()) {
				if (c < recipeList.size()) {
					edit.updateRecipeStep(recipeList.get(c), tf.getText());
				} else {
					edit.addRecipeStep(tf.getText());
				}
			}
			c++;
		}
	}

	public void updateIngr(List<TextField> ingredientsField, List<TextField> amountField, List<MenuButton> unitField,
			Cocktail edit) {
		try {
			for (int i = 0; i < ingredientsField.size(); i++) {
				if (i<edit.getContent().size()) {
					if (!ingredientsField.get(i).getText().isEmpty() && !amountField.get(i).getText().isEmpty()) {
						edit.updateContent(edit.getContent(i),
								new Content(Ingredients.getIngredient(ingredientsField.get(i).getText()).getId(),
										Double.valueOf(amountField.get(i).getText()), unitField.get(i).getText()));
					} else if (!ingredientsField.get(i).getText().isEmpty() && amountField.get(i).getText().isEmpty()) {
						edit.updateContent(edit.getContent(i),
								new Content(Ingredients.getIngredient(ingredientsField.get(i).getText()).getId(),
										edit.getContent(i).getAmnt(), unitField.get(i).getText()));
					} else if (ingredientsField.get(i).getText().isEmpty() && !amountField.get(i).getText().isEmpty()) {
						edit.updateContent(edit.getContent(i), new Content(edit.getContent(i).getIngrId(),
								Double.valueOf(amountField.get(i).getText()), unitField.get(i).getText()));
					} else if (!edit.getContent(i).getUnit().equalsIgnoreCase(unitField.get(i).getText())) {
						edit.getContent(i).setUnit(unitField.get(i).getText());
					}
				} else if (i >= edit.getContent().size()) {
					edit.addContent(new Content(Ingredients.getIngredient(ingredientsField.get(i).getText()).getId(),
							Double.valueOf(amountField.get(i).getText()), unitField.get(i).getText()));
				}
			}
		} catch (IngredientNotFoundException err) {
			JOptionPane.showMessageDialog(null, "Please Enter A Valid Ingredient", name,
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
}

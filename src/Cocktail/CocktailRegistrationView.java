package Cocktail;

import javax.swing.JOptionPane;

import Ingredient.IngredientNotFoundException;
import Ingredient.Ingredients;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import util.Content;
import util.MenuButtonMaker;
import util.WindowMaker;

public class CocktailRegistrationView {

	private Cocktail c;
	private String name = "Cocktail Registration";
	private Stage erfassungWindow = new WindowMaker().maker(name);

	public CocktailRegistrationView() {
		show();
	}

	public void show() {

		Label nameLabel = new Label("Name Of The Cocktails: ");
		Label ratingLabel = new Label("Rating: ");
		Label commentLabel = new Label("Comment: ");
		TextField nameTextField = new TextField();
		TextField ratingTextField = new TextField();
		TextField commentTextField = new TextField();
		Button nextButton = new Button("Next");
		Button cancelButton = new Button("Cancel");

		HBox hb = new HBox();
		hb.setSpacing(5.0);
		hb.getChildren().addAll(nextButton, cancelButton);

		GridPane gp = new GridPane();
		gp.setVgap(20);
		gp.setHgap(10);
		gp.add(nameLabel, 0, 1);
		gp.add(ratingLabel, 0, 2);
		gp.add(commentLabel, 0, 3);
		gp.add(nameTextField, 1, 1);
		gp.add(ratingTextField, 1, 2);
		gp.add(commentTextField, 1, 3);
		gp.add(hb, 1, 4);

		ratingTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.length() >= 1) {
				if (!Character.isDigit(newValue.charAt(newValue.length() - 1))) {
					if (!newValue.substring(newValue.length() - 1).equalsIgnoreCase(".")) {
						if (!newValue.substring(newValue.length() - 1).equalsIgnoreCase(",")) {
							ratingTextField.setText(oldValue);
						}
					}
				}
				if (newValue.length() - newValue.replaceAll("[,.]", "").length() > 1) {
					ratingTextField.setText(oldValue);
				}
			}
		});

		nextButton.setOnAction(e -> {
			try {
				if (!nameTextField.getText().isEmpty() && !ratingTextField.getText().isEmpty()) {
					c = new Cocktail(nameTextField.getText(),
							Double.parseDouble(ratingTextField.getText().replace(",", ".")),
							commentTextField.getText());
					techView();
				} else {
					JOptionPane.showMessageDialog(null, "Please Enter A Valid Name And Rating", name,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (Exception err) {
				JOptionPane.showMessageDialog(null, "Please Enter A Valid Name And Rating", name,
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		cancelButton.setOnAction(e -> {
			erfassungWindow.close();
		});

		Scene szene = new Scene(gp);
		erfassungWindow.setScene(szene);
		erfassungWindow.show();
	}

	public void techView() {

		Label glassLabel = new Label("Glass Ware: ");
		MenuButton glassMB = new MenuButtonMaker().glassMaker();
		Label methodLabel = new Label("Method: ");
		MenuButton methodMB = new MenuButtonMaker().methodMaker();
		Button nextButton = new Button("Next Ingredient");

		HBox hb = new HBox();
		hb.setSpacing(5.0);
		hb.getChildren().addAll(nextButton);

		GridPane gp = new GridPane();
		gp.setVgap(20);
		gp.setHgap(10);
		gp.add(glassLabel, 0, 1);
		gp.add(glassMB, 1, 1);
		gp.add(methodLabel, 0, 2);
		gp.add(methodMB, 1, 2);
		gp.add(hb, 1, 3);

		nextButton.setOnAction(e -> {
			if (!glassMB.getText().equalsIgnoreCase("----") && !methodMB.getText().equalsIgnoreCase("----")) {
				c.setGlassWare(glassMB.getText());
				c.setMethod(methodMB.getText());
				ingrView();
			} else {
				JOptionPane.showMessageDialog(null, "Please Enter A Valid Piece Of Glass Ware And Method", name,
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		Scene scene = new Scene(gp);
		erfassungWindow.setScene(scene);
		erfassungWindow.show();
	}

	public void ingrView() {

		Label ingrLabel = new Label("Ingredient Name: ");
		TextField ingrTextField = new TextField();
		Label amountLabel = new Label("Ingredient Amount: ");
		TextField amountTextField = new TextField();
		MenuButton unit = new MenuButtonMaker().unitMaker();
		Button nextButton = new Button("Next Ingredient");
		Button recipeButton = new Button("Recipe");

		HBox hb = new HBox();
		hb.setSpacing(5.0);
		hb.getChildren().addAll(nextButton, recipeButton);

		GridPane gp = new GridPane();
		gp.setVgap(20);
		gp.setHgap(10);
		gp.add(ingrLabel, 0, 1);
		gp.add(ingrTextField, 1, 1);
		gp.add(amountLabel, 0, 2);
		gp.add(amountTextField, 1, 2);
		gp.add(unit, 2, 2);
		gp.add(hb, 1, 3);
		
		amountTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.length() >= 1) {
				if (!Character.isDigit(newValue.charAt(newValue.length() - 1))) {
					if (!newValue.substring(newValue.length() - 1).equalsIgnoreCase(".")) {
						if (!newValue.substring(newValue.length() - 1).equalsIgnoreCase(",")) {
							amountTextField.setText(oldValue);
						}
					}
				}
				if (newValue.length() - newValue.replaceAll("[,.]", "").length() > 1) {
					amountTextField.setText(oldValue);
				}
			}
		});

		nextButton.setOnAction(e -> {
			try {
				if (!ingrTextField.getText().isEmpty() && !amountTextField.getText().isEmpty()) {
					c.addContent(new Content(
							Ingredients.getIngredient(ingrTextField.getText().trim().replaceAll("[Cc]reme", "Crème")
									.replaceAll("[Cc]uracao", "Curaçao")).getId(),
							Double.valueOf(amountTextField.getText().replace(",", ".").toLowerCase()
									.replaceAll("[a-z]", "").trim()),
							unit.getText()));

					ingrView();
				} else if (!ingrTextField.getText().isEmpty() && amountTextField.getText().isEmpty()) {
					c.addContent(new Content(Ingredients.getIngredient(ingrTextField.getText().trim()).getId(), 0.0,
							unit.getText()));
					ingrView();
				}
			} catch (IngredientNotFoundException err) {
				JOptionPane.showMessageDialog(null, "Please Enter A Valid Ingredient", name,
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		recipeButton.setOnAction(e -> {
			try {
				if (!ingrTextField.getText().isEmpty() && !amountTextField.getText().isEmpty()) {
					c.addContent(new Content(
							Ingredients.getIngredient(ingrTextField.getText().trim().replaceAll("[Cc]reme", "Crème")
									.replaceAll("[Cc]uracao", "Curaçao")).getId(),
							Double.valueOf(amountTextField.getText().replace(",", ".").toLowerCase()
									.replaceAll("[a-z]", "").trim()),
							unit.getText()));
				} else if (!ingrTextField.getText().isEmpty() && amountTextField.getText().isEmpty()) {
					c.addContent(new Content(Ingredients.getIngredient(ingrTextField.getText().trim()).getId(), 0.0,
							unit.getText()));
				}

				c.setAvailable(c.testAvailable());
				recipeView(1);
			} catch (IngredientNotFoundException err) {
				JOptionPane.showMessageDialog(null, "Please Enter A Valid Ingredient", name,
						JOptionPane.INFORMATION_MESSAGE);
			}

		});

		Scene scene = new Scene(gp);
		erfassungWindow.setScene(scene);
		erfassungWindow.show();
	}

	public void recipeView(int s) {

		int step = s;
		Label ingrLabel = new Label("Step " + step + ": ");
		TextField recipeTextField = new TextField();
		Button nextButton = new Button("Next Step");
		Button endButton = new Button("End");

		HBox hb = new HBox();
		hb.setSpacing(5.0);
		hb.getChildren().addAll(nextButton, endButton);

		GridPane gp = new GridPane();
		gp.setVgap(20);
		gp.setHgap(10);
		gp.add(ingrLabel, 0, 1);
		gp.add(recipeTextField, 1, 1);
		gp.add(hb, 1, 3);

		nextButton.setOnAction(e -> {
			if (!recipeTextField.getText().isEmpty()) {
				c.addRecipeStep(recipeTextField.getText());
				recipeView(step + 1);
			} else if (recipeTextField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please Enter A Recipe Step", name,
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		endButton.setOnAction(e -> {
			if (!recipeTextField.getText().isEmpty()) {
				c.addRecipeStep(recipeTextField.getText());
				recipeView(step);
			}
			Cocktails.addCocktail(c);
			erfassungWindow.close();
		});

		Scene scene = new Scene(gp);
		erfassungWindow.setScene(scene);
		erfassungWindow.show();
	}
}

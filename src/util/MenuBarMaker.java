package util;
import Cocktail.CocktailEditView;
import Cocktail.CocktailRegistrationView;
import Cocktail.CocktailRemovalView;
import Cocktail.CocktailShowView;
import Cocktail.Cocktails;
import Ingredient.IngredientEditView;
import Ingredient.IngredientRegistrationView;
import Ingredient.IngredientRemovalView;
import Ingredient.IngredientToogleView;
import Ingredient.Ingredients;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.stage.Stage;

public class MenuBarMaker {

	public static MenuBar menuBar(Stage stage) {

		MenuBar mb = new MenuBar();
		mb.prefWidthProperty().bind(stage.widthProperty());
		Menu file = new Menu("File");
		Menu cocktail = new Menu("Cocktail");
		Menu ingredient = new Menu("Ingredient");
		Menu filter = new Menu("Filter");
		MenuItem load = new MenuItem("Load");
		MenuItem safe = new MenuItem("Safe");
		MenuItem exit = new MenuItem("Exit");
		MenuItem showCocktail = new MenuItem("Show Cocktail");
		MenuItem addCocktail = new MenuItem("New Cocktail");
		MenuItem removeCocktail = new MenuItem("Remove Cocktail");
		MenuItem editCocktail = new MenuItem("Edit Cocktail");
		MenuItem ingredientAdd = new MenuItem("New Ingredient");
		MenuItem removeIngredient = new MenuItem("Remove Ingredient");
		MenuItem editIngredient = new MenuItem("Edit Ingredient");
		MenuItem toogleIngredient = new MenuItem("Toogle Ingredient");
		MenuItem selectFilter = new MenuItem("Filter Cocktails");
		file.getItems().addAll(load, safe, exit, new SeparatorMenuItem());
		cocktail.getItems().addAll(addCocktail, showCocktail, removeCocktail, editCocktail, new SeparatorMenuItem());
		ingredient.getItems().addAll(ingredientAdd, removeIngredient, editIngredient, toogleIngredient, new SeparatorMenuItem());
		filter.getItems().addAll(selectFilter, new SeparatorMenuItem());
		mb.getMenus().addAll(file, cocktail, ingredient, filter);

		load.setOnAction((event) -> {
			Cocktails.laden();
			Ingredients.laden();
		});

		safe.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Cocktails.speichern();
				Ingredients.speichern();
			}
		});

		exit.setOnAction((event) -> {
			Platform.exit();
		});

		showCocktail.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				new CocktailShowView();
			}
		});

		addCocktail.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				new CocktailRegistrationView();
			}
		});

		removeCocktail.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				new CocktailRemovalView();
			}
		});

		editCocktail.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				new CocktailEditView();
			}
		});

		ingredientAdd.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				new IngredientRegistrationView();
			}
		});

		removeIngredient.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				new IngredientRemovalView();
			}
		});
		
		editIngredient.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				new IngredientEditView();
			}
		});
		
		toogleIngredient.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				new IngredientToogleView();
			}
		});

		selectFilter.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				new FilterView();
			}
		});

		return mb;

	}

}

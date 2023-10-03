package util;
import Cocktail.CocktailShowView;
import Cocktail.Cocktails;
import Ingredient.Ingredients;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Bar extends Application {

	private static Stage s = new WindowMaker().maker("My Bar");
	
	@Override
	public void start(Stage stage) {
		new Ingredients();
		new Cocktails();
		mainMenu();
	}

	public static void mainMenu() {

		Button rnd = new Button("Random!");
		Button fl = new Button("Full List");
		HBox bhb = new HBox(rnd, fl);
		VBox mvb = new VBox(MenuBarMaker.menuBar(s), CocktailShowView.cocktailPane(), bhb);

		rnd.setOnAction(e -> {
			mvb.getChildren().setAll(MenuBarMaker.menuBar(s), CocktailShowView.cocktailPane(), bhb);
		});

		fl.setOnAction(e -> {
			new CocktailShowView().showCocktails();
		});

		Scene scene = new Scene(mvb);
		s.setScene(scene);
		s.show();
	}

}
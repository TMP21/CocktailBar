package util;

import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class MenuButtonMaker {

	private MenuButton menuButton = new MenuButton();
	private String[] units = { "ml", "cl", "oz", "Fill Up", "Drop(s)", "Dash(es)", "Part", "Gramm(s)", "Pieces", "",
			"Teaspoon", "Pinch", "Top off", "Barspoon", "Spray", "Rinse" };
	private String[] glass = { "----", "Cocktail Glass", "Copper Mug", "Coupe Glass", "Fancy Glass", "Flip Glass",
			"Highball Glass", "Hurricane Glass", "Margarita Glass", "Martini Glass", "Nick and Nora Glass",
			"Rocks Glass", "Sour Glass", "Shot", "Tumbler" };
	private String[] method = { "----", "Blender", "Build In The Glass", "Shaker", "Stirring", "Toss" };
	private String[] sort = { "Rating Desc", "Rating Asc", "ABV Desc", "ABV Asc", "Alphabetic Desc", "Alphabetic Asc" };

	public MenuButton unitMaker() {

		menuButton.setText(units[0]);

		for (String s : units) {
			MenuItem mi = new MenuItem(s);
			menuButton.getItems().add(mi);

			mi.setOnAction(e -> menuButton.setText(mi.getText()));

		}
		return menuButton;
	}

	public MenuButton glassMaker() {

		menuButton.setText(glass[0]);

		for (String s : glass) {
			MenuItem mi = new MenuItem(s);
			menuButton.getItems().add(mi);

			mi.setOnAction(e -> menuButton.setText(mi.getText()));

		}
		return menuButton;
	}

	public MenuButton methodMaker() {

		menuButton.setText(method[0]);

		for (String s : method) {
			MenuItem mi = new MenuItem(s);
			menuButton.getItems().add(mi);

			mi.setOnAction(e -> menuButton.setText(mi.getText()));

		}
		return menuButton;
	}

	public MenuButton sortMaker() {

		menuButton.setText(sort[0]);

		for (String s : sort) {
			MenuItem mi = new MenuItem(s);
			menuButton.getItems().add(mi);

			mi.setOnAction(e -> menuButton.setText(mi.getText()));

		}
		return menuButton;
	}

}

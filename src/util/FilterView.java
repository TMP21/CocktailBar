package util;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JOptionPane;

import Cocktail.Cocktail;
import Cocktail.CocktailShowView;
import Cocktail.Cocktails;
import Ingredient.Ingredient;
import Ingredient.Ingredients;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FilterView {

	private Stage filterWindow = new WindowMaker().maker("Filter");
	private int state = 0;
	private List<Ingredient> blocked = Ingredients.getBlocked();
	private List<Ingredient> filter = new LinkedList<Ingredient>();
	private List<Cocktail> filtered = new LinkedList<Cocktail>();

	private String labelLayout = "-fx-font-weight: bold";
	private String vboxLayout = "-fx-border-color: grey;\n" + "-fx-border-insets: 5;\n" + "-fx-border-width: 1;\n"
			+ "-fx-border-radius: 5;\n";

	public FilterView() {
		show();
	}

	public void show() {

		List<Ingredient> base = Ingredients.getIngredients();

		Collections.sort(base, new Comparator<Ingredient>() {
			@Override
			public int compare(final Ingredient object1, final Ingredient object2) {
				return object1.getName().compareTo(object2.getName());
			}

		});

		CheckBoxMaker vodkaField = new CheckBoxMaker(
				base.stream().filter(x -> x.getSecType().equalsIgnoreCase("vodka")).collect(Collectors.toList()));

		CheckBoxMaker rumField = new CheckBoxMaker(base.stream()
				.filter(x -> x.getSecType().equalsIgnoreCase("rum")).collect(Collectors.toList()));

		CheckBoxMaker mezcalField = new CheckBoxMaker(base.stream()
				.filter(x -> x.getSecType().equalsIgnoreCase("mezcal")).collect(Collectors.toList()));

		CheckBoxMaker ginField = new CheckBoxMaker(base.stream()
				.filter(x -> x.getSecType().equalsIgnoreCase("gin")).collect(Collectors.toList()));

		CheckBoxMaker whiskeyField = new CheckBoxMaker(base.stream()
				.filter(x -> x.getSecType().equalsIgnoreCase("whiskey")).collect(Collectors.toList()));

		CheckBoxMaker brandyField = new CheckBoxMaker(base.stream()
				.filter(x -> x.getSecType().equalsIgnoreCase("brandy")).collect(Collectors.toList()));

		CheckBoxMaker orangeLiqueurField = new CheckBoxMaker(base.stream()
				.filter(x -> x.getSecType().equalsIgnoreCase("orange liqueur")).collect(Collectors.toList()));

		CheckBoxMaker crèmeLiqueurField = new CheckBoxMaker(base.stream()
				.filter(x -> x.getSecType().equalsIgnoreCase("Crème de")).collect(Collectors.toList()));

		CheckBoxMaker juiceField = new CheckBoxMaker(base.stream()
				.filter(x -> x.getSecType().equalsIgnoreCase("juice")).collect(Collectors.toList()));

		CheckBoxMaker syrupField = new CheckBoxMaker(base.stream()
				.filter(x -> x.getSecType().equalsIgnoreCase("syrup")).collect(Collectors.toList()));

		CheckBoxMaker bitterField = new CheckBoxMaker(base.stream()
				.filter(x -> x.getSecType().equalsIgnoreCase("bitter")).collect(Collectors.toList()));

		CheckBoxMaker wineField = new CheckBoxMaker(base.stream()
				.filter(x -> x.getSecType().equalsIgnoreCase("wine")).collect(Collectors.toList()));

		CheckBoxMaker dairyField = new CheckBoxMaker(base.stream()
				.filter(x -> x.getSecType().equalsIgnoreCase("dairy")).collect(Collectors.toList()));

		CheckBoxMaker softDrinkField = new CheckBoxMaker(base.stream()
				.filter(x -> x.getSecType().equalsIgnoreCase("Soft Drink")).collect(Collectors.toList()));

		CheckBoxMaker miscField = new CheckBoxMaker(base.stream()
				.filter(x -> x.getSecType().equalsIgnoreCase("misc")).collect(Collectors.toList()));

		CheckBoxMaker eggField = new CheckBoxMaker(base.stream()
				.filter(x -> x.getSecType().equalsIgnoreCase("egg")).collect(Collectors.toList()));

		CheckBoxMaker liqueurField = new CheckBoxMaker(base.stream()
				.filter(x -> x.getSecType().equalsIgnoreCase("liqueur")).collect(Collectors.toList()));

		CheckBoxMaker amaroField = new CheckBoxMaker(base.stream()
				.filter(x -> x.getSecType().equalsIgnoreCase("amaro")).collect(Collectors.toList()));

		CheckBoxMaker creamLiqueurField = new CheckBoxMaker(base.stream()
				.filter(x -> x.getSecType().equalsIgnoreCase("cream liqueur")).collect(Collectors.toList()));

		CheckBoxMaker aperitifField = new CheckBoxMaker(base.stream()
				.filter(x -> x.getSecType().equalsIgnoreCase("Aperitif")).collect(Collectors.toList()));

		CheckBoxMaker nutLiqueurField = new CheckBoxMaker(base.stream()
				.filter(x -> x.getSecType().equalsIgnoreCase("Nut Liqueur")).collect(Collectors.toList()));

		CheckBoxMaker schnappsField = new CheckBoxMaker(base.stream()
				.filter(x -> x.getSecType().equalsIgnoreCase("Schnapps")).collect(Collectors.toList()));

		CheckBoxMaker fruitField = new CheckBoxMaker(base.stream()
				.filter(x -> x.getSecType().equalsIgnoreCase("Fruit")).collect(Collectors.toList()));

		CheckBoxMaker aniséeField = new CheckBoxMaker(base.stream()
				.filter(x -> x.getSecType().equalsIgnoreCase("Anisée")).collect(Collectors.toList()));

		GridPane egp = new GridPane();
		egp.setVgap(5);
		egp.setHgap(5);

		egp.add(vboxMaker("Vodka", vodkaField), 0, 0);
		egp.add(vboxMaker("Rum", rumField), 1, 0);
		egp.add(vboxMaker("Mezcal|Tequila", mezcalField), 2, 0);
		egp.add(vboxMaker("Gin", ginField), 3, 0);
		egp.add(vboxMaker("Schnapps", schnappsField), 4, 0);
		egp.add(vboxMaker("Anisée", aniséeField), 5, 0);

		egp.add(vboxMaker("Whiskey", whiskeyField), 6, 0);
		egp.add(vboxMaker("Brandy", brandyField), 7, 0);
		egp.add(vboxMaker("Amaro", amaroField), 8, 0);
		egp.add(vboxMaker("Aperitif", aperitifField), 9, 0);
		egp.add(vboxMaker("Wine", wineField), 10, 0);
		egp.add(vboxMaker("Bitter", bitterField), 11, 0);

		egp.add(vboxMaker("Orange Liqueur", orangeLiqueurField), 0, 1);
		egp.add(vboxMaker("Crème Liqueur", crèmeLiqueurField), 1, 1);
		egp.add(vboxMaker("Liqueur", liqueurField), 2, 1);
		egp.add(vboxMaker("Cream Liqueur", creamLiqueurField), 3, 1);
		egp.add(vboxMaker("Nut Liqueur", nutLiqueurField), 4, 1);

		egp.add(vboxMaker("Juice", juiceField), 5, 1);
		egp.add(vboxMaker("Syrup", syrupField), 6, 1);
		egp.add(vboxMaker("Dairy", dairyField), 7, 1);
		egp.add(vboxMaker("Soft Drink", softDrinkField), 8, 1);
		egp.add(vboxMaker("Misc", miscField), 9, 1);
		egp.add(vboxMaker("Fruit", fruitField), 10, 1);
		egp.add(vboxMaker("Egg", eggField), 11, 1);

		final ToggleGroup group = new ToggleGroup();

		RadioButton rb1 = new RadioButton("Contains Any Of These");
		rb1.setToggleGroup(group);
		rb1.setSelected(true);
		RadioButton rb2 = new RadioButton("Contains All Of These");
		rb2.setToggleGroup(group);
		RadioButton rb3 = new RadioButton("Only Contains These");
		rb3.setToggleGroup(group);
		RadioButton rb4 = new RadioButton("Contains None Of These");
		rb4.setToggleGroup(group);

		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				RadioButton chk = (RadioButton) new_toggle.getToggleGroup().getSelectedToggle();
				switch (chk.getText()) {
				case "Contains Any Of These":
					state = 0;
					break;
				case "Contains All Of These":
					state = 1;
					break;
				case "Only Contains These":
					state = 2;
					break;
				case "Contains None Of These":
					state = 3;
					break;
				default:
					break;
				}
			}
		});

		Button finishButton = new Button("Filter");
		Button cancelButton = new Button("Cancel");

		HBox hb = new HBox();
		hb.setSpacing(10.0);
		hb.getChildren().addAll(finishButton, cancelButton, rb1, rb2, rb3, rb4);

		VBox vb = new VBox();
		vb.setSpacing(15.0);
		vb.getChildren().addAll(egp, hb);

		finishButton.setOnAction(e -> {
			filter = Stream.of(vodkaField.findtickedIngr(), rumField.findtickedIngr(), mezcalField.findtickedIngr(),
					ginField.findtickedIngr(), whiskeyField.findtickedIngr(), orangeLiqueurField.findtickedIngr(),
					crèmeLiqueurField.findtickedIngr(), juiceField.findtickedIngr(), syrupField.findtickedIngr(),
					wineField.findtickedIngr(), bitterField.findtickedIngr(), amaroField.findtickedIngr(),
					wineField.findtickedIngr(), brandyField.findtickedIngr(), dairyField.findtickedIngr(),
					softDrinkField.findtickedIngr(), wineField.findtickedIngr(), miscField.findtickedIngr(),
					eggField.findtickedIngr(), liqueurField.findtickedIngr(), creamLiqueurField.findtickedIngr(),
					aperitifField.findtickedIngr(), nutLiqueurField.findtickedIngr(), schnappsField.findtickedIngr())
					.flatMap(Collection::stream).collect(Collectors.toList());
			showFilteredList();
		});

		cancelButton.setOnAction(e -> {
			filterWindow.close();
		});

		Scene szene = new Scene(vb);
		filterWindow.setScene(szene);
		filterWindow.show();
	}

	public void showFilteredList() {
		filtered.clear();
		switch (state) {
		case (0):
			for (Cocktail c : Cocktails.getCocktailList()) {
				if (!Collections.disjoint(c.getAllIngr(), filter) && Collections.disjoint(c.getAllIngr(), blocked)) {
					filtered.add(c);
				}
			}
			break;
		case (1):
			for (Cocktail c : Cocktails.getCocktailList()) {
				if (c.getAllIngr().containsAll(filter) && Collections.disjoint(c.getAllIngr(), blocked)) {
					filtered.add(c);
				}
			}
			break;
		case (2):
			for (Cocktail c : Cocktails.getCocktailList()) {
				if (filter.containsAll(c.getAllIngr()) && Collections.disjoint(c.getAllIngr(), blocked)) {
					filtered.add(c);
				}
			}
			break;
		case (3):
			for (Cocktail c : Cocktails.getCocktailList()) {
				if (Collections.disjoint(c.getAllIngr(), blocked)) {
					filtered.add(c);
				}
			}
			break;
		default:
			break;
		}
		if (filtered.isEmpty()) {
			JOptionPane.showMessageDialog(null, "There Are No Cocktails Fitting These Criterias.", "Cocktails",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			new CocktailShowView().showCocktaillist(filtered);
		}
	}

	public VBox vboxMaker(String name, CheckBoxMaker ingr) {

		Label tempLabel = new Label(name);
		tempLabel.setStyle(labelLayout);
		VBox tempBox = new VBox(tempLabel, ingr.filterGP());
		tempBox.setStyle(vboxLayout);
		return tempBox;

	}
}

package Cocktail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import Ingredient.Ingredients;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import util.Content;
import util.MenuButtonMaker;
import util.WindowMaker;

public class CocktailShowView {

	private Stage showWindow = new WindowMaker().maker("Show Screen");
	private List<Cocktail> cocktails = new LinkedList<Cocktail>();
	private boolean allBtnState = false;
	private String glassState = "----";
	private String methodState = "----";
	private String sortState = "Rating Desc";
	private Predicate<? super Cocktail> sortFunc = x -> true;
	private static final double charSizeH = 150;
	private static final double charSizeW = 150;

	public CocktailShowView() {
		show();
	}

	public void show() {

		Label nameLabel = new Label("Name des Cocktails: ");
		TextField nameTextField = new TextField();
		Button nextButton = new Button("Show");
		Button cancelButton = new Button("Ende");

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
			for (Cocktail c : Cocktails.getCocktailList()) {
				if (c.getName().toLowerCase().contains(nameTextField.getText().toLowerCase())) {
					cocktails.add(c);
				}
			}
			if (cocktails.isEmpty()) {
				JOptionPane.showMessageDialog(null, "There Is No Cocktail With This Name", "Rezept",
						JOptionPane.INFORMATION_MESSAGE);
			} else if (cocktails.size() == 1) {
				JOptionPane.showMessageDialog(null, cocktails.get(0).printContents(), "Rezept",
						JOptionPane.INFORMATION_MESSAGE);
			} else if (cocktails.size() > 1) {
				showCocktaillist(cocktails);
			}

		});

		cancelButton.setOnAction((event) -> {
			showWindow.close();
		});

		Scene szene = new Scene(gp);
		showWindow.setScene(szene);
		showWindow.show();
	}

	public void showCocktails() {

		List<Cocktail> sl = orderList(Cocktails.getCocktailList());

		GridPane gp = new GridPane();
		ScrollBar sc = new ScrollBar();
		gp.setHgap(30);
		Button nameButtons[] = new Button[Cocktails.getSize()];
		int index = 0;
		int counterX = 0;
		int counterY = 1;

		for (Cocktail c : sl) {
			Button name = new Button(c.getName() + " | Rating: " + c.getRating());
			name.setDisable(!c.testAvailable());
			name.setTooltip(new Tooltip(c.getContentString()));
			name.setOnAction(e -> {
				showCocktail(c);
			});
			if (!allBtnState && !name.isDisabled()) {
				nameButtons[index] = name;
				gp.add(nameButtons[index], counterX % 5, counterY);
				counterX++;
				index++;
				if (counterX % 5 == 0) {
					counterY++;
				}
			} else if (allBtnState) {
				nameButtons[index] = name;
				gp.add(nameButtons[index], counterX % 5, counterY);
				counterX++;
				index++;
				if (counterX % 5 == 0) {
					counterY++;
				}
			}
		}

		Button cancelButton = new Button("Cancel");
		cancelButton.setOnAction(e -> showWindow.close());

		gp.add(cancelButton, 6, counterY + 2);
		gp.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
				+ "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: black;");
		HBox root = new HBox();
		root.setSpacing(10);
		root.getChildren().addAll(filterGP(), gp, sc);
		Scene scene = new Scene(root);
		showWindow.setScene(scene);

		sc.setMin(0);
		sc.setOrientation(Orientation.VERTICAL);
		sc.setPrefHeight(showWindow.getHeight());
		sc.setMax(360);

		sc.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				root.setLayoutY(-new_val.doubleValue());
			}
		});

		showWindow.show();

	}

	public void showCocktail(Cocktail c) {

		Stage showWindow = new WindowMaker().maker("Show Screen");
		GridPane gp = new GridPane();

		try {
			Image cocktailPortirat;
			File f = new File(".\\img\\cocktail\\" + c.getName().replace(" ", "") + ".jpg");
			if (f.exists() && !f.isDirectory()) {
				cocktailPortirat = new Image(new FileInputStream(f));
			} else {
				cocktailPortirat = new Image(new FileInputStream(".\\img\\util\\noImg.jpg"));
			}
			ImageView cocktailView = new ImageView(cocktailPortirat);
			cocktailView.setFitHeight(charSizeH);
			cocktailView.setFitWidth(charSizeW);
			cocktailView.setPreserveRatio(true);
			gp.add(cocktailView, 0, 0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		VBox v1 = new VBox(new Label("Required Glass: "), new Label("Required Method: "), new Label("ABV: "));
		v1.setSpacing(10);
		v1.setAlignment(Pos.CENTER_RIGHT);
		VBox v2 = new VBox(new Label(c.getGlassWare()), new Label(c.getMethod()),
				new Label(new DecimalFormat("#.##").format(c.getAbv()) + " %"));
		v2.setSpacing(10);
		v2.setAlignment(Pos.CENTER_LEFT);
		HBox h1 = new HBox(v1, v2);
		h1.setSpacing(10);

		Button closeButton = new Button("Close");
		Button editButton = new Button("Edit");
		closeButton.setOnAction(e -> showWindow.close());
		editButton.setOnAction(e -> new CocktailEditView().editOutside(c));

		HBox h2 = new HBox(editButton, closeButton);
		h2.setSpacing(10);

		gp.setHgap(30);
		gp.setPadding(new Insets(10, 10, 10, 10));
		gp.add(new Label(c.printContents()), 1, 0);
		gp.add(new Label(c.getComment()), 2, 1);
		gp.add(h1, 2, 0);
		gp.add(new Label(c.printRecipe()), 1, 1);
		gp.add(h2, 3, 2);
		Group root = new Group();
		root.getChildren().addAll(gp);
		Scene scene = new Scene(root);

		showWindow.setScene(scene);
		showWindow.setTitle(c.getName() + "    " + c.getAddDate2() + "    " + c.getId());
		showWindow.show();

	}

	public List<Cocktail> orderList(List<Cocktail> sl) {

		switch (sortState) {
		case "Rating Desc":
			sl = sl.stream().sorted((x, y) -> x.getName().compareTo(y.getName())).collect(Collectors.toList());
			Collections.reverse(sl);
			sl = sl.stream().sorted((x, y) -> Double.compare(x.getRating(), y.getRating()))
					.collect(Collectors.toList());
			Collections.reverse(sl);
			break;
		case "Rating Asc":
			sl = sl.stream().sorted((x, y) -> x.getName().compareTo(y.getName())).collect(Collectors.toList());
			sl = sl.stream().sorted((x, y) -> Double.compare(x.getRating(), y.getRating()))
					.collect(Collectors.toList());
			break;
		case "Alphabetic Asc":
			sl = sl.stream().sorted((x, y) -> x.getName().compareTo(y.getName())).collect(Collectors.toList());
			Collections.reverse(sl);
			break;
		case "Alphabetic Desc":
			sl = sl.stream().sorted((x, y) -> x.getName().compareTo(y.getName())).collect(Collectors.toList());
			break;
		case "ABV Desc":
			sl = sl.stream().sorted((x, y) -> x.getName().compareTo(y.getName())).collect(Collectors.toList());
			Collections.reverse(sl);
			sl = sl.stream().sorted((x, y) -> Double.compare(x.getAbv(), y.getAbv())).collect(Collectors.toList());
			Collections.reverse(sl);
			break;
		case "ABV Asc":
			sl = sl.stream().sorted((x, y) -> x.getName().compareTo(y.getName())).collect(Collectors.toList());
			sl = sl.stream().sorted((x, y) -> Double.compare(x.getAbv(), y.getAbv())).collect(Collectors.toList());
			break;
		default:
			break;
		}

		sl = sl.parallelStream().filter(sortFunc).collect(Collectors.toList());
		return sl;
	}

	public void showCocktaillist(List<Cocktail> sl) {

		GridPane gp = new GridPane();
		gp.setHgap(30);
		Button nameButtons[] = new Button[sl.size()];
		int index = 0;
		int counterX = 0;
		int counterY = 1;
		for (Cocktail c : sl) {
			Button name = new Button(c.getName());
			name.setOnAction((event) -> {
				for (Cocktail cn : Cocktails.getCocktailList()) {
					if (cn.getName().toLowerCase().equals(name.getText().toLowerCase())) {
						showCocktail(cn);
						break;
					}
				}
			});
			nameButtons[index] = name;
			gp.add(nameButtons[index], counterX % 10, counterY);
			gp.add(new Label(c.getRating().toString()), counterX % 10 + 1, counterY);
			counterX = counterX + 2;
			index++;
			if (counterX % 10 == 0) {
				counterY++;
			}
		}

		Button cancelButton = new Button("Close");
		cancelButton.setOnAction((event) -> {
			showWindow.close();
		});
		gp.add(cancelButton, 6, counterY + 2);

		Scene szene = new Scene(gp);
		showWindow.setScene(szene);
		showWindow.show();

	}

	public static HBox cocktailPane() {
		Cocktail cp = null;
		while (true) {
			cp = Cocktails.getCocktail((int) (Math.random() * Cocktails.getSize()));
			if (cp.testAvailable()) {
				break;
			}
		}
		GridPane dataCocktails = new GridPane();
		dataCocktails.setPadding(new Insets(10, 10, 10, 10));
		dataCocktails.setHgap(6);
		dataCocktails.setVgap(2);
		int counter = 2;
		Text text = new Text(30.0, 80.0, cp.getName());
		text.setStyle("-fx-font-weight: bold");
		dataCocktails.add(text, 0, 1);
		dataCocktails.add(new Label(cp.getRating().toString()), 2, 1);
		for (Content c : cp.getContent()) {
			Label i = new Label(Ingredients.getIngredientFromId(c.getIngrId()).getName());
			Label a = new Label(c.getAmnt().toString());
			Label u = new Label(c.getUnit());
			dataCocktails.add(i, 0, counter);
			dataCocktails.add(a, 2, counter);
			dataCocktails.add(u, 3, counter);
			counter++;
		}

		HBox hb = null;

		try {
			Image cocktailPortirat;
			File f = new File(".\\img\\cocktail\\" + cp.getName().replace(" ", "") + ".jpg");
			if (f.exists() && !f.isDirectory()) {
				cocktailPortirat = new Image(new FileInputStream(f));
			} else {
				cocktailPortirat = new Image(new FileInputStream(".\\img\\util\\noImg.jpg"));
			}
			ImageView cocktailView = new ImageView(cocktailPortirat);
			cocktailView.setFitHeight(charSizeH);
			cocktailView.setFitWidth(charSizeW);
			cocktailView.setPreserveRatio(true);
			hb = new HBox(dataCocktails, cocktailView);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			hb = new HBox(dataCocktails);
		}

		return hb;
	}

	private VBox filterGP() {

		CheckBox allBtn = new CheckBox();
		allBtn.setSelected(allBtnState);

		Region filler = new Region();
		HBox.setHgrow(filler, Priority.ALWAYS);

		MenuButton glass = new MenuButtonMaker().glassMaker();
		MenuButton method = new MenuButtonMaker().methodMaker();
		MenuButton sort = new MenuButtonMaker().sortMaker();

		Button filterBtn = new Button("Filter");

		filterBtn.setOnAction(e -> {
			allBtnState = allBtn.isSelected();
			glassState = glass.getText();
			methodState = method.getText();
			sortState = sort.getText();
			if (!glassState.equalsIgnoreCase("----") && methodState.equalsIgnoreCase("----")) {
				sortFunc = x -> x.getGlassWare().equalsIgnoreCase(glassState);
			} else if (glassState.equalsIgnoreCase("----") && !methodState.equalsIgnoreCase("----")) {
				sortFunc = x -> x.getMethod().equalsIgnoreCase(methodState);
			} else if (!glassState.equalsIgnoreCase("----") && !methodState.equalsIgnoreCase("----")) {
				sortFunc = x -> x.getGlassWare().equalsIgnoreCase(glassState)
						&& x.getMethod().equalsIgnoreCase(methodState);
			} else if (glassState.equalsIgnoreCase("----") && methodState.equalsIgnoreCase("----")) {
				sortFunc = x -> true;
			}
			showCocktails();
		});

		VBox filter = new VBox();
		filter.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
				+ "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: black;");
		filter.setSpacing(10);
		filter.getChildren().add(new HBox(new Label("Show All"), filler, allBtn));
		filter.getChildren().add(new HBox(new Label("Filter By Glass: "), filler, glass));
		filter.getChildren().add(new HBox(new Label("Filter By Method: "), filler, method));
		filter.getChildren().add(new HBox(new Label("Sort By: "), filler, sort));
		filter.getChildren().add(filterBtn);
		return filter;
	}

}
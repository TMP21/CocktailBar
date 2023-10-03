package util;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import Ingredient.Ingredient;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class CheckBoxMaker {

	private CheckBox CheckBoxField[];
	private List<Ingredient> baseList = new LinkedList<Ingredient>();

	public CheckBoxMaker(List<Ingredient> list) {
		this.baseList = list;
		this.CheckBoxField = new CheckBox[list.size()];
	}

	public GridPane filterGP() {
		GridPane gp = new GridPane();
		int counter = 0;
		for (Ingredient i : baseList) {
			Label name = new Label(i.getName());
			CheckBoxField[counter] = new CheckBox();
			gp.add(name, 8, counter);
			gp.add(CheckBoxField[counter], 9, counter);
			if (!i.getAvailable()) {
				CheckBoxField[counter].setDisable(true);
			}
			counter++;
		}
		return gp;
	}

	public GridPane toogleGP() {
		GridPane gp = new GridPane();
		int counter = 0;
		for (Ingredient i : baseList) {
			Label name = new Label(i.getName());
			CheckBoxField[counter] = new CheckBox();
			gp.add(name, 8, counter);
			gp.add(CheckBoxField[counter], 9, counter);
			CheckBoxField[counter].setSelected(i.getAvailable());
			CheckBoxField[counter].setOnAction(e -> i.toogleAvailable());
			counter++;
		}
		return gp;
	}

	public List<Ingredient> findtickedIngr() {
		List<Ingredient> ticked = new LinkedList<Ingredient>();
		for (CheckBox cb : CheckBoxField) {
			if (cb.isSelected()) {
				ticked.add(baseList.get(Arrays.asList(CheckBoxField).indexOf(cb)));
			}
		}
		
		return ticked;
	}

}

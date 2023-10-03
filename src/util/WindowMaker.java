package util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class WindowMaker {

	private static double yValue = Screen.getPrimary().getVisualBounds().getHeight() / 7 + 100;
	private static double xValue = Screen.getPrimary().getVisualBounds().getWidth() / 2.5 + 100;
	private static double hight = 450;
	private static double width = 650;
	Stage makerWindow = new Stage();

	public WindowMaker() {
	}

	public Stage maker(String name) {
		makerWindow.setTitle(name);
		makerWindow.initModality(Modality.APPLICATION_MODAL);
		makerWindow.setX(xValue);
		makerWindow.setX(yValue);
		makerWindow.setMinHeight(hight);
		makerWindow.setMinWidth(width);
		try {
			makerWindow.getIcons().add(new Image(new FileInputStream(".\\img\\util\\icon.jpg")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return makerWindow;
	}

}

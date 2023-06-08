package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Window {
	static String path;
	
	public static void openWindow(String window) {
		try {
			path = window + ".fxml";
			
    		Stage primaryStage = new Stage();
			AnchorPane root = (AnchorPane)FXMLLoader.load(application.Main.class.getResource(path));
			// If the function is not static
			// AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource(path));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static AnchorPane switchPane(String pane) {
		try {
			path = pane + ".fxml";
			AnchorPane form = (AnchorPane) FXMLLoader.load(application.Main.class.getResource(path));
			return form;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void closeWindow(AnchorPane pane) {
		Stage stage = (Stage) pane.getScene().getWindow();
		stage.close();
	}
	
	public static void login(AnchorPane oldPane, int yetki) {
		String panel;
		if(yetki > 0) {
			panel = "dashboard";
		}else {
			panel = "kullaniciPaneli";
		}
		closeWindow(oldPane);
		openWindow(panel);
	}
	
	public static void logout(AnchorPane oldPane) {
		closeWindow(oldPane);
		openWindow("login");
	}
	
	public static void inSwitch(AnchorPane oldPane, String pane) {
		oldPane.getChildren().setAll(switchPane(pane));
	}
}

package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Window {
	String path;
	
	public void openWindow(String window) {
		try {
			path = window + ".fxml";
			
    		Stage primaryStage = new Stage();
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource(path));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public AnchorPane switchPane(String pane) {
		try {
			path = pane + ".fxml";
			AnchorPane form = (AnchorPane) FXMLLoader.load(getClass().getResource(path));
			return form;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void closeWindow(AnchorPane pane) {
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
		Window win = new Window();
		win.closeWindow(oldPane);
		win.openWindow(panel);
	}
}

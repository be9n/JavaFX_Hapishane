package application;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class FunctionsClass {

	public static void makeSpinner(Spinner<Integer> spinner, int start, int end) {
		spinner.setEditable(true);
    	SpinnerValueFactory<Integer> spinDegerleri = new SpinnerValueFactory.IntegerSpinnerValueFactory(start, end);
    	spinner.setValueFactory(spinDegerleri);
    }
    
    public static void makeComboBox(ComboBox<String> combo, ObservableList<String> veriler) {
    	combo.getItems().addAll(veriler);
	}
	
    public static int giveIntCinsiyet(String cins) {
    	int check;
    	if(cins.equals("Erkek")) {
    		check = 0;
    	}else {
    		check = 1;
    	}
    	
    	return check;
    }
    
    public static String giveStringCinsiyet(int cins) {
    	String check;
    	if(cins == 0) {
    		check = "Erkek";
    	}else {
    		check = "Kadın";
    	}
    	
    	return check;
    }
    
}

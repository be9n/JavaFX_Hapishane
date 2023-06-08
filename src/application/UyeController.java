package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class UyeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_uye;

    @FXML
    private Label lbl_sonuc;
    
    @FXML
    private TextField txt_kul_adi;

    @FXML
    private TextField txt_kul_soy;
    
    @FXML
    private TextField txt_sifre;

    @FXML
    private TextField txt_sifre_tekrar;

    @FXML
    private ComboBox<String> combo_cins;
    
    @FXML
    private Spinner<Integer> spin_yas;
    
    @FXML
    private AnchorPane uye_form;

    String sql;
    
    ObservableList<Object> veriler;
    @FXML
    void btn_uye_Click(ActionEvent event) {
    	sql = "INSERT INTO users (kul_ad, kul_soyad, sifre, age, gender) VALUES (?, ?, ?, ?, ?)";
    	
    	veriler = FXCollections.observableArrayList(
    			txt_kul_adi.getText(),
    			txt_kul_soy.getText(),
    			Query.MD5Sifrele(txt_sifre.getText()),
    			spin_yas.getValue(),
    			cins(combo_cins.getValue())
    			);

    		int islem = Query.insert(sql, veriler);
    		
			if (islem > 0) {
				Window.login(uye_form, 0);
			}else {
				lbl_sonuc.setText("Kullanici adi veya sifre hatali...");
			}
    }

    @FXML
    void lbl_haveAccount_Click(MouseEvent  event) {
    	Window.inSwitch(uye_form, "login");
    }

    public void makeSpinner() {
    	spin_yas.setEditable(true);
    	SpinnerValueFactory<Integer> spinDegerleri = new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 100);
    	spin_yas.setValueFactory(spinDegerleri);
    }
    
    public void makeComboBox() {
    	combo_cins.getItems().addAll("Erkek", "Kadın");
	}
    
    public int cins(String cins) {
    	int check;
    	if(cins.equals("Erkek")) {
    		check = 0;
    	}else {
    		check = 1;
    	}
    	
    	return check;
    }
    
    @FXML
    void initialize() {
    	makeSpinner();
    	makeComboBox();
    }

}

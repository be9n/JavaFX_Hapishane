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
    	if(!txt_kul_adi.getText().equals("") && !txt_kul_soy.getText().equals("") && !txt_sifre.getText().equals("")) {
    		if(combo_cins.getValue() != null) {
    			
    			sql = "INSERT INTO users (kul_ad, kul_soyad, sifre, age, gender) VALUES (?, ?, ?, ?, ?)";
    	
    			veriler = FXCollections.observableArrayList(
    					txt_kul_adi.getText(),
    					txt_kul_soy.getText(),
    					Query.MD5Sifrele(txt_sifre.getText()),
    					spin_yas.getValue(),
    					FunctionsClass.giveIntCinsiyet(combo_cins.getValue())
    					);

    			if(txt_sifre.getText().equals(txt_sifre_tekrar.getText())) {
    				int islem = Query.crud(sql, veriler);
    				if (islem > 0) {
    					Window.inSwitch(uye_form, "login");
    				}
    			}else {
    				lbl_sonuc.setText("Şifre tekrarı yanlış girildi");
    			}
    		}else {
    			lbl_sonuc.setText("Cinsiyeti seçiniz!");
    		}
    	}else {
    		lbl_sonuc.setText("Tüm boşlukları doldurunuz!!");
    	}
    }

    @FXML
    void lbl_haveAccount_Click(MouseEvent  event) {
    	Window.inSwitch(uye_form, "login");
    }
    
    
    
    @FXML
    void initialize() {
    	ObservableList<String> veriler2 = FXCollections.observableArrayList("Erkek", "Kız");
    	FunctionsClass.makeSpinner(spin_yas, 5, 100);
    	FunctionsClass.makeComboBox(combo_cins, veriler2);
    }

}

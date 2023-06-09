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
import javafx.scene.layout.AnchorPane;

public class HukumluEkleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private Label lbl_sonuc;
    
    @FXML
    private URL location;

    @FXML
    private Button btn_ekle;

    @FXML
    private ComboBox<String> combo_cins;

    @FXML
    private AnchorPane huk_form;

    @FXML
    private Spinner<Integer> spin_yas;

    @FXML
    private TextField txt_ana_ad;

    @FXML
    private TextField txt_baba_ad;

    @FXML
    private TextField txt_huk_adi;

    @FXML
    private TextField txt_huk_soy;
    
    String sql;
    ObservableList<Object> veriler;
    @FXML
    void btn_uye_Click(ActionEvent event) {
    	sql = "INSERT INTO hukumluler (huk_ad, huk_soyad, huk_ana_ad, huk_baba_ad, cins, yas) VALUES (?, ?, ?, ?, ?, ?)";
    	
    	veriler = FXCollections.observableArrayList(
    			txt_huk_adi.getText(),
    			txt_huk_soy.getText(),
    			txt_ana_ad.getText(),
    			txt_baba_ad.getText(),
    			FunctionsClass.giveIntCinsiyet(combo_cins.getValue()),
    			spin_yas.getValue()
    			);

    		int islem = Query.crud(sql, veriler);
    		
			if (islem > 0) {
				lbl_sonuc.setText("Hükümlü başarıyla eklendi");
			}else {
				lbl_sonuc.setText("Kullanici adi veya sifre hatali...");
			}
    }
    
    @FXML
    void initialize() {
    	ObservableList<String> veriler2 = FXCollections.observableArrayList("Erkek", "Kız");
    	FunctionsClass.makeSpinner(spin_yas, 5, 100);
    	FunctionsClass.makeComboBox(combo_cins, veriler2);
    }

}

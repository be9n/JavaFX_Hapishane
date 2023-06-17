package application;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class HukumluSorgulaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_cins;

    @FXML
    private Button btn_huk_ad;

    @FXML
    private Button btn_huk_soyad;

    @FXML
    private Button btn_sorgula;

    @FXML
    private Button btn_suc;

    @FXML
    private Button btn_sure;

    @FXML
    private Button btn_taleb_olustur;

    @FXML
    private Button btn_yas;
    
    @FXML
    private Label lbl_sonuc_error;

    @FXML
    private TextField txt_baba_ad;

    @FXML
    private TextField txt_ana_ad;

    @FXML
    private TextField txt_huk_ad;

    @FXML
    private TextField txt_huk_soyad;

    @FXML
    private AnchorPane hukumlu_sorgula_form;
    
    String sql;
    ObservableList<Object> veriler1;
    public int userSession = LoginController.getUserSession();
    public int hukumluId;
    
    @FXML
    void btn_sorgula_Click(ActionEvent event) {
    	if(!txt_huk_ad.getText().equals("") && !txt_huk_soyad.getText().equals("") && !txt_ana_ad.getText().equals("") && !txt_baba_ad.getText().equals("")) {
    		
    	
    		sql = "select * from hukumluler where huk_ad=? and huk_soyad=? and huk_ana_ad=? and huk_baba_Ad=?";
    	
			veriler1 = FXCollections.observableArrayList(
					txt_huk_ad.getText(),
					txt_huk_soyad.getText(),
					txt_ana_ad.getText(),
					txt_baba_ad.getText()
					);
    	
			ResultSet getirilen = Query.select(sql, veriler1);
		
			try {
				if (!getirilen.next()) {
					lbl_sonuc_error.setText("Hükümlü bilgileri bulunamadı!!");
					disableProps(true);
				}else {
					btn_huk_ad.setText(getirilen.getString("huk_ad"));
					btn_huk_soyad.setText(getirilen.getString("huk_soyad"));
					btn_suc.setText(getirilen.getString("huk_suc"));
					btn_sure.setText(String.valueOf(getirilen.getInt("ceza_sure"))+ " Ay");
					btn_cins.setText(FunctionsClass.giveStringCinsiyet(getirilen.getInt("cins")));
					btn_yas.setText(String.valueOf(getirilen.getString("yas")));
					disableProps(false);
					hukumluId = getirilen.getInt("id");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}else {
    		lbl_sonuc_error.setText("Tüm boşlukları doldurunuz!!");
    	}
    }

    @FXML
    void btn_taleb_olustur_Click(ActionEvent event) {
    	TalebOlusturController.setHukumlu_id(hukumluId);
    	Window.openWindow("TalepOlustur");
    }

    
   
    
    public void disableProps(boolean bool) {
    	
    	btn_huk_ad.setDisable(bool);
    	btn_huk_soyad.setDisable(bool);
    	btn_suc.setDisable(bool);
    	btn_sure.setDisable(bool);
    	btn_cins.setDisable(bool);
    	btn_yas.setDisable(bool);
    	btn_taleb_olustur.setDisable(bool);
    }
    
    @FXML
    void initialize() {
    	TalebOlusturController.setZiyaretci_id(userSession);
    	disableProps(true);
    }

}

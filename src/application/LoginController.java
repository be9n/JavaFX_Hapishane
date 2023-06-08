package application;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.control.Label;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class LoginController {
	
	@FXML
    private AnchorPane login_form;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lbl_sonuc;
    
    @FXML
    private TextField txt_kul_adi;
    
    @FXML
    private TextField txt_sifre;
    
    @FXML
    private Button btn_login;

    @FXML
    private Button btn_uye;

    String sql;
    ObservableList<Object> veriler1;
    @FXML
    void btn_login_Click(ActionEvent event) {
    	
    	
    	sql = "select * from users where kul_ad = ? and sifre = ?";
    	
    		veriler1 = FXCollections.observableArrayList(txt_kul_adi.getText(), Query.MD5Sifrele(txt_sifre.getText()));
        	
			ResultSet getirilen = Query.select(sql, veriler1);
			
			try {
				if (!getirilen.next()) {
					lbl_sonuc.setText("Kullanici adi veya sifre hatali...");
				}else {
					Window.login(login_form, getirilen.getInt("yetki"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
    	
    }

    @FXML
    void btn_uye_Click(ActionEvent event) {
    	Window.inSwitch(login_form, "uye");
    }

    @FXML
    void initialize() {} 
}

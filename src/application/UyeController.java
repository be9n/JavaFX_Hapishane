package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private TextField txt_sifre;

    @FXML
    private TextField txt_sifre_tekrar;

    @FXML
    private AnchorPane uye_form;

    String sql;
    
    ObservableList<String> veriler1;
    @FXML
    void btn_uye_Click(ActionEvent event) {
    	sql = "INSERT INTO users (kul_ad, sifre) VALUES (?, ?)";
    	veriler1 = FXCollections.observableArrayList(txt_kul_adi.getText(), Query.MD5Sifrele(txt_sifre.getText()));

    		int islem = Query.insert(sql, veriler1);
    		
			if (islem > 0) {
				Window.login(uye_form, 0);
			}else {
				lbl_sonuc.setText("Kullanici adi veya sifre hatali...");
			}
    }

    @FXML
    void lbl_haveAccount_Click(MouseEvent  event) {
    	Window win = new Window();
		uye_form.getChildren().setAll(win.switchPane("login"));
    }

    @FXML
    void initialize() {}

}

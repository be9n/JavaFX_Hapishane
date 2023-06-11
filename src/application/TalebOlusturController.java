package application;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.LocalDateStringConverter;

public class TalebOlusturController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_talep_olustur;
    
    @FXML
    private Label lbl_hukumlu_id;

    @FXML
    private AnchorPane talebOlustur_form;
    
    @FXML
    private DatePicker date_tarih;
    
    @FXML
    private Label lbl_ziyaretci_id;

    private static int ziyaretci_id;
    private static int hukumlu_id;
    
    String sql;
    ObservableList<Object> veriler;
	public static int getZiyaretci_id() {
		return ziyaretci_id;
	}

	public static void setZiyaretci_id(int ziyaretci_id) {
		TalebOlusturController.ziyaretci_id = ziyaretci_id;
	}

	public static int getHukumlu_id() {
		return hukumlu_id;
	}

	public static void setHukumlu_id(int hukumlu_id) {
		TalebOlusturController.hukumlu_id = hukumlu_id;
	}
	
	 @FXML
	void btn_talep_olustur_Click(ActionEvent event) {
		 sql = "INSERT INTO talebler (tarih, ziyaretci_id, hukumlu_id, durum) VALUES (?, ?, ?, ?)";
	    	
	    	veriler = FXCollections.observableArrayList(
	    			String.valueOf(date_tarih.getValue()),
	    			getZiyaretci_id(),
	    			getHukumlu_id(),
	    			"Bekliyor"
	    			);

	    		int islem = Query.crud(sql, veriler);
	    		
				if (islem > 0) {
					Window.closeWindow(talebOlustur_form);
				}else {
					
				}
		
	}

	@FXML
    void initialize() {
		 date_tarih.setConverter(new LocalDateStringConverter(DateTimeFormatter.ofPattern("dd/MM/yyyy"), null));
	}

}

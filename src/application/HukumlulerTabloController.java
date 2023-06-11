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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class HukumlulerTabloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_ekle;
    
    @FXML
    private Button btn_guncelle;

    @FXML
    private Button btn_sil;

    @FXML
    private TableColumn<Hukumlu, String> col_ad;

    @FXML
    private TableColumn<Hukumlu, String> col_ana_ad;

    @FXML
    private TableColumn<Hukumlu, String> col_baba_ad;
    
    @FXML
    private TableColumn<Hukumlu, String> col_cinsiyet;

    @FXML
    private TableColumn<Hukumlu, Integer> col_id;

    @FXML
    private TableColumn<Hukumlu, String> col_soyad;

    @FXML
    private TableColumn<Hukumlu, String> col_suc;

    @FXML
    private TableColumn<Hukumlu, Integer> col_sure;

    @FXML
    private TableColumn<Hukumlu, Integer> col_yas;

    @FXML
    private ComboBox<String> combo_cins;

    @FXML
    private Spinner<Integer> spin_sure;

    @FXML
    private Spinner<Integer> spin_yas;

    @FXML
    private TableView<Hukumlu> tbl_hukumluler;

    @FXML
    private TextField txt_ad;

    @FXML
    private TextField txt_ana_ad;


    @FXML
    private TextField txt_baba_ad;

    @FXML
    private TextField txt_soyad;

    @FXML
    private TextField txt_suc;

    @FXML
    private AnchorPane hukumluTable_form;

    ObservableList<Object> veriler;
    String sql;
    Hukumlu kayit = new Hukumlu();
    public void DegerleriGetir(TableView<Hukumlu> tablo) {
		sql="select * from hukumluler";
		ResultSet getirilen = Query.selectNoParamiters(sql);
		tabloDoldur(getirilen);
	}
    
    public void tabloDoldur(ResultSet getirilen) {
    	ObservableList<Hukumlu> kayitlarListe = FXCollections.observableArrayList();
    	String cins;
		try {
			while(getirilen.next()) {
				cins = FunctionsClass.giveStringCinsiyet(getirilen.getInt("cins"));
				kayitlarListe.add(new Hukumlu(
						getirilen.getInt("id"),
						getirilen.getString("huk_ad"),
						getirilen.getString("huk_soyad"),
						getirilen.getString("huk_ana_ad"),
						getirilen.getString("huk_baba_ad"),
						getirilen.getString("huk_suc"),
						getirilen.getInt("ceza_sure"),
						cins,
						getirilen.getInt("yas")
						));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_ad.setCellValueFactory(new PropertyValueFactory<>("huk_ad"));
		col_soyad.setCellValueFactory(new PropertyValueFactory<>("huk_soyad"));
		col_ana_ad.setCellValueFactory(new PropertyValueFactory<>("huk_ana_ad"));
		col_baba_ad.setCellValueFactory(new PropertyValueFactory<>("huk_baba_ad"));
		col_suc.setCellValueFactory(new PropertyValueFactory<>("huk_suc"));
		col_sure.setCellValueFactory(new PropertyValueFactory<>("ceza_sure"));
		col_cinsiyet.setCellValueFactory(new PropertyValueFactory<>("cins"));
		col_yas.setCellValueFactory(new PropertyValueFactory<>("yas"));
		
		tbl_hukumluler.setItems(kayitlarListe);
		
		disableProps(true);
    }
    
    public void disableProps(boolean bool) {
    	
    	txt_ad.setDisable(bool);
    	txt_soyad.setDisable(bool);
    	txt_ana_ad.setDisable(bool);
    	txt_baba_ad.setDisable(bool);
    	txt_suc.setDisable(bool);
    	
    	combo_cins.setDisable(bool);
    	spin_yas.setDisable(bool);
    	spin_sure.setDisable(bool);
    	
    	btn_guncelle.setDisable(bool);
    	btn_sil.setDisable(bool);
    }
    
    @FXML
    void btn_guncelle_Click(ActionEvent event) {
    	sql = "update hukumluler set huk_ad=?, huk_soyad=?, huk_ana_ad=?, huk_baba_ad=?, huk_suc=?, ceza_sure=?, cins=?, yas=? where id = ?";
    	
    	veriler = FXCollections.observableArrayList(
    			txt_ad.getText(),
    			txt_soyad.getText(),
    			txt_ana_ad.getText(),
    			txt_baba_ad.getText(),
    			txt_suc.getText(),
    			spin_sure.getValue(),
    			FunctionsClass.giveIntCinsiyet(combo_cins.getValue()),
    			spin_yas.getValue(),
    			getKayitId()
    			);

    		int islem = Query.crud(sql, veriler);
    		DegerleriGetir(tbl_hukumluler);
    }

    @FXML
    void btn_sil_Click(ActionEvent event) {
    	sql = "delete from hukumluler where id = ?";
    	
    	
		veriler = FXCollections.observableArrayList(getKayitId());
    	
		int sonuc = Query.crud(sql, veriler);
		DegerleriGetir(tbl_hukumluler);
    }

    public int getKayitId() {
    	kayit =  tbl_hukumluler.getItems().get(tbl_hukumluler.getSelectionModel().getSelectedIndex());
    	return kayit.getId();
    }
    
    @FXML
    void tbl_hukumluler_MouseClick(MouseEvent event) {
    	Hukumlu selectedItem = tbl_hukumluler.getSelectionModel().getSelectedItem();
    	if(selectedItem != null) {
    		disableProps(false);
    		FunctionsClass.makeSpinner(spin_yas, 5, 100);
        	FunctionsClass.makeSpinner(spin_sure, 1, 1000);
        	ObservableList<String> veriler2 = FXCollections.observableArrayList("Erkek", "Kadın");
        	FunctionsClass.makeComboBox(combo_cins, veriler2);
    	 
    	 
    	kayit =  tbl_hukumluler.getItems().get(tbl_hukumluler.getSelectionModel().getSelectedIndex());
    	txt_ad.setText(kayit.getHuk_ad());
    	txt_soyad.setText(kayit.getHuk_soyad());
    	txt_ana_ad.setText(kayit.getHuk_ana_ad());
    	txt_baba_ad.setText(kayit.getHuk_baba_ad());
    	txt_suc.setText(kayit.getHuk_suc());
    	spin_sure.getValueFactory().setValue(kayit.getCeza_sure());
    	combo_cins.setValue(kayit.getCins());
    	spin_yas.getValueFactory().setValue(kayit.getYas());
    	}
    }
    
    @FXML
    void btn_ekle_Click(ActionEvent event) {
    	Window.inSwitch(hukumluTable_form, "hukumluEkle");
    }

    
    @FXML
    void initialize() {
    	DegerleriGetir(tbl_hukumluler);
    	FunctionsClass.makeSpinner(spin_yas, 5, 100);
    	FunctionsClass.makeSpinner(spin_sure, 1, 1000);
    	ObservableList<String> veriler2 = FXCollections.observableArrayList("Erkek", "Kadın");
    	FunctionsClass.makeComboBox(combo_cins, veriler2);
    }

}

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

public class UsersTabloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_guncelle;

    @FXML
    private Button btn_sil;

    @FXML
    private TableColumn<Users, String> col_ad;

    @FXML
    private TableColumn<Users, String> col_cinsiyet;

    @FXML
    private TableColumn<Users, Integer> col_id;

    @FXML
    private TableColumn<Users, String> col_soyad;

    @FXML
    private TableColumn<Users, Integer> col_yas;

    @FXML
    private TextField txt_ad;

    @FXML
    private TextField txt_soyad;

    @FXML
    private ComboBox<String> combo_cins;

    @FXML
    private Spinner<Integer> spin_yas;

    @FXML
    private AnchorPane usersTable_form;

    @FXML
    private TableView<Users> tbl_users;

    ObservableList<Object> veriler;
    String sql;
    Users kayit = new Users();
    public void DegerleriGetir(TableView<Users> tablo) {
		sql="select * from users where yetki = 0";
		ObservableList<Users> kayitlarListe = FXCollections.observableArrayList();
		
		try {
			ResultSet getirilen = Query.selectNoParamiters(sql);
			
			String cins;
			while(getirilen.next()) {
				cins = FunctionsClass.giveStringCinsiyet(getirilen.getInt("gender"));
				kayitlarListe.add(new Users(
						getirilen.getInt("id"),
						getirilen.getString("kul_ad"),
						getirilen.getString("kul_soyad"),
						cins,
						getirilen.getInt("age"))
				);
			}
			
			col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
			col_ad.setCellValueFactory(new PropertyValueFactory<>("kul_ad"));
			col_soyad.setCellValueFactory(new PropertyValueFactory<>("kul_soyad"));
			col_cinsiyet.setCellValueFactory(new PropertyValueFactory<>("cins"));
			col_yas.setCellValueFactory(new PropertyValueFactory<>("yas"));
			tbl_users.setItems(kayitlarListe);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		disableProps(true);
	}
    
    @FXML
    void tbl_users_MouseClick(MouseEvent event) {
    	Users selectedItem = tbl_users.getSelectionModel().getSelectedItem();
    	if(selectedItem != null) {
    		disableProps(false);
    		FunctionsClass.makeSpinner(spin_yas, 5, 100);
        	ObservableList<String> veriler2 = FXCollections.observableArrayList("Erkek", "Kadın");
        	FunctionsClass.makeComboBox(combo_cins, veriler2);
    	 
    	 
    	kayit =  tbl_users.getItems().get(tbl_users.getSelectionModel().getSelectedIndex());
    	txt_ad.setText(kayit.getKul_ad());
    	txt_soyad.setText(kayit.getKul_soyad());
    	combo_cins.setValue(kayit.getCins());
    	spin_yas.getValueFactory().setValue(kayit.getYas());
    	}
    }
    
    @FXML
    void btn_guncelle_Click(ActionEvent event) {
    	sql = "update users set kul_ad=?, kul_soyad=?, age=?, gender=? where id = ?";
    	veriler = FXCollections.observableArrayList(
    			txt_ad.getText(),
    			txt_soyad.getText(),
    			spin_yas.getValue(),
    			FunctionsClass.giveIntCinsiyet(combo_cins.getValue()),
    			getKayitId()
    			);
    	int sonuc = Query.crud(sql, veriler);
    	DegerleriGetir(tbl_users);
    }

    public int getKayitId() {
    	kayit =  tbl_users.getItems().get(tbl_users.getSelectionModel().getSelectedIndex());
    	return kayit.getId();
    }
    
    @FXML
    void btn_sil_Click(ActionEvent event) {
    	sql = "delete from users where id = ?";
    	
    	
		veriler = FXCollections.observableArrayList(getKayitId());
    	
		int sonuc = Query.crud(sql, veriler);
		DegerleriGetir(tbl_users);
    }
    
    public void disableProps(boolean bool) {
    	
    	txt_ad.setDisable(bool);
    	txt_soyad.setDisable(bool);
    	
    	combo_cins.setDisable(bool);
    	spin_yas.setDisable(bool);
    	
    	btn_guncelle.setDisable(bool);
    	btn_sil.setDisable(bool);
    }
    
    
    @FXML
    void initialize() {
    	DegerleriGetir(tbl_users);
    }

    
    
}




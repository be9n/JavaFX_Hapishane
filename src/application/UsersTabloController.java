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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableColumn<Users, Integer> col_cinsiyet;

    @FXML
    private TableColumn<Users, Integer> col_id;

    @FXML
    private TableColumn<Users, String> col_soyad;

    @FXML
    private TableColumn<Users, Integer> col_yas;

    @FXML
    private TextField txt_ad;

    @FXML
    private TextField txt_cins;

    @FXML
    private TextField txt_soyad;

    @FXML
    private TextField txt_yas;

    @FXML
    private AnchorPane usersTable_form;

    @FXML
    private TableView<Users> tbl_users;

    String sql;
    public void DegerleriGetir(TableView tablo) {
		sql="select * from users";
		ObservableList<Users> kayitlarListe = FXCollections.observableArrayList();
		
		try {
			ResultSet getirilen = Query.selectNoParamiters(sql);
			while(getirilen.next()) {
				kayitlarListe.add(new Users(
						getirilen.getInt("id"),
						getirilen.getString("kul_ad"),
						getirilen.getString("kul_soyad"),
						getirilen.getInt("gender"),
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
	}
    
    @FXML
    void btn_guncelle_Click(ActionEvent event) {

    }

    @FXML
    void btn_sil_Click(ActionEvent event) {

    }

    @FXML
    void initialize() {
    	DegerleriGetir(tbl_users);
    }

    
    
}




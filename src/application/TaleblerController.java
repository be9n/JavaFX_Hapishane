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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class TaleblerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_red;

    @FXML
    private Button btn_kabul;
    
    @FXML
    private ComboBox<String> combo_durum_tipi;
    
    @FXML
    private TableColumn<Talebler, String> col_durum;

    @FXML
    private TableColumn<Talebler, Integer> col_hukumlu_id;

    @FXML
    private TableColumn<Talebler, Integer> col_id;

    @FXML
    private TableColumn<Talebler, String> col_tarih;

    @FXML
    private TableColumn<Talebler, Integer> col_ziyaretci_id;

    @FXML
    private AnchorPane talebler_form;

    @FXML
    private TableView<Talebler> tbl_talebler;

    ObservableList<Object> veriler;
    String sql;
    Talebler kayit = new Talebler();
    String durum = "Bekliyor";
    ResultSet getirilen;
    
    public void DegerleriGetir(TableView<Talebler> tablo) {
    	if(durum.equals("Hepsi")) {
    		sql="select * from talebler";
    		getirilen = Query.selectNoParamiters(sql);
    	}else {
    		sql="select * from talebler where durum = ?";
    	
    		veriler = FXCollections.observableArrayList(
    			durum
    			);
			getirilen = Query.select(sql, veriler);
    	}
    	
		tabloDoldur(getirilen);
	}
    
    public String getNameById(String obj, String table, int obj_id) {
    
    	sql="select "+ obj +" from "+table+" where id = ?";
    	
    	veriler = FXCollections.observableArrayList(
    			obj_id
    			);
    	
    	getirilen = Query.select(sql, veriler);
    	try {
    		if(getirilen.next()) {
    		return getirilen.getString(obj);
    		}else {
    			return null;
    		}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
    	
    }
    
    public void tabloDoldur(ResultSet getirilen) {
    	ObservableList<Talebler> kayitlarListe = FXCollections.observableArrayList();
   
		try {
			while(getirilen.next()) {
				kayitlarListe.add(new Talebler(
						getirilen.getInt("id"),
						getirilen.getString("tarih"),
						getNameById("kul_ad", "users", getirilen.getInt("ziyaretci_id")),
						getNameById("huk_ad", "hukumluler", getirilen.getInt("hukumlu_id")),
						getirilen.getString("durum")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_tarih.setCellValueFactory(new PropertyValueFactory<>("tarih"));
		col_ziyaretci_id.setCellValueFactory(new PropertyValueFactory<>("ziyaretci_ad"));
		col_hukumlu_id.setCellValueFactory(new PropertyValueFactory<>("hukumlu_ad"));
		col_durum.setCellValueFactory(new PropertyValueFactory<>("durum"));
		
		tbl_talebler.setItems(kayitlarListe);
		
		disableProps(true);
    }
    
    public void disableProps(boolean bool) {
    	btn_kabul.setDisable(bool);
    	btn_red.setDisable(bool);
    }
    
    @FXML
    void btn_kabul_Click(ActionEvent event) {
    	sql = "update talebler set durum=? where id = ?";
    	
    	veriler = FXCollections.observableArrayList(
    			"Kabul edildi",
    			getKayitId()
    			);

    		int islem = Query.crud(sql, veriler);
    		DegerleriGetir(tbl_talebler);
    }

    @FXML
    void btn_red_Click(ActionEvent event) {
    	sql = "update talebler set durum=? where id = ?";
    	
    	veriler = FXCollections.observableArrayList(
    			"Red edildi",
    			getKayitId()
    			);

    		int islem = Query.crud(sql, veriler);
    		DegerleriGetir(tbl_talebler);
    }

    public int getKayitId() {
    	kayit =  tbl_talebler.getItems().get(tbl_talebler.getSelectionModel().getSelectedIndex());
    	return kayit.getId();
    }
    
    @FXML
    void tbl_talebler_MouseClick(MouseEvent event) {
    	Talebler selectedItem = tbl_talebler.getSelectionModel().getSelectedItem();
    	if(selectedItem != null) {
    		disableProps(false);
    	 }
    }

    @FXML
    void initialize() {
    	DegerleriGetir(tbl_talebler);
    	ObservableList<String> veriler2 = FXCollections.observableArrayList("Hepsi", "Bekliyor", "Kabul edildi", "Red edildi");
    	FunctionsClass.makeComboBox(combo_durum_tipi, veriler2);
    	combo_durum_tipi.setValue(durum);
    	
    	combo_durum_tipi.setOnAction(event -> {
            durum = combo_durum_tipi.getValue();
            DegerleriGetir(tbl_talebler);
        });
    }

}

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
    
    public void DegerleriGetir(TableView<Talebler> tablo) {
		sql="select * from talebler";
		ResultSet getirilen = Query.selectNoParamiters(sql);
		tabloDoldur(getirilen);
	}
    
    public String getNameById(String obj, String table, int obj_id) {
    
    	sql="select "+ obj +" from "+table+" where id = ?";
    	
    	veriler = FXCollections.observableArrayList(
    			obj_id
    			);
    	
    	ResultSet getirilen = Query.select(sql, veriler);
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

    }

    @FXML
    void btn_red_Click(ActionEvent event) {

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
    }

}

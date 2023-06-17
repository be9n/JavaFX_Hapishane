package application;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class TaleplerimController {

	@FXML
    private TableColumn<Talebler, String> col_durum;

    @FXML
    private TableColumn<Talebler, Integer> col_hukumlu_id;

    @FXML
    private TableColumn<Talebler, Integer> col_id;

    @FXML
    private TableColumn<Talebler, String> col_tarih;

    @FXML
    private ComboBox<String> combo_durum_tipi;

    @FXML
    private AnchorPane taleblerim_form;

    @FXML
    private TableView<Talebler> tbl_taleblerim;

    ObservableList<Object> veriler;
    String sql;
    Talebler kayit = new Talebler();
    String durum = "Bekliyor";
    ResultSet getirilen;
    
    public void DegerleriGetir(TableView<Talebler> tablo) {
    	
    	if(durum.equals("Hepsi")) {
    		sql="select * from talepler";
    		getirilen = Query.selectNoParamiters(sql);
    	}else {
    		sql="select * from talepler where durum = ?";
    	
    		veriler = FXCollections.observableArrayList(
    			durum
    			);
			getirilen = Query.select(sql, veriler);
    	}
    	
		tabloDoldur(getirilen);
	}
    
    public void tabloDoldur(ResultSet getirilen) {
    	ObservableList<Talebler> kayitlarListe = FXCollections.observableArrayList();
   
		try {
			while(getirilen.next()) {
				kayitlarListe.add(new Talebler(
						getirilen.getInt("id"),
						getirilen.getString("tarih"),
						getNameById("huk_ad", "hukumluler", getirilen.getInt("hukumlu_id")),
						getirilen.getString("durum")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_tarih.setCellValueFactory(new PropertyValueFactory<>("tarih"));
		col_hukumlu_id.setCellValueFactory(new PropertyValueFactory<>("hukumlu_ad"));
		col_durum.setCellValueFactory(new PropertyValueFactory<>("durum"));
		
		tbl_taleblerim.setItems(kayitlarListe);
		
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
    
    @FXML
    void tbl_taleblerim_MouseClick(MouseEvent event) {
    	
    }
    
    @FXML
    void initialize() {
    	DegerleriGetir(tbl_taleblerim);
    	ObservableList<String> veriler2 = FXCollections.observableArrayList("Hepsi", "Bekliyor", "Kabul edildi", "Red edildi");
    	FunctionsClass.makeComboBox(combo_durum_tipi, veriler2);
    	combo_durum_tipi.setValue(durum);
    	
    	combo_durum_tipi.setOnAction(event -> {
            durum = combo_durum_tipi.getValue();
            DegerleriGetir(tbl_taleblerim);
        });
    }
}

package application;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.IsteMySQL.Util.VeritabaniUtil;

import javafx.collections.ObservableList;

public class Query {
	static Connection baglanti = null;
    static PreparedStatement sorguIfadesi = null;
    static ResultSet getirilen = null;
    
	public Query() {
		baglanti = VeritabaniUtil.Baglan();
	}
	
	
	public static ResultSet select(String sql, ObservableList<String> veriler) {
		
		try {
			/*sorguIfadesi = baglanti.prepareStatement(sql);
			
			
			for(int i =0; i < veriler.size(); i++) {
				sorguIfadesi.setString(i + 1, veriler.get(i));
			}*/
			sorguIfadesi = sqlStatmentKur(sql, veriler);
			getirilen = sorguIfadesi.executeQuery();
			
			return getirilen;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public static int insert(String sql, ObservableList<String> veriler) {
		
		try {
			sorguIfadesi = sqlStatmentKur(sql, veriler);
			int islem = sorguIfadesi.executeUpdate();
			
			return islem;
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static PreparedStatement sqlStatmentKur(String sql, ObservableList<String> veriler) {
		try {
		baglanti = VeritabaniUtil.Baglan();	
		sorguIfadesi = baglanti.prepareStatement(sql);
		
		for(int i =0; i < veriler.size(); i++) {
			sorguIfadesi.setString(i + 1, veriler.get(i));
		}
		
			return sorguIfadesi;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public static String MD5Sifrele(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] sifrelenmis = md.digest(password.getBytes());
			
			BigInteger no = new BigInteger(1, sifrelenmis);
			
			String hashIcerik = no.toString(16);
			while(hashIcerik.length() < 32) {
				hashIcerik = "0" + hashIcerik;
			}
			return hashIcerik;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}

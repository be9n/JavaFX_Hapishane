package application;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.IsteMySQL.Util.VeritabaniUtil;

import javafx.collections.ObservableList;

public class Query {
	static Connection baglanti = null;
    static PreparedStatement sorguIfadesi = null;
    static ResultSet getirilen = null;
    static private String sql;

	
	public static ResultSet select(String sql, ObservableList<Object> veriler) {
		try {
			setSql(sql);
			sorguIfadesi = sqlStatmentKur(veriler);
			getirilen = sorguIfadesi.executeQuery();
			
			return getirilen;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}
	
	public static ResultSet selectNoParamiters(String sql) {
		try {
			baglanti = VeritabaniUtil.Baglan();	
			setSql(sql);
			sorguIfadesi = baglanti.prepareStatement(getSql());
			getirilen = sorguIfadesi.executeQuery();
			
			return getirilen;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}
	
	public static int crud(String sql, ObservableList<Object> veriler) {
		try {
			setSql(sql);
			sorguIfadesi = sqlStatmentKur(veriler);
			int islem = sorguIfadesi.executeUpdate();
			
			return islem;
		} catch (SQLException e) {
			System.out.println(e);
			return 0;
		}
	}

	
	public static PreparedStatement sqlStatmentKur(ObservableList<Object> veriler) {
		
		try {
		baglanti = VeritabaniUtil.Baglan();	
		sorguIfadesi = baglanti.prepareStatement(getSql());
		
		for(int i =0; i < veriler.size(); i++) {
			
			if(veriler.get(i) instanceof Integer) {
				sorguIfadesi.setInt(i + 1, (Integer) veriler.get(i));
			}else if(veriler.get(i) instanceof String) {
				sorguIfadesi.setString(i + 1, (String) veriler.get(i));
			}
		}
		
			return sorguIfadesi;
		} catch (SQLException e) {
			System.out.println(e);
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
	
	
	public static String getSql() {
		return sql;
	}

	public static void setSql(String sql) {
		Query.sql = sql;
	}
}

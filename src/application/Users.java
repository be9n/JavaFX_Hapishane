package application;

public class Users{
	private int id;
	private String kul_ad;
	private String kul_soyad;
	private String cins;
	private int yas;
	
	Users() {}
	
	Users(int id, String kul_ad, String kul_soyad, String cins, int yas) {
		this.id = id;
		this.kul_ad = kul_ad;
		this.kul_soyad = kul_soyad;
		this.cins = cins;
		this.yas = yas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKul_ad() {
		return kul_ad;
	}

	public void setKul_ad(String kul_ad) {
		this.kul_ad = kul_ad;
	}

	public String getKul_soyad() {
		return kul_soyad;
	}

	public void setKul_soyad(String kul_soyad) {
		this.kul_soyad = kul_soyad;
	}

	
	public String getCins() {
		return cins;
	}
	
	public void setCins(String cins) {
		this.cins = cins;
	}

	public int getYas() {
		return yas;
	}

	public void setYas(int yas) {
		this.yas = yas;
	}
}
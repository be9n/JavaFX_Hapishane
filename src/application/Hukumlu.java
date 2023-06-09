package application;

public class Hukumlu{
	private int id;
	private String huk_ad;
	private String huk_soyad;
	private String huk_ana_ad;
	private String huk_baba_ad;
	private String huk_suc;
	private int ceza_sure;
	private String cins;
	private int yas;
	
	Hukumlu() {}
	
	Hukumlu(int id, String huk_ad, String huk_soyad, String huk_ana_ad, String huk_baba_ad, String huk_suc, int ceza_sure, String cins, int yas) {
		this.id = id;
		this.huk_ad = huk_ad;
		this.huk_soyad = huk_soyad;
		this.huk_ana_ad = huk_ana_ad;
		this.huk_baba_ad = huk_baba_ad;
		this.huk_suc = huk_suc;
		this.ceza_sure = ceza_sure;
		this.cins = cins;
		this.yas = yas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getHuk_ad() {
		return huk_ad;
	}

	public void setHuk_ad(String huk_ad) {
		this.huk_ad = huk_ad;
	}

	public String getHuk_soyad() {
		return huk_soyad;
	}

	public void setHuk_soyad(String huk_soyad) {
		this.huk_soyad = huk_soyad;
	}

	public String getHuk_ana_ad() {
		return huk_ana_ad;
	}

	public void setHuk_ana_ad(String huk_ana_ad) {
		this.huk_ana_ad = huk_ana_ad;
	}

	public String getHuk_baba_ad() {
		return huk_baba_ad;
	}

	public void setHuk_baba_ad(String huk_baba_ad) {
		this.huk_baba_ad = huk_baba_ad;
	}

	public String getHuk_suc() {
		return huk_suc;
	}

	public void setHuk_suc(String huk_suc) {
		this.huk_suc = huk_suc;
	}

	public int getCeza_sure() {
		return ceza_sure;
	}

	public void setCeza_sure(int ceza_sure) {
		this.ceza_sure = ceza_sure;
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
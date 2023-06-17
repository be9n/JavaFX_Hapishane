package application;

public class Talebler{
	private int id;
	private String tarih;
	private String ziyaretci_ad;
	private String hukumlu_ad;
	private String durum;
	
	Talebler() {}
	
	Talebler(int id, String tarih, String ziyaretci_ad, String hukumlu_ad, String durum) {
		this.id = id;
		this.tarih = tarih;
		this.ziyaretci_ad = ziyaretci_ad;
		this.hukumlu_ad = hukumlu_ad;
		this.durum = durum;
	}
	
	Talebler(int id, String tarih, String hukumlu_ad, String durum) {
		this.id = id;
		this.tarih = tarih;
		this.hukumlu_ad = hukumlu_ad;
		this.durum = durum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTarih() {
		return tarih;
	}

	public void setTarih(String tarih) {
		this.tarih = tarih;
	}

	public String getZiyaretci_ad() {
		return ziyaretci_ad;
	}

	public void setZiyaretci_ad(String ziyaretci_ad) {
		this.ziyaretci_ad = ziyaretci_ad;
	}

	public String getHukumlu_ad() {
		return hukumlu_ad;
	}

	public void setHukumlu_ad(String hukumlu_ad) {
		this.hukumlu_ad = hukumlu_ad;
	}

	public String getDurum() {
		return durum;
	}

	public void setDurum(String durum) {
		this.durum = durum;
	}
}

package com.turan.kumbaravegelirgider.domain;

public class KullaniciDomain {

	private int id;
	private String adSoyad;
	private String kullaniciAdi;
	private String hesapKurtarmaNo;
	private String parola;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdSoyad() {
		return adSoyad;
	}

	public void setAdSoyad(String adSoyad) {
		this.adSoyad = adSoyad;
	}

	public String getKullaniciAdi() {
		return kullaniciAdi;
	}

	public void setKullaniciAdi(String kullaniciAdi) {
		this.kullaniciAdi = kullaniciAdi;
	}

	public String getHesapKurtarmaNo() {
		return hesapKurtarmaNo;
	}

	public void setHesapKurtarmaNo(String hesapKurtarmaNo) {
		this.hesapKurtarmaNo = hesapKurtarmaNo;
	}

	public String getParola() {
		return parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

}

package com.turan.kumbaravegelirgider.domain;

import com.turan.kumbaravegelirgider.dao.HedefTanimlaDao;

public class KumbaraTanimlaDomain {

	private int id;
	private int kullaniciId;
	private int hedefId;
	private String adi;
	private String aciklama;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getKullaniciId() {
		return kullaniciId;
	}

	public void setKullaniciId(int kullaniciId) {
		this.kullaniciId = kullaniciId;
	}

	public int getHedefId() {
		return hedefId;
	}

	public void setHedefId(int hedefId) {
		this.hedefId = hedefId;
	}

	public String getAdi() {
		return adi;
	}

	public void setAdi(String adi) {
		this.adi = adi;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	@Override
	public String toString() {
		return aciklama;
	}

}

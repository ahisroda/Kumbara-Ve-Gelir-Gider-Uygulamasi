package com.turan.kumbaravegelirgider.domain;

import java.sql.Timestamp;

import com.turan.kumbaravegelirgider.dao.KumbaraTanimlaDao;

public class KumbaraDomain {

	private int id;
	private int kumbaraId;
	private int kullaniciId;
	private int miktar;
	private String aciklama;
	private Timestamp zaman;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getKumbaraId() {
		return kumbaraId;
	}

	public void setKumbaraId(int kumbaraId) {
		this.kumbaraId = kumbaraId;
	}

	public int getKullaniciId() {
		return kullaniciId;
	}

	public void setKullaniciId(int kullaniciId) {
		this.kullaniciId = kullaniciId;
	}

	public int getMiktar() {
		return miktar;
	}

	public void setMiktar(int miktar) {
		this.miktar = miktar;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public Timestamp getZaman() {
		return zaman;
	}

	public void setZaman(Timestamp zaman) {
		this.zaman = zaman;
	}

	@Override
	public String toString() {
		return aciklama;
	}

}

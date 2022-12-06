package com.turan.kumbaravegelirgider.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.turan.kumbaravegelirgider.domain.GelirGiderDomain;

public class GelirGiderDao {

	public static void tabloOlustur() {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("CREATE TABLE gelirgider("
					+ "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), "
					+ "kullaniciid INTEGER, " + "miktar INTEGER, " + "aciklama VARCHAR(100), " + "tarih VARCHAR(50))");

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Gelir gider tablosu zaten var");
		}
	}

	public static void ekke(GelirGiderDomain eklenecekGelirGider) {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti
					.prepareStatement("INSERT INTO gelirgider(kullaniciid, miktar, aciklama, tarih) VALUES (?,?,?,?)");

			sorgu.setInt(1, eklenecekGelirGider.getKullaniciId());
			sorgu.setInt(2, eklenecekGelirGider.getMiktar());
			sorgu.setString(3, eklenecekGelirGider.getAciklama());
			sorgu.setString(4, eklenecekGelirGider.getTarih());

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public static int toplamKasa(int kullaniciid) {

		int kasa = 0;
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti
					.prepareStatement("SELECT SUM(miktar) FROM gelirgider WHERE kullaniciid = ?");

			sorgu.setInt(1, kullaniciid);

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				kasa = rs.getInt(1);
			}

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return kasa;
	}

	public static ArrayList<GelirGiderDomain> listele(int kullaniciid) {
		ArrayList<GelirGiderDomain> liste = new ArrayList<GelirGiderDomain>();

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti
					.prepareStatement("SELECT * FROM gelirgider WHERE kullaniciid = ? ORDER BY tarih DESC");

			sorgu.setInt(1, kullaniciid);

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				GelirGiderDomain siradakiDomain = new GelirGiderDomain();

				siradakiDomain.setId(rs.getInt("id"));
				siradakiDomain.setAciklama(rs.getString("aciklama"));
				siradakiDomain.setKullaniciId(rs.getInt("kullaniciid"));
				siradakiDomain.setMiktar(rs.getInt("miktar"));
				siradakiDomain.setTarih(rs.getString("tarih"));

				liste.add(siradakiDomain);

			}

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return liste;
	}

	public static ArrayList<GelirGiderDomain> bugununListesi(int kullaniciid, String tarih) {
		ArrayList<GelirGiderDomain> liste = new ArrayList<GelirGiderDomain>();

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti
					.prepareStatement("SELECT * FROM gelirgider WHERE kullaniciid = ? AND tarih = ?");

			sorgu.setInt(1, kullaniciid);
			sorgu.setString(2, tarih);

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				GelirGiderDomain siradakiDomain = new GelirGiderDomain();

				siradakiDomain.setId(rs.getInt("id"));
				siradakiDomain.setAciklama(rs.getString("aciklama"));
				siradakiDomain.setKullaniciId(rs.getInt("kullaniciid"));
				siradakiDomain.setMiktar(rs.getInt("miktar"));
				siradakiDomain.setTarih(rs.getString("tarih"));

				liste.add(siradakiDomain);

			}

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return liste;
	}

	public static void verileriSil(int kullaniciId) {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("DELETE FROM gelirgider WHERE kullaniciid = ?");
			sorgu.setInt(1, kullaniciId);

			sorgu.executeUpdate();
			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

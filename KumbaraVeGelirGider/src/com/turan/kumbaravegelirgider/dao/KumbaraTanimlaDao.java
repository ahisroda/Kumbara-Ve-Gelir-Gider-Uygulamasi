package com.turan.kumbaravegelirgider.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.turan.kumbaravegelirgider.domain.HedefTanimlaDomain;
import com.turan.kumbaravegelirgider.domain.KumbaraTanimlaDomain;

public class KumbaraTanimlaDao {

	public static void tabloOlustur() {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("CREATE TABLE kumbaratanimla("
					+ "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), "
					+ "kullaniciid INTEGER, " + "hedefid INTEGER, " + "adi VARCHAR(50), " + "aciklama VARCHAR(100))");

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Kumbara tanýmla tablosu zaten var");
		}
	}

	public static void ekle(KumbaraTanimlaDomain kumbaraTanimla) {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement(
					"INSERT INTO kumbaratanimla (adi, kullaniciid, hedefid, aciklama) VALUES (?,?,?,?)");

			sorgu.setString(1, kumbaraTanimla.getAdi());
			sorgu.setInt(2, kumbaraTanimla.getKullaniciId());
			sorgu.setInt(3, kumbaraTanimla.getHedefId());
			sorgu.setString(4, kumbaraTanimla.getAciklama());

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public static void sil(KumbaraTanimlaDomain silinecekKumbara) {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("DELETE FROM kumbaratanimla WHERE id = ?");

			sorgu.setInt(1, silinecekKumbara.getId());

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public static ArrayList<KumbaraTanimlaDomain> listele(int kullaniciId) {
		ArrayList<KumbaraTanimlaDomain> liste = new ArrayList<KumbaraTanimlaDomain>();

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("SELECT * FROM kumbaratanimla WHERE kullaniciid = ?");

			sorgu.setInt(1, kullaniciId);

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				KumbaraTanimlaDomain sonrakiDomain = new KumbaraTanimlaDomain();

				sonrakiDomain.setId(rs.getInt("id"));
				sonrakiDomain.setAdi(rs.getString("adi"));
				sonrakiDomain.setHedefId(rs.getInt("hedefid"));
				sonrakiDomain.setKullaniciId(rs.getInt("kullaniciid"));
				sonrakiDomain.setAciklama(rs.getString("aciklama"));

				liste.add(sonrakiDomain);

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
			PreparedStatement sorgu = baglanti.prepareStatement("DELETE FROM kumbaratanimla WHERE kullaniciid = ?");
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

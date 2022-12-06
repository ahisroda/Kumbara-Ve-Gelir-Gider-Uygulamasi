package com.turan.kumbaravegelirgider.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.turan.kumbaravegelirgider.domain.AktifKullaniciDomain;

public class AktifKullaniciDao {

	public static void tabloOlustur() {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("CREATE TABLE aktifkullanici("
					+ "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), "
					+ "kullaniciid INTEGER)");

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Aktif kullanýcý tablosu zaten var");
		}
	}

	public static void ekle(AktifKullaniciDomain eklenecekKullanici) {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("INSERT INTO aktifkullanici(kullaniciid) VALUES (?)");

			sorgu.setInt(1, eklenecekKullanici.getKullaniciId());

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public static void sil() {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("DELETE FROM aktifkullanici");

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public static AktifKullaniciDomain aktifKullaniciIdGetir() {
		AktifKullaniciDomain gelenId = new AktifKullaniciDomain();

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("SELECT * FROM aktifkullanici");

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				gelenId.setId(rs.getInt("id"));
				gelenId.setKullaniciId(rs.getInt("kullaniciid"));
			}

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gelenId;
	}

}

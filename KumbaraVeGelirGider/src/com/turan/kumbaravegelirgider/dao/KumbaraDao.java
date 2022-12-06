package com.turan.kumbaravegelirgider.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.turan.kumbaravegelirgider.domain.KumbaraDomain;
import com.turan.kumbaravegelirgider.domain.KumbaraTanimlaDomain;

public class KumbaraDao {

	public static void tabloOlustur() {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("CREATE TABLE kumbara("
					+ "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), "
					+ "kullaniciid INTEGER, " + "kumbaraid INTEGER, " + "miktar INTEGER, "
					+ "aciklama VARCHAR(100), zaman TIMESTAMP)");

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Kumbara tablosu zaten var");
		}
	}

	public static void ekle(KumbaraDomain eklenecekPara) {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement(
					"INSERT INTO kumbara (kullaniciid, kumbaraid, miktar, aciklama, zaman) VALUES (?,?,?,?, CURRENT_TIMESTAMP)");

			sorgu.setInt(1, eklenecekPara.getKullaniciId());
			sorgu.setInt(2, eklenecekPara.getKumbaraId());
			sorgu.setInt(3, eklenecekPara.getMiktar());
			sorgu.setString(4, eklenecekPara.getAciklama());

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public static ArrayList<KumbaraDomain> listele(int kullaniciId, int kumbaraid) {
		ArrayList<KumbaraDomain> liste = new ArrayList<KumbaraDomain>();

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement(
					"SELECT * FROM kumbara WHERE kullaniciid = ? AND kumbaraid = ? ORDER BY zaman DESC");

			sorgu.setInt(1, kullaniciId);
			sorgu.setInt(2, kumbaraid);

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				KumbaraDomain sonrakiDomain = new KumbaraDomain();

				sonrakiDomain.setId(rs.getInt("id"));
				sonrakiDomain.setKullaniciId(rs.getInt("kullaniciid"));
				sonrakiDomain.setKumbaraId(rs.getInt("kumbaraid"));
				sonrakiDomain.setMiktar(rs.getInt("miktar"));
				sonrakiDomain.setAciklama(rs.getString("aciklama"));
				sonrakiDomain.setZaman(rs.getTimestamp("zaman"));

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

	public static int kumbaraToplamMiktar(int kullaniciid, int kumbaraid) {
		int toplam = 0;

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti
					.prepareStatement("SELECT SUM(miktar) FROM kumbara WHERE kullaniciid = ? AND kumbaraid = ?");

			sorgu.setInt(1, kullaniciid);
			sorgu.setInt(2, kumbaraid);

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				toplam = rs.getInt(1);
			}

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return toplam;
	}

	public static void verileriSil(int kullaniciId) {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("DELETE FROM kumbara WHERE kullaniciid = ?");
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

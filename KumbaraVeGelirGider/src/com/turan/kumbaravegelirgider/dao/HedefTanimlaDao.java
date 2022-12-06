package com.turan.kumbaravegelirgider.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.turan.kumbaravegelirgider.domain.HedefTanimlaDomain;

public class HedefTanimlaDao {

	public static void tabloOlustur() {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("CREATE TABLE hedef("
					+ "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), "
					+ "kullaniciid INTEGER, " + "adi VARCHAR(50), "
					+ "miktar INTEGER, durum INTEGER, aciklama VARCHAR(50))");

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Hedef tablosu zaten var");
		}
	}

	public static void ekle(HedefTanimlaDomain eklenecekHedef) {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement(
					"INSERT INTO hedef(adi, kullaniciid, miktar, durum, aciklama) VALUES (?,?,?,?,?)");

			sorgu.setString(1, eklenecekHedef.getAdi());
			sorgu.setInt(2, eklenecekHedef.getKullaniciId());
			sorgu.setInt(3, eklenecekHedef.getMiktar());
			sorgu.setInt(4, eklenecekHedef.getDurum());
			sorgu.setString(5, eklenecekHedef.getAciklama());

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public static void sil(HedefTanimlaDomain silinecekHedef) {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("DELETE FROM hedef WHERE id = ?");

			sorgu.setInt(1, silinecekHedef.getId());

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public static ArrayList<HedefTanimlaDomain> listele(int kullaniciId) {
		ArrayList<HedefTanimlaDomain> liste = new ArrayList<HedefTanimlaDomain>();

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("SELECT * FROM hedef WHERE kullaniciid = ?");

			sorgu.setInt(1, kullaniciId);

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				HedefTanimlaDomain gelenHedef = new HedefTanimlaDomain();

				gelenHedef.setId(rs.getInt("id"));
				gelenHedef.setKullaniciId(rs.getInt("kullaniciid"));
				gelenHedef.setAdi(rs.getString("adi"));
				gelenHedef.setMiktar(rs.getInt("miktar"));
				gelenHedef.setDurum(rs.getInt("durum"));
				gelenHedef.setAciklama(rs.getString("aciklama"));

				liste.add(gelenHedef);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return liste;
	}

	public static ArrayList<HedefTanimlaDomain> boxdaListele(int kullaniciId) {
		ArrayList<HedefTanimlaDomain> liste = new ArrayList<HedefTanimlaDomain>();

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti
					.prepareStatement("SELECT * FROM hedef WHERE kullaniciid = ? AND durum = 1");

			sorgu.setInt(1, kullaniciId);

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				HedefTanimlaDomain gelenHedef = new HedefTanimlaDomain();

				gelenHedef.setId(rs.getInt("id"));
				gelenHedef.setKullaniciId(rs.getInt("kullaniciid"));
				gelenHedef.setAdi(rs.getString("adi"));
				gelenHedef.setMiktar(rs.getInt("miktar"));
				gelenHedef.setDurum(rs.getInt("durum"));
				gelenHedef.setAciklama(rs.getString("aciklama"));

				liste.add(gelenHedef);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return liste;
	}

	public static void hedefDurumGuncelle(int id, int kullaniciid) {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti
					.prepareStatement("UPDATE hedef SET durum = 2 WHERE id = ? AND kullaniciid = ?");

			sorgu.setInt(1, id);
			sorgu.setInt(2, kullaniciid);

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public static void hedefAciklamaGuncelle(String aciklama, int kullaniciid) {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti
					.prepareStatement("UPDATE hedef SET aciklama = ? WHERE kullaniciid = ? AND durum = 2");

			sorgu.setString(1, aciklama);
			sorgu.setInt(2, kullaniciid);

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public static int hedefMiktarGetir(int id, int kullaniciid) {
		int para = 0;

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti
					.prepareStatement("SELECT SUM(miktar) FROM hedef WHERE id = ? AND kullaniciid = ?");
			sorgu.setInt(1, id);
			sorgu.setInt(2, kullaniciid);

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				para = rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return para;

	}

	public static HedefTanimlaDomain hedefBilgiGetir(int kullaniciid) {
		HedefTanimlaDomain gelenBilgi = new HedefTanimlaDomain();

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("SELECT * FROM hedef WHERE kullaniciid = ?");

			sorgu.setInt(1, kullaniciid);

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				gelenBilgi.setId(rs.getInt("id"));
				gelenBilgi.setAdi(rs.getString("adi"));
				gelenBilgi.setAciklama(rs.getString("aciklama"));
				gelenBilgi.setKullaniciId(rs.getInt("kullaniciid"));
				gelenBilgi.setMiktar(rs.getInt("miktar"));
				gelenBilgi.setDurum(rs.getInt("durum"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return gelenBilgi;

	}

	public static void verileriSil(int kullaniciId) {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("DELETE FROM hedef WHERE kullaniciid = ?");
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

package com.turan.kumbaravegelirgider.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.turan.kumbaravegelirgider.domain.AktifKullaniciDomain;
import com.turan.kumbaravegelirgider.domain.KullaniciDomain;

public class KullaniciDao {

	public static void tabloOlustur() {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("CREATE TABLE kullanici("
					+ "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),"
					+ "adi VARCHAR(50), " + "kullaniciadi VARCHAR(50), " + "hesapkurtarmano VARCHAR(50), "
					+ "parola VARCHAR(50))");

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Kullanýcý tablosu zaten var");
		}
	}

	public static void ekle(KullaniciDomain eklenecekKullanici) {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement(
					"INSERT INTO kullanici(adi,kullaniciadi,  hesapkurtarmano, parola) VALUES (?,?,?,?)");

			sorgu.setString(1, eklenecekKullanici.getAdSoyad());
			sorgu.setString(2, eklenecekKullanici.getKullaniciAdi());
			sorgu.setString(3, eklenecekKullanici.getHesapKurtarmaNo());
			sorgu.setString(4, eklenecekKullanici.getParola());

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public static boolean hesapMevcutMu(String kullaniciAdi) {

		boolean kontrol = false;

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("SELECT * FROM kullanici WHERE kullaniciadi = ?");

			sorgu.setString(1, kullaniciAdi);

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				kontrol = true;
			}

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return kontrol;

	}

	public static KullaniciDomain kullaniciIdGetir(String kullaniciAdi) {
		KullaniciDomain gelenId = new KullaniciDomain();

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("SELECT * FROM kullanici WHERE kullaniciadi =?");

			sorgu.setString(1, kullaniciAdi);

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				gelenId.setId(rs.getInt("id"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return gelenId;

	}

	public static boolean girisBilgiKontrolu(String kullaniciAdi, String parola) {

		boolean kontrol = false;

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti
					.prepareStatement("SELECT * FROM kullanici WHERE kullaniciadi = ? AND parola = ?");

			sorgu.setString(1, kullaniciAdi);
			sorgu.setString(2, parola);

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				kontrol = true;
			}

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return kontrol;

	}

	public static KullaniciDomain kullaniciAdiGetir(int id) {
		KullaniciDomain gelenId = new KullaniciDomain();

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti.prepareStatement("SELECT * FROM kullanici WHERE id =?");

			sorgu.setInt(1, id);

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				gelenId.setId(rs.getInt("id"));
				gelenId.setAdSoyad(rs.getString("adi"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return gelenId;

	}

	public static boolean sifremiUnuttum(String kullaniciAdi, String hesapKurtarmaNo) {

		boolean kontrol = false;

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti
					.prepareStatement("SELECT * FROM kullanici WHERE kullaniciadi = ? AND hesapkurtarmano = ?");

			sorgu.setString(1, kullaniciAdi);
			sorgu.setString(2, hesapKurtarmaNo);

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				kontrol = true;
			}

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return kontrol;

	}

	public static void sifreGuncelle(KullaniciDomain guncellenecekSifre, String kullaniciAdi, String hesapKurtarmaNo) {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti
					.prepareStatement("UPDATE kullanici SET parola = ? WHERE kullaniciadi = ? AND hesapkurtarmano = ?");

			sorgu.setString(1, guncellenecekSifre.getParola());
			sorgu.setString(2, kullaniciAdi);
			sorgu.setString(3, hesapKurtarmaNo);

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public static void sifreyiDegistir(KullaniciDomain degisecekSifre, String kullaniciAdi, String parola) {
		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti
					.prepareStatement("UPDATE kullanici SET parola = ? WHERE kullaniciadi = ? AND parola = ?");

			sorgu.setString(1, degisecekSifre.getParola());
			sorgu.setString(2, kullaniciAdi);
			sorgu.setString(3, parola);

			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static boolean sifreDegistirmeKontrol(String kullaniciAdi, String parola) {

		boolean kontrol = false;

		Connection baglanti = VeriTabani.baglantiAl();

		try {
			PreparedStatement sorgu = baglanti
					.prepareStatement("SELECT * FROM kullanici WHERE kullaniciadi = ? AND parola = ?");

			sorgu.setString(1, kullaniciAdi);
			sorgu.setString(2, parola);

			ResultSet rs = sorgu.executeQuery();

			while (rs.next()) {
				kontrol = true;
			}

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return kontrol;

	}
	
	
	public static void hesapSil(String kullaniciAdi, String parola) {
		Connection baglanti =VeriTabani.baglantiAl();
		
		try {
			PreparedStatement sorgu = baglanti.prepareStatement("DELETE FROM kullanici WHERE kullaniciadi = ? AND parola = ?");
			
			sorgu.setString(1, kullaniciAdi);
			sorgu.setString(2, parola);
			
			sorgu.executeUpdate();

			sorgu.close();
			baglanti.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}

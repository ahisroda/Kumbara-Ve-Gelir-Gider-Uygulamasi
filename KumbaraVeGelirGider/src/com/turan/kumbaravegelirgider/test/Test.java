package com.turan.kumbaravegelirgider.test;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.turan.kumbaravegelirgider.dao.AktifKullaniciDao;
import com.turan.kumbaravegelirgider.dao.GelirGiderDao;
import com.turan.kumbaravegelirgider.dao.HedefTanimlaDao;
import com.turan.kumbaravegelirgider.dao.KullaniciDao;
import com.turan.kumbaravegelirgider.dao.KumbaraDao;
import com.turan.kumbaravegelirgider.dao.KumbaraTanimlaDao;
import com.turan.kumbaravegelirgider.ui.AnaEkranGui;
import com.turan.kumbaravegelirgider.ui.GirisEkraniGui;
import com.turan.kumbaravegelirgider.utils.SiniflaraErisim;

public class Test {

	public static void main(String[] args) {

		AktifKullaniciDao.tabloOlustur();
		KullaniciDao.tabloOlustur();
		HedefTanimlaDao.tabloOlustur();
		KumbaraTanimlaDao.tabloOlustur();
		KumbaraDao.tabloOlustur();
		GelirGiderDao.tabloOlustur();

		// com.sun.java.swing.plaf.windows.WindowsLookAndFeel
		// javax.swing.plaf.nimbus.NimbusLookAndFeel
		// javax.swing.plaf.metal.MetalLookAndFeel
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/*
				 * if (hesapMevcutKontrolu == false) { JOptionPane.showMessageDialog(null,
				 * "Kayýtlý hesap yoktur, lütfen kayýt yapýnýz");
				 * SiniflaraErisim.yeniHesapOlusturEkrani(); // SiniflaraErisim.anaEkran(); }
				 * else { SiniflaraErisim.girisEkrani(); // SiniflaraErisim.anaEkran(); }
				 */
				// SiniflaraErisim.anaEkranGui();
			SiniflaraErisim.girisEkrani();
			}
		});

	}

}

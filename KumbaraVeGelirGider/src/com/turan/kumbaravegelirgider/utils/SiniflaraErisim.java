package com.turan.kumbaravegelirgider.utils;

import javax.swing.SwingUtilities;

import com.turan.kumbaravegelirgider.ui.AnaEkranGui;
import com.turan.kumbaravegelirgider.ui.GelirGiderGui;
import com.turan.kumbaravegelirgider.ui.GirisEkraniGui;
import com.turan.kumbaravegelirgider.ui.HedefTanimlaGui;
import com.turan.kumbaravegelirgider.ui.HesapSilGui;
import com.turan.kumbaravegelirgider.ui.KumbaraGui;
import com.turan.kumbaravegelirgider.ui.KumbaraTanimlaGui;
import com.turan.kumbaravegelirgider.ui.SifreDegistirGui;
import com.turan.kumbaravegelirgider.ui.SifremiUnuttumGui;
import com.turan.kumbaravegelirgider.ui.YeniHesapOlusturGui;

public class SiniflaraErisim {

	public static void anaEkranGui() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new AnaEkranGui();

			}
		});
	}

	public static void girisEkrani() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new GirisEkraniGui();

			}
		});
	}

	public static void gelirGiderEkrani() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new GelirGiderGui();

			}
		});
	}

	public static void kumbaraTanimlaEkrani() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new KumbaraTanimlaGui();
			}
		});
	}

	public static void hedefTanimlaEkrani() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new HedefTanimlaGui();
			}
		});
	}

	public static void kumbaraEkrani() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new KumbaraGui();
			}
		});
	}

	public static void yeniHesapEkrani() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new YeniHesapOlusturGui();
			}
		});
	}

	public static void sifremiUnuttumEkrani() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new SifremiUnuttumGui();
			}
		});
	}

	public static void sifreyiDegistirGui() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new SifreDegistirGui();
			}
		});
	}
	
	public static void hesapSilEkrani() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new HesapSilGui();
			}
		});
	}

}

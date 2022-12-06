package com.turan.kumbaravegelirgider.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.turan.kumbaravegelirgider.dao.AktifKullaniciDao;
import com.turan.kumbaravegelirgider.dao.GelirGiderDao;
import com.turan.kumbaravegelirgider.dao.HedefTanimlaDao;
import com.turan.kumbaravegelirgider.dao.KullaniciDao;
import com.turan.kumbaravegelirgider.dao.KumbaraDao;
import com.turan.kumbaravegelirgider.dao.KumbaraTanimlaDao;
import com.turan.kumbaravegelirgider.domain.AktifKullaniciDomain;
import com.turan.kumbaravegelirgider.domain.KullaniciDomain;
import com.turan.kumbaravegelirgider.utils.SiniflaraErisim;

public class AnaEkranGui extends JFrame {

	public AnaEkranGui() {
		initPencere();
	}

	private void initPencere() {
		add(initPanel());
		setTitle("Kumbaram ve Gelir Gider");
		ImageIcon kumbaraAnaIcon = new ImageIcon("icons/kumbaraanaikon48.png");
		setIconImage(kumbaraAnaIcon.getImage());
		setJMenuBar(initMenu());
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	private JPanel initPanel() {
		JPanel anaPanel = new JPanel(new BorderLayout());
		JPanel ustPanel = new JPanel();
		JPanel altPanel = new JPanel();

		ImageIcon gelirGiderIcon = new ImageIcon("icons/hareket48.png");
		JButton gelirGiderEkraniButon = new JButton("Gelir-Gider Ekraný", gelirGiderIcon);

		ImageIcon kumbaraIcon = new ImageIcon("icons/kasa48.png");
		JButton kumbaraEkraniButon = new JButton("Kumbara Ekraný", kumbaraIcon);

		JLabel kasadakiParaLabel = new JLabel();

		ustPanel.add(gelirGiderEkraniButon);
		ustPanel.add(kumbaraEkraniButon);
		ustPanel.add(kasadakiParaLabel);

		AktifKullaniciDomain gelenAktifKullaniciId = AktifKullaniciDao.aktifKullaniciIdGetir();
		KullaniciDomain gelenKullaniciBilgisi = KullaniciDao.kullaniciAdiGetir(gelenAktifKullaniciId.getKullaniciId());
		JLabel adLabel = new JLabel();
		adLabel.setText("Hoþgeldiniz " + gelenKullaniciBilgisi.getAdSoyad());

		ImageIcon yenileIcon = new ImageIcon("icons/yenile32.png");
		JButton yenileButon = new JButton("Yenile", yenileIcon);

		ImageIcon otorumKapatIcon = new ImageIcon("icons/giris32.png");
		JButton oturumKapatButon = new JButton("Oturum Kapat", otorumKapatIcon);

		ImageIcon cikisIcon = new ImageIcon("icons/geri32.png");
		JButton cikisButon = new JButton("Çýkýþ", cikisIcon);

		altPanel.add(adLabel);
		altPanel.add(oturumKapatButon);
		altPanel.add(cikisButon);
		altPanel.add(yenileButon);

		anaPanel.add(ustPanel, BorderLayout.NORTH);
		anaPanel.add(altPanel, BorderLayout.SOUTH);

		gelirGiderEkraniButon.setFocusable(false);
		kumbaraEkraniButon.setFocusable(false);
		oturumKapatButon.setFocusable(false);
		cikisButon.setFocusable(false);

		JList bugunkuHareketlerList = new JList();
		listele(bugunkuHareketlerList);
		JScrollPane bugunkuHareketlerPane = new JScrollPane(bugunkuHareketlerList);
		bugunkuHareketlerPane.setBorder(BorderFactory.createTitledBorder("Bugünkü Hareketler"));
		bugunkuHareketlerPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		anaPanel.add(bugunkuHareketlerPane);

		yenileButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listele(bugunkuHareketlerList);
			}
		});

		gelirGiderEkraniButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(AnaEkranGui.this,
						"Tarih girerken lütfen gün/ay/yýl formatýnda giriniz\n" + "Örnek: 05/06/2022");
				SiniflaraErisim.gelirGiderEkrani();
			}
		});

		kumbaraEkraniButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SiniflaraErisim.kumbaraEkrani();
			}
		});

		cikisButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});

		oturumKapatButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				SiniflaraErisim.girisEkrani();
				AktifKullaniciDao.sil();

			}
		});

		return anaPanel;
	}

	private JMenuBar initMenu() {
		JMenuBar bar = new JMenuBar();

		JMenu tanimlamalarMenu = new JMenu("Tanýmlamalar");
		JMenu hesapIslemleriMenu = new JMenu("Hesap Ýþlemler");
		JMenu programAyariMenu = new JMenu("Program Ayarý");

		ImageIcon kumbaraTanimlaIcon = new ImageIcon("icons/kumbara16.png");
		JMenuItem kumbaraTanimlaItem = new JMenuItem("Kumbara Tanýmla", kumbaraTanimlaIcon);

		ImageIcon hedefIcon = new ImageIcon("icons/hedef16.png");
		JMenuItem hedefTanimlaItem = new JMenuItem("Hedef Tanýmla", hedefIcon);

		tanimlamalarMenu.add(hedefTanimlaItem);
		tanimlamalarMenu.add(kumbaraTanimlaItem);

		ImageIcon sifreDegistirIcon = new ImageIcon("icons/sifredegistir16.png");
		JMenuItem sifreDegistirItem = new JMenuItem("Þifre Deðiþtir", sifreDegistirIcon);

		ImageIcon hesapSilIcon = new ImageIcon("icons/sil16.png");
		JMenuItem hesapSilItem = new JMenuItem("Hesap Sil", hesapSilIcon);

		hesapIslemleriMenu.add(sifreDegistirItem);
		hesapIslemleriMenu.add(hesapSilItem);

		ImageIcon resetIcon = new ImageIcon("icons/sifirla16.png");
		JMenuItem programSifirlaItem = new JMenuItem("Program Sýfýrla", resetIcon);
		programAyariMenu.add(programSifirlaItem);

		bar.add(tanimlamalarMenu);
		bar.add(hesapIslemleriMenu);
		bar.add(programAyariMenu);

		kumbaraTanimlaItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SiniflaraErisim.kumbaraTanimlaEkrani();

			}
		});

		hedefTanimlaItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SiniflaraErisim.hedefTanimlaEkrani();

			}
		});

		programSifirlaItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int sorgu = JOptionPane.showConfirmDialog(AnaEkranGui.this,
						"Tüm verileri gerçekten silmek istiyor musunuz?");
				if (sorgu == 0) {
					int sorgu2 = JOptionPane.showConfirmDialog(AnaEkranGui.this, "Buna emin misiniz?");

					if (sorgu2 == 0) {
						AktifKullaniciDomain gelenAktifKullanici = AktifKullaniciDao.aktifKullaniciIdGetir();
						GelirGiderDao.verileriSil(gelenAktifKullanici.getKullaniciId());
						HedefTanimlaDao.verileriSil(gelenAktifKullanici.getKullaniciId());
						KumbaraDao.verileriSil(gelenAktifKullanici.getKullaniciId());
						KumbaraTanimlaDao.verileriSil(gelenAktifKullanici.getKullaniciId());
						JOptionPane.showMessageDialog(AnaEkranGui.this, "Tüm veriler silinmiþtir");
					}

				}

			}
		});
		
		
		sifreDegistirItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SiniflaraErisim.sifreyiDegistirGui();
				
			}
		});
		
		
		hesapSilItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SiniflaraErisim.hesapSilEkrani();
				
			}
		});

		return bar;

	}

	private void listele(JList l) {
		Date tarih = new Date();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String simdikiTarih = format.format(tarih);

		AktifKullaniciDomain gelenKullaniciId = AktifKullaniciDao.aktifKullaniciIdGetir();

		l.setListData(GelirGiderDao.bugununListesi(gelenKullaniciId.getKullaniciId(), simdikiTarih).toArray());
	}

}

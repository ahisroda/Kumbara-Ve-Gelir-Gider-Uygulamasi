package com.turan.kumbaravegelirgider.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.turan.kumbaravegelirgider.dao.AktifKullaniciDao;
import com.turan.kumbaravegelirgider.dao.KullaniciDao;
import com.turan.kumbaravegelirgider.domain.AktifKullaniciDomain;
import com.turan.kumbaravegelirgider.domain.KullaniciDomain;
import com.turan.kumbaravegelirgider.utils.SiniflaraErisim;

public class GirisEkraniGui extends JDialog {

	public GirisEkraniGui() {
		initPencere();
	}

	public void initPencere() {
		add(initPanel());
		setSize(350, 200);
		setTitle("Giriþ Ekraný");
		setResizable(false);
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public JPanel initPanel() {
		JPanel anaPanel = new JPanel(new BorderLayout());
		JPanel ustPanel = new JPanel(new GridLayout(2, 2, 5, 5));
		JPanel altPanel = new JPanel(new GridLayout(2, 2, 5, 5));

		JLabel kullaniciAdiLabel = new JLabel("Kullanýcý Adý");
		JTextField kullaniciAdiText = new JTextField(11);

		JLabel parolaLabel = new JLabel("Þifre");
		JTextField parolaText = new JTextField(10);

		ustPanel.add(kullaniciAdiLabel);
		ustPanel.add(kullaniciAdiText);
		ustPanel.add(parolaLabel);
		ustPanel.add(parolaText);

		anaPanel.add(ustPanel);

		ImageIcon girisIcon = new ImageIcon("icons/giris_24_Ikon.png");
		JButton girisButon = new JButton("Giriþ", girisIcon);
		getRootPane().setDefaultButton(girisButon);

		ImageIcon cikisIcon = new ImageIcon("icons/exit_24_Ikon.png");
		JButton cikisButon = new JButton("Çýkýþ", cikisIcon);

		ImageIcon yeniHesapIcon = new ImageIcon("icons/yeniKullanici_24_Ikon.png");
		JButton yeniHesapButon = new JButton("Yeni Hesap", yeniHesapIcon);

		ImageIcon sifremiUnuttumIcon = new ImageIcon("icons/sifre_24_Ikon.png");
		JButton sifremiUnuttumButon = new JButton("Þifremi Unuttum", sifremiUnuttumIcon);

		altPanel.add(girisButon);
		altPanel.add(yeniHesapButon);
		altPanel.add(sifremiUnuttumButon);
		altPanel.add(cikisButon);

		anaPanel.add(altPanel, BorderLayout.SOUTH);

		girisButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AktifKullaniciDomain eklenecekAktifKullanici = new AktifKullaniciDomain();

				AktifKullaniciDao.sil();

				boolean hesapKontrol = KullaniciDao.girisBilgiKontrolu(kullaniciAdiText.getText(),
						parolaText.getText());

				KullaniciDomain gelenKullaniciId = KullaniciDao.kullaniciIdGetir(kullaniciAdiText.getText());

				if (hesapKontrol == true) {
					JOptionPane.showMessageDialog(GirisEkraniGui.this, "Giriþ baþarýlý");
					eklenecekAktifKullanici.setKullaniciId(gelenKullaniciId.getId());
					AktifKullaniciDao.ekle(eklenecekAktifKullanici);
					dispose();
					SiniflaraErisim.anaEkranGui();
				} else {
					JOptionPane.showMessageDialog(GirisEkraniGui.this, "Yanlýþ parola veya þifre!");
				}

			}
		});

		cikisButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});

		sifremiUnuttumButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				SiniflaraErisim.sifremiUnuttumEkrani();

			}
		});

		yeniHesapButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				SiniflaraErisim.yeniHesapEkrani();

			}
		});

		return anaPanel;
	}

}

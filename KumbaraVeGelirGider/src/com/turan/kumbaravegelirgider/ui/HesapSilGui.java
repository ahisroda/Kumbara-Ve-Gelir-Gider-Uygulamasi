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
import com.turan.kumbaravegelirgider.dao.GelirGiderDao;
import com.turan.kumbaravegelirgider.dao.HedefTanimlaDao;
import com.turan.kumbaravegelirgider.dao.KullaniciDao;
import com.turan.kumbaravegelirgider.dao.KumbaraDao;
import com.turan.kumbaravegelirgider.dao.KumbaraTanimlaDao;
import com.turan.kumbaravegelirgider.domain.AktifKullaniciDomain;

public class HesapSilGui extends JDialog {

	public HesapSilGui() {
		initPencere();
	}

	private void initPencere() {
		add(initPanel());
		setSize(300, 200);
		setTitle("Hesap Sil");
		setResizable(false);
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private JPanel initPanel() {
		JPanel anaPanel = new JPanel(new BorderLayout());
		JPanel ustPanel = new JPanel(new GridLayout(3, 2, 5, 5));
		JPanel altPanel = new JPanel();

		JLabel kullaniciAdiLabel = new JLabel("Kullanýcý Adý");
		JTextField kullaniciAdiText = new JTextField(10);

		JLabel parolaLabel = new JLabel("Þifre");
		JTextField parolaText = new JTextField(10);

		JLabel parolaTekrarLabel = new JLabel("Þifre Tekrar");
		JTextField parolaTekrarText = new JTextField(10);

		ustPanel.add(kullaniciAdiLabel);
		ustPanel.add(kullaniciAdiText);
		ustPanel.add(parolaLabel);
		ustPanel.add(parolaText);
		ustPanel.add(parolaTekrarLabel);
		ustPanel.add(parolaTekrarText);

		anaPanel.add(ustPanel);

		ImageIcon silIcon = new ImageIcon("icons/sil_24_Ikon.png");
		JButton silButon = new JButton("Sil", silIcon);
		getRootPane().setDefaultButton(silButon);

		ImageIcon geriIcon = new ImageIcon("icons/geri_24_Ikon.png");
		JButton geriButon = new JButton("Geri", geriIcon);

		altPanel.add(silButon);
		altPanel.add(geriButon);

		anaPanel.add(altPanel, BorderLayout.SOUTH);

		silButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int sorgu1 = JOptionPane.showConfirmDialog(HesapSilGui.this,
						"Hesabýnýzý ve verilerinizi gerçekten silmek istiyor musunuz?");

				boolean hesapBilgileriDogruMu = KullaniciDao.sifreDegistirmeKontrol(kullaniciAdiText.getText(),
						parolaText.getText());

				if (sorgu1 == 0) {

					if (hesapBilgileriDogruMu == false) {
						JOptionPane.showMessageDialog(HesapSilGui.this, "Girdiðiniz bilgiler yanlýþtýr!");
					} else {
						if (parolaText.getText().equals(parolaTekrarText.getText())) {
							KullaniciDao.hesapSil(kullaniciAdiText.getText(), parolaText.getText());

							AktifKullaniciDao.sil();
							AktifKullaniciDomain gelenAktifKullanici = AktifKullaniciDao.aktifKullaniciIdGetir();
							GelirGiderDao.verileriSil(gelenAktifKullanici.getKullaniciId());
							HedefTanimlaDao.verileriSil(gelenAktifKullanici.getKullaniciId());
							KumbaraDao.verileriSil(gelenAktifKullanici.getKullaniciId());
							KumbaraTanimlaDao.verileriSil(gelenAktifKullanici.getKullaniciId());
							
							JOptionPane.showMessageDialog(HesapSilGui.this, "Hesabýnýz ve içindekiler silinmiþtir");
							JOptionPane.showMessageDialog(HesapSilGui.this, "Çýkýþ yapýlýyor...");
							System.exit(0);
						} else {
							JOptionPane.showMessageDialog(HesapSilGui.this, "Parolalar uyuþmuyor");
						}

					}

				}

			}
		});

		geriButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

		return anaPanel;
	}

}

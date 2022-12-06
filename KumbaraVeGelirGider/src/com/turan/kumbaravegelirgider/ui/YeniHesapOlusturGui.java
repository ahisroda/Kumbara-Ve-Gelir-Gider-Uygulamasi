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

import com.turan.kumbaravegelirgider.dao.KullaniciDao;
import com.turan.kumbaravegelirgider.domain.AktifKullaniciDomain;
import com.turan.kumbaravegelirgider.domain.KullaniciDomain;
import com.turan.kumbaravegelirgider.utils.SiniflaraErisim;

public class YeniHesapOlusturGui extends JDialog {

	public YeniHesapOlusturGui() {
		initPencere();
	}

	public void initPencere() {
		add(initPanel());
		setSize(400, 300);
		setTitle("Kaydol");
		setResizable(false);
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public JPanel initPanel() {
		JPanel anaPanel = new JPanel(new BorderLayout());
		JPanel ustPanel = new JPanel(new GridLayout(5, 2, 5, 5));
		JPanel altPanel = new JPanel();

		JLabel adiLabel = new JLabel("Ad-Soyad");
		JTextField adiText = new JTextField(10);

		JLabel kullaniciAdiLabel = new JLabel("Kullan�c� Ad�");
		JTextField kullaniciAdiText = new JTextField(11);

		JLabel parolaLabel = new JLabel("�ifre");
		JTextField parolaText = new JTextField(10);

		JLabel parolaTekrarLabel = new JLabel("�ifre Tekrar");
		JTextField parolaTekrarText = new JTextField(10);

		JLabel hesapKurtarmaNoLabel = new JLabel("Hesap Kurtarma no");
		JTextField hesapKurtarmaNoText = new JTextField(10);

		ustPanel.add(adiLabel);
		ustPanel.add(adiText);
		ustPanel.add(kullaniciAdiLabel);
		ustPanel.add(kullaniciAdiText);
		ustPanel.add(parolaLabel);
		ustPanel.add(parolaText);
		ustPanel.add(parolaTekrarLabel);
		ustPanel.add(parolaTekrarText);
		ustPanel.add(hesapKurtarmaNoLabel);
		ustPanel.add(hesapKurtarmaNoText);

		anaPanel.add(ustPanel);

		ImageIcon kaydetIcon = new ImageIcon("icons/save_24_Ikon.png");
		JButton kaydetButon = new JButton("Kaydet", kaydetIcon);
		getRootPane().setDefaultButton(kaydetButon);

		ImageIcon geriIcon = new ImageIcon("icons/geri_24_Ikon.png");
		JButton geriButon = new JButton("Geri", geriIcon);

		altPanel.add(kaydetButon);
		altPanel.add(geriButon);

		anaPanel.add(altPanel, BorderLayout.SOUTH);

		kaydetButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				KullaniciDomain eklenecekKullaniciDomain = new KullaniciDomain();

				eklenecekKullaniciDomain.setAdSoyad(adiText.getText());
				eklenecekKullaniciDomain.setKullaniciAdi(kullaniciAdiText.getText());
				eklenecekKullaniciDomain.setParola(parolaText.getText());
				eklenecekKullaniciDomain.setHesapKurtarmaNo(hesapKurtarmaNoText.getText());

				boolean hesapVarlikKontrol = KullaniciDao.hesapMevcutMu(kullaniciAdiText.getText());

				if (adiText.getText().equals("") || kullaniciAdiText.getText().equals("")
						|| parolaText.getText().equals("") || hesapKurtarmaNoText.getText().equals("")
						|| parolaTekrarText.getText().equals("")) {
					JOptionPane.showMessageDialog(YeniHesapOlusturGui.this, "Bo� bilgi b�rakmay�n�z!");
				} else {
					if (parolaText.getText().equals(parolaTekrarText.getText())) {

						if (hesapVarlikKontrol == false) {
							KullaniciDao.ekle(eklenecekKullaniciDomain);
							JOptionPane.showMessageDialog(YeniHesapOlusturGui.this, "Hesab�n�z olu�mu�tur");
							dispose();
							SiniflaraErisim.girisEkrani();
						} else {
							JOptionPane.showMessageDialog(YeniHesapOlusturGui.this, "Kullan�c� ad� kullan�l�yor");
						}

					} else {
						JOptionPane.showMessageDialog(YeniHesapOlusturGui.this, "Girdi�iniz parolalar ayn� de�il!");
					}
				}

			}
		});

		geriButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				// SiniflaraErisimUtils.girisEkrani();

			}
		});

		return anaPanel;
	}

}

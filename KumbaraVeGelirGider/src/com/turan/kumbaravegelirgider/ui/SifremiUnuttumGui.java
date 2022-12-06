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
import com.turan.kumbaravegelirgider.domain.KullaniciDomain;
import com.turan.kumbaravegelirgider.utils.SiniflaraErisim;

public class SifremiUnuttumGui extends JDialog {

	public SifremiUnuttumGui() {
		initPencere();
	}

	public void initPencere() {
		add(initPanel());
		setSize(350, 250);
		setTitle("Þifremi Unuttum");
		setResizable(false);
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public JPanel initPanel() {
		JPanel anaPanel = new JPanel(new BorderLayout());
		JPanel ustPanel = new JPanel(new GridLayout(4, 2, 5, 5));
		JPanel altPanel = new JPanel();

		JLabel kullaniciAdiLabel = new JLabel("Kullanýcý Adý");
		JTextField kullaniciAdiText = new JTextField(11);

		JLabel hesapKurtarmaLabel = new JLabel("Hesap Kurtarma No");
		JTextField hesapKurtarmaText = new JTextField(11);

		JLabel yeniParolaLabel = new JLabel("Yeni Þifre");
		JTextField yeniParolaText = new JTextField(10);

		JLabel yeniParolaTekrarLabel = new JLabel("Yeni Þifre Tekrar");
		JTextField yeniParolaTekrarText = new JTextField(10);

		ustPanel.add(kullaniciAdiLabel);
		ustPanel.add(kullaniciAdiText);
		ustPanel.add(hesapKurtarmaLabel);
		ustPanel.add(hesapKurtarmaText);
		ustPanel.add(yeniParolaLabel);
		ustPanel.add(yeniParolaText);
		ustPanel.add(yeniParolaTekrarLabel);
		ustPanel.add(yeniParolaTekrarText);

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

				boolean kontrol = KullaniciDao.sifremiUnuttum(kullaniciAdiText.getText(), hesapKurtarmaText.getText());

				KullaniciDomain duzenlenecekKullanici = new KullaniciDomain();

				if (kullaniciAdiText.getText().equals("") || yeniParolaText.getText().equals("")
						|| yeniParolaTekrarText.getText().equals("") || hesapKurtarmaText.getText().equals("")) {
					JOptionPane.showMessageDialog(SifremiUnuttumGui.this, "Bilgileri tam giriniz");
				} else {
					if (yeniParolaText.getText().equals(yeniParolaTekrarText.getText())) {

						if (kontrol == true) {

							duzenlenecekKullanici.setParola(yeniParolaText.getText());
							KullaniciDao.sifreGuncelle(duzenlenecekKullanici, kullaniciAdiText.getText(),
									hesapKurtarmaText.getText());
							JOptionPane.showMessageDialog(SifremiUnuttumGui.this, "Hesabýnýz kurtarýlmýþtýr");
							dispose();
							SiniflaraErisim.girisEkrani();

						} else {
							JOptionPane.showMessageDialog(SifremiUnuttumGui.this, "Hesap bilgileri yanlýþ!");
						}

					} else {
						JOptionPane.showMessageDialog(SifremiUnuttumGui.this, "Parolalar uyuþmuyor!");
					}
				}

			}

		});

		geriButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				SiniflaraErisim.girisEkrani();

			}
		});

		return anaPanel;
	}

}

package com.turan.kumbaravegelirgider.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.turan.kumbaravegelirgider.dao.AktifKullaniciDao;
import com.turan.kumbaravegelirgider.dao.GelirGiderDao;
import com.turan.kumbaravegelirgider.domain.AktifKullaniciDomain;
import com.turan.kumbaravegelirgider.domain.GelirGiderDomain;
import com.turan.kumbaravegelirgider.utils.Utils;

public class GelirGiderGui extends JDialog {

	public GelirGiderGui() {
		initPencere();
	}

	private void initPencere() {
		add(initPanel());
		setSize(400, 500);
		setTitle("Gelir Gider Ekraný");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setVisible(true);
	}

	private JPanel initPanel() {
		JPanel anaPanel = new JPanel(new BorderLayout());
		JPanel altPanel = new JPanel(new BorderLayout());
		JPanel altAltPanel = new JPanel();
		JPanel altUstpanel = new JPanel(new BorderLayout());
		JPanel altUstGirdipanel = new JPanel(new GridLayout(4, 2, 5, 5));
		JPanel radioPanel = new JPanel();

		altPanel.add(altAltPanel, BorderLayout.SOUTH);
		altPanel.add(altUstpanel);

		anaPanel.add(altPanel, BorderLayout.SOUTH);

		JLabel tarihLabel = new JLabel("Tarih");
		JTextField tarihField = new JTextField();

		JLabel miktarLabel = new JLabel("Ücret");
		JTextField miktarField = new JTextField();

		JLabel yonLabel = new JLabel("Hareket: ");

		ButtonGroup grup = new ButtonGroup();
		JRadioButton girisRadio = new JRadioButton("Giriþ");
		JRadioButton cikisRadio = new JRadioButton("Çýkýþ");

		grup.add(girisRadio);
		grup.add(cikisRadio);

		radioPanel.add(girisRadio);
		radioPanel.add(cikisRadio);

		JLabel b = new JLabel("Kasadaki Para: ");
		JLabel kasadakiParaLabel = new JLabel();
		AktifKullaniciDomain gelenAktifKullanici = AktifKullaniciDao.aktifKullaniciIdGetir();
		int toplamKasa = GelirGiderDao.toplamKasa(gelenAktifKullanici.getKullaniciId());
		kasadakiParaLabel.setText(String.valueOf(toplamKasa) + " TL");

		altUstGirdipanel.add(tarihLabel);
		altUstGirdipanel.add(tarihField);
		altUstGirdipanel.add(miktarLabel);
		altUstGirdipanel.add(miktarField);
		altUstGirdipanel.add(yonLabel);
		altUstGirdipanel.add(radioPanel);
		altUstGirdipanel.add(b);
		altUstGirdipanel.add(kasadakiParaLabel);

		altUstpanel.add(altUstGirdipanel);

		JTextField aciklamaField = new JTextField();
		aciklamaField.setBorder(BorderFactory.createTitledBorder("Açýklama"));

		altUstpanel.add(aciklamaField, BorderLayout.NORTH);

		ImageIcon kaydetIcon = new ImageIcon("icons/ekle_24_Ikon.png");
		JButton kaydetButon = new JButton("Kaydet", kaydetIcon);

		ImageIcon geriIcon = new ImageIcon("icons/geri_24_Ikon.png");
		JButton geriButon = new JButton("Sil", geriIcon);

		altAltPanel.add(kaydetButon);
		altAltPanel.add(geriButon);

		JList hareketlerList = new JList();
		listele(hareketlerList);
		JScrollPane hareketlerPane = new JScrollPane(hareketlerList);
		hareketlerList.setBorder(BorderFactory.createTitledBorder("Kaydedilmiþ Hareketler"));
		hareketlerPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		anaPanel.add(hareketlerPane);

		hareketlerList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

			}
		});

		kaydetButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				GelirGiderDomain eklenecekGelirGider = new GelirGiderDomain();
				AktifKullaniciDomain gelenAktifKullanici = AktifKullaniciDao.aktifKullaniciIdGetir();
				int miktar = Integer.parseInt(miktarField.getText());
				int toplamKasa = GelirGiderDao.toplamKasa(gelenAktifKullanici.getKullaniciId());

				if (!aciklamaField.getText().equals("") && !miktarField.getText().equals("")
						&& !tarihField.getText().equals("") && !grup.isSelected(null)) {

					if (miktar > toplamKasa && cikisRadio.isSelected()) {
						JOptionPane.showMessageDialog(GelirGiderGui.this, "Kasada o kadar para yoktur");

					} else {
						if (girisRadio.isSelected()) {
							eklenecekGelirGider.setMiktar(miktar);
						} else {
							eklenecekGelirGider.setMiktar(-miktar);
						}

						eklenecekGelirGider.setAciklama(aciklamaField.getText());
						eklenecekGelirGider.setKullaniciId(gelenAktifKullanici.getKullaniciId());
						eklenecekGelirGider.setTarih(tarihField.getText());

						GelirGiderDao.ekke(eklenecekGelirGider);
						Utils.alanTemizle(new JTextField[] { aciklamaField, tarihField, miktarField });
						listele(hareketlerList);
						int toplamKasaGuncel = GelirGiderDao.toplamKasa(gelenAktifKullanici.getKullaniciId());
						kasadakiParaLabel.setText(String.valueOf(toplamKasaGuncel) + " TL");
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

	private void listele(JList l) {

		AktifKullaniciDomain gelenAktifKullanici = AktifKullaniciDao.aktifKullaniciIdGetir();
		l.setListData(GelirGiderDao.listele(gelenAktifKullanici.getKullaniciId()).toArray());

	}

}
